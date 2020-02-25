package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.mapper.UserMapper;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.model.UserDto;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final int PAGE_SIZE = 10;

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers(Integer page) {
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<User> users = userDao.findAll(pageRequest);
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public void deleteUser(Long id) throws NotFoundException {
        Optional<User> user = userDao.findByUserId(id);
        if (user.isEmpty() || user.get().getUsername().equals("uservist")) {
            throw new NotFoundException("User not found");
        }
    }

    public Optional<UserDto> getUserById(Long id) {
        Optional<User> user = userDao.findByUserId(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        LOGGER.info("User delete request private details: {}", details);

        return Optional.of(userMapper.toDto(user.get()));
    }

    public UserDto createNewUser(UserDto userDto) throws InstanceAlreadyExistsException {
        User user = userDao
                .findByUsername(userDto.getUsername())
                .orElseGet(User::new);

        if (user.getUserId() != null) {
            throw new InstanceAlreadyExistsException("User with this username already exists");
        }

        user = userMapper.toBase(user, userDto);
        userDao.save(user);

        return userMapper.toDto(userDto, user);
    }
}
