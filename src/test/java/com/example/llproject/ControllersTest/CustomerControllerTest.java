package com.example.llproject.ControllersTest;

import com.example.llproject.controller.CustomerController;
import com.example.llproject.model.Customer;
import com.example.llproject.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

  @Mock
  private CustomerService customerService;

  private CustomerController customerController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    customerController = new CustomerController(customerService);
  }

  @Test
  void testGetAllCustomers() {
    // Create a list of customers
    List<Customer> customers = Arrays.asList(
        new Customer(1, "test1@example.com", "password1", true),
        new Customer(2, "test2@example.com", "password2", false)
    );

    // Create a Page object for customers
    Page<Customer> customerPage = new PageImpl<>(customers);

    // Mock the getAllCustomers method of CustomerService to return the Page object
    when(customerService.getAllCustomers(any(Pageable.class))).thenReturn(customerPage);

    // Call the getAllCustomers method of CustomerController
    ResponseEntity<Page<Customer>> response = customerController.getAllCustomers();

    // Verify that the getAllCustomers method of CustomerService is called
    verify(customerService, times(1)).getAllCustomers(any(Pageable.class));

    // Verify the response status code and the returned Page object
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customerPage, response.getBody());
  }

  @Test
  void testRegisterCustomer() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the registerCustomer method of CustomerService to return an Optional containing the customer
    when(customerService.registerCustomer(customer)).thenReturn(Optional.of(customer));

    // Call the registerCustomer method of CustomerController
    ResponseEntity<Customer> response = customerController.registerCustomer(customer);

    // Verify that the registerCustomer method of CustomerService is called
    verify(customerService, times(1)).registerCustomer(customer);

    // Verify the response status code and the returned Customer object
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customer, response.getBody());
  }

  @Test
  void testGetCustomerById_CustomerFound() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCustomerId(100);
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the getCustomerById method of CustomerService to return an Optional containing the customer
    when(customerService.getCustomerById(100)).thenReturn(Optional.of(customer));

    // Call the getCustomerById method of CustomerController
    ResponseEntity<Customer> response = customerController.getCustomerById(100);

    // Verify that the getCustomerById method of CustomerService is called
    verify(customerService, times(1)).getCustomerById(100);

    // Verify the response status code and the returned Customer object
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customer, response.getBody());
  }
  @Test
  void testGetCustomerById_CustomerNotFound() {
    // Mock the getCustomerById method of CustomerService to return an empty Optional
    when(customerService.getCustomerById(1)).thenReturn(Optional.empty());

    // Call the getCustomerById method of CustomerController
    ResponseEntity<Customer> response = customerController.getCustomerById(1);

    // Verify that the getCustomerById method of CustomerService is called
    verify(customerService, times(1)).getCustomerById(1);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }
  @Test
  void testUpdateCustomer_CustomerFound() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCustomerId(100);
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the updateCustomer method of CustomerService to return the updated customer
    when(customerService.updateCustomer(100, customer)).thenReturn(Optional.of(customer));

    // Call the updateCustomer method of CustomerController
    ResponseEntity<Customer> response = customerController.updateCustomer(100, customer);

    // Verify that the updateCustomer method of CustomerService is called
    verify(customerService, times(1)).updateCustomer(100, customer);

    // Verify the response status code and the returned Customer object
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(customer, response.getBody());
  }

  @Test
  void testDeleteCustomer_CustomerFound() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCustomerId(1);
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the deleteCustomer method of CustomerService to return true
    when(customerService.deleteCustomer(1)).thenReturn(true);

    // Call the deleteCustomer method of CustomerController
    ResponseEntity<Void> response = customerController.deleteCustomer(1);

    // Verify that the deleteCustomer method of CustomerService is called
    verify(customerService, times(1)).deleteCustomer(1);

    // Verify the response status code
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    // Mock the deleteCustomer method of CustomerService to return false
    when(customerService.deleteCustomer(1)).thenReturn(false);

    // Call the deleteCustomer method of CustomerController
    response = customerController.deleteCustomer(1);

    // Verify that the deleteCustomer method of CustomerService is called again
    verify(customerService, times(2)).deleteCustomer(1);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void testDeleteCustomer_CustomerDeleted() {
    // Mock the deleteCustomer method of CustomerService to return true
    when(customerService.deleteCustomer(1)).thenReturn(true);

    // Call the deleteCustomer method of CustomerController
    ResponseEntity<Void> response = customerController.deleteCustomer(1);

    // Verify that the deleteCustomer method of CustomerService is called
    verify(customerService, times(1)).deleteCustomer(1);

    // Verify the response status code
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

}

