package com.example.llproject.service;

import com.example.llproject.model.Order;
import com.example.llproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order createOrder(Order order) {
    if (order == null) {
      throw new IllegalArgumentException("Order cannot be null");
    }
    return orderRepository.save(order);
  }

  public Optional<Order> getOrderById(Integer orderId) {
    if (orderId == null) {
      throw new IllegalArgumentException("Order ID cannot be null");
    }
    return orderRepository.findById(orderId);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Optional<Order> updateOrder(Integer orderId, Order updatedOrder) {
    if (orderId == null) {
      throw new IllegalArgumentException("Order ID cannot be null");
    }
    if (updatedOrder == null) {
      throw new IllegalArgumentException("Updated order cannot be null");
    }
    return getOrderById(orderId).map(order -> {
      order.setFName(updatedOrder.getFName());
      order.setLName(updatedOrder.getLName());
      order.setEmail(updatedOrder.getEmail());
      order.setPhoneNumber(updatedOrder.getPhoneNumber());
      order.setAddress(updatedOrder.getAddress());
      order.setHouseNumber(updatedOrder.getHouseNumber());
      order.setFloor(updatedOrder.getFloor());
      order.setZipCode(updatedOrder.getZipCode());
      // Update other properties as needed
      return orderRepository.save(order);
    });
  }

  public boolean deleteOrder(Integer orderId) {
    if (orderId == null) {
      throw new IllegalArgumentException("Order ID cannot be null");
    }
    Optional<Order> order = getOrderById(orderId);
    if (order.isPresent()) {
      orderRepository.deleteById(orderId);
      return true;
    } else {
      return false;
    }
  }
}
