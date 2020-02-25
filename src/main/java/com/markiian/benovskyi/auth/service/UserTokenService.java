package com.markiian.benovskyi.auth.service;

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
import com.markiian.benovskyi.model.UserAuthenticationDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserTokenService extends AbstractTokenService {

    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final SessionDao sessionDao;
    private final ServiceRoleDao serviceRoleDao;

    private final String USERNAME_KEY = "username";
    private final String SERVICE_KEY = "service";

    public UserTokenService(SessionDao sessionDao, UserDao userDao, ServiceDao serviceDao, ServiceRoleDao serviceRoleDao) {
        this.sessionDao = sessionDao;
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.serviceRoleDao = serviceRoleDao;
    }

    public void saveUserSession(Authentication authentication, String token) throws Exception {
        UserAuthenticationDto authDto = (UserAuthenticationDto) authentication.getDetails();

        Optional<User> user = userDao.findByUsername(authDto.getUsername());
        Optional<Service> service = serviceDao.findByKey(authDto.getKey());

        if (user.isEmpty() || service.isEmpty()) {
            throw new Exception("User or Service does not exist");
        }

        // Try to find existing session, or create new object if it does not exist
        Session session = sessionDao
                .findByUser_UsernameAndService_KeyAndHardwareId(
                        authDto.getUsername(), authDto.getKey(), authDto.getHardwareId())
                .orElseGet(Session::new)
                .withUser(user.get())
                .withService(service.get())
                .withHardwareId(authDto.getHardwareId())
                .withToken(token)
                .withExpiresAt(getExpiresAtFromToken(token).toInstant().atOffset(ZoneOffset.UTC));

        sessionDao.save(session);
    }

    /**
     * Takes an authentication object, that is supposed to be authenticated, and constructs a token from it.
     * The new token contains username, authority (role), serviceKey.
     * @param authentication Supposed authorized authentication object.
     * @return HS256 Token string.
     */
    public String generateToken(Authentication authentication) {
        UserAuthenticationDto authDto = (UserAuthenticationDto) authentication.getDetails();

        byte[] keyBytes = Decoders.BASE64.decode(SIGNING_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .claim(USERNAME_KEY, authentication.getName())
                .claim(SERVICE_KEY, authDto.getKey())
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
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
            return null;
        }

        List<ServiceRole> roles = serviceRoleDao.findAllByUserAndService(user.get(), service.get());
        Collection<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRoles(roles);

        final UserAuthenticationDto details = new UserAuthenticationDto()
                .hardwareId(remoteAddress)
                .key(serviceKey)
                .username(username);

        return new UservistAuthenticationToken(username, "", details, authorities);
    }

    public boolean validateToken(String token) {
        Optional<Session> session = sessionDao.findByToken(token);
        Optional<Claims> claims = getClaims(token);

        return claims.isPresent() && session.isPresent()
                && session.get().getExpiresAt().isAfter(OffsetDateTime.now())
                && claims.get().getExpiration().after(new Date());
    }
}
