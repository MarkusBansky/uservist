package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.mapper.UserMapper;
import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserCreateDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserUpdateDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UsersPageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class UserService {
    private final UserDao userDao;
    private final ServiceDao serviceDao;

    private final UserMapper userMapper;

    private final MailingService mailingService;

    @SuppressWarnings("FieldCanBeLocal")
    private final int PAGE_SIZE = 10;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserDao userDao,
                       UserMapper userMapper,
                       ServiceDao serviceDao,
                       MailingService mailingService) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.serviceDao = serviceDao;
        this.mailingService = mailingService;
    }

    /**
     * Try to get user by their username. If user exists then the optional would contain user object.
     * @param username Username to search for user.
     * @return Optional user object.
     */
    public Optional<User> getUserByUsername(String username) {
        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isEmpty()) {
            LOGGER.info(ApplicationConstants.USER_NOT_FOUND);
        }
        return optionalUser;
    }

    /**
     * Get all users for specific service.
     * @param page The page of users to receive.
     * @param serviceKey The key for the service.
     * @return A page with users.
     */
    public UsersPageDto getAllUsers(Integer page, String serviceKey) {
        LOGGER.info("Performing task to get all available users");

        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by("id"));
        Page<User> users;

        if (serviceKey == null || serviceKey.isBlank() && CurrentUser.isSuperUser()) {
            users = userDao.findAll(pageRequest);
        } else if (!serviceKey.isBlank()) {
            Optional<Service> optionalService = serviceDao.findByKey(serviceKey);

            if (optionalService.isEmpty()) {
                LOGGER.warn("Service for users request cannot be found, service key: {}", serviceKey);
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
            }

            users = userDao.findAllUsersByServiceId(optionalService.get().getId(), pageRequest);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("Found {} users", users.getTotalElements());

        UsersPageDto dto = new UsersPageDto();
        dto.setCurrentPage(page);
        dto.setItemsPerPage(PAGE_SIZE);
        dto.setNumberOfItems(users.getTotalElements());
        dto.setNumberOfPages((long) users.getTotalPages());
        dto.setUsers(users.get().map(userMapper::toDto).collect(Collectors.toList()));

        LOGGER.info("Received all users service: {}; page: {}; users: {}", serviceKey, page, dto.getNumberOfItems());

        return dto;
    }

    /**
     * Method used by super admin to get any user by their ID.
     * @param userId Users ID.
     * @return userDto if user was found.
     */
    public UserDto getUserById(Long userId) {
        LOGGER.info("Performing task to get all user by user ID {}", userId);
        Optional<User> user = userDao.findById(userId);

        if (user.isEmpty()) {
            LOGGER.warn("User with ID {} cannot be found", userId);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_NOT_FOUND);
        }

        LOGGER.info("Received info about user with id {}: {}", userId, user.get());
        return userMapper.toDto(user.get());
    }

    /**
     * Create a new user for the service, if such service exists, create a connection between user and the service
     * and create a default USER role on that service.
     * @param dto User to create.
     * @return Created user object.
     */
    public UserDto createUserForService(UserCreateDto dto) {
        LOGGER.info("Performing task to create new user with dto: {}", dto);
        Optional<User> optionalUser = userDao.findByUsername(dto.getUsername());
        Optional<Service> service = serviceDao.findByKey(dto.getServiceKey());

        if (service.isEmpty()) {
            LOGGER.warn("Service cannot be found with service key {}", dto.getServiceKey());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        if (optionalUser.isPresent()) {
            LOGGER.warn("User with such username already exists");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_ALREADY_EXISTS);
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        ServiceRole role = new ServiceRole();
        role.setUser(user);
        role.setService(service.get());
        role.setRole(RoleType.USER);

        user.getServiceRoles().add(role);

        User createdUser = userDao.save(user);
        LOGGER.info("User created successfully, new username: {}, id: {}",
                createdUser.getUsername(), createdUser.getId());

        try {
            LOGGER.info("Sending welcome email to new user email {}", createdUser.getEmail());
            mailingService.sendRegistrationEmail(createdUser);
        } catch (Exception e) {
            LOGGER.error("Could not send an email for user id: {}, reason: {}", user.getId(), e.getMessage());
        }

        return userMapper.toDto(createdUser);
    }

    /**
     * Update specific user information with a specified user update dto.
     * @param id User's ID.
     * @param dto Dto object containing the changes
     * @return Updated user dto.
     */
    public UserDto updateUser(Long id, UserUpdateDto dto) {
        LOGGER.info("Performing task to update user with id: {}", id);
        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isEmpty()) {
            LOGGER.warn("User with id {} from dto has not been found", id);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_NOT_FOUND);
        }

        User user = optionalUser.get();

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        userDao.save(user);

        LOGGER.info("Updated user with new firstName: {}, lastName: {}, email: {}",
                user.getFirstName(), user.getLastName(), user.getEmail());

        return userMapper.toDto(user);
    }

    /**
     * Remove user if user can be found, and if user is not uservist.
     * If user has no connection to the serviceKey provided then throw error.
     * @param id Users ID.
     */
    public void deleteUser(Long id) {
        LOGGER.info("Performing task to delete user with ID {}", id);
        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        userDao.delete(optionalUser.get());

        LOGGER.info("User deleted successfully");
    }
}
