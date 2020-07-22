package com.markiian.benovskyi.auth.persistance.mapper;

import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public User toBase(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
