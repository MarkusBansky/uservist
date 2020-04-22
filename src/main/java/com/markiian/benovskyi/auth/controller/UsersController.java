package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.UsersApi;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.UserPermissionService;
import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.InlineObject;
import com.markiian.benovskyi.model.UserDto;
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
            return ResponseEntity.ok(userService.createUserForService(userDto));
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
