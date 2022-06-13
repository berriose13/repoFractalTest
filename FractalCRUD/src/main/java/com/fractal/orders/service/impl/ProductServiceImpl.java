package com.fractal.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fractal.orders.converter.ProductConverter;
import com.fractal.orders.dto.ProductDTO;
import com.fractal.orders.entity.Product;
import com.fractal.orders.repository.ProductRepository;
import com.fractal.orders.service.ProductService;
@Service
public class ProductServiceImpl implements  ProductService{
  private ProductRepository productRepository;
  private ProductConverter productConverter;
  @Autowired
  public ProductServiceImpl(ProductRepository productRepository,ProductConverter productConverter) {
    this.productRepository=productRepository;
    this.productConverter=productConverter;
  }
  @Override
  public List<ProductDTO> getAllProducts() {
    return productConverter.toDtoList(productRepository.findAll());  
        
  }
  @Override
  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product=productConverter.toEntity(productDTO);
    product.setId(new Product().getId()); 
    return productConverter.toDTO(productRepository.save(product));   
    
  }



  @Override
  public void editProduct(ProductDTO productDTO) {
    Product productEntity=productRepository.findByProductId(productDTO.getProductId());
    Product product=productConverter.toEntity(productDTO);
    product.setId(productEntity.getId());
    productRepository.save(product);
  }

  @Override
  public void deleteProduct(Long productId) {
    Product product=productRepository.findByProductId(productId);
    productRepository.delete(product);
  }

}
