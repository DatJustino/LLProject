package com.example.llproject.service;

import com.example.llproject.model.Product;
import com.example.llproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
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

  public Optional<Product> updateProduct(Integer productId, Product updatedProduct) {
    return getProductById(productId).map(product -> {
      product.setProductName(updatedProduct.getProductName());
      product.setProductDescription(updatedProduct.getProductDescription());
      product.setProductImage(updatedProduct.getProductImage());
      product.setProductPrice(updatedProduct.getProductPrice());
      product.setProductQuantity(updatedProduct.getProductQuantity());
      product.setWidth(updatedProduct.getWidth());
      product.setHeight(updatedProduct.getHeight());
      product.setLength(updatedProduct.getLength());

      return productRepository.save(product);
    });
  }

  public void deleteProduct(Integer productId) {
    getProductById(productId).ifPresentOrElse(
        product -> productRepository.deleteById(productId),
        () -> {
          throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
    );
  }
}
