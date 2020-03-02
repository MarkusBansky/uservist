package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "select * from users.users u left join users.user_service_connections sc on u.user_id = sc.user_id where sc is not null and sc.service_id = :serviceId order by u.user_id --#pageable\\n")
    Page<User> findAllUsersByServiceId(Long serviceId, Pageable pageable);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByUsername(String username);
}
