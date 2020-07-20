package com.markiian.benovskyi.auth.service;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.markiian.benovskyi.auth.persistance.mapper.UserRoleMapper;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.dao.UserServiceInvitationDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.persistance.model.Invitation;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserServiceInvitationDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserServiceInvitationLinkDto;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
public class InvitationService {
    private final UserRoleMapper userRoleMapper;
    private final UserServiceInvitationDao invitationDao;
    private final UserDao userDao;
    private final ServiceDao serviceDao;

    public final EmailService emailService;

    private final Logger LOGGER = LoggerFactory.getLogger(InvitationService.class);

    @Value("${uservist.service.host}")
    private String host;

    @Value("${server.port}")
    private String port;

    @Autowired
    public InvitationService(UserServiceInvitationDao invitationDao, UserDao userDao, ServiceDao serviceDao, UserRoleMapper userRoleMapper, EmailService emailService) {
        this.invitationDao = invitationDao;
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.userRoleMapper = userRoleMapper;
        this.emailService = emailService;
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

        Optional<Invitation> existingInvitation = invitationDao.findByUserAndService(user.get(), service.get());

        if (existingInvitation.isPresent() && existingInvitation.get().getExpiresAt().isAfter(OffsetDateTime.now())) {
            LOGGER.warn("Invitation for user already exists");
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invitation already exists");
        }

        Invitation invitation = existingInvitation.orElseGet(Invitation::new);

        String token = Hashing.sha512().hashString(UUID.randomUUID().toString(), Charset.defaultCharset()).toString();
        invitation
                .withUser(user.get())
                .withService(service.get())
                .withToken(token)
                .withAccepted(false)
                .withExpiresAt(OffsetDateTime.now().plusDays(1))
                .withRole(userRoleMapper.toBase(invitationDto.getDesiredRole()));
        invitation = invitationDao.save(invitation);

        // Send invitation via mail server
        try {
            sendInvitationEmail(invitation);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        LOGGER.warn("Generated new invitation: {}", invitation);
        return new UserServiceInvitationLinkDto().link(host + ":" + port + "/api/v1/invitations?token=" + token);
    }

    public boolean acceptInvitation(String token) {
        LOGGER.debug("Trying to accept invitation with token {}", token);

        Optional<Invitation> invitation = invitationDao.findByToken(token);

        if (invitation.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ApplicationConstants.FORBIDDEN_EXCEPTION_MESSAGE);
        }

        return true;
    }

    public void sendInvitationEmail(Invitation invitation) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("no-reply@uservist.com", "Uservist No-Reply "))
                .to(Lists.newArrayList(new InternetAddress(invitation.getUser().getEmail(),
                        invitation.getUser().getFirstName() + " " + invitation.getUser().getLastName())))
                .subject("You are invited to service " + invitation.getService().getName())
                .body(String.format("YOu have been invited to service {} with user role {}.\n" +
                        "Please follow the link below: \n" +
                        host + ":" + port + "/api/v1/invitations?token=" + invitation.getToken(),
                        invitation.getService().getName(),
                        invitation.getRole().getValue()))
                .encoding("UTF-8").build();

        emailService.send(email);
    }
}
