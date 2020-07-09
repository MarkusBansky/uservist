package com.markiian.benovskyi.uservist.api.uservist_api.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Roles enum assignable to users for specific services.
 */
public enum UserRoleDto {
  
  USER("USER"),
  
  MODER("MODER"),
  
  ADMIN("ADMIN");

  private String value;

  UserRoleDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static UserRoleDto fromValue(String value) {
    for (UserRoleDto b : UserRoleDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

