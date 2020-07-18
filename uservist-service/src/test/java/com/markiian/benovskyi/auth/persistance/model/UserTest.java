package com.markiian.benovskyi.auth.persistance.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void withUserId() {
        User user = new User()
                .withUserId(1L);
        assertEquals((Long) 1L, user.getUserId());
    }

    @Test
    void withFirstName() {
        final String NAME = "name";
        User user = new User()
                .withFirstName(NAME);
        assertEquals(NAME, user.getFirstName());
    }

    @Test
    void withLastName() {
        final String SURNAME = "surname";
        User user = new User()
                .withLastName(SURNAME);
        assertEquals(SURNAME, user.getLastName());
    }

    @Test
    void withUsername() {
        final String USERNAME = "username";
        User user = new User()
                .withUsername(USERNAME);
        assertEquals(USERNAME, user.getUsername());
    }

    @Test
    void withPasswordHash() {
        final String PASSWORD = "password";
        User user = new User()
                .withPasswordHash(PASSWORD);
        assertEquals(PASSWORD, user.getPasswordHash());
    }

    @Test
    void withEmail() {
        final String EMAIL = "email";
        User user = new User()
                .withEmail(EMAIL);
        assertEquals(EMAIL, user.getEmail());
    }
}