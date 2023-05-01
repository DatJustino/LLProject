package com.example.llproject.service;

import com.example.llproject.model.Order;
import com.example.llproject.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class OrderService {

  @Autowired
  OrderRepo orderRepo;


  public OrderService(OrderRepo orderRepo) {
    this.orderRepo = orderRepo;
  }

  public Order createOrder(Order order) {
    return orderRepo.save(order);
  }

  public Optional<Order> getOrderById(Integer orderId) {
    return orderRepo.findById(orderId);
  }

  public List<Order> getAllOrders() {
    return orderRepo.findAll();
  }

  public void updateOrder(Order order) {
    orderRepo.save(order);
  }

  public void deleteOrder(Integer orderId) {
    orderRepo.deleteById(orderId);
  }
}