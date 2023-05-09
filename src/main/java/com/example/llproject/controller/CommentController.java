package com.example.llproject.controller;

import com.example.llproject.model.Comment;
import com.example.llproject.service.CommentService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RestControllerAdvice
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "Authorization"})
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @Nullable
  @PostMapping("/{blogPostId}")
  public ResponseEntity<Comment> createComment(@PathVariable("blogPostId") Integer blogPostId, @RequestBody Comment comment) {
    commentService.createComment(blogPostId, comment);
    return ResponseEntity.status(HttpStatus.CREATED).body(comment);
  }

  @Nullable
  @GetMapping("/{commentId}")
  public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Integer commentId) {
    Optional<Comment> comment = commentService.getCommentById(commentId);
    return comment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
  @Nullable
  @GetMapping("/blog/{blogPostId}")
  public ResponseEntity<List<Comment>> getCommentsByBlogPostId(@PathVariable("blogPostId") Integer blogPostId) {
    List<Comment> comments = commentService.getCommentsByBlogPostId(blogPostId);
    return ResponseEntity.ok(comments);
  }
  @Nullable
  @PutMapping("/{commentId}")
  public ResponseEntity<String> updateComment(
      @PathVariable("commentId") Integer commentId,
      @RequestBody Comment updatedComment) {
    commentService.updateComment(commentId, updatedComment);
    return ResponseEntity.ok("Comment updated successfully");
  }
  @Nullable
  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable("commentId") Integer commentId) {
    commentService.deleteComment(commentId);
    return ResponseEntity.ok("Comment deleted successfully");
  }
}

