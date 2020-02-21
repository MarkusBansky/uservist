package com.markiian.benovskyi.auth.persistance.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void withUserId() {
        User user = new User()
                .withUserId(1L);
        assertEquals(1L, user.getUserId());
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
    void testEquals() {
        User user1 = new User()
                .withUserId(1L)
                .withFirstName("first")
                .withLastName("last")
                .withPasswordHash("pass")
                .withUsername("username");

        User user2 = new User()
                .withUserId(1L)
                .withFirstName("first")
                .withLastName("last")
                .withPasswordHash("pass")
                .withUsername("username");

        assertEquals(user1, user2);
    }
}