/*
 * Uservist Service API
 * Defines the API enpoints and objects for authentication service. Describes the REST service architecture and models.
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: admin@markiian-benovskyi.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.markiian.benovskyi.uservist.api.uservist_sdk.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

