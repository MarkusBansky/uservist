package com.markiian.benovskyi.auth.util;

import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class RoleUtil {

    private static final List<RoleType> SORTED_TYPES =
            List.of(RoleType.REVOKED, RoleType.USER, RoleType.MODER, RoleType.ADMIN);

    public static List<SimpleGrantedAuthority> getAuthoritiesFromServiceRole(ServiceRole serviceRole) {
        if (serviceRole == null) {
            return List.of();
        }

        return SORTED_TYPES.stream()
                .limit(getRoleLevel(serviceRole.getRole()))
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getValue()))
                .collect(Collectors.toList());
    }

    private static Integer getRoleLevel(RoleType role) {
        switch (role) {
            case REVOKED:
                return 1;
            case USER:
                return 2;
            case MODER:
                return 3;
            case ADMIN:
                return 4;
        }

        return 0;
    }
}
