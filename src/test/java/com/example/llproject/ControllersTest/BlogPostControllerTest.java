package com.example.llproject.ControllersTest;

import com.example.llproject.controller.BlogPostController;
import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BlogPostControllerTest {

  private BlogPostController blogPostController;

  @Mock
  private BlogService blogService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    blogPostController = new BlogPostController(blogService);
  }

  @Test
  public void testCreateBlogPost() {
    BlogPost blogPost = new BlogPost();

    ResponseEntity<Void> response = blogPostController.createBlogPost(blogPost);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(blogService, times(1)).createBlogPost(blogPost);
  }

  @Test
  public void testGetBlogPostById_existingBlogPost() {
    int blogPostId = 100;
    BlogPost blogPost = new BlogPost();
    blogPost.setId(blogPostId);
    Optional<BlogPost> blogPostOptional = Optional.of(blogPost);

    when(blogService.getBlogPostById(blogPostId)).thenReturn(blogPostOptional);

    ResponseEntity<BlogPost> response = blogPostController.getBlogPostById(blogPostId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(blogPost, response.getBody());
    verify(blogService, times(1)).getBlogPostById(blogPostId);
  }

  @Test
  public void testGetBlogPostById_nonExistingBlogPost() {
    int blogPostId = 100;
    Optional<BlogPost> blogPostOptional = Optional.empty();

    when(blogService.getBlogPostById(blogPostId)).thenReturn(blogPostOptional);

    ResponseEntity<BlogPost> response = blogPostController.getBlogPostById(blogPostId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(blogService, times(1)).getBlogPostById(blogPostId);
  }

  @Test
  public void testUpdateBlogPost() {
    int blogPostId = 100;
    BlogPost updatedBlogPost = new BlogPost();

    ResponseEntity<Void> response = blogPostController.updateBlogPost(blogPostId, updatedBlogPost);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(blogService, times(1)).updateBlogPost(blogPostId, updatedBlogPost);
  }

  @Test
  public void testDeleteBlogPost() {
    int blogPostId = 100;

    ResponseEntity<Void> response = blogPostController.deleteBlogPost(blogPostId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(blogService, times(1)).deleteBlogPost(blogPostId);
  }

  @Test
  public void testAddCommentToBlogPost() {
    int blogPostId = 100;
    Comment comment = new Comment();

    ResponseEntity<Void> response = blogPostController.addCommentToBlogPost(blogPostId, comment);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(blogService, times(1)).addCommentToBlogPost(blogPostId, comment);
  }

  @Test
  void testDeleteCommentFromBlogPost() {
    // Create a blog post
    BlogPost blogPost = new BlogPost();
    blogPost.setId(200);
    blogPost.setTitle("Test Blog Post");
    blogPost.setContent("This is a test blog post");
    blogPost.setImageUrl("image-url");
    blogPost.setFileUrl("file-url");
    blogPost.setCreatedAt(LocalDateTime.now());

    // Create a comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setContent("Test Comment");

    // Add comment to blog post
    blogPost.addComment(comment);

    // Mock the blogService
    BlogService blogService = mock(BlogService.class);
    BlogPostController blogPostController = new BlogPostController(blogService);

    // Mock the blogService.getBlogPostById() method
    when(blogService.getBlogPostById(blogPost.getBlogPostId())).thenReturn(Optional.of(blogPost));

    // Delete the comment from the blog post
    blogPostController.deleteCommentFromBlogPost(blogPost.getBlogPostId(), comment.getCommentId());

    // Verify that the blogService.updateBlogPost() method is called with the updated blog post
    verify(blogService, times(1)).updateBlogPost(blogPost.getBlogPostId(), blogPost);
  }

}