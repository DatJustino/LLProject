package com.example.llproject.service;

import com.example.llproject.model.Product;
import com.example.llproject.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Integer productId) {
    return productRepository.findById(productId);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Integer productId, Product updatedProduct) {
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isPresent()) {
      Product product = productOptional.get();
      product.setProductName(updatedProduct.getProductName());
      product.setProductDescription(updatedProduct.getProductDescription());
      product.setProductImage(updatedProduct.getProductImage());
      product.setProductPrice(updatedProduct.getProductPrice());
      product.setProductQuantity(updatedProduct.getProductQuantity());
      product.setWidth(updatedProduct.getWidth());
      product.setHeight(updatedProduct.getHeight());
      product.setLength(updatedProduct.getLength());
      return productRepository.save(product);
    } else {
      throw new IllegalArgumentException("Product not found with ID: " + productId);
    }
  }

  public void deleteProduct(Integer productId) {
    productRepository.deleteById(productId);
  }
}