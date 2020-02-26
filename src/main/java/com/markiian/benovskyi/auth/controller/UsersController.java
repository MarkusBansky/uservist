package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.UsersApi;
import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.auth.util.ResponseUtil;
import com.markiian.benovskyi.model.InlineObject;
import com.markiian.benovskyi.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity usersCreate(@Valid UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.createNewUser(userDto));
        } catch (InstanceAlreadyExistsException e) {
            return ResponseUtil.buildErrorResponse(HttpStatus.BAD_REQUEST, "User already exists");
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> usersCreatePermission(Long userId, Long serviceId, @Valid InlineObject inlineObject) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> usersDeleteById(Long id) {
//        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("hasRole('MODER')")
    public ResponseEntity<List<UserDto>> usersGetAll(@NotNull @Valid Integer page) {
        return ResponseEntity.ok(userService.getAllUsers(page));
    }

    @Override
    @PreAuthorize("hasRole('MODER')")
    public ResponseEntity<UserDto> usersGetById(Long id) {
        Optional<UserDto> userDto = userService.getUserById(id);

        if (userDto.isEmpty() || (!CurrentUser.hasRole(Role.ADMIN) && !userDto.get().getUsername().equals(CurrentUser.getUsername()))) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.of(userDto);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> usersUpdateById(Long id, @Valid UserDto userDto) {
        return null;
    }
}
