package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.mapper.ServiceMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceCreateDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceUpdateDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServicesPageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService {
    private final UserService userService;
    private final ServiceDao serviceDao;
    private final ServiceMapper serviceMapper;
    private final ServiceRoleDao serviceRoleDao;

    @SuppressWarnings("FieldCanBeLocal")
    private final int PAGE_SIZE = 10;
    private final Logger LOGGER = LoggerFactory.getLogger(ServicesService.class);

    @Autowired
    public ServicesService(ServiceDao serviceDao, ServiceMapper serviceMapper, UserService userService, ServiceRoleDao serviceRoleDao) {
        this.serviceDao = serviceDao;
        this.serviceMapper = serviceMapper;
        this.userService = userService;
        this.serviceRoleDao = serviceRoleDao;
    }

    public ServicesPageDto getServicesPage(Integer page) {
        LOGGER.debug("Performing task to get all available services for page: {}", page);

        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by("id"));
        Page<Service> services = serviceDao.findAll(pageRequest);

        LOGGER.debug("Received {} services from the database", services.getTotalElements());
        ServicesPageDto dto = new ServicesPageDto();
        dto.setCurrentPage(page);
        dto.setItemsPerPage(PAGE_SIZE);
        dto.setNumberOfItems(services.getTotalElements());
        dto.setNumberOfPages((long) services.getTotalPages());
        dto.setServices(services.get().map(serviceMapper::toDto).collect(Collectors.toList()));

        LOGGER.debug("Constructed service dto response page for page {} of services: {}", page, dto.getNumberOfPages());
        return dto;
    }

    public ServiceDto getServiceById(Long id) {
        LOGGER.debug("Performing task to get service by it's unique ID {}", id);
        Optional<Service> service = serviceDao.findById(id);

        if (service.isEmpty()) {
            LOGGER.warn("Service for id {} is empty and does not exist", id);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        LOGGER.debug("Found service {} returning result dto", service.get());
        return serviceMapper.toDto(service.get());
    }

    /**
     * SImply create new service using service dto.
     * @param dto A service dto to create.
     * @return New created dto.
     */
    public ServiceDto createNewService(ServiceCreateDto dto) {
        LOGGER.debug("Performing task to get create new service with dto: {}", dto);
        Optional<Service> optionalService = serviceDao.findByKey(dto.getKey());

        if (optionalService.isPresent()) {
            LOGGER.warn("Service with ky {} already exists", dto.getKey());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_ALREADY_EXISTS);
        }

        Optional<User> currentUser = userService.getUserByUsername(CurrentUser.getUsername());
        if (currentUser.isEmpty()) {
            LOGGER.warn("Cannot find current user entity");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_NOT_FOUND);
        }

        Service service = new Service();
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setKey(dto.getKey());

        Service createdService = serviceDao.save(service);
        LOGGER.debug("New service has been created with id: {}", createdService.getId());

        ServiceRole role = new ServiceRole();
        role.setService(service);
        role.setUser(currentUser.get());
        role.setRole(RoleType.ADMIN);

        serviceRoleDao.save(role);
        LOGGER.debug("Created role {} for service id: {} and user id: {}",
                role.getRole(), role.getService().getId(), role.getUser().getId());

        return serviceMapper.toDto(createdService);
    }

    /**
     * Update existing service with new data, service is found using id from the dto.
     * @param id The ID of the service that should be updated.
     * @param dto Service DTO used to update information.
     * @return New updated dto.
     */
    public ServiceDto updateExistingService(Long id, ServiceUpdateDto dto) {
        LOGGER.debug("Performing task to update service with id {}", id);
        Optional<Service> optionalService = serviceDao.findById(id);

        if (optionalService.isEmpty()) {
            LOGGER.warn("Service for update has not been found");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        Service service = optionalService.get();
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());

        serviceDao.save(service);

        LOGGER.debug("Service with id {} has been updated successfully, new name: {}, new description: {}",
                service.getId(), service.getName(), service.getDescription());

        return serviceMapper.toDto(service);
    }

    /**
     * Delete service if it exists.
     * @param id Service ID to delete.
     */
    public void deleteService(Long id) {
        LOGGER.debug("Performing task to delete service by id {}", id);
        Optional<Service> optionalService = serviceDao.findById(id);

        if (optionalService.isEmpty()) {
            LOGGER.warn("Service for update has not been found");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        serviceDao.delete(optionalService.get());

        LOGGER.debug("Service with id {} successfully deleted", id);
    }
}
