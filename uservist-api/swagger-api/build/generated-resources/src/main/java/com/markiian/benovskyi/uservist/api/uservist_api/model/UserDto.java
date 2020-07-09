package com.markiian.benovskyi.uservist.api.uservist_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceRoleDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-09T13:06:45.607674+01:00[Europe/London]")

public class UserDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

  @JsonProperty("email")
  private String email;

  @JsonProperty("serviceRoles")
  @Valid
  private List<ServiceRoleDto> serviceRoles = null;

  @JsonProperty("createdAt")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @JsonProperty("updatedAt")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public UserDto id(Long id) {
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

  public UserDto firstName(String firstName) {
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

  public UserDto lastName(String lastName) {
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

  public UserDto username(String username) {
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

  public UserDto password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Encrypted users pasword hash.
   * @return password
  */
  @ApiModelProperty(value = "Encrypted users pasword hash.")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Email.
   * @return email
  */
  @ApiModelProperty(value = "Email.")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserDto serviceRoles(List<ServiceRoleDto> serviceRoles) {
    this.serviceRoles = serviceRoles;
    return this;
  }

  public UserDto addServiceRolesItem(ServiceRoleDto serviceRolesItem) {
    if (this.serviceRoles == null) {
      this.serviceRoles = new ArrayList<>();
    }
    this.serviceRoles.add(serviceRolesItem);
    return this;
  }

  /**
   * User roles for services.
   * @return serviceRoles
  */
  @ApiModelProperty(value = "User roles for services.")

  @Valid

  public List<ServiceRoleDto> getServiceRoles() {
    return serviceRoles;
  }

  public void setServiceRoles(List<ServiceRoleDto> serviceRoles) {
    this.serviceRoles = serviceRoles;
  }

  public UserDto createdAt(OffsetDateTime createdAt) {
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

  public UserDto updatedAt(OffsetDateTime updatedAt) {
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
    UserDto userDto = (UserDto) o;
    return Objects.equals(this.id, userDto.id) &&
        Objects.equals(this.firstName, userDto.firstName) &&
        Objects.equals(this.lastName, userDto.lastName) &&
        Objects.equals(this.username, userDto.username) &&
        Objects.equals(this.password, userDto.password) &&
        Objects.equals(this.email, userDto.email) &&
        Objects.equals(this.serviceRoles, userDto.serviceRoles) &&
        Objects.equals(this.createdAt, userDto.createdAt) &&
        Objects.equals(this.updatedAt, userDto.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, username, password, email, serviceRoles, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    serviceRoles: ").append(toIndentedString(serviceRoles)).append("\n");
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

