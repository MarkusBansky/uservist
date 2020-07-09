package com.markiian.benovskyi.uservist.api.uservist_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Describes a unique user logged session object. This is unique to user, service, hardwareId.
 */
@ApiModel(description = "Describes a unique user logged session object. This is unique to user, service, hardwareId.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-09T13:06:45.607674+01:00[Europe/London]")

public class SessionDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("user")
  private UserDto user;

  @JsonProperty("service")
  private ServiceDto service;

  @JsonProperty("hardwareId")
  private String hardwareId;

  @JsonProperty("token")
  private String token;

  @JsonProperty("createdAt")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @JsonProperty("updatedAt")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public SessionDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier.
   * @return id
  */
  @ApiModelProperty(value = "Unique identifier.")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SessionDto user(UserDto user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public SessionDto service(ServiceDto service) {
    this.service = service;
    return this;
  }

  /**
   * Get service
   * @return service
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public ServiceDto getService() {
    return service;
  }

  public void setService(ServiceDto service) {
    this.service = service;
  }

  public SessionDto hardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
    return this;
  }

  /**
   * Unique hardware identifier.
   * @return hardwareId
  */
  @ApiModelProperty(required = true, value = "Unique hardware identifier.")
  @NotNull


  public String getHardwareId() {
    return hardwareId;
  }

  public void setHardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
  }

  public SessionDto token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Generated token.
   * @return token
  */
  @ApiModelProperty(value = "Generated token.")


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public SessionDto createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public SessionDto updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionDto sessionDto = (SessionDto) o;
    return Objects.equals(this.id, sessionDto.id) &&
        Objects.equals(this.user, sessionDto.user) &&
        Objects.equals(this.service, sessionDto.service) &&
        Objects.equals(this.hardwareId, sessionDto.hardwareId) &&
        Objects.equals(this.token, sessionDto.token) &&
        Objects.equals(this.createdAt, sessionDto.createdAt) &&
        Objects.equals(this.updatedAt, sessionDto.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, service, hardwareId, token, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    service: ").append(toIndentedString(service)).append("\n");
    sb.append("    hardwareId: ").append(toIndentedString(hardwareId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

