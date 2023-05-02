package com.example.llproject.service;

import com.example.llproject.model.Product;
import com.example.llproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product createProduct (Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> getProductById(Integer productId) {
    return productRepository.findById(productId);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public void updateProduct (Product product) {
    productRepository.save(product);
  }

  public void deleteProduct(Integer productId) {
    productRepository.deleteById(productId);
  }

}