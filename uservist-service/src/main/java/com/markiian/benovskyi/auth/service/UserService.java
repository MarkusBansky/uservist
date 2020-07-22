package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.mapper.ServiceRoleMapper;
import com.markiian.benovskyi.auth.persistance.mapper.UserMapper;
import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserCreateDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserUpdateDto;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class UserService {
    private final UserDao userDao;
    private final ServiceDao serviceDao;

    private final UserMapper userMapper;
    private final ServiceRoleMapper serviceRoleMapper;

    private final MailingService mailingService;

    @SuppressWarnings("FieldCanBeLocal")
    private final int PAGE_SIZE = 10;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserDao userDao,
                       UserMapper userMapper,
                       ServiceDao serviceDao,
                       ServiceRoleMapper serviceRoleMapper,
                       MailingService mailingService) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.serviceDao = serviceDao;
        this.serviceRoleMapper = serviceRoleMapper;
        this.mailingService = mailingService;
    }

    /**
     * Get all users for specific service.
     * @param page The page of users to receive.
     * @param serviceKey The key for the service.
     * @return A page with users.
     */
    public Page<UserDto> getAllUsers(Integer page, String serviceKey) {
        LOGGER.info("Performing task to get all available users");
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Optional<com.markiian.benovskyi.auth.persistance.model.Service> service = serviceDao.findByKey(serviceKey);

        if (service.isEmpty()) {
            LOGGER.warn("Service for users request cannot be found, service key: {}", serviceKey);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        Page<User> users = userDao.findAllUsersByServiceId(service.get().getId(), pageRequest);
        LOGGER.info("Found {} users", users.getTotalElements());

        Page<UserDto> resultPage = new PageImpl(
                users.stream().map(userMapper::toDto).collect(Collectors.toList()),
                pageRequest,
                users.getTotalElements());

        LOGGER.info("Received all users service: {}; page: {}; users: {}", serviceKey, page, resultPage);
        return resultPage;
    }

    /**
     * Method used by super admin to get any user by their ID.
     * @param userId Users ID.
     * @return userDto if user was found.
     */
    public UserDto getUserById(Long userId) {
        LOGGER.info("Performing task to get all user by user ID {}", userId);
        Optional<User> user = userDao.findByUserId(userId);

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
    public UserDto createUserForService(UserCreateDto dto)
            throws UnsupportedEncodingException, CannotSendEmailException {
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

        // Send email
        LOGGER.info("Sending welcome email to new user email {}", user.getEmail());
        mailingService.sendRegistrationEmail(user);

        User createdUser = userDao.save(user);
        LOGGER.info("User created successfully, new user: {}", createdUser);
        return userMapper.toDto(user);
    }

    /**
     * Update specific user information with a specified user update dto.
     * @param id User's ID.
     * @param dto Dto object containing the changes
     * @return Updated user dto.
     */
    public UserDto updateUser(Long id, UserUpdateDto dto) {
        LOGGER.info("Performing task to update user with id: {}", id);
        Optional<User> optionalUser = userDao.findByUserId(id);

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
     * @return True if user deleted successfully.
     */
    public Boolean deleteUser(Long id) {
        LOGGER.info("Performing task to delete user with ID {}", id);
        userDao.deleteById(id);

        LOGGER.info("User deleted successfully");
        return true;
    }
}
