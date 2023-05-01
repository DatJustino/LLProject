package com.example.llproject.service;

import com.example.llproject.model.Order;
import com.example.llproject.model.Product;
import com.example.llproject.repository.OrderRepo;
import com.example.llproject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  ProductRepo productRepo;

  public ProductService(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  public Product createProduct (Product product) {
    return productRepo.save(product);
  }

  public Optional<Product> getProductById(Integer productId) {
    return productRepo.findById(productId);
  }

  public List<Product> getAllProducts() {
    return productRepo.findAll();
  }

  public void updateProduct (Product product) {
    productRepo.save(product);
  }

  public void deleteProduct(Integer productId) {
    productRepo.deleteById(productId);
  }

}