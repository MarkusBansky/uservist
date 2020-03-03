package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceRoleDao;
import com.markiian.benovskyi.auth.persistance.dao.UserDao;
import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.misc.IPermissionService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPermissionService implements IPermissionService<UserDto> {
    private final UserDao userDao;
    private final ServiceRoleDao serviceRoleDao;

    @Autowired
    public UserPermissionService(UserDao userDao, ServiceRoleDao serviceRoleDao) {
        this.userDao = userDao;
        this.serviceRoleDao = serviceRoleDao;
    }

    public boolean canGetAll(String service, Authentication auth) {
        return CurrentUser.isSuper() || service.equals(CurrentUser.getServiceKey());
    }

    @Override
    public boolean canGetById(Long id, Authentication auth) {
        if (CurrentUser.isSuper()) {
            return true;
        }

        Optional<User> user = userDao.findByUserId(id);

        return user.isPresent() && user.get()
                .getServiceConnections().parallelStream()
                .anyMatch(con -> con.getService().getKey().equals(CurrentUser.getServiceKey(auth)));
    }

    public boolean canCreate(String service, Authentication auth) {
        return CurrentUser.isSuper(auth) || service.equals(CurrentUser.getServiceKey(auth));
    }

    @Override
    public boolean canUpdate(UserDto dto, Authentication auth) {
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

    @Override
    public boolean canDelete(Long id, Authentication auth) {
        Optional<User> user = userDao.findByUserId(id);
        if (user.isEmpty() || user.get().getUsername().equals(ApplicationConstants.SUPER_ADMIN_USERNAME)) {
            return false;
        }

        return user.get()
                .getServiceConnections().parallelStream()
                .anyMatch(con -> con.getService().getKey().equals(CurrentUser.getServiceKey(auth)));
    }
}
