package com.fractal.orders.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fractal.orders.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductId(Long productId);
}
