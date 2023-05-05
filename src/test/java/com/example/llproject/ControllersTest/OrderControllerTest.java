package com.example.llproject.ControllersTest;

import com.example.llproject.config.GlobalExceptionHandler;
import com.example.llproject.controller.OrderController;
import com.example.llproject.model.Order;
import com.example.llproject.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private OrderService orderService;
  private ObjectMapper objectMapper;
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService))
        .setControllerAdvice(new GlobalExceptionHandler((MessageSource) orderService))
        .build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testGetOrderById() throws Exception {
    Order order = new Order();
    order.setOrderId(1);
    order.setFName("John");
    order.setLName("Doe");
    order.setEmail("john@example.com");
    order.setPhoneNumber("1234567890");

    when(orderService.getOrderById(1)).thenReturn(Optional.of(order));

    mockMvc.perform(get("/orders/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.orderId").value(1))
        .andExpect(jsonPath("$.fName").value("John"))
        .andExpect(jsonPath("$.lName").value("Doe"))
        .andExpect(jsonPath("$.email").value("john@example.com"))
        .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
  }

  @Test
  public void testGetOrderById_OrderNotFound() throws Exception {
    when(orderService.getOrderById(1)).thenReturn(Optional.empty());

    mockMvc.perform(get("/orders/1"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateOrder() throws Exception {
    Order order = new Order();
    order.setFName("John");
    order.setLName("Doe");
    order.setEmail("john@example.com");
    order.setPhoneNumber("1234567890");

    mockMvc.perform(post("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"fName\":\"John\",\"lName\":\"Doe\",\"email\":\"john@example.com\",\"phoneNumber\":\"1234567890\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.fName").value("John"))
        .andExpect(jsonPath("$.lName").value("Doe"))
        .andExpect(jsonPath("$.email").value("john@example.com"))
        .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
  }

  @Test
  public void testUpdateOrder() throws Exception {
    // Mock existing order
    Order existingOrder = new Order();
    existingOrder.setOrderId(1);
    existingOrder.setFName("John");
    existingOrder.setLName("Doe");
    existingOrder.setEmail("john@example.com");
    existingOrder.setPhoneNumber("1234567890");

    // Mock updated order
    Order updatedOrder = new Order();
    updatedOrder.setFName("Jane");
    updatedOrder.setLName("Doe");
    updatedOrder.setEmail("jane@example.com");
    updatedOrder.setPhoneNumber("9876543210");

    // Mock getOrderById() response
    given(orderService.getOrderById(existingOrder.getOrderId())).willReturn(Optional.of(existingOrder));

    // Perform the update request
    mockMvc.perform(put("/orders/{orderId}", existingOrder.getOrderId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedOrder)))
        .andExpect(status().isNoContent());

    // Verify that the orderService.updateOrder() method is called with the correct arguments
    verify(orderService).updateOrder(existingOrder.getOrderId(), updatedOrder);


  }
}