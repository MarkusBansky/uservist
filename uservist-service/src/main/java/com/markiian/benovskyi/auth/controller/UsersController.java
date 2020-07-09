package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.UserPermissionService;
import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.uservist.api.uservist_api.api.UsersApi;
import com.markiian.benovskyi.uservist.api.uservist_api.model.InlineObject;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1")
public class UsersController implements UsersApi {

    private final UserPermissionService userPermissionService;
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService, UserPermissionService userPermissionService) {
        this.userService = userService;
        this.userPermissionService = userPermissionService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersGetAll(@NotNull @Valid String service, @NotNull @Valid Integer page) {
        if (userPermissionService.canGetAll(service, CurrentUser.getAuth())) {
            return ResponseEntity.ok(userService.getAllUsers(page, service));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersGetById(Long id) {
        if (userPermissionService.canGetById(id, CurrentUser.getAuth())) {
            return ResponseEntity.ok(userService.getUserById(id, CurrentUser.getServiceKey()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreatePermission(Long userId, Long serviceId, @Valid InlineObject inlineObject) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("NOT IMPLEMENTED");
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreate(@Valid UserDto userDto) {
        if (userPermissionService.canCreate(userDto.getServiceRoles(), CurrentUser.getAuth())) {
            try {
                return ResponseEntity.ok(userService.createUserForService(userDto));
            } catch (UnsupportedEncodingException | CannotSendEmailException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity usersUpdate(@Valid UserDto userDto) {
        if (userPermissionService.canUpdate(userDto, CurrentUser.getAuth())) {
            return ResponseEntity.ok(userService.updateUser(userDto));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersDelete(Long id) {
        if (userPermissionService.canDelete(id, CurrentUser.getAuth())) {
            return ResponseEntity.ok(userService.deleteUser(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApplicationConstants.FORBIDDEN_EXCEPTION_MESSAGE);
    }
}
