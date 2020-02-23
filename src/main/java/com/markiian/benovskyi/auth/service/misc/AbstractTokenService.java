package com.markiian.benovskyi.auth.service.misc;

import io.jsonwebtoken.*;

import java.util.Optional;

public abstract class AbstractTokenService {

    protected Integer   ACCESS_TOKEN_VALIDITY_SECONDS = 60*60*24;
    protected String    SIGNING_KEY = "q+E43gM6xEnT/M4nbf/FYxMWfViB2Ixi2G+CMthbaYc=";

    /**
     * Retrieve claims from the token, if successful then returns an non null optional object with claims.
     * @param token Token string.
     * @return Optional claims, empty if any error or no claims in token.
     */
    protected Optional<Claims> getClaims(String token) {
        try {
            final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);
            final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return Optional.ofNullable(claimsJws.getBody());
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            return Optional.empty();
        }
    }

}
