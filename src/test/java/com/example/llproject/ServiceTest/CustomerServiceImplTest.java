package com.example.llproject.ServiceTest;

import com.example.llproject.model.Customer;
import com.example.llproject.repository.CustomerRepository;
import com.example.llproject.service.CustomerService;
import com.example.llproject.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class CustomerServiceImplTest {

  @Mock
  private CustomerRepository customerRepository;

  private CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    customerService = new CustomerServiceImpl(customerRepository);
  }

  @Test
  void testGetAllCustomers() {
    // Mock the findAll method of CustomerRepository to return a Page object
    Page<Customer> customers = mock(Page.class);
    when(customerRepository.findAll(any(Pageable.class))).thenReturn(customers);

    // Call the getAllCustomers method of CustomerService
    Page<Customer> retrievedCustomers = customerService.getAllCustomers(Pageable.ofSize(10));

    // Verify that the findAll method of CustomerRepository is called
    verify(customerRepository, times(1)).findAll(any(Pageable.class));

    // Verify the returned Page object
    assertEquals(customers, retrievedCustomers);
  }

  @Test
  void testGetCustomerById_CustomerFound() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCustomerId(100);
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the findById method of CustomerRepository to return the Customer object
    when(customerRepository.findById(100)).thenReturn(Optional.of(customer));

    // Call the getCustomerById method of CustomerService
    Optional<Customer> foundCustomer = customerService.getCustomerById(100);

    // Verify that the findById method of CustomerRepository is called
    verify(customerRepository, times(1)).findById(100);

    // Verify the returned Optional object
    assertTrue(foundCustomer.isPresent());
    assertEquals(customer, foundCustomer.get());
  }

  @Test
  void testGetCustomerById_CustomerNotFound() {
    // Mock the findById method of CustomerRepository to return an empty Optional
    when(customerRepository.findById(100)).thenReturn(Optional.empty());

    // Call the getCustomerById method of CustomerService
    Optional<Customer> foundCustomer = customerService.getCustomerById(100);

    // Verify that the findById method of CustomerRepository is called
    verify(customerRepository, times(1)).findById(100);

    // Verify the returned Optional object
    assertFalse(foundCustomer.isPresent());
  }

  @Test
  void testUpdateCustomer_CustomerNotFound() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCustomerId(100);
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the findById method of CustomerRepository to return an empty Optional
    when(customerRepository.findById(100)).thenReturn(Optional.empty());

    // Call the updateCustomer method and expect an exception
    Optional<Customer> updatedCustomer = customerService.updateCustomer(100, customer);

    // Verify that the findById method of CustomerRepository is called
    verify(customerRepository, times(1)).findById(100);

    // Verify that the save method of CustomerRepository is not called
    verify(customerRepository, never()).save(any(Customer.class));

    // Verify the returned Optional object
    assertFalse(updatedCustomer.isPresent());
  }

  @Test
  void testDeleteCustomer_CustomerFound() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCustomerId(100);
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the findById method of CustomerRepository to return the Customer object
    when(customerRepository.findById(100)).thenReturn(Optional.of(customer));

    // Call the deleteCustomer method and expect a true result
    boolean deleted = customerService.deleteCustomer(100);

    // Verify that the findById method of CustomerRepository is called
    verify(customerRepository, times(1)).findById(100);

    // Verify that the deleteById method of CustomerRepository is called
    verify(customerRepository, times(1)).deleteById(100);

    // Verify the returned result
    assertTrue(deleted);
  }

  @Test
  void testRegisterCustomer() {
    // Create a Customer object
    Customer customer = new Customer();
    customer.setCEmail("test@example.com");
    customer.setCPassword("password");
    customer.setNewsLetter(true);

    // Mock the save method of CustomerRepository
    when(customerRepository.save(customer)).thenReturn(customer);

    // Call the registerCustomer method
    Optional<Customer> registeredCustomer = customerService.registerCustomer(customer);

    // Verify that the save method of CustomerRepository is called
    verify(customerRepository, times(1)).save(customer);

    // Verify the returned Optional object
    assertTrue(registeredCustomer.isPresent());
    assertEquals(customer, registeredCustomer.get());
  }
}