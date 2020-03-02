package com.markiian.benovskyi.auth.mapper;

import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.model.ServiceRoleDto;
import com.markiian.benovskyi.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    private final ServiceRoleMapper serviceRoleMapper;

    @Autowired
    public UserMapper(ServiceRoleMapper serviceRoleMapper) {
        this.serviceRoleMapper = serviceRoleMapper;
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
//        userDto.setPasswordHash(user.getPasswordHash());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setCreatedAt(user.getCreatedAt());

        List<ServiceRole> serviceRoles = user.getServiceRoles();
        List<ServiceRoleDto> roles = serviceRoles
                .stream()
                .map(serviceRoleMapper::toDto)
                .collect(Collectors.toList());
        userDto.setServiceRoles(roles);

        return userDto;
    }

    @Override
    public User toBase(User user, UserDto userDto) {
        user.setUserId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setCreatedAt(userDto.getCreatedAt());

        return user;
    }
}
