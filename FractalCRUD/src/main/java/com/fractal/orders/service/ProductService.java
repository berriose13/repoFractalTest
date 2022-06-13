package com.fractal.orders.service;

import java.util.List;

import com.fractal.orders.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO productDTO);
    void editProduct(ProductDTO productDTO);
    void deleteProduct(Long productId);
}
