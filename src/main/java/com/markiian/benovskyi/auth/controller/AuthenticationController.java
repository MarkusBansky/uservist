package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.AuthenticateApi;
import com.markiian.benovskyi.auth.security.UservistAuthenticationManager;
import com.markiian.benovskyi.auth.security.UservistAuthenticationToken;
import com.markiian.benovskyi.auth.service.UserTokenService;
import com.markiian.benovskyi.model.UserAuthenticationDto;
import com.markiian.benovskyi.model.UserSessionTokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController implements AuthenticateApi {

    private final UservistAuthenticationManager authenticationManager;
    private final UserTokenService userTokenService;

    public AuthenticationController(UservistAuthenticationManager authenticationManager, UserTokenService userTokenService) {
        this.authenticationManager = authenticationManager;
        this.userTokenService = userTokenService;
    }

    @Override
    public ResponseEntity<UserSessionTokenDto> authenticate(@Valid UserAuthenticationDto userAuthenticationDto) {
        assert userAuthenticationDto != null;

        // Build authentication object
        Authentication auth = new UservistAuthenticationToken(
                userAuthenticationDto.getUsername(),
                userAuthenticationDto.getPassword(),
                userAuthenticationDto);

        // Try to authenticate user
        Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create a token of successful authentication
        final String token = userTokenService.generateToken(authentication);

        // Save token into the session
        try {
            userTokenService.saveUserSession(authentication, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and return dto
        UserSessionTokenDto dto = new UserSessionTokenDto().token(token);
        return ResponseEntity.ok(dto);
    }
}
