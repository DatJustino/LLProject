package com.example.llproject.repository;

import com.example.llproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//JPARepo contains implements paging and sorting repo as is.
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Customer findBycEmail(String email);

}