package com.markiian.benovskyi.auth.persistance.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_service_session", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "service_id", "browser", "ipAddress"})
})
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Service service;

    @Column(nullable = false)
    private String browser;

    @Column(nullable = false)
    private String ipAddress;

    @OneToMany
    private Set<EventLog> eventLogs = new HashSet<>();

    @Column(nullable = false)
    private OffsetDateTime expiresAt;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Set<EventLog> getEventLogs() {
        return eventLogs;
    }

    public void setEventLogs(Set<EventLog> eventLogs) {
        this.eventLogs = eventLogs;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
