package com.markiian.benovskyi.auth.persistance.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "services", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"key"}),
        @UniqueConstraint(columnNames = {"name"})
})
public class Service {

    @Id
    @Column(name = "service_id", insertable = false)
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

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private Set<ServiceRole> serviceRoles;

    public Set<ServiceRole> getServiceRoles() {
        return serviceRoles;
    }

    public void setServiceRoles(Set<ServiceRole> serviceRoles) {
        this.serviceRoles = serviceRoles;
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

    @Column
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(getServiceId(), service.getServiceId()) &&
                getName().equals(service.getName()) &&
                getDescription().equals(service.getDescription()) &&
                getKey().equals(service.getKey()) &&
                Objects.equals(getCreatedAt(), service.getCreatedAt()) &&
                Objects.equals(getServiceRoles(), service.getServiceRoles()) &&
                Objects.equals(getUpdatedAt(), service.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId(), getServiceRoles(), getName(), getDescription(), getKey(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", key='" + key + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
