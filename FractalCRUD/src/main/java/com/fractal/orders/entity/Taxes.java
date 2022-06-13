package com.fractal.orders.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("taxes")

public class Taxes implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Double subtotal;
  private Double cityTax;
  private Double countyTax;
  private Double stateTax;
  private Double federalTax;
  private Double totalTax;
  private Double total;
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
  
}
