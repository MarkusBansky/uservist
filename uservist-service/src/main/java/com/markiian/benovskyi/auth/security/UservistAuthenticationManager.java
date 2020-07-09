package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.model.UserAuthentication;
import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.util.RoleUtil;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
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

        UserAuthentication authDto = (UserAuthentication) auth.getDetails();

        if (username.equals("") || password.equals("") || authDto.getServiceKey().equals("")) {
            throw new AuthenticationCredentialsNotFoundException("Invalid login credentials");
        }

        Optional<User> user = userDao.findByUsername(username);

        if (user.isEmpty() || !user.get().getPasswordHash().equals(password)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user password");
        }

        Optional<Service> service = serviceDao.findByKey(authDto.getServiceKey());

        if (service.isEmpty()) {
            throw new AuthenticationServiceException("Invalid service key");
        }

        List<ServiceRole> roles = serviceRoleDao.findAllByUserAndService(user.get(), service.get());

        if (roles.size() == 0) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user, no access to this service");
        }

        //noinspection UnnecessaryLocalVariable
        UservistAuthenticationToken newAuthentication = new UservistAuthenticationToken(
                auth.getPrincipal(), "",
                authDto,
                RoleUtil.getAuthoritiesFromServiceRoles(roles));

        return newAuthentication;
    }
}
