package com.fractal.orders.converter;

import org.springframework.stereotype.Service;

import com.fractal.orders.dto.ItemDTO;
import com.fractal.orders.entity.Item;

@Service
public class ItemConverter extends AbstractConverter<Item, ItemDTO> {

  @Override
  public Item toEntity(ItemDTO dto) {
    // TODO Auto-generated method stub
    return Item.builder().itemId(dto.getItemId()).unitPrice(dto.getUnitPrice()).quantity(dto.getQuantity())
        .name(dto.getName()).cost(dto.getCost()).build();
  }

  @Override
  public ItemDTO toDTO(Item entity) {
    // TODO Auto-generated method stub
    return ItemDTO.builder().itemId(entity.getItemId()).unitPrice(entity.getUnitPrice()).quantity(entity.getQuantity())
        .name(entity.getName()).cost(entity.getCost()).build();
  }

}
