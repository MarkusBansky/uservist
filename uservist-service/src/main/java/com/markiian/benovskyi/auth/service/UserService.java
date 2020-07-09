package com.markiian.benovskyi.auth.service;

import com.google.common.hash.Hashing;
import com.markiian.benovskyi.auth.mapper.ServiceRoleMapper;
import com.markiian.benovskyi.auth.mapper.UserMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.persistance.model.UserServiceConnection;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.auth.util.PasswordUtil;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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

        Page<User> users = userDao.findAllUsersByServiceId(service.get().getServiceId(), pageRequest);
        LOGGER.info("Found {} users", users.getTotalElements());

        Page<UserDto> resultPage = new PageImpl(
                users.stream().map(user -> {
                    UserDto userDto = userMapper.toDto(user);
                    userDto.setServiceRoles(user
                            .getServiceRoles().parallelStream()
                            .filter(r -> r.getService().getKey().equals(serviceKey))
                            .map(serviceRoleMapper::toDto)
                            .collect(Collectors.toList()));
                    return userDto;
                }).collect(Collectors.toList()),
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
     * Method used by any admin to get user for their service.
     * User returns only those service roles that they have for the selected serviceKey.
     * @param userId User ID to search for.
     * @param serviceKey User's serviceKey.
     * @return UserDto if found.
     */
    public UserDto getUserById(Long userId, String serviceKey) {
        LOGGER.info("Performing task to get user by ID");
        Optional<User> user = userDao.findByUserId(userId);
        if (user.isEmpty()) {
            LOGGER.warn("User cannot be found with user ID {}", userId);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_NOT_FOUND);
        }

        UserDto userDto = userMapper.toDto(user.get());
        userDto.setServiceRoles(user.get()
                .getServiceRoles().parallelStream()
                .filter(r -> r.getService().getKey().equals(serviceKey))
                .map(serviceRoleMapper::toDto)
                .collect(Collectors.toList()));

        LOGGER.info("Received info about user with id {}: {}", userId, user.get());
        return userDto;
    }

    /**
     * Create a new user for the service, if such service exists, create a connection between user and the service
     * and create a default USER role on that service.
     * @param userDto User to create.
     * @return Created user object.
     */
    public UserDto createUserForService(UserDto userDto) throws UnsupportedEncodingException, CannotSendEmailException {
        if (userDto.getServiceRoles().size() > 1) {
            throw new HttpClientErrorException(HttpStatus.METHOD_NOT_ALLOWED,
                    ApplicationConstants.CREATE_USER_FOR_MULTIPLE_SERVICES);
        } else if (userDto.getServiceRoles().isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        String serviceKey = userDto.getServiceRoles().iterator().next().getService().getKey();

        LOGGER.info("Performing task to create new user for service: {} with dto: {}", serviceKey, userDto);
        Optional<User> optionalUser = userDao.findByUsername(userDto.getUsername());
        Optional<com.markiian.benovskyi.auth.persistance.model.Service> service = serviceDao.findByKey(serviceKey);

        if (service.isEmpty()) {
            LOGGER.warn("Service cannot be found with service key {}", serviceKey);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        if (optionalUser.isPresent()) {
            LOGGER.warn("User with ID {} cannot be found", userDto.getId());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_ALREADY_EXISTS);
        }

        User user = userMapper.toBase(userDto);
        String password = PasswordUtil.randomPassword();
        user.setPasswordHash(Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString());

        // create permission for current service
        UserServiceConnection connection = new UserServiceConnection()
                .withUser(user)
                .withService(service.get());
        user.getServiceConnections().add(connection);

        // create basic role
        ServiceRole role = new ServiceRole()
                .withUser(user)
                .withService(service.get())
                .withRole(Role.USER);
        user.getServiceRoles().add(role);


        // Send email
        LOGGER.info("Sending registration email to {}", user.getEmail());
        mailingService.sendRegistrationEmail(user, password);

        User createdUser = userDao.save(user);
        LOGGER.info("User created successfully, new user: {}", createdUser);
        return userMapper.toDto(userDto, createdUser);
    }


    public UserDto updateUser(UserDto userDto) {
        LOGGER.info("Performing task to update user with dto: {}", userDto);
        Optional<User> user = userDao.findByUserId(userDto.getId());

        if (user.isEmpty()) {
            LOGGER.warn("User with id {} from dto has not been found", userDto.getId());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_NOT_FOUND);
        }

        User updatedUser = userDao.save(userMapper.toBase(user.get(), userDto));

        LOGGER.info("Updated user, new user info: {}", updatedUser);
        return userMapper.toDto(updatedUser);
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
