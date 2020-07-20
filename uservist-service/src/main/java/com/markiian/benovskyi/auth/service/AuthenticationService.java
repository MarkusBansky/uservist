package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.mapper.UserAuthenticationMapper;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.security.UservistAuthenticationManager;
import com.markiian.benovskyi.auth.security.UservistAuthenticationToken;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserAuthenticationDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserSessionTokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthenticationService {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserAuthenticationMapper userAuthenticationMapper;
    private final UservistAuthenticationManager authenticationManager;
    private final UserTokenService userTokenService;

    public AuthenticationService(
            UservistAuthenticationManager authenticationManager, UserTokenService userTokenService,
            UserAuthenticationMapper userAuthenticationMapper) {
        this.authenticationManager = authenticationManager;
        this.userTokenService = userTokenService;
        this.userAuthenticationMapper = userAuthenticationMapper;
    }

    /**
     * Try to authenticate user by their username and password.
     * All fields in user authentication dto request are required.
     * @param dto User authentication request object.
     * @return Session object with token.
     */
    public UserSessionTokenDto authenticateUser(UserAuthenticationDto dto) {
        if (dto == null || dto.getPassword() == null || dto.getUsername() == null || dto.getKey() == null) {
            if (dto != null) {
                LOGGER.info("Application rejected request to log in user with dto objects: {} {} {} {}",
                        dto.getUsername(), dto.getPassword(), dto.getKey(), dto.getHardwareId());
            }
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "All fields in authentication form are required");
        }

        // Build authentication object
        Authentication auth = new UservistAuthenticationToken(
                dto.getUsername(), dto.getPassword(), userAuthenticationMapper.toBase(dto));

        // Try to authenticate user
        Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create a token of successful authentication
        final String token = userTokenService.generateToken(authentication);

        // Save token into the session
        try {
            userTokenService.saveUserSession(authentication, token);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        // Create and return dto
        return new UserSessionTokenDto().token(token);
    }

    /**
     * Method used to validate user token against existing records in the database.
     * @param dto Data object containing the requested token.
     */
    public void validateUserToken(UserSessionTokenDto dto) {
        boolean valid = userTokenService.validateToken(dto.getToken());

        if (valid) {
            return;
        }

        throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE, "Token is not valid");
    }

    /**
     * Method used to return current user information that is making that request.
     * @return A user object with all requested information.
     */
    public UserDto getCurrentUser() {
        String username = CurrentUser.getUsername();
        return userTokenService.getUserFromUsername(username);
    }
}
