package com.fractal.orders.converter;

import org.springframework.stereotype.Service;

import com.fractal.orders.dto.OrderDTO;
import com.fractal.orders.entity.Order;
import com.fractal.orders.entity.OrderStatus;

@Service
public class OrderConverter extends AbstractConverter<Order, OrderDTO> {
  private ItemConverter itemConverter;

  public OrderConverter(ItemConverter itemConverter) {
    this.itemConverter = itemConverter;
  }

  @Override
  public Order toEntity(OrderDTO dto) {

    // TODO Auto-generated method stub
    return Order.builder().taxes(dto.getTaxes()).orderId(dto.getOrderId()).total(dto.getTotal())
        .status(OrderStatus.valueOf(dto.getStatus())).items(itemConverter.toEntityList(dto.getItems()))
        .date(dto.getDate()).consumer(dto.getConsumer()).build();
  }

  @Override
  public OrderDTO toDTO(Order entity) {
    // TODO Auto-generated method stub
    return OrderDTO.builder().taxes(entity.getTaxes()).orderId(entity.getOrderId()).total(entity.getTotal())
        .status(entity.getStatus().toString()).items(itemConverter.toDtoList(entity.getItems())).date(entity.getDate())
        .consumer(entity.getConsumer()).build();
  }

}
