package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.SessionDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.mapper.UserMapper;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.Session;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.properties.UservistProperties;
import com.markiian.benovskyi.auth.security.UservistAuthenticationToken;
import com.markiian.benovskyi.auth.util.RoleUtil;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserAuthenticationDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Key;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserTokenService {

    private final UserMapper userMapper;
    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final SessionDao sessionDao;
    private final ServiceRoleDao serviceRoleDao;

    private final String USERNAME_KEY = "username";
    private final String SERVICE_KEY = "service";
    private final String ROLE_KEY = "role";
    private final String BROWSER_KEY = "browser";
    private final String IP_KEY = "ip";

    private final UservistProperties uservistProperties;

    private final Logger LOGGER = LoggerFactory.getLogger(UserTokenService.class);

    @Autowired
    public UserTokenService(SessionDao sessionDao, UserDao userDao, ServiceDao serviceDao,
                            ServiceRoleDao serviceRoleDao, UserMapper userMapper, UservistProperties uservistProperties) {
        this.sessionDao = sessionDao;
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.serviceRoleDao = serviceRoleDao;
        this.userMapper = userMapper;
        this.uservistProperties = uservistProperties;
    }

    public UserDto getUserFromUsername(String username) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            LOGGER.warn("User with username {} cannot be found", username);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User or Service does not exist");
        }

        return userMapper.toDto(user.get());
    }

    public void updateUserSession(Authentication authentication) throws Exception {
        UserAuthenticationDto authDto = (UserAuthenticationDto) authentication.getDetails();

        Optional<User> user = userDao.findByUsername(authDto.getUsername());
        Optional<Service> service = serviceDao.findByKey(authDto.getService());

        if (user.isEmpty() || service.isEmpty()) {
            LOGGER.warn("User with Username {} or service with Key {} cannot be found",
                    authDto.getUsername(), authDto.getService());
            throw new Exception("User or Service does not exist");
        }

        // Find an existing session, if it exist then expire the existing session
        Optional<Session> optionalSession = sessionDao
                .findByUserAndServiceAndBrowserAndIpAddress(
                        user.get(), service.get(), authDto.getBrowser().substring(0, 200), authDto.getIpAddress());
        optionalSession.ifPresent(sessionDao::delete);

        // Create new session
        Session session = new Session();

        session.setUser(user.get());
        session.setService(service.get());
        session.setBrowser(authDto.getBrowser().substring(0, 200));
        session.setIpAddress(authDto.getIpAddress());
        session.setExpiresAt(OffsetDateTime.now().plusDays(1));

        session = sessionDao.save(session);
        LOGGER.debug("Created new login session: {}", session);
    }

    /**
     * Takes an authentication object, that is supposed to be authenticated, and constructs a token from it.
     * The new token contains username, authority (role), serviceKey.
     * @param authentication Supposed authorized authentication object.
     * @return HS256 Token string.
     */
    public String generateToken(Authentication authentication) {
        UserAuthenticationDto authDto = (UserAuthenticationDto) authentication.getDetails();

        byte[] keyBytes = Decoders.BASE64.decode(uservistProperties.getTokenSigningKey());
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Optional<Service> optionalService = serviceDao.findByKey(authDto.getService());
        String userServiceRole = optionalService.orElseThrow()
                .getServiceRoles().stream()
                .filter(sr -> sr.getUser().getUsername().equals(authentication.getName()))
                .findFirst().orElseThrow()
                .getRole().getValue();


        String token = Jwts.builder()
                .claim(USERNAME_KEY, authentication.getName())
                .claim(SERVICE_KEY, authDto.getService())
                .claim(ROLE_KEY, userServiceRole)
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + uservistProperties.getTokenValidity() * 1000))
                .compact();

        LOGGER.debug("Generated new token for: {}, token: {}", authentication.getName(), token);
        return token;
    }

    public UservistAuthenticationToken createAuthenticationFromToken(final String token) {
        Map<String, String> claims = getClaimsMap(token);

        Optional<User> user = userDao.findByUsername(claims.get(USERNAME_KEY));
        Optional<Service> service = serviceDao.findByKey(claims.get(SERVICE_KEY));

        if (user.isEmpty() || service.isEmpty()) {
            LOGGER.warn("Username or service are empty");
            return null;
        }

        Optional<ServiceRole> optionalServiceRole = serviceRoleDao.findByUserAndService(user.get(), service.get());
        Collection<SimpleGrantedAuthority> authorities =
                RoleUtil.getAuthoritiesFromServiceRole(optionalServiceRole.orElse(null));

        UserAuthenticationDto details = new UserAuthenticationDto();
        details.setUsername(claims.get(USERNAME_KEY));
        details.setService(claims.get(SERVICE_KEY));
        details.setBrowser(claims.get(BROWSER_KEY));
        details.setIpAddress(claims.get(IP_KEY));

        UservistAuthenticationToken auth =
                new UservistAuthenticationToken(claims.get(USERNAME_KEY), "", details, authorities);

        LOGGER.debug("Result for user authentication: {}", auth);
        return auth;
    }

    /**
     * Validate user token to be active in the database and relevant.
     * @param token User token.
     * @return True if token is valid and all check are passed.
     */
    public boolean validateToken(String token) {
        Optional<Claims> optionalClaims = getClaims(token);

        if (optionalClaims.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "No claims");
        }

        Claims claims = optionalClaims.get();

        Optional<User> optionalUser = userDao.findByUsername(claims.get(USERNAME_KEY).toString());
        Optional<Service> optionalService = serviceDao.findByKey(claims.get(SERVICE_KEY).toString());

        if (optionalUser.isEmpty() || optionalService.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "User or Service does not exist");
        }

        Optional<Session> session = sessionDao.findByUserAndServiceAndBrowserAndIpAddress(
                optionalUser.get(), optionalService.get(), claims.get(BROWSER_KEY).toString(), claims.get(IP_KEY).toString());

        boolean valid = session.isPresent()
                && session.get().getExpiresAt().isAfter(OffsetDateTime.now())
                && claims.getExpiration().after(new Date());

        LOGGER.debug("Token {} is valid: [{}]", token, valid);
        return valid;
    }

    /**
     * Get claims from the token.
     * @param token User token.
     * @return Claims object if parsing was successful, or empty if otherwise.
     */
    private Optional<Claims> getClaims(String token) {
        try {
            return Optional.of(Jwts.parser()
                    .setSigningKey(uservistProperties.getTokenSigningKey())
                    .parseClaimsJws(token).getBody());
        } catch (Exception e) {
            LOGGER.error("Error trying to get claims from jwt, [{}]", token);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Method to composite a map consisting of a claim KEY and a claim value as String.
     * @param token Original token received from user.
     * @return A map of claims, if claims exist, or throw an error.
     */
    private Map<String, String> getClaimsMap(String token) {
        Optional<Claims> claims = getClaims(token);
        if (claims.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "No claims");
        }

        return claims.get().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, c -> c.getValue().toString()));
    }

}
