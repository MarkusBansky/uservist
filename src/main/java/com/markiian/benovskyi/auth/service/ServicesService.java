package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.mapper.ServiceMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.ServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService {
    private final ServiceDao serviceDao;
    private final ServiceMapper serviceMapper;

    @SuppressWarnings("FieldCanBeLocal")
    private final int PAGE_SIZE = 10;
    private final Logger LOGGER = LoggerFactory.getLogger(ServicesService.class);

    @Autowired
    public ServicesService(ServiceDao serviceDao, ServiceMapper serviceMapper) {
        this.serviceDao = serviceDao;
        this.serviceMapper = serviceMapper;
    }

    public Page<ServiceDto> getAllServices(Integer page) {
        LOGGER.debug("Performing task to get all available services");
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Service> services = serviceDao.findAll(pageRequest);

        LOGGER.debug("Received {} services from the database", services.getTotalElements());
        Page<ServiceDto> resultPage = new PageImpl(
                services.stream().map(serviceMapper::toDto).collect(Collectors.toList()),
                pageRequest,
                services.getTotalElements());

        LOGGER.debug("Constructed service dto response page for: {}; services: {}", page, resultPage);
        return resultPage;
    }

    public ServiceDto getServiceById(Long id) {
        LOGGER.debug("Performing task to get service by it's unique ID {}", id);
        Optional<Service> service = serviceDao.findByServiceId(id);

        if (service.isEmpty()) {
            LOGGER.debug("Service for id {} is empty and does not exist", id);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        LOGGER.debug("Found service {} returning result dto", service.get());
        return serviceMapper.toDto(service.get());
    }

    /**
     * SImply create new service using service dto.
     * @param serviceDto A service dto to create.
     * @return New created dto.
     */
    public ServiceDto createNewService(ServiceDto serviceDto) {
        LOGGER.debug("Performing task to get create new service with dto: {}", serviceDto);
        Service newService = serviceMapper.toBase(serviceDto);
        newService = serviceDao.save(newService);

        LOGGER.debug("New service has been created: {}", newService);
        return serviceMapper.toDto(newService);
    }

    /**
     * Update existing service with new data, service is found using id from the dto.
     * @param serviceDto Service DTO used to update information.
     * @return New updated dto.
     */
    public ServiceDto updateExistingService(ServiceDto serviceDto) {
        LOGGER.debug("Performing task to update service with dto {}", serviceDto);
        Optional<Service> service = serviceDao.findByServiceId(serviceDto.getId());

        if (service.isEmpty()) {
            LOGGER.debug("Service for update has not been found");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        Service updatedService = serviceMapper.toBase(service.get(), serviceDto);
        updatedService = serviceDao.save(updatedService);

        LOGGER.debug("Service has been updated successfully: {}", updatedService);
        return serviceMapper.toDto(updatedService);
    }

    /**
     * Delete service if it exists.
     * @param serviceId Service ID to delete.
     * @return True if deleted without exceptions.
     */
    public Boolean deleteService(Long serviceId) {
        LOGGER.debug("Performing task to delete service by id {}", serviceId);
        serviceDao.deleteById(serviceId);

        LOGGER.debug("Service with id {} successfully deleted", serviceId);
        return true;
    }
}
