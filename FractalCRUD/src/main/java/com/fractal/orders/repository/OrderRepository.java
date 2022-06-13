package com.fractal.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fractal.orders.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
   Order findByOrderId(Long orderId);
}
