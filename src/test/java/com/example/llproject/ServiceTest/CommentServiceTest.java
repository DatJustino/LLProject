/*
package com.example.llproject.ServiceTest;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.BlogPostRepository;
import com.example.llproject.repository.CommentRepository;
import com.example.llproject.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentServiceTest {

  private CommentRepository commentRepository;
  private BlogPostRepository blogPostRepository;
  private CommentService commentService;

  @BeforeEach
  public void setup() {
    // Create mock repositories
    commentRepository = Mockito.mock(CommentRepository.class);
    blogPostRepository = Mockito.mock(BlogPostRepository.class);
    commentService = new CommentService(commentRepository, blogPostRepository);
  }

  @Test
  public void testCreateComment() {
    // Create a mock BlogPost and Comment
    BlogPost blogPost = new BlogPost();
    blogPost.setId(100);
    Comment comment = new Comment();
    comment.setContent("Test Comment");
    comment.setCreatedAt(LocalDateTime.now());

    // Mock the findById method of BlogPostRepository
    Mockito.when(blogPostRepository.findById(blogPost.getBlogPostId())).thenReturn(Optional.of(blogPost));

    // Invoke the createComment method
    commentService.createComment(blogPost.getBlogPostId(), comment);

    // Verify that the comment is added to the blog post and saved
    Assertions.assertTrue(blogPost.getComments().contains(comment));
    Mockito.verify(commentRepository, Mockito.times(1)).save(comment);
  }
  @Test
  public void testGetCommentById() {
    // Create a mock Comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setContent("Test Comment");

    // Mock the findById method of CommentRepository
    Mockito.when(commentRepository.findById(comment.getCommentId())).thenReturn(Optional.of(comment));

    // Invoke the getCommentById method
    Optional<Comment> result = commentService.getCommentById(comment.getCommentId());

    // Verify that the returned Comment matches the mock
    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(comment, result.get());
  }

  @Test
  public void testGetCommentsByBlogPostId() {
    // Create a mock BlogPost
    BlogPost blogPost = new BlogPost();
    blogPost.setId(100);

    // Create mock Comments
    Comment comment1 = new Comment();
    comment1.setCommentId(200);
    comment1.setContent("Test Comment 1");
    Comment comment2 = new Comment();
    comment2.setCommentId(201);
    comment2.setContent("Test Comment 2");
    List<Comment> comments = new ArrayList<>();
    comments.add(comment1);
    comments.add(comment2);

    // Mock the findCommentsByBlogPostId method of BlogPostRepository
    Mockito.when(blogPostRepository.findCommentsByBlogPostId(blogPost.getBlogPostId())).thenReturn(comments);

    // Invoke the getCommentsByBlogPostId method
    List<Comment> result = commentService.getCommentsByBlogPostId(blogPost.getBlogPostId());

    // Verify that the returned list of comments matches the mock
    Assertions.assertEquals(comments, result);
  }

  @Test
  public void testUpdateComment() {
    // Create a mock Comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setContent("Test Comment");

    // Create an updated Comment
    Comment updatedComment = new Comment();
    updatedComment.setCommentId(200);
    updatedComment.setContent("Updated Comment");

    // Mock the findById method of CommentRepository
    Mockito.when(commentRepository.findById(comment.getCommentId())).thenReturn(Optional.of(comment));

    // Invoke the updateComment method
    commentService.updateComment(comment.getCommentId(), updatedComment);

    // Verify that the commentRepository.save() method is called
    Mockito.verify(commentRepository, Mockito.times(1)).save(comment);
  }

  @Test
  public void testUpdateComment_InvalidId() {
    // Create an invalid commentId
    Integer commentId = 999;

    // Create an updated Comment
    Comment updatedComment = new Comment();
    updatedComment.setCommentId(999);
    updatedComment.setContent("Updated Comment");

    // Mock the findById method of CommentRepository to return an empty Optional
    Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

    // Invoke the updateComment method and expect an exception
    assertThrows(IllegalArgumentException.class,
        () -> commentService.updateComment(commentId, updatedComment));

    // Verify that the commentRepository.save() method is not called
    Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any(Comment.class));
  }

  @Test
  public void testDeleteComment() {
    // Create a mock Comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setContent("Test Comment");

    // Mock the findById method of CommentRepository
    Mockito.when(commentRepository.findById(comment.getCommentId())).thenReturn(Optional.of(comment));

    // Invoke the deleteComment method
    commentService.deleteComment(comment.getCommentId());

    // Verify that the commentRepository.deleteById() method is called
    Mockito.verify(commentRepository, Mockito.times(1)).deleteById(comment.getCommentId());
  }

  @Test
  public void testDeleteComment_InvalidId() {
    // Create an invalid commentId
    Integer commentId = 999;

    // Mock the findById method of CommentRepository to return an empty Optional
    Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

    // Invoke the deleteCommentFromBlogPost method and expect an exception
    assertThrows(IllegalArgumentException.class,
        () -> commentService.deleteCommentFromBlogPost(200, commentId));

    // Verify that the commentRepository.deleteById() method is not called
    Mockito.verify(commentRepository, Mockito.never()).deleteById(commentId);
  }


  @Test
  public void testDeleteCommentFromBlogPost() {
    // Create a mock BlogPost
    BlogPost blogPost = new BlogPost();
    blogPost.setId(100);

    // Create a mock Comment
    Comment comment = new Comment();
    comment.setCommentId(200);
    comment.setBlogPost(blogPost);

    // Mock the findById methods of CommentRepository and BlogPostRepository
    Mockito.when(commentRepository.findById(comment.getCommentId())).thenReturn(Optional.of(comment));
    Mockito.when(blogPostRepository.findById(blogPost.getBlogPostId())).thenReturn(Optional.of(blogPost));

    // Invoke the deleteCommentFromBlogPost method
    commentService.deleteCommentFromBlogPost(blogPost.getBlogPostId(), comment.getCommentId());

    // Verify that the commentRepository.delete() method is called
    Mockito.verify(commentRepository, Mockito.times(1)).delete(comment);
  }

  @Test
  public void testDeleteCommentFromBlogPost_InvalidBlogPostId() {
    // Create an invalid blogPostId and commentId
    Integer blogPostId = 999;
    Integer commentId = 200;

    // Create a mock Comment
    Comment comment = new Comment();
    comment.setCommentId(commentId);

    // Mock the findById methods of CommentRepository and BlogPostRepository to return empty Optionals
    Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
    Mockito.when(blogPostRepository.findById(blogPostId)).thenReturn(Optional.empty());

    // Invoke the deleteCommentFromBlogPost method and expect an exception
    assertThrows(IllegalArgumentException.class, () -> {
      commentService.deleteCommentFromBlogPost(999, commentId);
    });

    // Verify that the commentRepository.delete() method is not called
    Mockito.verify(commentRepository, Mockito.never()).delete(comment);
  }

  // Additional test methods...

}


*/
