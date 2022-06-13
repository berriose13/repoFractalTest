package com.fractal.orders.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fractal.orders.converter.ItemConverter;
import com.fractal.orders.converter.OrderConverter;
import com.fractal.orders.dto.ItemDTO;
import com.fractal.orders.dto.OrderDTO;
import com.fractal.orders.entity.Item;
import com.fractal.orders.entity.Order;
import com.fractal.orders.entity.Taxes;
import com.fractal.orders.repository.OrderRepository;
import com.fractal.orders.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
  private OrderRepository orderRepository;
  private OrderConverter orderConverter;
  private ItemConverter itemConverter;

  @Value("${order.taxes.cityTax}")
  private Double cityTax;
  @Value("${order.taxes.countyTax}")
  private Double countyTax;
  @Value("${order.taxes.stateTax}")
  private Double stateTax;
  @Value("${order.taxes.federalTax}")
  private Double federalTax;
  
  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter, ItemConverter itemConverter) {
    this.orderRepository = orderRepository;
    this.orderConverter = orderConverter;
    this.itemConverter = itemConverter;
  }

  @Override
  public OrderDTO createOrder(OrderDTO orderDTO) {
    Order order = orderConverter.toEntity(orderDTO);
    order.setId(new Order().getId());
    return orderConverter.toDTO(orderRepository.save(order));
  }

  @Override
  public List<OrderDTO> getAllOrders() {
    // TODO Auto-generated method stub
    List<Order> orders = orderRepository.findAll();
    return orderConverter.toDtoList(orders);
  }

  @Override
  public List<ItemDTO> addItem(Long orderId, ItemDTO itemDto) {
    Order order = orderRepository.findByOrderId(orderId);
    Item item = itemConverter.toEntity(itemDto);

    List<Item> items = new ArrayList<>();
    if (order.getItems() != null) {
      items.addAll(order.getItems());
    }
    item.setCost(item.getUnitPrice() * item.getQuantity().doubleValue());
    items.add(item);
    order.setItems(items);

    Taxes taxes = calculateOrderTaxes(items);
    order.setTaxes(taxes);
    order.setTotal(taxes.getTotal());

    return orderConverter.toDTO(orderRepository.save(order)).getItems();
  }

  private Taxes calculateOrderTaxes(List<Item> items) {
    /*
     * if (items==null || items.isEmpty()) return null;
     */
    Double subTotal = items.stream().mapToDouble(i -> i.getCost()).sum();
    Double total = subTotal;

    List<Double> taxes = new ArrayList<>();
    taxes.add(cityTax);
    taxes.add(countyTax);
    taxes.add(stateTax);
    taxes.add(federalTax);
    List<Double> totalTaxes = new ArrayList<>();

    Double aux;
    for (Double tax : taxes) {
      aux = total * tax / (double) 100;
      total = total + aux;
      totalTaxes.add(aux);
    }

    /*
     * HashMap<Double, Double> taxes = new HashMap<>(); Doubl e total = subTotal;
     * Double totalTax = null; taxes.put(cityTax, (double) 0); taxes.put(countyTax,
     * (double) 0); taxes.put(stateTax, (double) 0); taxes.put(federalTax, (double)
     * 0); Set<Double> keys=taxes.keySet(); for (Double key:keys ) { Double tax =
     * total * key/ (double) 100; total=total+tax; taxes.put(key,tax); }
     */
    Double totalTax = totalTaxes.stream().reduce((double) 0, Double::sum);
    return Taxes.builder().subtotal(subTotal).total(total).totalTax(totalTax).cityTax(totalTaxes.get(0))
        .countyTax(totalTaxes.get(1)).stateTax(totalTaxes.get(2)).federalTax(totalTaxes.get(3)).build();
    /*
     * return
     * Taxes.builder().subtotal(subTotal).total(total).totalTax(totalTax).cityTax(
     * taxes.get(cityTax))
     * .countyTax(taxes.get(countyTax)).stateTax(taxes.get(stateTax)).federalTax(
     * taxes.get(federalTax)).build();
     * 
     * Double orderCityTax=total*cityTax;
     * 
     * total=subTotal+orderCityTax;
     * 
     * Double orderCountyTax=(total)*countyTax;
     * 
     * total=+orderCountyTax; Double orderStateTax= (total)*stateTax;
     * 
     * total=+orderStateTax;
     * 
     * Double orderFederalTax=(total)*federalTax;
     * 
     * total=+orderFederalTax;
     * 
     * Double
     * orderTotalTaxes=orderCityTax+orderCountyTax+orderStateTax+orderFederalTax;
     */

  }

  @Override
  public Taxes getTaxes(Long orderId) {
    Order order = orderRepository.findByOrderId(orderId);
    return order.getTaxes();
  }

  @Override
  public OrderDTO getOrder(Long orderId) {
    Order order = orderRepository.findByOrderId(orderId);
    return orderConverter.toDTO(order);
  }

  @Override
  public List<ItemDTO> getItems(Long orderId) {
    Order order = orderRepository.findByOrderId(orderId);
    List<ItemDTO> items = itemConverter.toDtoList(order.getItems());
    return items;
  }

  @Override
  public void deleteItem(Long orderId, Long itemId) {
    Order order = orderRepository.findByOrderId(orderId);
    Item item = order.getItems().stream().filter(p -> p.getItemId() == itemId).collect(Collectors.toList()).get(0);
    order.getItems().remove(item);
    orderRepository.save(order);
  }

  @Override
  public void deleteOrder(Long orderId) {
    // TODO Auto-generated method stub
    Order order = orderRepository.findByOrderId(orderId);
    orderRepository.delete(order);
    
  }

}
