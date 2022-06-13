
package com.fractal.orders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fractal.orders.dto.OrderDTO;
import com.fractal.orders.entity.Taxes;
import com.fractal.orders.dto.ItemDTO;

import com.fractal.orders.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/add")
  public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
    logger.info("START  [POST] /create-order: {}", orderDTO.toString());
    HttpStatus status = null;
    OrderDTO responseOrder = null;
    try {
      responseOrder = orderService.createOrder(orderDTO);
      status = HttpStatus.OK;
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in create order {}", e.getMessage());

    }
    logger.info("END [POST] /create-order");
    return new ResponseEntity<>(responseOrder, status);
  }

  @PatchMapping("items/{id}")
  public ResponseEntity<Void> addItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
    logger.info("START  [POST] /add-item: {}", itemDTO.toString());
    HttpStatus status = null;
    try {
      orderService.addItem(id, itemDTO);
      status = HttpStatus.OK;
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in add item {}", e.getMessage());

    }
    logger.info("END [POST] /add-item {}");

    return new ResponseEntity<>(status);
  }

  @DeleteMapping("{orderId}/items/{itemId}")
  public ResponseEntity<Void> deleteItem(@PathVariable String orderId, @PathVariable String itemId) {
    logger.info("START  [DELETE] /delete-item {}", orderId + " " + itemId);
    HttpStatus status = null;
    try {
      orderService.deleteItem(Long.valueOf(orderId), Long.valueOf(itemId));
      status = HttpStatus.OK;
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in add item {}", e.getMessage());

    }
    logger.info("END  [DELETE] /delete-item {}", orderId + " " + itemId);
    return new ResponseEntity<>(status);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
    logger.info("START  [DELETE] /delete-order {}", orderId);
    HttpStatus status = null;
    try {
      orderService.deleteOrder(Long.valueOf(orderId));
      status = HttpStatus.OK;
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in delete order {}", e.getMessage());

    }
    logger.info("END  [DELETE] /delete-order ");

    return new ResponseEntity<Void>(status);
  }

  @GetMapping("/taxes/{id}")
  public ResponseEntity<Taxes> getTaxes(@PathVariable Long id) {
    logger.info("START  [GET] /get-taxes: {}", String.valueOf(id));
    HttpStatus status = null;
    Taxes taxes = null;
    try {
      taxes = orderService.getTaxes(id);
      status = HttpStatus.OK;
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      logger.error("Exception ocurred in get taxes", e.getMessage());

    }
    logger.info("END  [GET] /get-taxes: {}", taxes.toString());
    return new ResponseEntity<Taxes>(taxes, status);
  }

  @GetMapping("/items/{orderId}")
  public List<ItemDTO> getItems(@PathVariable String orderId) {
    logger.info("START  [GET] /list-all-items: {}", orderId);
    List<ItemDTO> items = orderService.getItems(Long.valueOf(orderId));
    logger.info("END  [GET] /list-all-items: {}", items.toString());
    return items;

  }

  @GetMapping("/{orderId}")
  public OrderDTO getOrder(@PathVariable String orderId) {
    logger.info("START  [GET] /get-order: {}", orderId);
    OrderDTO order = orderService.getOrder(Long.valueOf(orderId));
    logger.info("END  [GET] /get-order: {}", order.toString());
    return order;

  }

  @GetMapping
  public List<OrderDTO> listAllOrders() {
    logger.info("START  [GET] /list-all-orders");

    List<OrderDTO> orders = orderService.getAllOrders();
    logger.info("END  [GET] /list-all-orders: {}", orders.toString());

    return orders;
  }

}
