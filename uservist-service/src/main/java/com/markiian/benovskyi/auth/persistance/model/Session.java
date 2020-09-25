package com.markiian.benovskyi.auth.persistance.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_service_session", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "service_id", "browser", "ipAddress"})
})
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="service_id", nullable=false)
    private Service service;

    @Column(nullable = false)
    private String browser;

    @Column(nullable = false)
    private String ipAddress;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "session")
    private Set<EventLog> eventLogs = new HashSet<>();

    @Column(name = "expired_at", nullable = false)
    private OffsetDateTime expiresAt;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}
