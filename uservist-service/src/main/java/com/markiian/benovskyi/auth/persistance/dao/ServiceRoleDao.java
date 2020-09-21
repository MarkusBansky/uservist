package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.Service;
import com.markiian.benovskyi.auth.persistance.model.ServiceRole;
import com.markiian.benovskyi.auth.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRoleDao extends JpaRepository<ServiceRole, Long> {
    List<ServiceRole> findAllByUser(User user);
    Optional<ServiceRole> findByUserAndService(User user, Service service);
}
