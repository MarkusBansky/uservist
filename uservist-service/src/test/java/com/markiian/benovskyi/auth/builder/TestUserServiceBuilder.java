package com.markiian.benovskyi.auth.builder;

import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;

import java.time.OffsetDateTime;

public final class TestUserServiceBuilder {
    public static ServiceRole connectUserService(User user, Service service, RoleType type) {
        ServiceRole serviceRole = new ServiceRole();
        serviceRole.setId(user.getId() + service.getId());
        serviceRole.setService(service);
        serviceRole.setUser(user);
        serviceRole.setUpdatedAt(OffsetDateTime.now());
        serviceRole.setCreatedAt(OffsetDateTime.now());
        serviceRole.setRole(type);

        user.getServiceRoles().add(serviceRole);
        service.getServiceRoles().add(serviceRole);

        return serviceRole;
    }
}
