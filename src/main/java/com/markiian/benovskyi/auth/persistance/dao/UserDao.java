package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.persistance.model.UserServiceConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    List<User> findAllByServiceConnections(List<UserServiceConnection> connections);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByUsername(String username);
}
