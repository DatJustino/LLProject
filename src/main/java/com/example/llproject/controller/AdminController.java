package com.example.llproject.controller;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  BlogService blogService;


  public AdminController(BlogService blogService) {
    this.blogService = blogService;
  }

  @PostMapping("/blog")
  public ResponseEntity<String> createBlogPost(@RequestParam("title") String title,
                                               @RequestParam("content") String content,
                                               @RequestParam("image") MultipartFile image,
                                               @RequestParam("file") MultipartFile file) {
    try {
      // Process the image and file uploads
      String imageUrl = processImageUpload(image);
      String fileUrl = processFileUpload(file);

      // Create a new blog post with the provided data and URLs
      BlogPost blogPost = new BlogPost();
      blogPost.setTitle(title);
      blogPost.setContent(content);
      blogPost.setImageUrl(imageUrl);
      blogPost.setFileUrl(fileUrl);

      blogService.createBlogPost(blogPost);

      return ResponseEntity.status(HttpStatus.CREATED).body("Blog post created successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create blog post");
    }
  }

  @PostMapping("/blog/{postId}/comments")
  public ResponseEntity<String> addCommentToBlogPost(@PathVariable("postId") Integer postId,
                                                     @RequestBody Comment comment) {
    Optional<BlogPost> blogPost = blogService.getBlogPostById(postId);
    if (blogPost.isPresent()) {
      blogService.addCommentToBlogPost(blogPost.get().getId(), comment);
      return ResponseEntity.ok("Comment added successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private String processImageUpload(MultipartFile image) {
    // Process the image upload and return the URL
    // Example: imageService.uploadImage(image);
    return "image-url"; // Replace with actual image URL
  }

  private String processFileUpload(MultipartFile file) {
    // Process the file upload and return the URL
    // Example: fileService.uploadFile(file);
    return "file-url"; // Replace with actual file URL
  }
}
