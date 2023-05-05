package com.example.llproject.ControllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.llproject.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.example.llproject.model.Admin;
import com.example.llproject.service.AdminService;
import com.example.llproject.service.BlogService;
import com.example.llproject.service.CommissionService;
import com.example.llproject.service.CourseService;
import com.example.llproject.service.ImageService;
import com.example.llproject.service.ProductService;

public class AdminControllerTest {

  @Test
  public void createAdminTest() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CourseService courseService = mock(CourseService.class);
    ImageService imageService = mock(ImageService.class);
    CommissionService commissionService = mock(CommissionService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        courseService,
        imageService,
        commissionService,
        productService
    );

    // Prepare the request body
    Admin admin = new Admin();
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    // Call the createAdmin method
    ResponseEntity<String> response = adminController.createAdmin(admin);

    // Verify the service method is called with the correct argument
    verify(adminService).createAdmin(admin);

    // Assert the response
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Admin created successfully", response.getBody());
  }

  @Test
  public void getAdminByIdTest_existingAdmin() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CourseService courseService = mock(CourseService.class);
    ImageService imageService = mock(ImageService.class);
    CommissionService commissionService = mock(CommissionService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        courseService,
        imageService,
        commissionService,
        productService
    );

    // Prepare the admin ID
    Integer adminId = 1;

    // Prepare the mock admin
    Admin admin = new Admin();
    admin.setAdminId(adminId);
    admin.setAdminEmail("admin@example.com");
    admin.setAdminPassword("password");

    // Mock the service method to return the admin
    when(adminService.getAdminById(adminId)).thenReturn(Optional.of(admin));

    // Call the getAdminById method
    ResponseEntity<Admin> response = adminController.getAdmin(adminId);

    // Verify the service method is called with the correct argument
    verify(adminService).getAdminById(adminId);

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(admin, response.getBody());
  }

  @Test
  public void getAdminByIdTest_nonExistingAdmin() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CourseService courseService = mock(CourseService.class);
    ImageService imageService = mock(ImageService.class);
    CommissionService commissionService = mock(CommissionService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        courseService,
        imageService,
        commissionService,
        productService
    );

    // Prepare the admin ID
    Integer adminId = 1000;

    // Mock the service method to return an empty Optional
    when(adminService.getAdminById(adminId)).thenReturn(Optional.empty());

    // Call the getAdminById method
    ResponseEntity<Admin> response = adminController.getAdmin(adminId);

    // Verify the service method is called with the correct argument
    verify(adminService).getAdminById(adminId);

    // Assert the response
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  public void updateAdminTest_existingAdmin() {
    // Mock the dependencies
    AdminService adminService = mock(AdminService.class);
    BlogService blogService = mock(BlogService.class);
    CourseService courseService = mock(CourseService.class);
    ImageService imageService = mock(ImageService.class);
    CommissionService commissionService = mock(CommissionService.class);
    ProductService productService = mock(ProductService.class);

    AdminController adminController = new AdminController(
        adminService,
        blogService,
        courseService,
        imageService,
        commissionService,
        productService
    );

    // Prepare the existing admin and updated admin
    Admin existingAdmin = new Admin();
    existingAdmin.setAdminId(1);
    existingAdmin.setAdminEmail("admin@example.com");
    existingAdmin.setAdminPassword("password");

    Admin updatedAdmin = new Admin();
    updatedAdmin.setAdminEmail("updatedadmin@example.com");
    updatedAdmin.setAdminPassword("updatedpassword");

    // Mock the adminService behavior
    when(adminService.getAdminById(eq(existingAdmin.getAdminId()))).thenReturn(Optional.of(existingAdmin));

    // Call the updateAdmin method
    ResponseEntity<String> response = adminController.updateAdmin(existingAdmin.getAdminId(), updatedAdmin);

    // Verify the service method is called with the correct arguments
    verify(adminService).getAdminById(existingAdmin.getAdminId());
    verify(adminService).updateAdmin(existingAdmin.getAdminId(), updatedAdmin);

    // Assert the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Admin updated successfully", response.getBody());
  }
}