package com.example.llproject.controller;

import com.example.llproject.model.Order;
import com.example.llproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RestControllerAdvice
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {
    Order createdOrder = orderService.createOrder(order);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
    Optional<Order> order = orderService.getOrderById(orderId);
    return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    List<Order> orders = orderService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  @PutMapping("/{orderId}")
  public ResponseEntity<Void> updateOrder(@PathVariable Integer orderId, @RequestBody Order order) {
    Optional<Order> existingOrder = orderService.getOrderById(orderId);
    if (existingOrder.isPresent()) {
      Order updatedOrder = existingOrder.get();
      updatedOrder.setFName(order.getFName());
      updatedOrder.setLName(order.getLName());
      updatedOrder.setEmail(order.getEmail());
      updatedOrder.setPhoneNumber(order.getPhoneNumber());
      updatedOrder.setAddress(order.getAddress());
      updatedOrder.setHouseNumber(order.getHouseNumber());
      updatedOrder.setFloor(order.getFloor());
      updatedOrder.setZipCode(order.getZipCode());

      orderService.updateOrder(orderId, updatedOrder);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
    Optional<Order> existingOrder = orderService.getOrderById(orderId);
    if (existingOrder.isPresent()) {
      orderService.deleteOrder(orderId);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
