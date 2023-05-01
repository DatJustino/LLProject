package com.example.llproject.repository;

import com.example.llproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer>{
}
