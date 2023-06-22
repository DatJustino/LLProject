/*
package com.example.llproject.ControllersTest;

import com.example.llproject.controller.AdminController;
import com.example.llproject.controller.BlogPostController;
import com.example.llproject.controller.CommissionController;
import com.example.llproject.model.*;
import com.example.llproject.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    Admin admin = new Admin();
    admin.setAdminId(100);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    ResponseEntity<Admin> response = adminController.createAdmin(admin);

    verify(adminService).createAdmin(admin);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(admin, response.getBody());
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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );
    Integer adminId = 1;
    Admin admin = new Admin();
    admin.setAdminId(adminId);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    when(adminService.getAdminById(adminId)).thenReturn(Optional.of(admin));

    ResponseEntity<Admin> response = adminController.getAdminById(adminId);

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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );
    Integer adminId = 1000;

    when(adminService.getAdminById(adminId)).thenReturn(Optional.empty());

    ResponseEntity<Admin> response = adminController.getAdminById(adminId);

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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    Admin existingAdmin = new Admin();
    existingAdmin.setAdminId(1);
    existingAdmin.setAdminEmail("admin@example.com");
    existingAdmin.setAdminPassword("password");

    Admin updatedAdmin = new Admin();
    updatedAdmin.setAdminEmail("updatedadmin@example.com");
    updatedAdmin.setAdminPassword("updatedpassword");

    when(adminService.getAdminById(eq(existingAdmin.getAdminId()))).thenReturn(Optional.of(existingAdmin));

    ResponseEntity<Admin> response = adminController.updateAdmin(existingAdmin.getAdminId(), updatedAdmin);

    verify(adminService).getAdminById(existingAdmin.getAdminId());
    verify(adminService).updateAdmin(existingAdmin.getAdminId(), updatedAdmin);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response, response.getBody());
  }@Test
  public void deleteAdminTest_existingAdmin() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    Integer adminId = 1;

    doNothing().when(adminService).deleteAdmin(adminId);

    ResponseEntity<Void> response = adminController.deleteAdmin(adminId);

    verify(adminService).deleteAdmin(adminId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNull(response.getBody());
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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    Integer adminId = 1000;

    doThrow(new IllegalArgumentException("Admin not found with ID: " + adminId))
        .when(adminService).deleteAdmin(adminId);

    ResponseEntity<Void> response = adminController.deleteAdmin(adminId);

    verify(adminService).deleteAdmin(adminId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

////////////////////////////// Blog Post CRUD operations //////////////////////////////

  @Test
  public void testCreateBlogPost() {
    BlogPost blogPost = new BlogPost();
    blogPost.setTitle("Test Blog Post");
    blogPost.setContent("Lorem ipsum dolor sit amet.");

    BlogService blogService = mock(BlogService.class);

    BlogPostController blogPostController = new BlogPostController(blogService);

    ResponseEntity<BlogPost> response = blogPostController.createBlogPost(blogPost);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    verify(blogService, times(1)).createBlogPost(blogPost);
  }

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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );
    Course course = new Course();
    course.setCourseName("Test Course");

    ResponseEntity<Course> response = adminController.createCourse(course);

    verify(courseService).createCourse(course);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Course created successfully", response.getBody());
  }

////////////////////////////////// Image CRUD operations //////////////////////////////

  @Test
  public void createImageTest() {
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );
    Image image = new Image();
    image.setImageName("Test Image");

    ResponseEntity<String> response = adminController.createImage(image);

    verify(imageService).createImage(image);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Image created successfully", response.getBody());
  }

///////////////////////////////////////// Commission CRUD operations ///////////////////////////////////////

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
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );
    List<Commission> commissions = new ArrayList<>();
    commissions.add(new Commission());
    commissions.add(new Commission());

    when(commissionService.getAllCommissions()).thenReturn(commissions);

    ResponseEntity<List<Commission>> response = adminController.getAllCommissions();

    verify(commissionService).getAllCommissions();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(commissions, response.getBody());
  }

///////////////////////////////////////// Commission CRUD operations ///////////////////////////////////////

  @Test
  public void testCreateCommission() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CommentService commentService = mock(CommentService.class);
    CommissionService commissionService = mock(CommissionService.class);
    CommissionController commissionController = mock(CommissionController.class);
    CourseService courseService = mock(CourseService.class);
    CustomerService customerService = mock(CustomerService.class);
    ImageService imageService = mock(ImageService.class);

*/
/*
    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );
*//*


    // Arrange
    String firstname = "John";
    String lastname = "Doe";
    String email = "john.doe@example.com";
    String phonenumber = "1234567890";
    String subject = "Test Commission";
    String description = "This is a test commission";
    String pageformat1 = "A4";
    String pageformat2 = "Portrait";
    LocalDate deliverydate = LocalDate.now();
    String street = "Main Street";
    Integer housenumber = 123;
    String floor = "2nd";
    Integer zipcode = 12345;
    String imageurl1 = "http://example.com/image1.jpg";
    String imageurl2 = "http://example.com/image2.jpg";
    String imageurl3 = "http://example.com/image3.jpg";

    Commission commission = new Commission();
    commission.setFirstname(firstname);
    commission.setLastname(lastname);
    commission.setEmail(email);
    commission.setPhonenumber(phonenumber);
    commission.setSubject(subject);
    commission.setDescription(description);
    commission.setPageformat1(pageformat1);
    commission.setPageformat2(pageformat2);
    commission.setDeliverydate(deliverydate);
    commission.setStreet(street);
    commission.setHousenumber(housenumber);
    commission.setFloor(floor);
    commission.setZipcode(zipcode);
    commission.setImageurl1(imageurl1);
    commission.setImageurl2(imageurl2);
    commission.setImageurl3(imageurl3);

    Commission createdCommission = new Commission();
    // Set necessary properties of the createdCommission object

    when(commissionService.createCommission(commission)).thenReturn(createdCommission);

    // Act
    ResponseEntity<Commission> response = commissionController.createCommission(
        firstname, lastname, email, phonenumber, subject, description,
        pageformat1, pageformat2, deliverydate, street, housenumber,
        floor, zipcode, imageurl1, imageurl2, imageurl3);

    // Assert
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(createdCommission, response.getBody());
    verify(commissionService, times(1)).createCommission(commission);
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

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    // Prepare the commission ID
    Integer commissionId = 1;

    // Prepare the mock commission
    Commission commission = new Commission();
    commission.setCommissionId(commissionId);
    commission.setFirstname("John");
    commission.setLastname("Doe");

    // Mock the service method to return the commission
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.of(commission));

    // Call the getCommission method
    ResponseEntity<Commission> response = adminController.getCommissionById(commissionId);

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

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    // Prepare the commission ID
    Integer commissionId = 1000;

    // Mock the service method to return an empty Optional
    when(commissionService.getCommissionById(commissionId)).thenReturn(Optional.empty());

    // Call the getCommission method
    ResponseEntity<Commission> response = adminController.getCommissionById(commissionId);

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

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        commentService,
        commissionService,
        courseService,
        customerService,
        imageService
    );

    // Prepare the existing commission and updated commission
    Commission existingCommission = new Commission();
    existingCommission.setCommissionId(1);
    existingCommission.setFirstname("John");
    existingCommission.setLastname("Doe");

    Commission updatedCommission = new Commission();
    updatedCommission.setFirstname("Jane");
    updatedCommission.setLastname("Smith");

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

}
*/
