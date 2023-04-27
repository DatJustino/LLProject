package com.example.llproject.repository;

import com.example.llproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository <Customer, Integer> {
}
