package com.example.llproject.controller;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.service.BlogService;
import com.example.llproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

  private final BlogService blogService;
  private final CommentService commentService;

  public BlogPostController(BlogService blogService, CommentService commentService) {
    this.blogService = blogService;
    this.commentService = commentService;
  }

  @PostMapping
  public ResponseEntity<Void> createBlogPost(@RequestBody BlogPost blogPost) {
    blogService.createBlogPost(blogPost);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Integer id) {
    Optional<BlogPost> blogPostOptional = blogService.getBlogPostById(id);
    return blogPostOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateBlogPost(@PathVariable Integer id, @RequestBody BlogPost updatedBlogPost) {
    blogService.updateBlogPost(id, updatedBlogPost);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBlogPost(@PathVariable Integer id) {
    blogService.deleteBlogPost(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{blogPostId}/comments")
  public ResponseEntity<Void> addCommentToBlogPost(
      @PathVariable Integer blogPostId,
      @RequestBody Comment comment) {
    blogService.addCommentToBlogPost(blogPostId, comment);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  @PutMapping("/{blogPostId}/comments/{commentId}")
  public ResponseEntity<Void> updateComment(
      @PathVariable Integer blogPostId,
      @PathVariable Integer commentId,
      @RequestBody Comment updatedComment) {
    commentService.updateComment(blogPostId, commentId, updatedComment);
    return ResponseEntity.noContent().build();
  }
  @DeleteMapping("/{blogPostId}/comments/{commentId}")
  public ResponseEntity<Void> deleteCommentFromBlogPost(
      @PathVariable Integer blogPostId,
      @PathVariable Integer commentId) {
    blogService.deleteCommentFromBlogPost(blogPostId, commentId);
    return ResponseEntity.noContent().build();
  }
}
