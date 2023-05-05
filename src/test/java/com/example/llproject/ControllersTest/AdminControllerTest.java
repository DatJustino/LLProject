package com.example.llproject.ControllersTest;

import com.example.llproject.controller.AdminController;
import com.example.llproject.model.*;
import com.example.llproject.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

  @Test
  public void createAdminTest() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    Admin admin = new Admin();
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    ResponseEntity<String> response = adminController.createAdmin(admin);

    verify(adminService).createAdmin(admin);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Admin created successfully", response.getBody());
  }

  @Test
  public void getAdminByIdTest_existingAdmin() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    Integer adminId = 1;

    Admin admin = new Admin();
    admin.setAdminId(adminId);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    when(adminService.getAdminById(adminId)).thenReturn(Optional.of(admin));

    ResponseEntity<Admin> response = adminController.getAdmin(adminId);

    verify(adminService).getAdminById(adminId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(admin, response.getBody());
  }

  @Test
  public void getAdminByIdTest_nonExistingAdmin() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    Integer adminId = 1000;

    when(adminService.getAdminById(adminId)).thenReturn(Optional.empty());

    ResponseEntity<Admin> response = adminController.getAdmin(adminId);

    verify(adminService).getAdminById(adminId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  public void updateAdminTest_existingAdmin() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    Admin existingAdmin = new Admin();
    existingAdmin.setAdminId(1);
    existingAdmin.setAdminEmail("admin@example.com");
    existingAdmin.setAdminPassword("password");

    Admin updatedAdmin = new Admin();
    updatedAdmin.setAdminEmail("updatedadmin@example.com");
    updatedAdmin.setAdminPassword("updatedpassword");

    when(adminService.getAdminById(eq(existingAdmin.getAdminId()))).thenReturn(Optional.of(existingAdmin));

    ResponseEntity<String> response = adminController.updateAdmin(existingAdmin.getAdminId(), updatedAdmin);

    verify(adminService).getAdminById(existingAdmin.getAdminId());
    verify(adminService).updateAdmin(existingAdmin.getAdminId(), updatedAdmin);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Admin updated successfully", response.getBody());
  }
  @Test
  public void deleteAdminTest_existingAdmin() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    Integer adminId = 1;

    ResponseEntity<String> response = adminController.deleteAdmin(adminId);

    verify(adminService).deleteAdmin(adminId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Admin deleted successfully", response.getBody());
  }

  @Test
  public void deleteAdminTest_nonExistingAdmin() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    Integer adminId = 1000;

    doThrow(new IllegalArgumentException("Admin not found with ID: " + adminId))
        .when(adminService).deleteAdmin(adminId);

    ResponseEntity<String> response = adminController.deleteAdmin(adminId);

    verify(adminService).deleteAdmin(adminId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

// Other CRUD logic methods and tests

// Blog Post CRUD operations

  @Test
  public void createBlogPostTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the request parameters
    String title = "Test Blog Post";
    String content = "Lorem ipsum dolor sit amet.";

    ResponseEntity<String> response = adminController.createBlogPost(title, content, null, null);

    // Verify the service method is called with the correct argument
    verify(blogService).createBlogPost(eq(title), eq(content), isNull(), isNull());

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Blog post created successfully", response.getBody());
  }
// Course CRUD operations

  @Test
  public void createCourseTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the request body
    Course course = new Course();
    course.setCourseName("Test Course");

    ResponseEntity<String> response = adminController.createCourse(course);

    // Verify the service method is called with the correct argument
    verify(courseService).createCourse(course);

    // Assert the response
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Course created successfully", response.getBody());
  }

// Implement tests for getCourse(), updateCourse(), and deleteCourse() methods

// Image CRUD operations

  @Test
  public void createImageTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the request body
    Image image = new Image();
    image.setImageName("Test Image");

    ResponseEntity<String> response = adminController.createImage(image);

    // Verify the service method is called with the correct argument
    verify(imageService).createImage(image);

    // Assert the response
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Image created successfully", response.getBody());
  }

// Implement tests for getImage(), updateImage(), and deleteImage() methods

// Commission CRUD operations

  @Test
  public void getAllCommissionsTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the mock commissions
    List<Commission> commissions = new ArrayList<>();
    commissions.add(new Commission());
    commissions.add(new Commission());

    // Mock the service method to return the commissions
    when(commissionService.getAllCommissions()).thenReturn(commissions);

    // Call the getAllCommissions method
    ResponseEntity<List<Commission>> response = adminController.getAllCommissions();

    // Verify the service method is called
    verify(commissionService).getAllCommissions();

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(commissions, response.getBody());
  }

