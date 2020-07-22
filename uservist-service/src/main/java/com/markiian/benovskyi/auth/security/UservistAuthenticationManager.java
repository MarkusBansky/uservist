package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.util.RoleUtil;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserAuthenticationDto;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UservistAuthenticationManager implements AuthenticationManager {

    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final ServiceRoleDao serviceRoleDao;

    public UservistAuthenticationManager(UserDao userDao, ServiceDao serviceDao, ServiceRoleDao serviceRoleDao) {
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.serviceRoleDao = serviceRoleDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            return authentication;
        }

        UservistAuthenticationToken auth = (UservistAuthenticationToken) authentication;
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        // First check if all properties are present
        UserAuthenticationDto authDto = (UserAuthenticationDto) auth.getDetails();

        if (username.equals("") || password.equals("") || authDto.getService().equals("")) {
            throw new AuthenticationCredentialsNotFoundException("Invalid authentication credentials");
        }

        // Find the user by username
        Optional<User> optionalUser = userDao.findByUsername(username);

        if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(password)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user password");
        }

        // Find the service by service unique key
        Optional<Service> optionalService = serviceDao.findByKey(authDto.getService());

        if (optionalService.isEmpty()) {
            throw new AuthenticationServiceException("Invalid service key");
        }

        // Find user role for this service
        Optional<ServiceRole> optionalServiceRole = serviceRoleDao
                .findByUserAndService(optionalUser.get(), optionalService.get());

        if (optionalServiceRole.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user, no access to this service");
        }

        if (optionalServiceRole.get().getRole().equals(RoleType.REVOKED)) {
            throw new AuthenticationServiceException("User access has been revoked");
        }

        //noinspection UnnecessaryLocalVariable
        UservistAuthenticationToken newAuthentication = new UservistAuthenticationToken(
                auth.getPrincipal(), "", authDto,
                RoleUtil.getAuthoritiesFromServiceRole(optionalServiceRole.get()));

        return newAuthentication;
    }
}
