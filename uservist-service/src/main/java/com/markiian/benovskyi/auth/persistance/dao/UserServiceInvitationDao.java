package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.User;
import com.markiian.benovskyi.auth.persistance.model.UserServiceInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceInvitationDao extends JpaRepository<UserServiceInvitation, Long> {
    Optional<UserServiceInvitation> findByUserAndService(User user, Service service);
    Optional<UserServiceInvitation> findByToken(String token);
}