// Implement tests for getCommission(), createCommission(), updateCommission(), and deleteCommission() methods

// Product CRUD operations

  @Test
  public void createProductTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the request body
    Product product = new Product();
    product.setProductName("Test Product");

    ResponseEntity<String> response = adminController.createProduct(product);

    // Verify the service method is called with the correct argument
    verify(productService).createProduct(product);

    // Assert the response
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Product created successfully", response.getBody());
  }

// Implement tests for getProduct(), updateProduct(), and deleteProduct() methods

  @Test
  public void getProductTest_nonExistingProduct() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    // Prepare the product ID
    Integer productId = 1000;

    // Mock the service method to return an empty Optional
    when(productService.getProductById(productId)).thenReturn(Optional.empty());

    // Call the getProduct method
    ResponseEntity<Product> response = adminController.getProduct(productId);

    // Verify the service method is called with the correct argument
    verify(productService).getProductById(productId);

    // Assert the response
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

// Commission CRUD operations

  @Test
  public void createCommissionTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the request body
    Commission commission = new Commission();
    commission.setFName("John");
    commission.setLName("Doe");

    ResponseEntity<String> response = adminController.createCommission(commission);

    // Verify the service method is called with the correct argument
    verify(commissionService).createCommission(commission);

    // Assert the response
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Commission created successfully", response.getBody());
  }



  @Test
  public void getCommissionTest_existingCommission() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    // Prepare the commission ID
    Integer commissionId = 1;

    // Prepare the mock commission
    Commission commission = new Commission();
    commission.setCommissionId(commissionId);
    commission.setFName("John");
    commission.setLName("Doe");

    // Mock the service method to return the commission
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.of(commission));

    // Call the getCommission method
    ResponseEntity<Commission> response = adminController.getCommission(commissionId);

    // Verify the service method is called with the correct argument
    verify(commissionService).getCommissionById(commissionId);

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(commission, response.getBody());
  }

  @Test
  public void getCommissionTest_nonExistingCommission() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    // Prepare the commission ID
    Integer commissionId = 1000;

    // Mock the service method to return an empty Optional
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.empty());

    // Call the getCommission method
    ResponseEntity<Commission> response = adminController.getCommission(commissionId);

    // Verify the service method is called with the correct argument
    verify(commissionService).getCommissionById(commissionId);

    // Assert the response
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  public void updateCommissionTest_existingCommission() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    // Prepare the existing commission and updated commission
    Commission existingCommission = new Commission();
    existingCommission.setCommissionId(1);
    existingCommission.setFName("John");
    existingCommission.setLName("Doe");

    Commission updatedCommission = new Commission();
    updatedCommission.setFName("Jane");
    updatedCommission.setLName("Smith");

    // Mock the commissionService behavior
    when(commissionService.getCommissionById(eq(existingCommission.getCommissionId()))).thenReturn(Optional.of(existingCommission));

    // Call the updateCommission method
    ResponseEntity<String> response = adminController.updateCommission(existingCommission.getCommissionId(), updatedCommission);

    // Verify the service method is called with the correct arguments
    verify(commissionService).getCommissionById(existingCommission.getCommissionId());
    verify(commissionService).updateCommission(existingCommission.getCommissionId(), updatedCommission);

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Commission updated successfully", response.getBody());
  }

// Product CRUD operations

  @Test
  public void updateProductTest_existingProduct() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );

    // Prepare the existing product and updated product
    Product existingProduct = new Product();
    existingProduct.setProductId(1);
    existingProduct.setProductName("Product A");
    existingProduct.setProductDescription("Description A");

    Product updatedProduct = new Product();
    updatedProduct.setProductName("Product B");
    updatedProduct.setProductDescription("Description B");

    // Mock the productService behavior
    when(productService.getProductById(eq(existingProduct.getProductId()))).thenReturn(Optional.of(existingProduct));

    // Call the updateProduct method
    ResponseEntity<String> response = adminController.updateProduct(existingProduct.getProductId(), updatedProduct);

    // Verify the service method is called with the correct arguments
    verify(productService).getProductById(existingProduct.getProductId());
    verify(productService).updateProduct(existingProduct.getProductId(), updatedProduct);

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Product updated successfully", response.getBody());
  }

  @Test
  public void deleteProductTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    OrderService orderService = mock(OrderService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService,
        orderService,
        productService
    );
    // Prepare the product ID
    Integer productId = 1;

    // Call the deleteProduct method
    ResponseEntity<String> response = adminController.deleteProduct(productId);

    // Verify the service method is called with the correct argument
    verify(productService).deleteProduct(productId);

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Product deleted successfully", response.getBody());
  }
}