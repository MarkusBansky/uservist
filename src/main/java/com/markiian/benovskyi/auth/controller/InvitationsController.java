package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.InvitationsApi;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.InvitationService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.UserServiceInvitationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class InvitationsController implements InvitationsApi {

    private final InvitationService invitationService;

    @Autowired
    public InvitationsController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @Override
    public ResponseEntity acceptInvitation(@NotNull @Valid String token) {
        return ResponseEntity.ok(invitationService.acceptInvitation(token));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createInvitation(@Valid UserServiceInvitationDto userServiceInvitationDto) {
        if (CurrentUser.isSuper() || CurrentUser.getServiceKey().equals(userServiceInvitationDto.getServiceKey())) {
            return ResponseEntity.ok(invitationService.createInvitation(userServiceInvitationDto));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApplicationConstants.FORBIDDEN_EXCEPTION_MESSAGE);
    }
}
