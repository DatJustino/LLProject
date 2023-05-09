package com.example.llproject.controller;

import com.example.llproject.model.*;
import com.example.llproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "Authorization"})
@RestControllerAdvice
@RequestMapping("/admin")
public class AdminController {
  private final AdminService adminService;
  private final BlogService blogService;
  private final CommentService commentService;
  private final CommissionService commissionService;
  private final CourseService courseService;
  private final CustomerService customerService;
  private final ImageService imageService;
/*
  private final BCryptPasswordEncoder passwordEncoder;
*/


  @Autowired
  public AdminController(
      AdminService adminService,
      BlogService blogService,
      CommentService commentService,
      CommissionService commissionService,
      CourseService courseService,
      CustomerService customerService,
      ImageService imageService
/*
      BCryptPasswordEncoder passwordEncoder
*/
  )
  {
    this.blogService = blogService;
    this.adminService = adminService;
    this.commentService = commentService;
    this.commissionService = commissionService;
    this.courseService = courseService;
    this.customerService = customerService;
    this.imageService = imageService;
/*
    this.passwordEncoder = passwordEncoder;
*/
  }

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
/*      String encodedPassword = passwordEncoder.encode(admin.getAdminPassword());
      admin.setAdminPassword(encodedPassword);*/
      adminService.createAdmin(admin);
      return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer adminId) {
      Optional<Admin> adminOptional = adminService.getAdminById(adminId);
      return adminOptional.map(admin -> new ResponseEntity<>(admin, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
      List<Admin> admins = adminService.getAllAdmins();
      return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer adminId, @RequestBody Admin admin) {
      Optional<Admin> adminOptional = adminService.getAdminById(adminId);
      if (adminOptional.isPresent()) {
        Admin existingAdmin = adminOptional.get();
        existingAdmin.setAdminEmail(admin.getAdminEmail());
/*        String encodedPassword = passwordEncoder.encode(admin.getAdminPassword());
        existingAdmin.setAdminPassword(encodedPassword);*/
        adminService.updateAdmin(adminId, existingAdmin);
        return new ResponseEntity<>(existingAdmin, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer adminId) {
      Optional<Admin> adminOptional = adminService.getAdminById(adminId);
      if (adminOptional.isPresent()) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

  // Blog Post CRUD operations

  @PostMapping
  public ResponseEntity<Void> createBlogPost(@RequestBody BlogPost blogPost) {
    blogService.createBlogPost(blogPost);
    return ResponseEntity.status(HttpStatus.CREATED).build();
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
    Optional<Image> image = imageService.getImageById(imageId);
    if (image.isPresent()) {
      Image existingImage = image.get();
      existingImage.setImageName(updatedImage.getImageName());
      existingImage.setImageUrl(updatedImage.getImageUrl());
      imageService.updateImage(imageId, existingImage);
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
      existingCommission.setStreet(updatedCommission.getStreet());
      existingCommission.setHouseNumber(updatedCommission.getHouseNumber());
      existingCommission.setFloor(updatedCommission.getFloor());
      existingCommission.setZipCode(updatedCommission.getZipCode());
      existingCommission.setImage1(updatedCommission.getImage1());
      existingCommission.setImage2(updatedCommission.getImage2());
      existingCommission.setImage3(updatedCommission.getImage3());

      // Save the updated commission
      commissionService.updateCommission(existingCommission.getCommissionId(), existingCommission);

      return ResponseEntity.ok("Commission updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}