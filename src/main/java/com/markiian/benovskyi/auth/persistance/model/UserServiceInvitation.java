package com.markiian.benovskyi.auth.persistance.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_service_invitations")
public class UserServiceInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserServiceInvitation withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserServiceInvitation withUser(User user) {
        this.user = user;
        return this;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public UserServiceInvitation withService(Service service) {
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

    public UserServiceInvitation withRole(Role role) {
        this.role = role;
        return this;
    }

    @Column(nullable = false)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserServiceInvitation withToken(String token) {
        this.token = token;
        return this;
    }

    @Column(nullable = false)
    private Boolean accepted;

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public UserServiceInvitation withAccepted(Boolean accepted) {
        this.accepted = accepted;
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

    public UserServiceInvitation withExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    @CreationTimestamp
    private OffsetDateTime createdAt;

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserServiceInvitation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserServiceInvitation that = (UserServiceInvitation) o;
        return Objects.equals(getId(), that.getId()) &&
                getUser().equals(that.getUser()) &&
                getService().equals(that.getService()) &&
                getRole() == that.getRole() &&
                getExpiresAt().equals(that.getExpiresAt()) &&
                getAccepted() == that.getAccepted() &&
                Objects.equals(getToken(), that.getToken()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getService(), getRole(), getToken(), getAccepted(), getExpiresAt(), getCreatedAt());
    }
}
