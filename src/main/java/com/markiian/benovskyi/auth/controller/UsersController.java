package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.UsersApi;
import com.markiian.benovskyi.auth.service.UserService;
import com.markiian.benovskyi.model.InlineObject;
import com.markiian.benovskyi.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsersController implements UsersApi {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity usersCreate(@Valid UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.createNewUser(userDto));
        } catch (InstanceAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User already exists");
        }
    }

    @Override
    public ResponseEntity<Void> usersCreatePermission(Long userId, Long serviceId, @Valid InlineObject inlineObject) {
        return null;
    }

    @Override
    public ResponseEntity<Void> usersDeleteById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserDto>> usersGetAll(@NotNull @Valid Integer page) {
        return ResponseEntity.ok(userService.getAllUsers(page));
    }

    @Override
    public ResponseEntity<UserDto> usersGetById(Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<UserDto> usersUpdateById(Long id, @Valid UserDto userDto) {
        return null;
    }
}
