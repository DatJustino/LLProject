package com.example.llproject.ModelsTest;


import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class BlogPostTest {

  @Test
  public void testAddComment() {
    // Create a blog post
    BlogPost blogPost = new BlogPost();
    blogPost.setId(1);
    blogPost.setTitle("Test Blog Post");
    blogPost.setContent("This is a test blog post");
    blogPost.setImageUrl("image-url");
    blogPost.setFileUrl("file-url");
    blogPost.setCreatedAt(LocalDateTime.now());

    // Create a comment
    Comment comment = new Comment();
    comment.setCommentId(1);
    comment.setContent("This is a test comment");

    // Add the comment to the blog post
    blogPost.addComment(comment);

    // Verify the comment is added to the blog post
    List<Comment> comments = blogPost.getComments();
    Assertions.assertEquals(1, comments.size());
    Assertions.assertEquals(comment, comments.get(0));
    Assertions.assertEquals(blogPost, comment.getBlogPost());
  }

  @Test
  public void testRemoveComment() {
    // Create a blog post
    BlogPost blogPost = new BlogPost();
    blogPost.setId(1);
    blogPost.setTitle("Test Blog Post");
    blogPost.setContent("This is a test blog post");
    blogPost.setImageUrl("image-url");
    blogPost.setFileUrl("file-url");
    blogPost.setCreatedAt(LocalDateTime.now());

    // Create a comment
    Comment comment = new Comment();
    comment.setCommentId(1);
    comment.setContent("This is a test comment");

    // Add the comment to the blog post
    blogPost.addComment(comment);

    // Remove the comment from the blog post
    blogPost.removeComment(comment);

    // Verify the comment is removed from the blog post
    List<Comment> comments = blogPost.getComments();
    Assertions.assertEquals(0, comments.size());
    Assertions.assertNull(comment.getBlogPost());
  }
}
