package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.AuthApi;
import com.markiian.benovskyi.auth.service.AuthenticationService;
import com.markiian.benovskyi.model.UserAuthenticationDto;
import com.markiian.benovskyi.model.UserDto;
import com.markiian.benovskyi.model.UserSessionTokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController implements AuthApi {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<UserSessionTokenDto> authenticate(@Valid UserAuthenticationDto userAuthenticationDto) {
        return ResponseEntity.ok(authenticationService.authenticateUser(userAuthenticationDto));
    }

    @Override
    public ResponseEntity<Void> validate(@Valid UserSessionTokenDto userSessionTokenDto) {
        authenticationService.validateUserToken(userSessionTokenDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(authenticationService.getCurrentUser());
    }
}
