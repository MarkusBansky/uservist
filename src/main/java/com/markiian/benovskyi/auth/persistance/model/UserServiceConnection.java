package com.markiian.benovskyi.auth.persistance.model;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_service_connections", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "service_id"})
})
public class UserServiceConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserServiceConnection withId(Long id) {
        this.id = id;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserServiceConnection withUser(User user) {
        this.user = user;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public UserServiceConnection withService(Service service) {
        this.service = service;
        return this;
    }

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CreationTimestamp
    private OffsetDateTime createdAt;

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserServiceConnection() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserServiceConnection that = (UserServiceConnection) o;
        return Objects.equals(getId(), that.getId()) &&
                getUser().equals(that.getUser()) &&
                getService().equals(that.getService()) &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getService(), getUpdatedAt(), getCreatedAt());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("service", service)
                .add("updatedAt", updatedAt)
                .add("createdAt", createdAt)
                .toString();
    }
}
