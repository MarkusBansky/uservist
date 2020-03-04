package com.markiian.benovskyi.auth.persistance.model;

import com.google.common.base.MoreObjects;
import com.markiian.benovskyi.model.UserRoleDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "service_roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "service_id", "role"})
})
public class ServiceRole {

    @Id
    @Column(name = "service_role_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceRoleId;

    public Long getServiceRoleId() {
        return serviceRoleId;
    }

    public void setServiceRoleId(Long serviceRoleId) {
        this.serviceRoleId = serviceRoleId;
    }

    public ServiceRole withId(Long id) {
        this.serviceRoleId = id;
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

    public ServiceRole withUser(User user) {
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

    public ServiceRole withService(Service service) {
        this.service = service;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ServiceRole withRole(Role role) {
        this.role = role;
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

    public ServiceRole() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRole that = (ServiceRole) o;
        return Objects.equals(getServiceRoleId(), that.getServiceRoleId()) &&
                getUser().equals(that.getUser()) &&
                getService().equals(that.getService()) &&
                getRole() == that.getRole() &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceRoleId(), getUser(), getService(), getRole(), getUpdatedAt(), getCreatedAt());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("serviceRoleId", serviceRoleId)
                .add("user", user)
                .add("service", service)
                .add("role", role)
                .add("updatedAt", updatedAt)
                .add("createdAt", createdAt)
                .toString();
    }
}
