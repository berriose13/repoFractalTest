package com.fractal.orders.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Document("order")
public class Order implements Serializable{
  /**
   * 
   */
  
  private static final long serialVersionUID = 1L;
  @BsonId
  private String id;
  @Field("order_id")
  private Long orderId;
  private String consumer;
  private OrderStatus status;
  private Date date;
  private Double total;
  private List<Item> items;
  private Taxes taxes;
}
