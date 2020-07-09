package com.markiian.benovskyi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body authentication request used to call the authenticate endpoint to log in user and create session.
 */
@ApiModel(description = "Body authentication request used to call the authenticate endpoint to log in user and create session.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-08T11:32:36.704640+01:00[Europe/London]")

public class UserAuthenticationDto   {
  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

  @JsonProperty("hardwareId")
  private String hardwareId;

  @JsonProperty("key")
  private String key;

  public UserAuthenticationDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * The user identifier as a unique username.
   * @return username
  */
  @ApiModelProperty(value = "The user identifier as a unique username.")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserAuthenticationDto password(String password) {
    this.password = password;
    return this;
  }

  /**
   * User's password hashed with SHA256.
   * @return password
  */
  @ApiModelProperty(value = "User's password hashed with SHA256.")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserAuthenticationDto hardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
    return this;
  }

  /**
   * A unique user identifier for this specific machine.
   * @return hardwareId
  */
  @ApiModelProperty(value = "A unique user identifier for this specific machine.")


  public String getHardwareId() {
    return hardwareId;
  }

  public void setHardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
  }

  public UserAuthenticationDto key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Service key identifier.
   * @return key
  */
  @ApiModelProperty(value = "Service key identifier.")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAuthenticationDto userAuthenticationDto = (UserAuthenticationDto) o;
    return Objects.equals(this.username, userAuthenticationDto.username) &&
        Objects.equals(this.password, userAuthenticationDto.password) &&
        Objects.equals(this.hardwareId, userAuthenticationDto.hardwareId) &&
        Objects.equals(this.key, userAuthenticationDto.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, hardwareId, key);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAuthenticationDto {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    hardwareId: ").append(toIndentedString(hardwareId)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
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

