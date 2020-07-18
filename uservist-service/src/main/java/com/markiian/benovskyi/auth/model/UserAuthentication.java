package com.markiian.benovskyi.auth.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class UserAuthentication {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAuthentication withUsername(String username) {
        this.username = username;
        return this;
    }

    private String hardwareId;

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public UserAuthentication withHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
        return this;
    }

    private String serviceKey;

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public UserAuthentication withServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    public UserAuthentication() {
    }

    public UserAuthentication(String username, String hardwareId, String serviceKey) {
        this.username = username;
        this.hardwareId = hardwareId;
        this.serviceKey = serviceKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthentication that = (UserAuthentication) o;
        return Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getHardwareId(), that.getHardwareId()) &&
                Objects.equals(getServiceKey(), that.getServiceKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getHardwareId(), getServiceKey());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("hardwareId", hardwareId)
                .add("serviceKey", serviceKey)
                .toString();
    }
}
