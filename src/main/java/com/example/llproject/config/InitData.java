package com.example.llproject.config;

import com.example.llproject.model.*;
import com.example.llproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

  private final AdminRepository adminRepository;
  private final BlogPostRepository blogPostRepository;
  private final CommentRepository commentRepository;
  private final CommissionRepository commissionRepository;
  private final CourseRepository courseRepository;
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final ImageRepository imageRepository;


  @Autowired
  public InitData(AdminRepository adminRepository, BlogPostRepository blogPostRepository,
                  CommentRepository commentRepository, CommissionRepository commissionRepository,
                  CourseRepository courseRepository, CustomerRepository customerRepository,
                  OrderRepository orderRepository, ProductRepository productRepository,
                  ImageRepository imageRepository)
  {
    this.adminRepository = adminRepository;
    this.blogPostRepository = blogPostRepository;
    this.commentRepository = commentRepository;
    this.commissionRepository = commissionRepository;
    this.courseRepository = courseRepository;
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.imageRepository = imageRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    initializeAdminData();
    initializeBlogPostData();
    initializeCommissionData();
    initializeCourseData();
    initializeProductData();
    initializeCustomerData();
    initializeImageData();
    initializeOrderData();
  }

  private void initializeAdminData() {
    // Create dummy admins
    Admin admin1 = new Admin();
    admin1.setAdminEmail("admin1@example.com");
    admin1.setAdminPassword("password1");

    Admin admin2 = new Admin();
    admin2.setAdminEmail("admin2@example.com");
    admin2.setAdminPassword("password2");

    // Save admins to the database
    adminRepository.save(admin1);
    adminRepository.save(admin2);
  }

  private void initializeBlogPostData() {
    // Create dummy blog posts
    List<BlogPost> blogPosts = createBlogPosts();

    // Save blog posts to the database
    blogPostRepository.saveAll(blogPosts);

    // Create dummy comments
    List<Comment> comments = createComments(blogPosts);

    // Save comments to the database
    commentRepository.saveAll(comments);
  }

  private List<BlogPost> createBlogPosts() {
    List<BlogPost> blogPosts = new ArrayList<>();

    // Create blog post 1
    BlogPost blogPost1 = new BlogPost();
    blogPost1.setTitle("Blog Post 1");
    blogPost1.setContent("Content of blog post 1");
    blogPost1.setCreatedAt(LocalDateTime.now());
    blogPost1.setImageUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    blogPost1.setFileUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");

    // Create blog post 2
    BlogPost blogPost2 = new BlogPost();
    blogPost2.setTitle("Blog Post 2");
    blogPost2.setContent("Content of blog post 2");
    blogPost2.setCreatedAt(LocalDateTime.now());
    blogPost2.setImageUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    blogPost2.setFileUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");

    // Add blog posts to the list
    blogPosts.add(blogPost1);
    blogPosts.add(blogPost2);

    return blogPosts;
  }

  private List<Comment> createComments(List<BlogPost> blogPosts) {
    List<Comment> comments = new ArrayList<>();

    // Create comment 1 for blog post 1
    Comment comment1 = new Comment();
    comment1.setContent("Comment 1 for Blog Post 1");
    comment1.setCreatedAt(LocalDateTime.now());
    comment1.setBlogPost(blogPosts.get(0));

    // Create comment 2 for blog post 1
    Comment comment2 = new Comment();
    comment2.setContent("Comment 2 for Blog Post 1");
    comment2.setCreatedAt(LocalDateTime.now());
    comment2.setBlogPost(blogPosts.get(0));

    // Create comment 3 for blog post 2
    Comment comment3 = new Comment();
    comment3.setContent("Comment 1 for Blog Post 2");
    comment3.setCreatedAt(LocalDateTime.now());
    comment3.setBlogPost(blogPosts.get(1));

    // Add comments to the list
    comments.add(comment1);
    comments.add(comment2);
    comments.add(comment3);

    return comments;
  }

  private void initializeCommissionData() {
    // Create dummy commissions
    List<Commission> commissions = createCommissions();

    // Save commissions to the database
    commissionRepository.saveAll(commissions);
  }

  private List<Commission> createCommissions() {
    List<Commission> commissions = new ArrayList<>();

    // Create commission 1
    Commission commission1 = new Commission();
    commission1.setFName("John");
    commission1.setLName("Doe");
    commission1.setEmail("john.doe@example.com");
    commission1.setPhoneNumber("1234567890");
    commission1.setSubject("Artwork Commission");
    commission1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
    commission1.setAddress("123 Main St");
    commission1.setHouseNumber(10);
    commission1.setFloor("2");
    commission1.setZipCode(12345);
    commission1.setImage("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80".getBytes());

    // Create commission 2
    Commission commission2 = new Commission();
    commission2.setFName("Jane");
    commission2.setLName("Smith");
    commission2.setEmail("jane.smith@example.com");
    commission2.setPhoneNumber("9876543210");
    commission2.setSubject("Portrait Commission");
    commission2.setDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem.");
    commission2.setAddress("456 Elm St");
    commission2.setHouseNumber(20);
    commission2.setFloor("3");
    commission2.setZipCode(54321);
    commission2.setImage("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80".getBytes());

    // Add commissions to the list
    commissions.add(commission1);
    commissions.add(commission2);

    return commissions;
  }

  private void initializeCourseData() {
    // Create dummy courses
    List<Course> courses = createCourses();

    // Save courses to the database
    courseRepository.saveAll(courses);
  }

  private List<Course> createCourses() {
    List<Course> courses = new ArrayList<>();

    // Create course 1
    Course course1 = new Course();
    course1.setCourseName("Introduction to Painting");

    // Create course 2
    Course course2 = new Course();
    course2.setCourseName("Advanced Photography");

    // Add courses to the list
    courses.add(course1);
    courses.add(course2);

    return courses;
  } private void initializeCustomerData() {
    // Create dummy customers
    List<Customer> customers = createCustomers();

    // Save customers to the database
    customerRepository.saveAll(customers);
  }

  private List<Customer> createCustomers() {
    List<Customer> customers = new ArrayList<>();

    // Create customer 1
    Customer customer1 = new Customer();
    customer1.setCEmail("customer1@example.com");
    customer1.setCPassword("password1");

    // Create customer 2
    Customer customer2 = new Customer();
    customer2.setCEmail("customer2@example.com");
    customer2.setCPassword("password2");

    // Add customers to the list
    customers.add(customer1);
    customers.add(customer2);

    return customers;
  }

  private void initializeImageData() {
    // Create dummy images
    List<Image> images = createImages();

    // Save images to the database
    imageRepository.saveAll(images);
  }

  private List<Image> createImages() {
    List<Image> images = new ArrayList<>();

    // Create image 1
    Image image1 = new Image();
    image1.setImageName("Image 1");
    image1.setImageUrl("https://example.com/image1.jpg");

    // Create image 2
    Image image2 = new Image();
    image2.setImageName("Image 2");
    image2.setImageUrl("https://example.com/image2.jpg");

    // Add images to the list
    images.add(image1);
    images.add(image2);

    return images;
  }
  private void initializeOrderData() {
    // Create dummy orders
    List<Order> orders = createOrders();

    // Save orders to the database
    orderRepository.saveAll(orders);
  }

  private List<Order> createOrders() {
    List<Order> orders = new ArrayList<>();

    // Create order 1
    Order order1 = new Order();
    order1.setFName("John");
    order1.setLName("Doe");
    order1.setEmail("john@example.com");
    order1.setPhoneNumber("1234567890");
    order1.setAddress("123 Main St");
    order1.setFloor("10stth");
    order1.setHouseNumber(12);
    order1.setZipCode(12345);

    // Create order 2
    Order order2 = new Order();
    order2.setFName("Jane");
    order2.setLName("Smith");
    order2.setEmail("jane@example.com");
    order2.setPhoneNumber("9876543210");
    order2.setAddress("456 Elm St");
    order2.setFloor("10stth");
    order2.setHouseNumber(12);
    order2.setZipCode(12345);
    // Add orders to the list
    orders.add(order1);
    orders.add(order2);

    return orders;
  }

  private void initializeProductData() {
    // Create dummy products
    List<Product> products = createProducts();

    // Save products to the database
    productRepository.saveAll(products);
  }

  private List<Product> createProducts() {
    List<Product> products = new ArrayList<>();

    // Create product 1
    Product product1 = new Product();
    product1.setProductName("Product 1");
    product1.setProductDescription("Description of Product 1");
    product1.setProductPrice(10.0);
    product1.setProductQuantity(5);
    product1.setProductImage("https://example.com/image.jpg");
    product1.setHeight(10.0);
    product1.setWidth(10.0);
    product1.setLength(10.0);

    // Create product 2
    Product product2 = new Product();
    product2.setProductName("Product 2");
    product2.setProductDescription("Description of Product 2");
    product2.setProductPrice(15.0);
    product2.setProductQuantity(3);
    product2.setProductImage("https://example.com/image.jpg");
    product2.setHeight(10.0);
    product2.setWidth(10.0);
    product2.setLength(10.0);

    // Add products to the list
    products.add(product1);
    products.add(product2);

    return products;
  }


}