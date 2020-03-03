package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.dao.UserServiceConnectionDao;
import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class UserPermissionService {
    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final ServiceRoleDao serviceRoleDao;
    private final UserServiceConnectionDao connectionDao;

    @Autowired
    public UserPermissionService(UserDao userDao, ServiceDao serviceDao, ServiceRoleDao serviceRoleDao, UserServiceConnectionDao connectionDao) {
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.serviceRoleDao = serviceRoleDao;
        this.connectionDao = connectionDao;
    }

    public boolean canGetAllUsers(String service, Authentication auth) {
        if (CurrentUser.isSuper() || service.equals(CurrentUser.getServiceKey())) {
            return true;
        }
        return false;
    }

    public boolean canCreateUser(String service, Authentication auth) {
        if (CurrentUser.isSuper(auth) || service.equals(CurrentUser.getServiceKey(auth))) {
            return true;
        }
        return false;
    }

    public boolean canUpdateUser(UserDto dto, Authentication auth) {
        Optional<User> currentUser = userDao.findByUsername(auth.getName());

        if (currentUser.isEmpty()) {
            return false;
        }

        if (currentUser.get().getUserId().equals(dto.getId())) {
            return true;
        }

        if (CurrentUser.isSuper(auth)) {
            return true;
        }

        Optional<User> updateUser = userDao.findByUserId(dto.getId());

        if (updateUser.isEmpty()) {
            return false;
        }

        return serviceRoleDao.findAllByUser(currentUser.get()).parallelStream()
                .anyMatch(r -> r.getService().getKey().equals(CurrentUser.getServiceKey(auth))
                        && r.getRole().getValue().equals(Role.ADMIN.getValue()));
    }

    public boolean canDeleteUser(Long id, Authentication auth) {
        Optional<User> user = userDao.findByUserId(id);
        if (user.isEmpty() || user.get().getUsername().equals(ApplicationConstants.SUPER_ADMIN_USERNAME)) {
            return false;
        }


        return user.get()
                .getServiceConnections().parallelStream()
                .anyMatch(con -> con.getService().getKey().equals(CurrentUser.getServiceKey(auth)));
    }
}
