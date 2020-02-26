package com.markiian.benovskyi.auth.mapper;

import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.model.ServiceRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRoleMapper implements Mapper<ServiceRole, ServiceRoleDto> {
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public ServiceRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public ServiceRoleDto toDto(ServiceRole serviceRole) {
        return toDto(new ServiceRoleDto(), serviceRole);
    }

    @Override
    public ServiceRoleDto toDto(ServiceRoleDto userServiceRoleDto, ServiceRole serviceRole) {
        userServiceRoleDto.setServiceKey(serviceRole.getService().getKey());
        userServiceRoleDto.setRole(userRoleMapper.toDto(serviceRole.getRole()));
        return userServiceRoleDto;
    }

    @Override
    public ServiceRole toBase(ServiceRole serviceRole, ServiceRoleDto userServiceRoleDto) {
        return null;
    }
}
