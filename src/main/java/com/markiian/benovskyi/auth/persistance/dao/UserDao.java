package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);
    Optional<User> findByUsername(String username);
}
