package com.markiian.benovskyi.auth.persistance.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "event_log")
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="session_id", nullable=false)
    private Session session;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String message;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}
