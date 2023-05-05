package com.example.llproject.controller;

import com.example.llproject.model.Customer;
import com.example.llproject.service.CustomerService;
import com.example.llproject.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RestControllerAdvice
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/all")
  public ResponseEntity<Page<Customer>> getAllCustomers() {
    Page<Customer> customers = customerService.getAllCustomers(Pageable.ofSize(10));
    return ResponseEntity.ok(customers);
  }

  @PostMapping("/register")
  public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
    Optional<Customer> registeredCustomer = customerService.registerCustomer(customer);
    return ResponseEntity.ok(registeredCustomer.orElse(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
    Optional<Customer> customer = customerService.getCustomerById(id);
    return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @Valid @RequestBody Customer customer) {
    Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customer);
    return updatedCustomer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    boolean deleted = customerService.deleteCustomer(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
