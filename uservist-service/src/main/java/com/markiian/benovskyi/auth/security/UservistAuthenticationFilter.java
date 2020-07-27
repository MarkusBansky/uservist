package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.properties.UservistProperties;
import com.markiian.benovskyi.auth.service.UserTokenService;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserAuthenticationDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UservistAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UservistProperties uservistProperties;

    private final Logger LOGGER = LoggerFactory.getLogger(UservistAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException, RuntimeException {

        String header = req.getHeader(uservistProperties.getTokenName());

        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(uservistProperties.getTokenPrefix())) {
            authToken = header.replace(uservistProperties.getTokenPrefix(), "").trim();
            try {
                UservistAuthenticationToken token = userTokenService.createAuthenticationFromToken(authToken);
                UserAuthenticationDto details = (UserAuthenticationDto) token.getDetails();
                username = details.getUsername();
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
                UservistAuthenticationToken authentication = userTokenService
                        .createAuthenticationFromToken(authToken);

                LOGGER.info("Authenticated user {}, token {}, setting security context", username, authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}