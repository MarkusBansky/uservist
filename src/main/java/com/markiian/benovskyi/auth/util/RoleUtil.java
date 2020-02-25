package com.markiian.benovskyi.auth.util;

import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class RoleUtil {

    public static List<GrantedAuthority> getAuthoritiesFromString(String roleString) {
        return Arrays
                .stream(roleString.split(","))
                .map(s -> "ROLE_" + s)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static List<SimpleGrantedAuthority> getAuthoritiesFromServiceRoles(List<ServiceRole> roles) {
        if (roles.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole().getValue()))
                .collect(Collectors.toList());
    }

}
