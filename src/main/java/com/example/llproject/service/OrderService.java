package com.example.llproject.service;

import com.example.llproject.model.Order;
import com.example.llproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;


  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  public Optional<Order> getOrderById(Integer orderId) {
    return orderRepository.findById(orderId);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public void updateOrder(Order order) {
    orderRepository.save(order);
  }

  public void deleteOrder(Integer orderId) {
    orderRepository.deleteById(orderId);
  }
}