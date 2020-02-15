package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query
    Optional<User> findById();
}
