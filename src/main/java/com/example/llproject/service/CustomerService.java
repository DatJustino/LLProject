package com.example.llproject.service;


import com.example.llproject.model.Customer;
import com.example.llproject.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService {

  Page<Customer> getAllCustomers(Pageable pageable);

  Optional<Customer> getCustomerById(Integer id);

  Optional<Customer> updateCustomer(Integer id, Customer customer);

  boolean deleteCustomer(Integer id);

  Optional<Customer> registerCustomer(Customer customer);
}


