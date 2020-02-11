package com.markiian.benovskyi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.markiian.benovskyi.model.Service;
import com.markiian.benovskyi.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Describes user&#39;s roles for services.
 */
@ApiModel(description = "Describes user's roles for services.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-11T20:00:52.596177Z[Europe/London]")

public class UserServiceRole   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("user")
  private User user;

  @JsonProperty("service")
  private Service service;

  /**
   * Gets or Sets role
   */
  public enum RoleEnum {
    USER("USER"),
    
    MODER("MODER"),
    
    ADMIN("ADMIN");

    private String value;

    RoleEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoleEnum fromValue(String value) {
      for (RoleEnum b : RoleEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("role")
  private RoleEnum role;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt;

  public UserServiceRole id(Long id) {
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

  public UserServiceRole user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @ApiModelProperty(value = "")

  @Valid

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public UserServiceRole service(Service service) {
    this.service = service;
    return this;
  }

  /**
   * Get service
   * @return service
  */
  @ApiModelProperty(value = "")

  @Valid

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public UserServiceRole role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  */
  @ApiModelProperty(value = "")


  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public UserServiceRole createdAt(OffsetDateTime createdAt) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserServiceRole userServiceRole = (UserServiceRole) o;
    return Objects.equals(this.id, userServiceRole.id) &&
        Objects.equals(this.user, userServiceRole.user) &&
        Objects.equals(this.service, userServiceRole.service) &&
        Objects.equals(this.role, userServiceRole.role) &&
        Objects.equals(this.createdAt, userServiceRole.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, service, role, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserServiceRole {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    service: ").append(toIndentedString(service)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

