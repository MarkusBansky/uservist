package com.markiian.benovskyi.auth.persistance.mapper;

import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserRoleDto;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements Mapper<RoleType, UserRoleDto> {
    @Override
    public RoleType toBase(UserRoleDto userRoleDto) {
        return RoleType.fromValue(userRoleDto.getValue());
    }

    @Override
    public UserRoleDto toDto(RoleType role) {
        return UserRoleDto.fromValue(role.getValue());
    }
}
