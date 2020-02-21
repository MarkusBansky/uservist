package com.markiian.benovskyi.auth.persistance.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_service_session")
public class Session {

    @Id
    @Column(name = "session_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Session withId(Long id) {
        this.sessionId = id;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session withUser(User user) {
        this.user = user;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Session withService(Service service) {
        this.service = service;
        return this;
    }

    @Column(nullable = false)
    private String hardwareId;

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Session withHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
        return this;
    }

    @Column(nullable = false)
    private OffsetDateTime expiresAt;

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Session withExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    @Column
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column
    @CreationTimestamp
    private OffsetDateTime createdAt;

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Session() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session that = (Session) o;
        return Objects.equals(getSessionId(), that.getSessionId()) &&
                getUser().equals(that.getUser()) &&
                getService().equals(that.getService()) &&
                getHardwareId().equals(that.getHardwareId()) &&
                getExpiresAt().equals(that.getExpiresAt()) &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionId(), getUser(), getService(), getHardwareId(), getExpiresAt(), getUpdatedAt(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + sessionId +
                ", user=" + user +
                ", service=" + service +
                ", hardwareId='" + hardwareId + '\'' +
                ", expiresAt=" + expiresAt +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
