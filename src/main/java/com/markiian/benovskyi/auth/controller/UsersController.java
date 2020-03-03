package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.UsersApi;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.auth.util.ResponseUtil;
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

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersGetById(Long id) {
        if (CurrentUser.isSuper()) {
            return ResponseUtil.buildResponse(() -> userService.getUserById(id));
        } else {
            return ResponseUtil.buildResponse(() -> userService.getUserById(id, CurrentUser.getServiceKey()));
        }
    }

    @Override
    public ResponseEntity usersGetAll(@NotNull @Valid String service, @NotNull @Valid Integer page) {
        if (CurrentUser.isSuper() || service.equals(CurrentUser.getServiceKey())) {
            return ResponseUtil.buildResponse(() -> userService.getAllUsers(page, service));
        } else {
            return ResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreatePermission(Long userId, Long serviceId, @Valid InlineObject inlineObject) {
        return ResponseUtil.buildErrorResponse(HttpStatus.NOT_IMPLEMENTED, "NOT IMPLEMENTED");
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreate(@NotNull @Valid String service, @Valid UserDto userDto) {
        if (CurrentUser.isSuper() || service.equals(CurrentUser.getServiceKey())) {
            return ResponseUtil.buildResponse(() -> userService.createUserForService(userDto, service));
        } else {
            return ResponseUtil.buildErrorResponse(HttpStatus.UNAUTHORIZED, ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public ResponseEntity usersUpdate(@Valid UserDto userDto) {
        return ResponseUtil.buildResponse(() -> userService.updateUser(userDto, CurrentUser.getUsername()));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersDelete(Long id) {
        return ResponseUtil.buildResponse(() -> userService.deleteUser(id, CurrentUser.getServiceKey()));
    }
}
