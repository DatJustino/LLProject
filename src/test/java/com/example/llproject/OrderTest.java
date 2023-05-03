package com.example.llproject;

import com.example.llproject.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {

  @Test
  public void testGettersAndSetters() {

    Order order = new Order();

    order.setOrderId(1);
    order.setPhoneNumber("12345678");
    order.setFName("John");
    order.setLName("Doe");
    order.setAddress("123 Main St");
    byte[] image = {0x01, 0x02, 0x03};


    Assertions.assertEquals(1, order.getOrderId());
    Assertions.assertEquals("12345678", order.getPhoneNumber());
    Assertions.assertEquals("John", order.getFName());
    Assertions.assertEquals("Doe", order.getLName());
    Assertions.assertEquals("123 Main St", order.getAddress());

  }


}