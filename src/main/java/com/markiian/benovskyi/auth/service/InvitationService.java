package com.markiian.benovskyi.auth.service;

import com.google.common.hash.Hashing;
import com.markiian.benovskyi.auth.mapper.UserRoleMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.dao.UserServiceInvitationDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.persistance.model.UserServiceInvitation;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.UserServiceInvitationDto;
import com.markiian.benovskyi.model.UserServiceInvitationLinkDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class InvitationService {
    private final UserRoleMapper userRoleMapper;
    private final UserServiceInvitationDao invitationDao;
    private final UserDao userDao;
    private final ServiceDao serviceDao;

    private final Logger LOGGER = LoggerFactory.getLogger(InvitationService.class);

    @Autowired
    public InvitationService(UserServiceInvitationDao invitationDao, UserDao userDao, ServiceDao serviceDao, UserRoleMapper userRoleMapper) {
        this.invitationDao = invitationDao;
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.userRoleMapper = userRoleMapper;
    }

    public UserServiceInvitationLinkDto createInvitation(UserServiceInvitationDto invitationDto) {
        LOGGER.debug("Creating invitation for user {} and service {} with assigned role {}",
                invitationDto.getUsername(), invitationDto.getServiceKey(), invitationDto.getDesiredRole());

        Optional<User> user = userDao.findByUsername(invitationDto.getUsername());

        if (user.isEmpty()) {
            LOGGER.warn("User with username {} has not been found", invitationDto.getUsername());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.USER_NOT_FOUND);
        }

        Optional<Service> service = serviceDao.findByKey(invitationDto.getServiceKey());

        if (service.isEmpty()) {
            LOGGER.warn("Service with serviceKey {} has not been found", invitationDto.getServiceKey());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.SERVICE_NOT_FOUND);
        }

        String token = Hashing.sha512().hashString(UUID.randomUUID().toString(), Charset.defaultCharset()).toString();
        UserServiceInvitation invitation = new UserServiceInvitation()
                .withUser(user.get())
                .withService(service.get())
                .withToken(token)
                .withRole(userRoleMapper.toBase(invitationDto.getDesiredRole()));
        invitation = invitationDao.save(invitation);

        LOGGER.warn("Generated new invitation: {}", invitation);
        return new UserServiceInvitationLinkDto().link("http://127.0.0.1:9090/api/v1/invitations?token=" + token);
    }
}
