package com.markiian.benovskyi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.markiian.benovskyi.model.UserServiceRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Defines user object, user can have diffirent permissions for diffirent roles. If user does not have a role then they do not have access to that service.
 */
@ApiModel(description = "Defines user object, user can have diffirent permissions for diffirent roles. If user does not have a role then they do not have access to that service.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-11T20:00:52.596177Z[Europe/London]")

public class User   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("username")
  private String username;

  @JsonProperty("passwordHash")
  private String passwordHash;

  @JsonProperty("roles")
  @Valid
  private List<UserServiceRole> roles = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt;

  @JsonProperty("updatedAt")
  private OffsetDateTime updatedAt;

  public User id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * User's unique identifier.
   * @return id
  */
  @ApiModelProperty(value = "User's unique identifier.")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * First name.
   * @return firstName
  */
  @ApiModelProperty(required = true, value = "First name.")
  @NotNull


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Last name.
   * @return lastName
  */
  @ApiModelProperty(required = true, value = "Last name.")
  @NotNull


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * The internal shorted nickname of the user.
   * @return username
  */
  @ApiModelProperty(required = true, value = "The internal shorted nickname of the user.")
  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User passwordHash(String passwordHash) {
    this.passwordHash = passwordHash;
    return this;
  }

  /**
   * Encrypted users pasword hash.
   * @return passwordHash
  */
  @ApiModelProperty(required = true, value = "Encrypted users pasword hash.")
  @NotNull


  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public User roles(List<UserServiceRole> roles) {
    this.roles = roles;
    return this;
  }

  public User addRolesItem(UserServiceRole rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
   * Users active roles in services.
   * @return roles
  */
  @ApiModelProperty(value = "Users active roles in services.")

  @Valid

  public List<UserServiceRole> getRoles() {
    return roles;
  }

  public void setRoles(List<UserServiceRole> roles) {
    this.roles = roles;
  }

  public User createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The date and time this user was created at.
   * @return createdAt
  */
  @ApiModelProperty(value = "The date and time this user was created at.")

  @Valid

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public User updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * The date and time when this record has been updated.
   * @return updatedAt
  */
  @ApiModelProperty(value = "The date and time when this record has been updated.")

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
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.lastName, user.lastName) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.passwordHash, user.passwordHash) &&
        Objects.equals(this.roles, user.roles) &&
        Objects.equals(this.createdAt, user.createdAt) &&
        Objects.equals(this.updatedAt, user.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, username, passwordHash, roles, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    passwordHash: ").append(toIndentedString(passwordHash)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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

