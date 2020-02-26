package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.mapper.ServiceMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.model.ServiceDto;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService {
    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final ServiceMapper serviceMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(ServicesService.class);

    @Autowired
    public ServicesService(ServiceDao serviceDao, UserDao userDao, ServiceMapper serviceMapper) {
        this.serviceDao = serviceDao;
        this.userDao = userDao;
        this.serviceMapper = serviceMapper;
    }

    public List<ServiceDto> getAllServicesForUser(String username) throws NotFoundException {
        Optional<User> user = userDao.findByUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException("User could not be found");
        }

        return user.get().getServiceRoles()
                .stream()
                .map(sr -> serviceMapper.toDto(sr.getService()))
                .collect(Collectors.toList());
    }

    public ServiceDto getServiceForUserById(String username, Long id) throws NotFoundException {
        Optional<User> user = userDao.findByUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException("User could not be found");
        }

        Optional<Service> service = user.get().getServiceRoles().stream()
                .filter(sr -> sr.getService().getServiceId().equals(id))
                .map(ServiceRole::getService)
                .findFirst();

        if (service.isEmpty()) {
            throw new NotFoundException("User service could not be found");
        }

        return serviceMapper.toDto(service.get());
    }

    public ServiceDto createNewService(ServiceDto serviceDto) {
        Service newService = serviceMapper.toBase(serviceDto);
        newService = serviceDao.save(newService);
        return serviceMapper.toDto(newService);
    }

    public ServiceDto updateExistingService(ServiceDto serviceDto, Long id) throws NotFoundException {
        Optional<Service> service = serviceDao.findByServiceId(id);

        if (service.isEmpty() || !id.equals(service.get().getServiceId())) {
            throw new NotFoundException("Service could not be found");
        }

        Service updatedService = serviceMapper.toBase(service.get(), serviceDto);
        updatedService = serviceDao.save(updatedService);

        return serviceMapper.toDto(updatedService);
    }
}
