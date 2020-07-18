package com.markiian.benovskyi.auth.service;

import com.markiian.benovskyi.auth.persistance.dao.ServiceDao;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.misc.IPermissionService;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesPermissionService implements IPermissionService<ServiceDto> {

    private final ServiceDao serviceDao;

    @Autowired
    public ServicesPermissionService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public boolean canGetAll(Authentication auth) {
        return CurrentUser.isSuper(auth);
    }

    @Override
    public boolean canGetById(Long id, Authentication auth) {
        if (CurrentUser.isSuper(auth)) {
            return true;
        }

        Optional<com.markiian.benovskyi.auth.persistance.model.Service> service = serviceDao.findByServiceId(id);

        return service.isPresent() && service.get().getKey().equals(CurrentUser.getServiceKey(auth));
    }

    @Override
    public boolean canCreate(ServiceDto dto, Authentication auth) {
        return CurrentUser.isSuper(auth);
    }

    @Override
    public boolean canUpdate(ServiceDto dto, Authentication auth) {
        return CurrentUser.isSuper(auth);
    }

    @Override
    public boolean canDelete(Long id, Authentication auth) {
        return CurrentUser.isSuper(auth);
    }
}
