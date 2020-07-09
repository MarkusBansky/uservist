package com.markiian.benovskyi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.markiian.benovskyi.model.UserRoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Object being sent to API endpoint to send an email with an invitation link to invite a specific user.
 */
@ApiModel(description = "Object being sent to API endpoint to send an email with an invitation link to invite a specific user.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-08T11:32:36.704640+01:00[Europe/London]")

public class UserServiceInvitationDto   {
  @JsonProperty("username")
  private String username;

  @JsonProperty("serviceKey")
  private String serviceKey;

  @JsonProperty("desiredRole")
  private UserRoleDto desiredRole;

  public UserServiceInvitationDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Name of the user.
   * @return username
  */
  @ApiModelProperty(required = true, value = "Name of the user.")
  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserServiceInvitationDto serviceKey(String serviceKey) {
    this.serviceKey = serviceKey;
    return this;
  }

  /**
   * Key identifier of the service.
   * @return serviceKey
  */
  @ApiModelProperty(required = true, value = "Key identifier of the service.")
  @NotNull


  public String getServiceKey() {
    return serviceKey;
  }

  public void setServiceKey(String serviceKey) {
    this.serviceKey = serviceKey;
  }

  public UserServiceInvitationDto desiredRole(UserRoleDto desiredRole) {
    this.desiredRole = desiredRole;
    return this;
  }

  /**
   * Get desiredRole
   * @return desiredRole
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserRoleDto getDesiredRole() {
    return desiredRole;
  }

  public void setDesiredRole(UserRoleDto desiredRole) {
    this.desiredRole = desiredRole;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserServiceInvitationDto userServiceInvitationDto = (UserServiceInvitationDto) o;
    return Objects.equals(this.username, userServiceInvitationDto.username) &&
        Objects.equals(this.serviceKey, userServiceInvitationDto.serviceKey) &&
        Objects.equals(this.desiredRole, userServiceInvitationDto.desiredRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, serviceKey, desiredRole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserServiceInvitationDto {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    serviceKey: ").append(toIndentedString(serviceKey)).append("\n");
    sb.append("    desiredRole: ").append(toIndentedString(desiredRole)).append("\n");
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

