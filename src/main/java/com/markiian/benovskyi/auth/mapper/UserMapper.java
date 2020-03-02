package com.markiian.benovskyi.auth.mapper;

import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {

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
//        userDto.setPassword(user.getPasswordHash());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setCreatedAt(user.getCreatedAt());

        return userDto;
    }

    @Override
    public User toBase(User user, UserDto userDto) {
        user.setUserId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPasswordHash(userDto.getPassword());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setCreatedAt(userDto.getCreatedAt());

        return user;
    }
}
