package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.mapper.UserMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.dao.UserServiceConnectionDao;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.persistance.model.UserServiceConnection;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.UserDto;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final ServiceDao serviceDao;
    private final UserServiceConnectionDao userServiceConnectionDao;

    private final int PAGE_SIZE = 10;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserDao userDao, UserMapper userMapper, UserServiceConnectionDao userServiceConnectionDao, ServiceDao serviceDao) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.userServiceConnectionDao = userServiceConnectionDao;
        this.serviceDao = serviceDao;
    }

    public Boolean deleteUser(Long id) throws NotFoundException {
        Optional<User> user = userDao.findByUserId(id);
        if (user.isEmpty() || user.get().getUsername().equals("uservist")) {
            throw new NotFoundException("User not found");
        }

        return true;
    }

    /**
     * Method used by super admin to get any user by their ID.
     * @param userId Users ID.
     * @return userDto if user was found.
     * @throws NotFoundException Exception if user cannot be found.
     */
    public UserDto getUserById(Long userId) throws NotFoundException {
        Optional<User> user = userDao.findByUserId(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        LOGGER.debug("Received info about user with id {}: {}", userId, user.get());
        return userMapper.toDto(user.get());
    }

    /**
     * Method used by any admin to get user for their service.
     * User returns only those service roles that they have for the selected serviceKey.
     * @param userId User ID to search for.
     * @param serviceKey User's serviceKey.
     * @return UserDto if found.
     * @throws NotFoundException Exception if user not found or user has no access to this service.
     */
    public UserDto getUserById(Long userId, String serviceKey) throws NotFoundException {
        Optional<User> user = userDao.findByUserId(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        // check if found user has access to this service
        if (user.get().getServiceConnections().stream().noneMatch(a -> a.getService().getKey().equals(serviceKey))) {
            throw new InternalError(ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
        }

        user.get().setServiceRoles(user.get()
                .getServiceRoles().parallelStream()
                .filter(r -> r.getService().getKey().equals(serviceKey))
                .collect(Collectors.toList()));

        LOGGER.debug("Received info about user with id {}: {}", userId, user.get());
        return userMapper.toDto(user.get());
    }

    /**
     * Get all users for specific service.
     * @param page The page of users to receive.
     * @param serviceKey The key for the service.
     * @return A page with users.
     * @throws NotFoundException Any exception if there are no users found.
     */
    public Page<UserDto> getAllUsers(Integer page, String serviceKey) throws NotFoundException {
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Optional<com.markiian.benovskyi.auth.persistance.model.Service> service = serviceDao.findByKey(serviceKey);

        if (service.isEmpty()) {
            throw new NotFoundException("Service could not be found");
        }

        Page<User> users = userDao.findAllUsersByServiceId(service.get().getServiceId(), pageRequest);

        Page<UserDto> resultPage = new PageImpl(
                users.stream().map(userMapper::toDto).collect(Collectors.toList()),
                pageRequest,
                users.getTotalElements());

        LOGGER.debug("Received all users service: {}; page: {}; users: {}", serviceKey, page, resultPage);
        return resultPage;
    }

    public UserDto createNewUserForService(UserDto userDto, String serviceKey) throws InstanceAlreadyExistsException {
        User user = userDao
                .findByUsername(userDto.getUsername())
                .orElseGet(User::new);

        if (user.getUserId() != null) {
            throw new InstanceAlreadyExistsException("User with this username already exists");
        }

        user = userMapper.toBase(user, userDto);
        user = userDao.save(user);

        LOGGER.debug("Registered new user: {}", user);

        // TODO: Create user basic permissions for this service

        return userMapper.toDto(userDto, user);
    }

}
