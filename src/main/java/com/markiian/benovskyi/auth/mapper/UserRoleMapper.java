package com.markiian.benovskyi.auth.mapper;

import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.model.UserRoleDto;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements Mapper<Role, UserRoleDto> {
    @Override
    public Role toBase(UserRoleDto userRoleDto) {
        return Role.fromValue(userRoleDto.getValue());
    }

    @Override
    public UserRoleDto toDto(Role role) {
        return UserRoleDto.fromValue(role.getValue());
    }

    @Override
    public Role toBase(Role userRole, UserRoleDto userRoleDto) {
        return Role.fromValue(userRoleDto.getValue());
    }

    @Override
    public UserRoleDto toDto(UserRoleDto userRoleDto, Role userRole) {
        return UserRoleDto.fromValue(userRole.getValue());
    }
}
