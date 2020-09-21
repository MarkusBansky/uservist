package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.uservist.api.uservist_api.api.UsersApi;
import com.markiian.benovskyi.uservist.api.uservist_api.model.*;
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
    public ResponseEntity<UsersPageDto> usersGetAll(@NotNull @Valid String service, @NotNull @Valid Integer page) {
        return ResponseEntity.ok(userService.getAllUsers(page, service));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> usersGetById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreatePermission(Long userId, Long serviceId, @Valid InlineObject inlineObject) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("NOT IMPLEMENTED");
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> usersCreate(@Valid UserCreateDto dto) {
        return ResponseEntity.ok(userService.createUserForService(dto));
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> usersUpdate(Long id, @Valid UserUpdateDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> usersDelete(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
