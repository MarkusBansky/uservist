package com.markiian.benovskyi.auth.persistance.mapper;

import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements Mapper<Service, ServiceDto> {
    @Override
    public ServiceDto toDto(Service service) {
        return toDto(new ServiceDto(), service);
    }

    @Override
    public Service toBase(ServiceDto serviceDto) {
        return toBase(new Service(), serviceDto);
    }

    @Override
    public ServiceDto toDto(ServiceDto serviceDto, Service service) {
        serviceDto.setId(service.getServiceId());
        serviceDto.setKey(service.getKey());
        serviceDto.setName(service.getName());
        serviceDto.setDescription(service.getDescription());
        serviceDto.setUpdatedAt(service.getUpdatedAt());
        serviceDto.setCreatedAt(service.getCreatedAt());
        return serviceDto;
    }

    @Override
    public Service toBase(Service service, ServiceDto serviceDto) {
        service.setKey(serviceDto.getKey());
        service.setName(serviceDto.getName());
        service.setDescription(serviceDto.getDescription());
        service.setUpdatedAt(serviceDto.getUpdatedAt());
        service.setCreatedAt(serviceDto.getCreatedAt());
        return service;
    }
}
