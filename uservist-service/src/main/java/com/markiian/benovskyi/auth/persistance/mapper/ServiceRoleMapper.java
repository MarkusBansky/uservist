package com.markiian.benovskyi.auth.persistance.mapper;

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
        ServiceRoleDto dto = new ServiceRoleDto();
        dto.setService(serviceMapper.toDto(serviceRole.getService()));
        dto.setRole(userRoleMapper.toDto(serviceRole.getRole()));
        return dto;
    }

    @Override
    public ServiceRole toBase(ServiceRoleDto serviceRoleDto) {
        return null;
    }
}
