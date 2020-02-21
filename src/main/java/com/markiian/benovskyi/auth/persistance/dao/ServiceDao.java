package com.markiian.benovskyi.auth.persistance.dao;

import com.markiian.benovskyi.auth.persistance.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceDao extends JpaRepository<Service, Long> {
    public Optional<Service> findByServiceId(Long serviceId);
}
