package com.example.llproject.config;

import com.example.llproject.model.Customer;
import com.example.llproject.model.Order;
import com.example.llproject.model.Product;
import com.example.llproject.repository.CustomerRepo;
import com.example.llproject.repository.OrderRepo;
import com.example.llproject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner {


  @Autowired
  CustomerRepo customerRepo;
  @Autowired
  OrderRepo orderRepo;

   @Autowired
   ProductRepo productRepo;

  @Override
  public void run(String... args) throws Exception {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();

    customer1.setCEmail("asd@gmail.com");
    customer1.setCPassword("asdfghjkl");

    customer2.setCEmail("123@gmail.com");
    customer2.setCPassword("12345678");
    customerRepo.save(customer1);
    customerRepo.save(customer2);

    Product product = new Product();
    product.setProductName("asd");
    product.setProductPrice(123);
    product.setProductDescription("asdas");
    product.setProductImage("asdasd");
    product.setProductQuantity(123);
    product.setHeight("asdasd");
    product.setWidth("asdfasdf");
    product.setLength("asdasd");

    productRepo.save(product);



    Order order = new Order();
    order.setFName("asd");
    order.setLName("fgh");
    order.setAddress("asd");
    order.setDescription("asdkfhjaskldkjaldhskjladhsfkashdfkjhasdflkjh");
    byte[] image = {0x01, 0x02, 0x03};
    order.setImage(image);
    order.setPhoneNumber("23232323");
    order.setSubject("asd");
    order.setProductsList(Arrays.asList(product));

    Order order2 = new Order();
    order2.setFName("123");
    order2.setLName("123");
    order2.setAddress("123123");
    order2.setDescription("123123123123123123123123123123");
    byte[] image2 = {0x05, 0x06, 0x04};
    order2.setImage(image2);
    order2.setPhoneNumber("9898989898");
    order2.setSubject("23232323");


    orderRepo.save(order2);
    orderRepo.save(order);


  }
  }