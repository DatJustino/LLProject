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

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Page<Customer> getAllCustomers(Pageable pageable) {
    return customerRepository.findAll(pageable);
  }

  @Override
  public Optional<Customer> getCustomerById(Integer id) {
    return customerRepository.findById(id);
  }
  @Override
  public Optional<Customer> registerCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null");
    }
    return Optional.of(customerRepository.save(customer));
  }


  @Override
  public Optional<Customer> updateCustomer(Integer id, Customer customer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer updatedCustomer = existingCustomer.get();
      updatedCustomer.setCEmail(customer.getCEmail());
      updatedCustomer.setCPassword(customer.getCPassword());
      updatedCustomer.setNewsLetter(customer.isNewsLetter());
      customerRepository.save(updatedCustomer);
      return Optional.of(updatedCustomer);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean deleteCustomer(Integer id) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      customerRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}

