package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.service.UserTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UservistAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTHENTICATION_HEADER = "Authentication";
    private final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private UserTokenService userTokenService;

    private final Logger LOGGER = LoggerFactory.getLogger(UservistAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException, RuntimeException {

        String header = req.getHeader(AUTHENTICATION_HEADER);

        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                username = userTokenService.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                LOGGER.error("An error occurred during getting username from token", e);
            } catch (ExpiredJwtException e) {
                LOGGER.warn("The token is expired and not valid anymore", e);
            }
        } else {
            LOGGER.warn("Couldn't find bearer string, will ignore the header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (userTokenService.validateToken(authToken)) {
                WebAuthenticationDetails webDetails = new WebAuthenticationDetailsSource().buildDetails(req);
                UservistAuthenticationToken authentication = userTokenService
                        .getAuthenticationFromToken(authToken, webDetails.getRemoteAddress());

                LOGGER.info("Authenticated user {}, token {}, setting security context", username, authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}
