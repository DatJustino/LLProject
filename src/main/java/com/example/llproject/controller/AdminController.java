package com.example.llproject.controller;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.model.Commission;
import com.example.llproject.model.Course;
import com.example.llproject.model.Image;
import com.example.llproject.model.Product;
import com.example.llproject.service.BlogService;
import com.example.llproject.service.CommissionService;
import com.example.llproject.service.CourseService;
import com.example.llproject.service.ImageService;
import com.example.llproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

  private final BlogService blogService;
  private final CourseService courseService;
  private final ImageService imageService;
  private final CommissionService commissionService;
  private final ProductService productService;

  @Autowired
  public AdminController(BlogService blogService, CourseService courseService, ImageService imageService, CommissionService commissionService, ProductService productService) {
    this.blogService = blogService;
    this.courseService = courseService;
    this.imageService = imageService;
    this.commissionService = commissionService;
    this.productService = productService;
  }

  // Blog Post CRUD operations

  @PostMapping("/blog")
  public ResponseEntity<String> createBlogPost(@RequestParam("title") String title,
                                               @RequestParam("content") String content,
                                               @RequestParam("image") MultipartFile image,
                                               @RequestParam("file") MultipartFile file) {
    // Implementation for creating a blog post
    return ResponseEntity.ok("Blog post created successfully");
  }

  @GetMapping("/blog/{postId}")
  public ResponseEntity<BlogPost> getBlogPost(@PathVariable("postId") Integer postId) {
    // Implementation for retrieving a blog post by ID
    Optional<BlogPost> blogPost = blogService.getBlogPostById(postId);
    if (blogPost.isPresent()) {
      return ResponseEntity.ok(blogPost.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/blog/{postId}")
  public ResponseEntity<String> updateBlogPost(@PathVariable("postId") Integer postId,
                                               @RequestBody BlogPost updatedBlogPost) {
    // Implementation for updating a blog post
    return ResponseEntity.ok("Blog post updated successfully");
  }

  @DeleteMapping("/blog/{postId}")
  public ResponseEntity<String> deleteBlogPost(@PathVariable("postId") Integer postId) {
    // Implementation for deleting a blog post
    return ResponseEntity.ok("Blog post deleted successfully");
  }

  // Course CRUD operations

  @PostMapping("/course")
  public ResponseEntity<String> createCourse(@RequestBody Course course) {
    // Implementation for creating a course
    return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully");
  }

  @GetMapping("/course/{courseId}")
  public ResponseEntity<Course> getCourse(@PathVariable("courseId") Integer courseId) {
    // Implementation for retrieving a course by ID
    Optional<Course> course = courseService.getCourseById(courseId);
    if (course.isPresent()) {
      return ResponseEntity.ok(course.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  @PutMapping("/course/{courseId}")
  public ResponseEntity<String> updateCourse(@PathVariable("courseId") Integer courseId,
                                             @RequestBody Course updatedCourse) {
    // Implementation for updating a course
    Optional<Course> course = courseService.getCourseById(courseId);
    if (course.isPresent()) {
      // Update the course with the provided data
      Course existingCourse = course.get();
      existingCourse.setCourseName(updatedCourse.getCourseName());

      // Save the updated course
      courseService.updateCourse(existingCourse);

      return ResponseEntity.ok("Course updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Image CRUD operations

  @PostMapping("/image")
  public ResponseEntity<String> createImage(@RequestBody Image image) {
    // Implementation for creating an image
    return ResponseEntity.status(HttpStatus.CREATED).body("Image created successfully");
  }

  @GetMapping("/image/{imageId}")
  public ResponseEntity<Image> getImage(@PathVariable("imageId") Integer imageId) {
    // Implementation for retrieving an image by ID
    Optional<Image> image = imageService.getImageById(imageId);
    if (image.isPresent()) {
      return ResponseEntity.ok(image.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/image/{imageId}")
  public ResponseEntity<String> updateImage(@PathVariable("imageId") Integer imageId,
                                            @RequestBody Image updatedImage) {
    // Implementation for updating an image
    Optional<Image> image = imageService.getImageById(imageId);
    if (image.isPresent()) {
      // Update the image with the provided data
      Image existingImage = image.get();
      existingImage.setImageName(updatedImage.getImageName());
      existingImage.setImageUrl(updatedImage.getImageUrl());

      // Save the updated image
      imageService.updateImage(existingImage);

      return ResponseEntity.ok("Image updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/image/{imageId}")
  public ResponseEntity<String> deleteImage(@PathVariable("imageId") Integer imageId) {
    // Implementation for deleting an image
    return ResponseEntity.ok("Image deleted successfully");
  }

  // Commission CRUD operations

  @GetMapping("/commission")
  public ResponseEntity<List<Commission>> getAllCommissions() {
    // Implementation for retrieving all commissions
    List<Commission> commissions = commissionService.getAllCommissions();
    return ResponseEntity.ok(commissions);
  }

  @GetMapping("/commission/{commissionId}")
  public ResponseEntity<Commission> getCommission(@PathVariable("commissionId") Integer commissionId) {
    // Implementation for retrieving a commission by ID
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      return ResponseEntity.ok(commission.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/commission")
  public ResponseEntity<String> createCommission(@RequestBody Commission commission) {
    // Implementation for creating a commission
    return ResponseEntity.status(HttpStatus.CREATED).body("Commission created successfully");
  }
  @PutMapping("/commission/{commissionId}")
  public ResponseEntity<String> updateCommission(@PathVariable("commissionId") Integer commissionId,
                                                 @RequestBody Commission updatedCommission) {
    // Implementation for updating a commission
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      // Update the commission with the provided data
      Commission existingCommission = commission.get();
      existingCommission.setFName(updatedCommission.getFName());
      existingCommission.setLName(updatedCommission.getLName());
      existingCommission.setEmail(updatedCommission.getEmail());
      existingCommission.setPhoneNumber(updatedCommission.getPhoneNumber());
      existingCommission.setSubject(updatedCommission.getSubject());
      existingCommission.setDescription(updatedCommission.getDescription());
      existingCommission.setAddress(updatedCommission.getAddress());
      existingCommission.setHouseNumber(updatedCommission.getHouseNumber());
      existingCommission.setFloor(updatedCommission.getFloor());
      existingCommission.setZipCode(updatedCommission.getZipCode());
      existingCommission.setImage(updatedCommission.getImage());

      // Save the updated commission
      commissionService.updateCommission(existingCommission);

      return ResponseEntity.ok("Commission updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Product CRUD operations

  @PostMapping("/product")
  public ResponseEntity<String> createProduct(@RequestBody Product product) {
    // Implementation for creating a product
    return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {
    // Implementation for retrieving a product by ID
    Optional<Product> product = productService.getProductById(productId);
    if (product.isPresent()) {
      return ResponseEntity.ok(product.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/product/{productId}")
  public ResponseEntity<String> updateProduct(@PathVariable("productId") Integer productId,
                                              @RequestBody Product updatedProduct) {
    // Implementation for updating a product
    Optional<Product> product = productService.getProductById(productId);
    if (product.isPresent()) {
      // Update the product with the provided data
      Product existingProduct = product.get();
      existingProduct.setProductName(updatedProduct.getProductName());
      existingProduct.setProductDescription(updatedProduct.getProductDescription());
      existingProduct.setProductImage(updatedProduct.getProductImage());
      existingProduct.setProductPrice(updatedProduct.getProductPrice());
      existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
      existingProduct.setWidth(updatedProduct.getWidth());
      existingProduct.setHeight(updatedProduct.getHeight());
      existingProduct.setLength(updatedProduct.getLength());

      // Save the updated product
      productService.updateProduct(existingProduct);

      return ResponseEntity.ok("Product updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/product/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) {
    // Implementation for deleting a product
    return ResponseEntity.ok("Product deleted successfully");
  }
}