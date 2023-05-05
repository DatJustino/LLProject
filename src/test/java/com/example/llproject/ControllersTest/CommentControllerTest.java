package com.example.llproject.ControllersTest;

import com.example.llproject.controller.CommentController;
import com.example.llproject.model.Comment;
import com.example.llproject.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentControllerTest {

  private CommentService commentService;
  private CommentController commentController;

  @BeforeEach
  public void setup() {
    // Create a mock CommentService
    commentService = Mockito.mock(CommentService.class);
    commentController = new CommentController(commentService);
  }

  @Test
  public void testCreateComment() {
    // Create a comment
    Comment comment = new Comment();
    comment.setContent("Test Comment");

    // Mock the CommentService createComment method
    Mockito.doNothing().when(commentService).createComment(Mockito.anyInt(), Mockito.any(Comment.class));

    // Send a POST request to create a comment
    ResponseEntity<Comment> response = commentController.createComment(100, comment);

    // Verify the response status code
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // Verify the response body
    Assertions.assertEquals(comment, response.getBody());
  }

  @Test
  public void testGetCommentById() {
    // Create a comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setContent("Test Comment");

    // Mock the CommentService getCommentById method
    Mockito.when(commentService.getCommentById(comment.getCommentId())).thenReturn(Optional.of(comment));

    // Send a GET request to get a comment by ID
    ResponseEntity<Comment> response = commentController.getCommentById(comment.getCommentId());

    // Verify the response status code and body
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(comment, response.getBody());
  }
  @Test
  public void testGetCommentsByBlogPostId() {
    // Create a list of comments
    List<Comment> comments = new ArrayList<>();
    Comment comment1 = new Comment();
    comment1.setCommentId(200);
    comment1.setContent("Test Comment 1");
    Comment comment2 = new Comment();
    comment2.setCommentId(201);
    comment2.setContent("Test Comment 2");
    comments.add(comment1);
    comments.add(comment2);

    // Mock the CommentService getCommentsByBlogPostId method
    Mockito.when(commentService.getCommentsByBlogPostId(Mockito.anyInt())).thenReturn(comments);

    // Send a GET request to get comments by blog post ID
    ResponseEntity<List<Comment>> response = commentController.getCommentsByBlogPostId(100);

    // Verify the response status code and body
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(comments, response.getBody());
  }

  @Test
  public void testUpdateComment() {
    // Create a comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setContent("Test Comment");

    // Mock the CommentService updateComment method
    Mockito.doNothing().when(commentService).updateComment(Mockito.anyInt(), Mockito.any(Comment.class));

    // Send a PUT request to update a comment
    ResponseEntity<String> response = commentController.updateComment(200, comment);

    // Verify the response status code and body
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Comment updated successfully", response.getBody());
  }

  @Test
  public void testDeleteComment() {
    // Mock the CommentService deleteComment method
    Mockito.doNothing().when(commentService).deleteComment(Mockito.anyInt());

    // Send a DELETE request to delete a comment
    ResponseEntity<String> response = commentController.deleteComment(200);

    // Verify the response status code and body
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Comment deleted successfully", response.getBody());
  }

}
