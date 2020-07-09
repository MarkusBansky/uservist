package com.markiian.benovskyi.auth.service.misc;

import org.springframework.security.core.Authentication;

public interface IPermissionService<T> {

    default boolean canGetAll(Authentication auth) {
        return false;
    }

    default boolean canGetById(Long id, Authentication auth) {
        return false;
    }

    default boolean canCreate(T dto, Authentication auth) {
        return false;
    }

    default boolean canUpdate(T dto, Authentication auth) {
        return false;
    }

    default boolean canDelete(Long id, Authentication auth) {
        return false;
    }

}
