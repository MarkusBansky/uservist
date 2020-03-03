package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.InvitationsApi;
import com.markiian.benovskyi.model.UserServiceInvitationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class InvitationsController implements InvitationsApi {

    @Override
    public ResponseEntity<Void> acceptInvitation() {
        return null;
    }

    @Override
    public ResponseEntity<Void> createInvitation(@NotNull @Valid String token, @Valid UserServiceInvitationDto userServiceInvitationDto) {
        return null;
    }
}
