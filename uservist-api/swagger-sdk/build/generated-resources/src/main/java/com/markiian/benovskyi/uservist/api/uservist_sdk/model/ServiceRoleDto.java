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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.ServiceDto;
import com.markiian.benovskyi.uservist.api.uservist_sdk.model.UserRoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Describes an object with service key and a list of roles for this service for a specific user.
 */
@ApiModel(description = "Describes an object with service key and a list of roles for this service for a specific user.")
@JsonPropertyOrder({
  ServiceRoleDto.JSON_PROPERTY_SERVICE,
  ServiceRoleDto.JSON_PROPERTY_ROLE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-07-09T13:06:45.626587+01:00[Europe/London]")
public class ServiceRoleDto {
  public static final String JSON_PROPERTY_SERVICE = "service";
  private ServiceDto service;

  public static final String JSON_PROPERTY_ROLE = "role";
  private UserRoleDto role;


  public ServiceRoleDto service(ServiceDto service) {
    
    this.service = service;
    return this;
  }

   /**
   * Get service
   * @return service
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SERVICE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

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
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ROLE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

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

