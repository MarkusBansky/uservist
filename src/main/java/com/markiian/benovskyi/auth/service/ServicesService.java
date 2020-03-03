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
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Service> services = serviceDao.findAll(pageRequest);

        Page<ServiceDto> resultPage = new PageImpl(
                services.stream().map(serviceMapper::toDto).collect(Collectors.toList()),
                pageRequest,
                services.getTotalElements());

        LOGGER.debug("Received all services for page: {}; services: {}", page, resultPage);
        return resultPage;
    }

    public ServiceDto getServiceById(Long id) {
        Optional<Service> service = serviceDao.findByServiceId(id);

        if (service.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        return serviceMapper.toDto(service.get());
    }

    /**
     * SImply create new service using service dto.
     * @param serviceDto A service dto to create.
     * @return New created dto.
     */
    public ServiceDto createNewService(ServiceDto serviceDto) {
        Service newService = serviceMapper.toBase(serviceDto);
        newService = serviceDao.save(newService);
        return serviceMapper.toDto(newService);
    }

    /**
     * Update existing service with new data, service is found using id from the dto.
     * @param serviceDto Service DTO used to update information.
     * @return New updated dto.
     */
    public ServiceDto updateExistingService(ServiceDto serviceDto) {
        Optional<Service> service = serviceDao.findByServiceId(serviceDto.getId());

        if (service.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        Service updatedService = serviceMapper.toBase(service.get(), serviceDto);
        updatedService = serviceDao.save(updatedService);

        return serviceMapper.toDto(updatedService);
    }

    /**
     * Delete service if it exists.
     * @param serviceId Service ID to delete.
     * @return True if deleted without exceptions.
     */
    public Boolean deleteService(Long serviceId) {
        serviceDao.deleteById(serviceId);
        return true;
    }
}
