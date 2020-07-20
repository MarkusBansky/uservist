package com.markiian.benovskyi.auth.persistance.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleType {
    REVOKED("REVOKED"),
    USER("USER"),
    MODER("MODER"),
    ADMIN("ADMIN");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    @JsonCreator
    public static RoleType fromValue(String value) {
        RoleType[] var1 = values();
        for (RoleType b : var1) {
            if (b.value.equals(value)) {
                return b;
            }
        }

        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
