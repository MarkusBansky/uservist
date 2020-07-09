package com.markiian.benovskyi.auth.mapper;

import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRoleMapper implements Mapper<ServiceRole, ServiceRoleDto> {
    private final UserRoleMapper userRoleMapper;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceRoleMapper(UserRoleMapper userRoleMapper, ServiceMapper serviceMapper) {
        this.userRoleMapper = userRoleMapper;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public ServiceRoleDto toDto(ServiceRole serviceRole) {
        return toDto(new ServiceRoleDto(), serviceRole);
    }

    @Override
    public ServiceRoleDto toDto(ServiceRoleDto userServiceRoleDto, ServiceRole serviceRole) {
        userServiceRoleDto.setService(serviceMapper.toDto(serviceRole.getService()));
        userServiceRoleDto.setRole(userRoleMapper.toDto(serviceRole.getRole()));
        return userServiceRoleDto;
    }

    @Override
    public ServiceRole toBase(ServiceRole serviceRole, ServiceRoleDto userServiceRoleDto) {
        return null;
    }
}
