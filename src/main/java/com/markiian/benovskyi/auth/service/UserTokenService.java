package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.mapper.UserMapper;
import com.markiian.benovskyi.auth.model.UserAuthentication;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.SessionDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.Session;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.security.UservistAuthenticationToken;
import com.markiian.benovskyi.auth.service.misc.AbstractTokenService;
import com.markiian.benovskyi.auth.util.RoleUtil;
import com.markiian.benovskyi.model.UserDto;
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
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserTokenService extends AbstractTokenService {

    private final UserMapper userMapper;
    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final SessionDao sessionDao;
    private final ServiceRoleDao serviceRoleDao;

    private final String USERNAME_KEY = "username";
    private final String SERVICE_KEY = "service";

    private final Logger LOGGER = LoggerFactory.getLogger(UserTokenService.class);

    @Autowired
    public UserTokenService(SessionDao sessionDao, UserDao userDao, ServiceDao serviceDao, ServiceRoleDao serviceRoleDao, UserMapper userMapper) {
        this.sessionDao = sessionDao;
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.serviceRoleDao = serviceRoleDao;
        this.userMapper = userMapper;
    }

    public UserDto getUserFromUsername(String username) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            LOGGER.warn("User with username {} cannot be found", username);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User or Service does not exist");
        }

        return userMapper.toDto(user.get());
    }

    public void saveUserSession(Authentication authentication, String token) throws Exception {
        UserAuthentication authDto = (UserAuthentication) authentication.getDetails();

        Optional<User> user = userDao.findByUsername(authDto.getUsername());
        Optional<Service> service = serviceDao.findByKey(authDto.getServiceKey());

        if (user.isEmpty() || service.isEmpty()) {
            LOGGER.warn("User with Username {} or service with Key {} cannot be found",
                    authDto.getUsername(), authDto.getServiceKey());
            throw new Exception("User or Service does not exist");
        }

        // Try to find existing session, or create new object if it does not exist
        Session session = sessionDao
                .findByUser_UsernameAndService_KeyAndHardwareId(
                        authDto.getUsername(), authDto.getServiceKey(), authDto.getHardwareId())
                .orElseGet(Session::new)
                .withUser(user.get())
                .withService(service.get())
                .withHardwareId(authDto.getHardwareId())
                .withToken(token)
                .withExpiresAt(getExpiresAtFromToken(token).toInstant().atOffset(ZoneOffset.UTC));

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
        UserAuthentication authDto = (UserAuthentication) authentication.getDetails();

        byte[] keyBytes = Decoders.BASE64.decode(SIGNING_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
                .claim(USERNAME_KEY, authentication.getName())
                .claim(SERVICE_KEY, authDto.getServiceKey())
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();

        LOGGER.debug("Generated new token for: {}, token: {}", authentication.getName(), token);
        return token;
    }

    /**
     * Get username from user token.
     * @param token HS256 Token string.
     * @return A username or null if claims is empty.
     */
    public String getUsernameFromToken(String token) {
        Optional<Claims> claims = getClaims(token);
        if (claims.isEmpty()) {
            return null;
        }

        return claims.get().get(USERNAME_KEY).toString();
    }

    public Date getExpiresAtFromToken(String token) {
        Optional<Claims> claims = getClaims(token);
        if (claims.isEmpty()) {
            return null;
        }

        return claims.get().getExpiration();
    }

    public String getServiceKeyFromToken(String token) {
        Optional<Claims> claims = getClaims(token);
        if (claims.isEmpty()) {
            return null;
        }

        return claims.get().get(SERVICE_KEY).toString();
    }

    public UservistAuthenticationToken getAuthenticationFromToken(final String token, String remoteAddress) {
        String username = getUsernameFromToken(token);
        String serviceKey = getServiceKeyFromToken(token);

        Optional<User> user = userDao.findByUsername(username);
        Optional<Service> service = serviceDao.findByKey(serviceKey);

        if (user.isEmpty() || service.isEmpty()) {
            LOGGER.warn("Username or service are empty");
            return null;
        }

        List<ServiceRole> roles = serviceRoleDao.findAllByUserAndService(user.get(), service.get());
        Collection<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRoles(roles);

        final UserAuthentication details = new UserAuthentication(username, remoteAddress, serviceKey);
        UservistAuthenticationToken auth = new UservistAuthenticationToken(username, "", details, authorities);

        LOGGER.debug("Result for user authentication: {}", auth);
        return auth;
    }

    public boolean validateToken(String token) {
        Optional<Session> session = sessionDao.findByToken(token);
        Optional<Claims> claims = getClaims(token);

        boolean valid = claims.isPresent() && session.isPresent()
                && session.get().getExpiresAt().isAfter(OffsetDateTime.now())
                && claims.get().getExpiration().after(new Date());

        LOGGER.debug("Token {} is valid: [{}]", token, valid);
        return valid;
    }
}
