package com.markiian.benovskyi.auth.builder;

import com.markiian.benovskyi.auth.persistance.model.User;

import java.time.OffsetDateTime;

public final class TestUserBuilder {
    public static User buildUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setEmail("email." + id + "@user.com");
        user.setLastName("Last Name " + id);
        user.setFirstName("First Name " + id);
        user.setUsername("user" + id);
        user.setPassword("password" + id);
        user.setUpdatedAt(OffsetDateTime.now());
        user.setCreatedAt(OffsetDateTime.now());
        return user;
    }
}
