package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.model.UserAuthenticationDto;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
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

        UserAuthenticationDto authDto = (UserAuthenticationDto) auth.getDetails();

        if (username.equals("") || password.equals("") || authDto.getKey().equals("")) {
            throw new AuthenticationCredentialsNotFoundException("Invalid login credentials");
        }

        Optional<User> user = userDao.findByUsername(username);

        if (user.isEmpty() || !user.get().getPasswordHash().equals(password)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user password");
        }

        Optional<Service> service = serviceDao.findByKey(authDto.getKey());

        if (service.isEmpty()) {
            throw new AuthenticationServiceException("Invalid service key");
        }

        Optional<ServiceRole> role = serviceRoleDao.findByUserAndService(user.get(), service.get());

        if (role.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Invalid user, no access to this service");
        }

        //noinspection UnnecessaryLocalVariable
        UservistAuthenticationToken newAuthentication = new UservistAuthenticationToken(
                auth.getPrincipal(), "",
                ((UserAuthenticationDto) auth.getDetails()).password(""),
                AuthorityUtils.createAuthorityList(role.get().getRole().getValue())
        );

        return newAuthentication;
    }
}
