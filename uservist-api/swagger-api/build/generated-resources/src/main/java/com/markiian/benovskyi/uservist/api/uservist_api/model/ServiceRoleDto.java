package com.markiian.benovskyi.uservist.api.uservist_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.UserRoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Describes an object with service key and a list of roles for this service for a specific user.
 */
@ApiModel(description = "Describes an object with service key and a list of roles for this service for a specific user.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-09T13:06:45.607674+01:00[Europe/London]")

public class ServiceRoleDto   {
  @JsonProperty("service")
  private ServiceDto service;

  @JsonProperty("role")
  private UserRoleDto role;

  public ServiceRoleDto service(ServiceDto service) {
    this.service = service;
    return this;
  }

  /**
   * Get service
   * @return service
  */
  @ApiModelProperty(value = "")

  @Valid

  public ServiceDto getService() {
    return service;
  }

  public void setService(ServiceDto service) {
    this.service = service;
  }

  public ServiceRoleDto role(UserRoleDto role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserRoleDto getRole() {
    return role;
  }

  public void setRole(UserRoleDto role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceRoleDto serviceRoleDto = (ServiceRoleDto) o;
    return Objects.equals(this.service, serviceRoleDto.service) &&
        Objects.equals(this.role, serviceRoleDto.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(service, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceRoleDto {\n");
    
    sb.append("    service: ").append(toIndentedString(service)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

