package com.example.llproject.service;

import com.example.llproject.model.Customer;
import com.example.llproject.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private final CustomerRepo customerRepo;

  public CustomerServiceImpl(CustomerRepo customerRepo) {
    this.customerRepo = customerRepo;
  }

  @Override
  public Page<Customer> getAllCustomers(Pageable pageable) {
    return customerRepo.findAll(pageable);
  }


  public Optional<Customer> registerCustomer(Customer customer) {
    return Optional.of(customerRepo.save(customer));
  }

  public Optional<Customer> getCustomerById(Integer id) {
    return customerRepo.findById(id);
  }

  public Optional<Customer> updateCustomer(Integer id, Customer customer) {
    Optional<Customer> existingCustomer = customerRepo.findById(id);
    if (existingCustomer.isPresent()) {
      Customer updatedCustomer = existingCustomer.get();
      updatedCustomer.setCEmail(customer.getCEmail());
      updatedCustomer.setCPassword(customer.getCPassword());
      return Optional.of(customerRepo.save(updatedCustomer));
    } else {
      // If Customer not found
      return Optional.empty();
    }
  }

  public boolean deleteCustomer(Integer id) {
    Optional<Customer> existingCustomer = customerRepo.findById(id);
    if (existingCustomer.isPresent()) {
      customerRepo.delete(existingCustomer.get());
      return true;
    } else {
      // If costumer not found
      return false;
    }
  }

}
