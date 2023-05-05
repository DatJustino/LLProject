package com.example.llproject.ServiceTest;

import com.example.llproject.model.Order;
import com.example.llproject.repository.OrderRepository;
import com.example.llproject.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest {

  private OrderRepository orderRepository;
  private OrderService orderService;

  @BeforeEach
  public void setUp() {
    orderRepository = Mockito.mock(OrderRepository.class);
    orderService = new OrderService(orderRepository);
  }

  @Test
  public void testCreateOrder() {
    // Create a sample order
    Order order = new Order();
    order.setFName("John");
    order.setLName("Doe");

    // Mock the behavior of the orderRepository
    Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

    // Call the createOrder method
    Order createdOrder = orderService.createOrder(order);

    // Verify the result
    Assertions.assertEquals(order.getFName(), createdOrder.getFName());
    Assertions.assertEquals(order.getLName(), createdOrder.getLName());
  }

  @Test
  public void testGetOrderById() {
    // Create a sample order
    Order order = new Order();
    order.setOrderId(1);
    order.setFName("John");
    order.setLName("Doe");

    // Mock the behavior of the orderRepository
    Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));

    // Call the getOrderById method
    Optional<Order> retrievedOrder = orderService.getOrderById(1);

    // Verify the result
    Assertions.assertTrue(retrievedOrder.isPresent());
    Assertions.assertEquals(order.getFName(), retrievedOrder.get().getFName());
    Assertions.assertEquals(order.getLName(), retrievedOrder.get().getLName());
  }

  @Test
  public void testGetAllOrders() {
    // Create a list of sample orders
    List<Order> orders = new ArrayList<>();
    Order order1 = new Order();
    order1.setOrderId(1);
    order1.setFName("John");
    order1.setLName("Doe");
    Order order2 = new Order();
    order2.setOrderId(2);
    order2.setFName("Jane");
    order2.setLName("Smith");
    orders.add(order1);
    orders.add(order2);

    // Mock the behavior of the orderRepository
    Mockito.when(orderRepository.findAll()).thenReturn(orders);

    // Call the getAllOrders method
    List<Order> retrievedOrders = orderService.getAllOrders();

    // Verify the result
    Assertions.assertEquals(orders.size(), retrievedOrders.size());
    Assertions.assertEquals(order1.getFName(), retrievedOrders.get(0).getFName());
    Assertions.assertEquals(order1.getLName(), retrievedOrders.get(0).getLName());
    Assertions.assertEquals(order2.getFName(), retrievedOrders.get(1).getFName());
    Assertions.assertEquals(order2.getLName(), retrievedOrders.get(1).getLName());
  }

  @Test
  public void testUpdateOrderNonexistentId() {
    // Mock the behavior of the orderRepository to return an empty optional
    Mockito.when(orderRepository.findById(1)).thenReturn(Optional.empty());

    // Create an updated order
    Order updatedOrder = new Order();
    updatedOrder.setOrderId(1);
    updatedOrder.setFName("Updated");

    // Call the updateOrder method
    Optional<Order> result = orderService.updateOrder(1, updatedOrder);

    // Verify that the result is empty
    Assertions.assertFalse(result.isPresent());
  }

  @Test
  public void testDeleteOrderExistingId() {
    // Mock the behavior of the orderRepository to return a non-empty optional
    Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(new Order()));

    // Call the deleteOrder method
    boolean result = orderService.deleteOrder(1);

    // Verify that the order is deleted successfully
    Assertions.assertTrue(result);
  }

  @Test
  public void testDeleteOrderNonexistentId() {
    // Mock the behavior of the orderRepository to return an empty optional
    Mockito.when(orderRepository.findById(1)).thenReturn(Optional.empty());

    // Call the deleteOrder method
    boolean result = orderService.deleteOrder(1);

    // Verify that the order is not deleted
    Assertions.assertFalse(result);
  }

}
