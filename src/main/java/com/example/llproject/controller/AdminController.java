package com.example.llproject.controller;

import com.example.llproject.model.*;
import com.example.llproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
  ) {
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
  @GetMapping("/blog")
  public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
    List<BlogPost> blogPosts = blogService.getAllBlogPosts();
    return ResponseEntity.ok(blogPosts);
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

  @PostMapping("/blog")
  public ResponseEntity<Void> createBlogPost(@RequestBody BlogPost blogPost) {
    blogService.createBlogPost(blogPost);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


  @PutMapping("/blog/{postId}")
  public ResponseEntity<String> updateBlogPost(@PathVariable("postId") Integer postId,
                                               @RequestBody BlogPost updatedBlogPost) {
    blogService.updateBlogPost(postId, updatedBlogPost);
    return ResponseEntity.ok("Blog post updated successfully");
  }

  @DeleteMapping("/blog/{postId}")
  public ResponseEntity<String> deleteBlogPost(@PathVariable("postId") Integer postId) {
blogService.deleteBlogPost(postId);
  return ResponseEntity.ok("Blog post deleted successfully");
  }

  ///////////////////////////////////// Course CRUD operations ////////////////////////////

  @GetMapping("/courses")
  public ResponseEntity<List<Course>> getAllCourses() {
    List<Course> courses = courseService.getAllCourses();
    return ResponseEntity.ok(courses);
  }

  @GetMapping("/courses/{courseId}")
  public ResponseEntity<Course> getCourseById(@PathVariable() Integer courseId) {
    Optional<Course> course = courseService.getCourseById(courseId);
    return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/courses")
  public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    Course createdCourse = courseService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
  }

  @PutMapping("/courses/{courseId}")
  public ResponseEntity<String> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody Course updatedCourse) {
    Optional<Course> course = courseService.getCourseById(courseId);
    if (course.isPresent()) {
      updatedCourse.setCourseId(courseId);
      courseService.updateCourse(updatedCourse);
      return ResponseEntity.ok("Course updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/courses/{courseId}")
  public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Integer courseId) {
    Optional<Course> course = courseService.getCourseById(courseId);
    if (course.isPresent()) {
      courseService.deleteCourse(courseId);
      return ResponseEntity.ok("Course deleted successfully");
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
  @GetMapping("/commissions")
  public ResponseEntity<List<Commission>> getAllCommissions() {
    List<Commission> commissions = commissionService.getAllCommissions();
    return ResponseEntity.ok(commissions);
  }

  @GetMapping("/commissions/{commissionId}")
  public ResponseEntity<Commission> getCommissionById(@PathVariable("commissionId") Integer commissionId) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    return commission.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }


  @PostMapping("/commissions")
  public ResponseEntity<Commission> createCommission(
      @RequestParam("firstname") String firstname,
      @RequestParam("lastname") String lastname,
      @RequestParam("email") String email,
      @RequestParam("phonenumber") String phonenumber,
      @RequestParam("subject") String subject,
      @RequestParam("description") String description,
      @RequestParam("pageformat1") String pageformat1,
      @RequestParam("pageformat2") String pageformat2,
      @RequestParam("deliverydate") LocalDate deliverydate,
      @RequestParam("street") String street,
      @RequestParam("housenumber") Integer housenumber,
      @RequestParam("floor") String floor,
      @RequestParam("zipcode") Integer zipcode,
      @RequestParam("imageurl1") String imageurl1,
      @RequestParam("imageurl2") String imageurl2,
      @RequestParam("imageurl3") String imageurl3) {
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

    Commission createdCommission = commissionService.createCommission(commission);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCommission);
  }


  @PutMapping("/commissions/{commissionId}")
  public ResponseEntity<String> updateCommission(@PathVariable("commissionId") Integer commissionId, @RequestBody Commission updatedCommission) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      updatedCommission.setCommissionId(commissionId);
      commissionService.updateCommission(commissionId, updatedCommission);
      return ResponseEntity.ok("Commission updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("commissions/{commissionId}")
  public ResponseEntity<String> deleteCommission(@PathVariable("commissionId") Integer commissionId) {
    Optional<Commission> commission = commissionService.getCommissionById(commissionId);
    if (commission.isPresent()) {
      commissionService.deleteCommission(commissionId);
      return ResponseEntity.ok("Commission deleted successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
