package com.fractal.orders.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document("item")
public class Item implements Serializable {

  
  private static final long serialVersionUID = 1L;
  @Field("item_id")
  private Long itemId;
  private String name;
  private Integer quantity;
  @Field("unit_price")
  private Double unitPrice;
  private Double cost;
}
