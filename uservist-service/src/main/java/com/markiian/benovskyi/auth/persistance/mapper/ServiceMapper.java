package com.markiian.benovskyi.auth.persistance.mapper;

import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements Mapper<Service, ServiceDto> {
    @Override
    public ServiceDto toDto(Service service) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(service.getId());
        serviceDto.setKey(service.getKey());
        serviceDto.setName(service.getName());
        serviceDto.setDescription(service.getDescription());
        serviceDto.setUpdatedAt(service.getUpdatedAt());
        serviceDto.setCreatedAt(service.getCreatedAt());
        return serviceDto;
    }

    @Override
    public Service toBase(ServiceDto serviceDto) {
        return null;
    }
}
