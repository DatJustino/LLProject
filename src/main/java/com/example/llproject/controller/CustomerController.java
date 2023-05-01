package com.example.llproject.controller;

import com.example.llproject.model.Customer;
import com.example.llproject.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {


  @Autowired
  CustomerServiceImpl customerServiceImpl;

  CustomerController(CustomerServiceImpl customerServiceImpl) {
    this.customerServiceImpl = customerServiceImpl;
  }

  @GetMapping("/all")
  public ResponseEntity<Page<Customer>> getAllCustomers() {
    Page<Customer> customer = customerServiceImpl.getAllCustomers(Pageable.ofSize(10));
    if (customer != null) {
      return ResponseEntity.ok(customer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @PostMapping("/register")
  public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
    Optional<Customer> registeredCustomer = customerServiceImpl.registerCustomer(customer);
    return ResponseEntity.ok(registeredCustomer.orElse(null));
  }


  @GetMapping("/{id}")
  public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Integer id) {
    Optional<Customer> customer = customerServiceImpl.getCustomerById(id);
    if (customer != null) {
      return ResponseEntity.ok(customer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Customer>> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
    Optional<Customer> updatedCustomer = customerServiceImpl.updateCustomer(id, customer);
    if (updatedCustomer != null) {
      return ResponseEntity.ok(updatedCustomer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    boolean deleted = customerServiceImpl.deleteCustomer(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
