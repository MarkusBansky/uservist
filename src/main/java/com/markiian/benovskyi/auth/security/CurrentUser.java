package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.model.UserAuthenticationDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.stream.Collectors;

public final class CurrentUser {
    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String getServiceKey() {
        UserAuthenticationDto details = (UserAuthenticationDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return details.getKey();
    }

    public static Collection<SimpleGrantedAuthority> getAuthorities() {
        return (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public static boolean hasRole(Role role) {
        String prefixed = "ROLE_" + role.getValue();
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                .contains(prefixed);
    }
}
