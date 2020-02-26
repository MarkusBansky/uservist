package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.UsersApi;
import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.auth.util.ResponseUtil;
import com.markiian.benovskyi.model.InlineObject;
import com.markiian.benovskyi.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity usersCreate(@Valid UserDto userDto) {
        return ResponseUtil.buildResponse(() -> userService.createNewUserForService(userDto, CurrentUser.getServiceKey()));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreatePermission(Long userId, Long serviceId, @Valid InlineObject inlineObject) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersDeleteById(Long id) {
        return ResponseUtil.buildResponse(() -> userService.deleteUser(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersGetAll(@NotNull @Valid Integer page) {
        return ResponseUtil.buildResponse(() -> userService.getAllUsers(page));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersGetById(Long id) {
        return ResponseUtil.buildResponse(() -> userService.getUserById(id));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> usersUpdateById(Long id, @Valid UserDto userDto) {
        return null;
    }
}
