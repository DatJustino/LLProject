package com.example.llproject.config;

import com.example.llproject.model.*;
import com.example.llproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

  @Autowired
  public InitData(AdminRepository adminRepository, BlogPostRepository blogPostRepository,
                  CommentRepository commentRepository, CommissionRepository commissionRepository,
                  CourseRepository courseRepository, CustomerRepository customerRepository) {
    this.adminRepository = adminRepository;
    this.blogPostRepository = blogPostRepository;
    this.commentRepository = commentRepository;
    this.commissionRepository = commissionRepository;
    this.courseRepository = courseRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    initializeAdminData();
    initializeBlogPostData();
    initializeCommissionData();
    initializeCourseData();
    initializeCustomerData();
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
    adminRepository.saveAll(List.of(admin1, admin2));
  }

  private void initializeBlogPostData() {
    // Check if the blog posts already exist in the database
    if (blogPostRepository.count() > 0) {
      return; // Skip initialization if data already exists
    }

    // Create dummy blog posts
    List<BlogPost> blogPosts = createBlogPosts();

    // Save blog posts to the database
    blogPostRepository.saveAll(blogPosts);

    createComments(blogPosts);
  }

  private List<BlogPost> createBlogPosts() {
    List<BlogPost> blogPosts = new ArrayList<>();

    // Create blog post 1
    BlogPost blogPost1 = new BlogPost();
    blogPost1.setHeaderTitle("Header Title 1");
    blogPost1.setTitle("Blog Post 1");
    blogPost1.setContent("Content of blog post 1");
    blogPost1.setDescription("Description of blog post 1");
    blogPost1.setCreatedAt(LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
    blogPost1.setImageUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    blogPost1.setFileUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    blogPosts.add(blogPost1);

    // Create blog post 2
    BlogPost blogPost2 = new BlogPost();
    blogPost2.setHeaderTitle("Header Title 2");
    blogPost2.setTitle("Blog Post 2");
    blogPost2.setContent("Content of blog post 2");
    blogPost2.setDescription("Description of blog post 2");
    blogPost2.setCreatedAt(LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
    blogPost2.setImageUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    blogPost2.setFileUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    blogPosts.add(blogPost2);

    // Return blog posts
    return blogPosts;
  }

  private void createComments(List<BlogPost> blogPosts) {
    List<Comment> comments = new ArrayList<>();

    // Create comments for each blog post
    for (BlogPost blogPost : blogPosts) {
      // Create comment 1
      Comment comment1 = new Comment();
      comment1.setContent("Comment 1 for " + blogPost.getTitle());
      comment1.setCreatedAt(LocalDateTime.now());
      comment1.setBlogPost(blogPost);
      comment1.setUserName("John Doe");
      comment1.setIpAddress("255.255.255.255");
      comments.add(comment1);

      // Create comment 2
      Comment comment2 = new Comment();
      comment2.setContent("Comment 2 for " + blogPost.getTitle());
      comment2.setCreatedAt(LocalDateTime.now());
      comment2.setBlogPost(blogPost);
      comment2.setUserName("John Doe");
      comment2.setIpAddress("255.255.255.255");
      comments.add(comment2);
    }

    // Save comments to the database
    commentRepository.saveAll(comments);
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
    commission1.setFirstname("John");
    commission1.setLastname("Doe");
    commission1.setEmail("john.doe@example.com");
    commission1.setPhonenumber("1234567890");
    commission1.setSubject("Artwork Commission");
    commission1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
    commission1.setStreet("123 Main St");
    commission1.setHousenumber(10);
    commission1.setFloor("2");
    commission1.setZipcode(12345);
    commission1.setDeliverydate(LocalDate.now().plusDays(14));
    commission1.setPageformat1("A4");
    commission1.setPageformat2("landscape");
    commission1.setImageurl1("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    commission1.setImageurl2("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    commission1.setImageurl3("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");

    // Create commission 2
    Commission commission2 = new Commission();
    commission2.setFirstname("Jane");
    commission2.setLastname("Smith");
    commission2.setEmail("jane.smith@example.com");
    commission2.setPhonenumber("9876543210");
    commission2.setSubject("Portrait Commission");
    commission2.setDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem.");
    commission2.setStreet("456 Elm St");
    commission2.setHousenumber(20);
    commission2.setFloor("3");
    commission2.setZipcode(54321);
    //TODO: Add delivery date that's not hardcoded !IMPORTANT!
    commission2.setDeliverydate(LocalDate.now().plusDays(14));
    commission2.setPageformat1("A4");
    commission2.setPageformat2("landscape");
    commission2.setImageurl1("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    commission2.setImageurl2("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    commission2.setImageurl3("https://images.unsplash.com/photo-1515879218367-8466d910aaa4?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");

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
    course1.setCourseDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum ornare sem, sed " +
        "ullamcorper neque eleifend quis. Fusce vel lorem tincidunt, finibus nunc eu, ultrices leo. Maecenas accumsan " +
        "vel felis ac suscipit. Nullam interdum et diam sit amet malesuada. Phasellus tempus, nulla eget consequat " +
        "malesuada, nisi quam ultricies arcu, ut imperdiet nibh turpis quis quam. Nulla eleifend at nisl ut convallis." +
        " Ut varius at tellus eget pulvinar. Mauris fringilla condimentum nisl vel eleifend. Etiam volutpat quam ut ante" +
        " cursus, quis faucibus augue porta. Donec condimentum, ipsum id luctus molestie, risus risus volutpat ipsum," +
        " ut aliquam risus ante vel urna. Nulla facilisi. Suspendisse nec libero justo. Praesent vulputate convallis " +
        "varius. Curabitur sagittis mollis fringilla. ");
    course1.setCourseImageUrl("../../images/misc/miscUdsnit med kort.jpg");

    // Create course 2
    Course course2 = new Course();
    course2.setCourseName("Advanced Photography");
    course2.setCourseDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum ornare sem, sed " +
        "ullamcorper neque eleifend quis. Fusce vel lorem tincidunt, finibus nunc eu, ultrices leo. Maecenas accumsan " +
        "vel felis ac suscipit. Nullam interdum et diam sit amet malesuada. Phasellus tempus, nulla eget consequat " +
        "malesuada, nisi quam ultricies arcu, ut imperdiet nibh turpis quis quam. Nulla eleifend at nisl ut convallis." +
        " Ut varius at tellus eget pulvinar. Mauris fringilla condimentum nisl vel eleifend. Etiam volutpat quam ut ante" +
        " cursus, quis faucibus augue porta. Donec condimentum, ipsum id luctus molestie, risus risus volutpat ipsum," +
        " ut aliquam risus ante vel urna. Nulla facilisi. Suspendisse nec libero justo. Praesent vulputate convallis " +
        "varius. Curabitur sagittis mollis fringilla. Cras convallis dui sit amet neque tempor, sed dictum orci blandit." +
        "Cras vel libero sit amet est mattis placerat. Fusce lacinia sem vel neque consectetur, sed dictum dui " +
        "sagittis. Nam fringilla diam sed erat blandit interdum. Fusce laoreet euismod interdum. Pellentesque in " +
        "dictum est. Duis eget bibendum sem, in pulvinar risus. Quisque ut dolor varius, dapibus arcu vel, cursus " +
        "orci. Mauris ultricies mi ut ex ullamcorper mollis.");
    course2.setCourseImageUrl("../../images/misc/miscRAS badge.jpg");

    // Create course 3
    Course course3 = new Course();
    course3.setCourseName("Advanced Drawing");
    course3.setCourseDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum ornare sem, sed " +
        "ullamcorper neque eleifend quis. Fusce vel lorem tincidunt, finibus nunc eu, ultrices leo. Maecenas accumsan " +
        "vel felis ac suscipit. Nullam interdum et diam sit amet malesuada. Phasellus tempus, nulla eget consequat " +
        "malesuada, nisi quam ultricies arcu, ut imperdiet nibh turpis quis quam. Nulla eleifend at nisl ut convallis." +
        " Ut varius at tellus eget pulvinar. Mauris fringilla condimentum nisl vel eleifend. Etiam volutpat quam ut ante" +
        " cursus, quis faucibus augue porta. Donec condimentum, ipsum id luctus molestie, risus risus volutpat ipsum," +
        " ut aliquam risus ante vel urna. Nulla facilisi. Suspendisse nec libero justo. Praesent vulputate convallis " +
        "varius. Curabitur sagittis mollis fringilla. Cras convallis dui sit amet neque tempor, sed dictum orci blandit." +
        "Cras vel libero sit amet est mattis placerat. Fusce lacinia sem vel neque consectetur, sed dictum dui " +
        "sagittis. Nam fringilla diam sed erat blandit interdum. Fusce laoreet euismod interdum. Pellentesque in " +
        "dictum est. Duis eget bibendum sem, in pulvinar risus. Quisque ut dolor varius, dapibus arcu vel, cursus " +
        "orci. Mauris ultricies mi ut ex ullamcorper mollis.\n" +
        "Aenean vel egestas dui. Nulla porttitor enim ut euismod ornare. Suspendisse sit amet sapien sit amet purus" +
        " ullamcorper gravida eu sed massa. Morbi placerat urna et diam feugiat, vel consequat velit rhoncus." +
        " Nam lobortis massa a turpis pretium volutpat. Vestibulum ante ipsum primis in faucibus orci luctus et " +
        "ultrices posuere cubilia curae; Sed ac scelerisque erat. Phasellus dapibus luctus lorem et fringilla. " +
        "Ut nisi sapien, tristique non posuere sit amet, rhoncus a ipsum. Interdum et malesuada fames ac" +
        " ante ipsum primis in faucibus. Phasellus sem ligula, ullamcorper vitae ex eu, sodales dictum eros. " +
        "Donec vestibulum risus odio, mattis varius massa facilisis vitae. Curabitur malesuada aliquam mauris, " +
        "sit amet lobortis tellus tincidunt quis. Nunc condimentum ante ac elit fermentum malesuada. Suspendisse" +
        " potenti. Ut vestibulum sed elit sed imperdiet.");
    course3.setCourseImageUrl("../../images/misc/miscKænguru.jpg");


    // Add courses to the list
    courses.add(course1);
    courses.add(course2);
    courses.add(course3);

    return courses;
  }

  private void initializeCustomerData() {
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
}
