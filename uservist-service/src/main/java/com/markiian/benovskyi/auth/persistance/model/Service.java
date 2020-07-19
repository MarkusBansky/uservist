package com.markiian.benovskyi.auth.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "services", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"key"}),
        @UniqueConstraint(columnNames = {"name"})
})
public class Service {

    @Id
    @Column(name = "service_id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Service withServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service withName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Service withDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Service withKey(String key) {
        this.key = key;
        return this;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private Set<ServiceRole> serviceRoles;

    public Set<ServiceRole> getServiceRoles() {
        return serviceRoles;
    }

    public void setServiceRoles(Set<ServiceRole> serviceRoles) {
        this.serviceRoles = serviceRoles;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private List<UserServiceConnection> serviceConnections;

    public List<UserServiceConnection> getServiceConnections() {
        return serviceConnections;
    }

    public void setServiceConnections(List<UserServiceConnection> serviceConnections) {
        this.serviceConnections = serviceConnections;
    }

    public Service withServiceConnections(List<UserServiceConnection> serviceConnections) {
        this.serviceConnections = serviceConnections;
        return this;
    }

    @Column(updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(updatable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Service() {
    }
}