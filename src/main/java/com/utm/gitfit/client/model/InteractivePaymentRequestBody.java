/*
 * Salt Edge Payment Initiation API
 * API Reference for services
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@saltedge.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.utm.gitfit.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * InteractivePaymentRequestBody
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-11-21T15:13:42.040Z[GMT]")
public class InteractivePaymentRequestBody {
  @SerializedName("data")
  private InteractivePaymentRequestBodyData data = null;

  public InteractivePaymentRequestBody data(InteractivePaymentRequestBodyData data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @Schema(required = true, description = "")
  public InteractivePaymentRequestBodyData getData() {
    return data;
  }

  public void setData(InteractivePaymentRequestBodyData data) {
    this.data = data;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InteractivePaymentRequestBody interactivePaymentRequestBody = (InteractivePaymentRequestBody) o;
    return Objects.equals(this.data, interactivePaymentRequestBody.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InteractivePaymentRequestBody {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}