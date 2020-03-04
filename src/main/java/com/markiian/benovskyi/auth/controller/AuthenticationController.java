package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.AuthApi;
import com.markiian.benovskyi.auth.mapper.UserAuthenticationMapper;
import com.markiian.benovskyi.auth.security.UservistAuthenticationManager;
import com.markiian.benovskyi.auth.security.UservistAuthenticationToken;
import com.markiian.benovskyi.auth.service.UserTokenService;
import com.markiian.benovskyi.auth.util.ResponseUtil;
import com.markiian.benovskyi.model.UserAuthenticationDto;
import com.markiian.benovskyi.model.UserSessionTokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController implements AuthApi {

    private final UserAuthenticationMapper userAuthenticationMapper;
    private final UservistAuthenticationManager authenticationManager;
    private final UserTokenService userTokenService;

    public AuthenticationController(UservistAuthenticationManager authenticationManager, UserTokenService userTokenService, UserAuthenticationMapper userAuthenticationMapper) {
        this.authenticationManager = authenticationManager;
        this.userTokenService = userTokenService;
        this.userAuthenticationMapper = userAuthenticationMapper;
    }

    @Override
    public ResponseEntity authenticate(@Valid UserAuthenticationDto userAuthenticationDto) {
        assert userAuthenticationDto != null;

        // Build authentication object
        Authentication auth = new UservistAuthenticationToken(
                userAuthenticationDto.getUsername(),
                userAuthenticationDto.getPassword(),
                userAuthenticationMapper.toBase(userAuthenticationDto));

        // Try to authenticate user
        Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create a token of successful authentication
        final String token = userTokenService.generateToken(authentication);

        // Save token into the session
        try {
            userTokenService.saveUserSession(authentication, token);
        } catch (Exception e) {
            return ResponseUtil.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        // Create and return dto
        UserSessionTokenDto dto = new UserSessionTokenDto().token(token);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity validate(@Valid UserSessionTokenDto userSessionTokenDto) {
        assert userSessionTokenDto != null;
        assert userSessionTokenDto.getToken() != null;

        boolean valid = userTokenService.validateToken(userSessionTokenDto.getToken());

        if (valid) {
            return ResponseEntity.ok().build();
        }

        return ResponseUtil.buildErrorResponse(HttpStatus.NOT_ACCEPTABLE, "Token is no longer valid.");
    }
}
