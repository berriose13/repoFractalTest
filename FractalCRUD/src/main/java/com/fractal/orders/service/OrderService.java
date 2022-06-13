package com.fractal.orders.service;

import java.util.List;

import com.fractal.orders.dto.ItemDTO;
import com.fractal.orders.dto.OrderDTO;
import com.fractal.orders.entity.Item;
import com.fractal.orders.entity.Taxes;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    List<ItemDTO> addItem(Long orderId,ItemDTO itemDto);
    void deleteOrder(Long orderId);
    Taxes getTaxes(Long orderId);
    OrderDTO getOrder(Long orderId);
    void deleteItem(Long orderId,Long itemId);
    List<ItemDTO> getItems(Long orderId);
}
