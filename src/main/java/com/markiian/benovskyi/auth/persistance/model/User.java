package com.markiian.benovskyi.auth.persistance.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class User {

    @Id
    @Column(name = "user_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Column(nullable = false)
    private String firstName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    private String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    @OneToMany(mappedBy = "user")
    private List<ServiceRole> serviceRoles;

    public List<ServiceRole> getServiceRoles() {
        return serviceRoles;
    }

    public void setServiceRoles(List<ServiceRole> serviceRoles) {
        this.serviceRoles = serviceRoles;
    }

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @OneToMany(mappedBy = "user")
    private List<UserServiceConnection> serviceConnections;

    public List<UserServiceConnection> getServiceConnections() {
        return serviceConnections;
    }

    public void setServiceConnections(List<UserServiceConnection> serviceConnections) {
        this.serviceConnections = serviceConnections;
    }

    public User withServiceConnections(List<UserServiceConnection> serviceConnections) {
        this.serviceConnections = serviceConnections;
        return this;
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

    public User() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getUsername().equals(user.getUsername()) &&
                getPasswordHash().equals(user.getPasswordHash()) &&
                Objects.equals(getCreatedAt(), user.getCreatedAt()) &&
                Objects.equals(getSessions(), user.getSessions()) &&
                Objects.equals(getServiceRoles(), user.getServiceRoles()) &&
                Objects.equals(getServiceConnections(), user.getServiceConnections()) &&
                Objects.equals(getUpdatedAt(), user.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getSessions(), getServiceConnections(), getServiceRoles(), getFirstName(), getLastName(), getUsername(), getPasswordHash(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
