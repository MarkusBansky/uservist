package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.Session;
import com.markiian.benovskyi.auth.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDao extends JpaRepository<Session, Long> {
    public Optional<Session> findByUserAndServiceAndHardwareId(User user, Service service, String hardwareId);
}
