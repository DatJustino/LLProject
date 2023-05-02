package com.example.llproject.service;

import com.example.llproject.model.Customer;
import com.example.llproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Page<Customer> getAllCustomers(Pageable pageable) {
    return customerRepository.findAll(pageable);
  }


  public Optional<Customer> registerCustomer(Customer customer) {
    return Optional.of(customerRepository.save(customer));
  }

  public Optional<Customer> getCustomerById(Integer id) {
    return customerRepository.findById(id);
  }

  public Optional<Customer> updateCustomer(Integer id, Customer customer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer updatedCustomer = existingCustomer.get();
      updatedCustomer.setCEmail(customer.getCEmail());
      updatedCustomer.setCPassword(customer.getCPassword());
      return Optional.of(customerRepository.save(updatedCustomer));
    } else {
      // If Customer not found
      return Optional.empty();
    }
  }

  public boolean deleteCustomer(Integer id) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      customerRepository.delete(existingCustomer.get());
      return true;
    } else {
      // If costumer not found
      return false;
    }
  }

}
