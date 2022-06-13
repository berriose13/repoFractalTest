package com.fractal.orders.converter;

import org.springframework.stereotype.Service;

import com.fractal.orders.dto.ProductDTO;
import com.fractal.orders.entity.Product;
import com.fractal.orders.entity.ProductStatus;

@Service
public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

  @Override
  public Product toEntity(ProductDTO dto) { 
    // TODO Auto-generated method stub
    return Product.builder().productId(dto.getProductId()).category(dto.getCategory()).name(dto.getName()).price(dto.getPrice())
        .status(ProductStatus.valueOf(dto.getStatus().toUpperCase())).build();
  }

  @Override
  public ProductDTO toDTO(Product entity) {
    return ProductDTO.builder().productId(entity.getProductId()).category(entity.getCategory()).name(entity.getName()).price(entity.getPrice())
        .status(entity.getStatus().toString()).build();
  }

}
