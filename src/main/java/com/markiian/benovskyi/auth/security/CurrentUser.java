package com.markiian.benovskyi.auth.security;

import com.markiian.benovskyi.auth.persistance.model.Role;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.model.UserAuthenticationDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A helper class, used to get information about currently authenticated user for the request.
 */
public final class CurrentUser {

    /**
     * Checks if currently authenticated user is a service SUPER user.
     * @return True if it is equal to ApplicationConstants.SUPER_ADMIN_USERNAME.
     */
    public static boolean isSuper() {
        return SecurityContextHolder.getContext().getAuthentication().getName().equals(ApplicationConstants.SUPER_ADMIN_USERNAME);
    }

    /**
     * Extracts a username from the authentication.
     * @return A string representing authenticated user's username.
     */
    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Extracts service key from authentication.
     * @return A string representing service jey hash.
     */
    public static String getServiceKey() {
        UserAuthenticationDto details = (UserAuthenticationDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return details.getKey();
    }

    /**
     * Extracts users authorities.
     * @return A collection of SimpleGrantedAuthority's.
     */
    public static Collection<SimpleGrantedAuthority> getAuthorities() {
        return (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    /**
     * Checks if currently authenticated user has a specific role.
     * @param role Role to check.
     * @return True if user has ROLE_{role} authority.
     */
    public static boolean hasRole(Role role) {
        String prefixed = "ROLE_" + role.getValue();
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                .contains(prefixed);
    }
}
