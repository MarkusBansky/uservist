package com.markiian.benovskyi.auth.persistance.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "services", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"key"}),
        @UniqueConstraint(columnNames = {"name"})
})
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String key;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "service")
    private Set<ServiceRole> serviceRoles = new HashSet<>();

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}
