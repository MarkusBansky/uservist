package com.markiian.benovskyi.auth.util;

import com.markiian.benovskyi.auth.builder.TestServiceBuilder;
import com.markiian.benovskyi.auth.builder.TestUserBuilder;
import com.markiian.benovskyi.auth.builder.TestUserServiceBuilder;
import com.markiian.benovskyi.auth.persistance.model.RoleType;
import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleUtilTest {

    Service testService;
    User testUser;

    @BeforeEach
    void before() {
        testService = TestServiceBuilder.buildService(1L);
        testUser = TestUserBuilder.buildUser(1L);
        TestUserServiceBuilder.connectUserService(testUser, testService, RoleType.USER);
    }

    @Test
    void getAuthoritiesFromServiceRole_Null() {
        List<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRole(null);

        assertEquals(0, authorities.size());
    }

    @Test
    void getAuthoritiesFromServiceRole_Revoked() {
        ServiceRole role = testUser.getServiceRoles().iterator().next();
        role.setRole(RoleType.REVOKED);

        List<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRole(role);

        assertEquals(1, authorities.size());
        assertEquals("ROLE_REVOKED", authorities.get(0).getAuthority());
    }

    @Test
    void getAuthoritiesFromServiceRole_User() {
        ServiceRole role = testUser.getServiceRoles().iterator().next();
        role.setRole(RoleType.USER);

        List<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRole(role);

        assertEquals(2, authorities.size());
        assertEquals("ROLE_REVOKED", authorities.get(0).getAuthority());
        assertEquals("ROLE_USER", authorities.get(1).getAuthority());
    }

    @Test
    void getAuthoritiesFromServiceRole_Moder() {
        ServiceRole role = testUser.getServiceRoles().iterator().next();
        role.setRole(RoleType.MODER);

        List<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRole(role);

        assertEquals(3, authorities.size());
        assertEquals("ROLE_REVOKED", authorities.get(0).getAuthority());
        assertEquals("ROLE_USER", authorities.get(1).getAuthority());
        assertEquals("ROLE_MODER", authorities.get(2).getAuthority());
    }

    @Test
    void getAuthoritiesFromServiceRole_Admin() {
        ServiceRole role = testUser.getServiceRoles().iterator().next();
        role.setRole(RoleType.ADMIN);

        List<SimpleGrantedAuthority> authorities = RoleUtil.getAuthoritiesFromServiceRole(role);

        assertEquals(4, authorities.size());
        assertEquals("ROLE_REVOKED", authorities.get(0).getAuthority());
        assertEquals("ROLE_USER", authorities.get(1).getAuthority());
        assertEquals("ROLE_MODER", authorities.get(2).getAuthority());
        assertEquals("ROLE_ADMIN", authorities.get(3).getAuthority());
    }
}