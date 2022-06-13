package com.fractal.orders.entity;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonId;
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
@Document("product")
public class Product implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @BsonId
  private String id;

  @Field("product_id")
  private Long productId;
  private String name;
  private String category;
  private Double price;
  private ProductStatus status;
}
