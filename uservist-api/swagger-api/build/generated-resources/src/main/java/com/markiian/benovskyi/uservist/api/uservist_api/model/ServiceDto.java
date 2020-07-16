package com.markiian.benovskyi.uservist.api.uservist_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Object representing each Service registered in the system. Each service can have unique users and unique user logins.
 */
@ApiModel(description = "Object representing each Service registered in the system. Each service can have unique users and unique user logins.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-16T13:34:50.527602+01:00[Europe/London]")

public class ServiceDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("key")
  private String key;

  @JsonProperty("createdAt")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @JsonProperty("updatedAt")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public ServiceDto id(Long id) {
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

  public ServiceDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The short name of this service to display on UI.
   * @return name
  */
  @ApiModelProperty(required = true, value = "The short name of this service to display on UI.")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * String describing this service and it's purpose.
   * @return description
  */
  @ApiModelProperty(required = true, value = "String describing this service and it's purpose.")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceDto key(String key) {
    this.key = key;
    return this;
  }

  /**
   * String key of SHA256, that is unique for each service. It is used to identify the service authentication request alongside user token.
   * @return key
  */
  @ApiModelProperty(required = true, value = "String key of SHA256, that is unique for each service. It is used to identify the service authentication request alongside user token.")
  @NotNull


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public ServiceDto createdAt(OffsetDateTime createdAt) {
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

  public ServiceDto updatedAt(OffsetDateTime updatedAt) {
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
    ServiceDto serviceDto = (ServiceDto) o;
    return Objects.equals(this.id, serviceDto.id) &&
        Objects.equals(this.name, serviceDto.name) &&
        Objects.equals(this.description, serviceDto.description) &&
        Objects.equals(this.key, serviceDto.key) &&
        Objects.equals(this.createdAt, serviceDto.createdAt) &&
        Objects.equals(this.updatedAt, serviceDto.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, key, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
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

