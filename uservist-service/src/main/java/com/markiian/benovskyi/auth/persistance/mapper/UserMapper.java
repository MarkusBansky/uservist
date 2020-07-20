package com.markiian.benovskyi.auth.persistance.mapper;

import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    private final ServiceRoleMapper serviceRoleMapper;

    @Autowired
    public UserMapper(ServiceRoleMapper serviceRoleMapper) {
        this.serviceRoleMapper = serviceRoleMapper;
    }

    @Override
    public User toBase(UserDto userDto) {
        return toBase(new User(), userDto);
    }

    @Override
    public UserDto toDto(User user) {
        return toDto(new UserDto(), user);
    }

    @Override
    public UserDto toDto(UserDto userDto, User user) {
        userDto.setId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setCreatedAt(user.getCreatedAt());

        userDto.setServiceRoles(user.getServiceRoles()
                .stream()
                .map(serviceRoleMapper::toDto)
                .collect(Collectors.toList()));

        return userDto;
    }

    @Override
    public User toBase(User user, UserDto userDto) {
        if (userDto.getId() != null) {
            user.setUserId(userDto.getId());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            user.setPasswordHash(userDto.getPassword());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getUpdatedAt() != null) {
            user.setUpdatedAt(userDto.getUpdatedAt());
        }
        if (userDto.getCreatedAt() != null) {
            user.setCreatedAt(userDto.getCreatedAt());
        }

        return user;
    }
}
