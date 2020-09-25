package com.markiian.benovskyi.auth.persistance.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "service_roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "service_id", "role"})
})
public class ServiceRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="service_id", nullable=false)
    private Service service;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}
