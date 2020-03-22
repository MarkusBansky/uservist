package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.UserServiceInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceInvitationDao extends JpaRepository<UserServiceInvitation, Long> {
    Optional<UserServiceInvitation> findByToken(String token);
}
