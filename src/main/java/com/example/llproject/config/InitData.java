package com.example.llproject.config;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.model.Customer;
import com.example.llproject.model.Order;
import com.example.llproject.model.Product;
import com.example.llproject.repository.BlogPostRepo;
import com.example.llproject.repository.CommentRepo;
import com.example.llproject.repository.CustomerRepo;
import com.example.llproject.repository.OrderRepo;
import com.example.llproject.repository.ProductRepo;
import com.example.llproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InitData implements CommandLineRunner {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductService productService;

  @Autowired
  private BlogService blogService;

  @Autowired
  private CommentService commentService;
  @Override
  public void run(String... args) throws Exception {
    initializeData();
  }

  private void initializeData() {
    // Create dummy customers
    List<Customer> customers = createCustomers();

    // Save customers using the service layer
    customers.forEach(customerService::registerCustomer);

    // Create dummy orders
    List<Order> orders = createOrders();

    // Save orders using the service layer
    orders.forEach(orderService::createOrder);

    // Create dummy products
    List<Product> products = createProducts();

    // Save products using the service layer
    products.forEach(productService::createProduct);

    // Create dummy blog posts
    List<BlogPost> blogPosts = createBlogPosts();

    // Save blog posts using the service layer
    blogPosts.forEach(blogService::createBlogPost);

    createComments();
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

  private List<Order> createOrders() {
    List<Order> orders = new ArrayList<>();

    // Create order 1
    Order order1 = new Order();
    order1.setPhoneNumber("1234567890");
    order1.setFName("John");
    order1.setLName("Doe");
    order1.setSubject("Artwork");
    order1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
    order1.setAddress("123 Main St");
    order1.setImage("https://www.hostinger.com/tutorials/wp-content/uploads/sites/2/2022/07/what-is-a-url.webp".getBytes());

    // Create order 2
    Order order2 = new Order();
    order2.setPhoneNumber("9876543210");
    order2.setFName("Jane");
    order2.setLName("Smith");
    order2.setSubject("Photography");
    order2.setDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem.");
    order2.setAddress("456 Elm St");
    order2.setImage("https://d27jswm5an3efw.cloudfront.net/app/uploads/2019/07/how-to-make-a-url-for-a-picture-on-your-computer-4.jpg".getBytes());

    // Add orders to the list
    orders.add(order1);
    orders.add(order2);

    return orders;
  }
  private List<Product> createProducts() {
    List<Product> products = new ArrayList<>();

    // Create product 1
    Product product1 = new Product();
    product1.setProductName("Product 1");
    product1.setProductDescription("Description of product 1");
    product1.setProductPrice(10);
    product1.setProductQuantity(5);
    product1.setWidth("5");
    product1.setHeight("5");
    product1.setLength("5");


    // Create product 2
    Product product2 = new Product();
    product2.setProductName("Product 2");
    product2.setProductDescription("Description of product 2");
    product2.setProductPrice(15);
    product2.setProductQuantity(3);
    product2.setWidth("10");
    product2.setHeight("10");
    product2.setLength("10");

    // Add products to the list
    products.add(product1);
    products.add(product2);

    return products;
  }
  private List<BlogPost> createBlogPosts() {
    List<BlogPost> blogPosts = new ArrayList<>();

    // Create blog post 1
    BlogPost blogPost1 = new BlogPost();
    blogPost1.setTitle("Blog Post 1");
    blogPost1.setContent("Content of blog post 1");
    blogPost1.setCreatedAt(LocalDateTime.now());
    blogPost1.setFileUrl("https://d27jswm5an3efw.cloudfront.net/app/uploads/2019/07/how-to-make-a-url-for-a-picture-on-your-computer-4.jpg");
    blogPost1.setImageUrl("https://d27jswm5an3efw.cloudfront.net/app/uploads/2019/07/how-to-make-a-url-for-a-picture-on-your-computer-4.jpg");


    // Create blog post 2
    BlogPost blogPost2 = new BlogPost();
    blogPost2.setTitle("Blog Post 2");
    blogPost2.setContent("Content of blog post 2");
    blogPost2.setCreatedAt(LocalDateTime.now());
    blogPost2.setFileUrl("https://d27jswm5an3efw.cloudfront.net/app/uploads/2019/07/how-to-make-a-url-for-a-picture-on-your-computer-4.jpg");
    blogPost2.setImageUrl("https://d27jswm5an3efw.cloudfront.net/app/uploads/2019/07/how-to-make-a-url-for-a-picture-on-your-computer-4.jpg");

    // Add blog posts to the list
    blogPosts.add(blogPost1);
    blogPosts.add(blogPost2);

    return blogPosts;
  }

  private void createComments() {
    // Retrieve the blog posts using the blogService
    Optional<BlogPost> optionalBlogPost1 = blogService.getBlogPostById(1);
    Optional<BlogPost> optionalBlogPost2 = blogService.getBlogPostById(2);

    if (optionalBlogPost1.isPresent() && optionalBlogPost2.isPresent()) {
      BlogPost blogPost1 = optionalBlogPost1.get();
      BlogPost blogPost2 = optionalBlogPost2.get();

      // Create comment 1
      Comment comment1 = new Comment();
      comment1.setContent("Comment 1");
      comment1.setCreatedAt(LocalDateTime.now());
      commentService.createComment(blogPost1.getId(), comment1);

      // Create comment 2
      Comment comment2 = new Comment();
      comment2.setContent("Comment 2");
      comment2.setCreatedAt(LocalDateTime.now());
      commentService.createComment(blogPost1.getId(), comment2);

      // Create comment 3
      Comment comment3 = new Comment();
      comment3.setContent("Comment 3");
      comment3.setCreatedAt(LocalDateTime.now());
      commentService.createComment(blogPost2.getId(), comment3);
    } else {
      throw new IllegalArgumentException("BlogPost not found");
    }
  }


  }


