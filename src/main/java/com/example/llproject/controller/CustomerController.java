package com.example.llproject.controller;

import com.example.llproject.model.Customer;
import com.example.llproject.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  CustomerService customerService;


  @PostMapping
  public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
    Optional<Customer> registeredCustomer = customerService.registerCustomer(customer);
    return ResponseEntity.ok(null);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Integer id) {
    Optional<Customer> customer = customerService.getCustomerById(id);
    if (customer != null) {
      return ResponseEntity.ok(customer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Customer>> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
    // Add validation and error handling as per your requirements
    Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customer);
    if (updatedCustomer != null) {
      return ResponseEntity.ok(updatedCustomer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    boolean deleted = customerService.deleteCustomer(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
