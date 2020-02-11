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
 * Object that is returned in case of any error happening on the server side. The code is HTTP code.
 */
@ApiModel(description = "Object that is returned in case of any error happening on the server side. The code is HTTP code.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-11T20:00:52.596177Z[Europe/London]")

public class GeneralError   {
  @JsonProperty("code")
  private Integer code;

  @JsonProperty("message")
  private String message;

  public GeneralError code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * The HTTP response code.
   * @return code
  */
  @ApiModelProperty(required = true, value = "The HTTP response code.")
  @NotNull


  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public GeneralError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Message describing the error.
   * @return message
  */
  @ApiModelProperty(required = true, value = "Message describing the error.")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeneralError generalError = (GeneralError) o;
    return Objects.equals(this.code, generalError.code) &&
        Objects.equals(this.message, generalError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeneralError {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

