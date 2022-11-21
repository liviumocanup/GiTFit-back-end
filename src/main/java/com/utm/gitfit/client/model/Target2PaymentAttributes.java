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
 * Target2PaymentAttributes
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-11-21T15:13:42.040Z[GMT]")
public class Target2PaymentAttributes extends CommonPaymentAttributes implements PaymentAttributes {
  @SerializedName("currency_code")
  private String currencyCode = "EUR";

  @SerializedName("debtor_iban")
  private String debtorIban = null;

  @SerializedName("creditor_iban")
  private String creditorIban = null;

  public Target2PaymentAttributes currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

   /**
   * The currency of the payment.
   * @return currencyCode
  **/
  @Schema(required = true, description = "The currency of the payment.")
  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Target2PaymentAttributes debtorIban(String debtorIban) {
    this.debtorIban = debtorIban;
    return this;
  }

   /**
   * The debtor&#x27;s IBAN
   * @return debtorIban
  **/
  @Schema(description = "The debtor's IBAN")
  public String getDebtorIban() {
    return debtorIban;
  }

  public void setDebtorIban(String debtorIban) {
    this.debtorIban = debtorIban;
  }

  public Target2PaymentAttributes creditorIban(String creditorIban) {
    this.creditorIban = creditorIban;
    return this;
  }

   /**
   * The creditor&#x27;s IBAN
   * @return creditorIban
  **/
  @Schema(required = true, description = "The creditor's IBAN")
  public String getCreditorIban() {
    return creditorIban;
  }

  public void setCreditorIban(String creditorIban) {
    this.creditorIban = creditorIban;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Target2PaymentAttributes target2PaymentAttributes = (Target2PaymentAttributes) o;
    return Objects.equals(this.currencyCode, target2PaymentAttributes.currencyCode) &&
        Objects.equals(this.debtorIban, target2PaymentAttributes.debtorIban) &&
        Objects.equals(this.creditorIban, target2PaymentAttributes.creditorIban) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currencyCode, debtorIban, creditorIban, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Target2PaymentAttributes {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
    sb.append("    debtorIban: ").append(toIndentedString(debtorIban)).append("\n");
    sb.append("    creditorIban: ").append(toIndentedString(creditorIban)).append("\n");
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