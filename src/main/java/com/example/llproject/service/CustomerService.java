package com.example.llproject.service;


import com.example.llproject.model.Customer;
import com.example.llproject.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  CustomerRepo customerRepo;

  public Optional<Customer> registerCustomer(Customer customer) {
    // Perform necessary validations
    // ...

    // Save the customer and return the saved entity
    return Optional.of(customerRepo.save(customer));
  }

  public Optional<Customer> getCustomerById(Integer id) {
    return customerRepo.findById(id);
  }

  public Optional<Customer> updateCustomer(Integer id, Customer customer) {
    Optional<Customer> existingCustomer = customerRepo.findById(id);
    if (existingCustomer.isPresent()) {
      // Update the existing customer
      Customer updatedCustomer = existingCustomer.get();
      // Set the updated fields
      updatedCustomer.setcPassword(customer.getcEmail());
      updatedCustomer.setcPassword(customer.getcPassword());
      // ... Set other fields as needed

      // Save the updated customer and return the saved entity
      return Optional.of(customerRepo.save(updatedCustomer));
    } else {
      // Customer not found
      return Optional.empty();
    }
  }

  public boolean deleteCustomer(Integer id) {
    Optional<Customer> existingCustomer = customerRepo.findById(id);
    if (existingCustomer.isPresent()) {
      customerRepo.delete(existingCustomer.get());
      return true;
    } else {
      // Customer not found
      return false;
    }
  }
}
