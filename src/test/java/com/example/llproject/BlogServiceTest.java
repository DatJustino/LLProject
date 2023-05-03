package com.example.llproject;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.BlogPostRepository;
import com.example.llproject.service.BlogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import java.util.Optional;

public class BlogServiceTest {

  @Mock
  private BlogPostRepository blogPostRepository;

  private BlogService blogService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    blogService = new BlogService(blogPostRepository);
  }

  @Test
  public void testCreateBlogPost() {
    // Create a blog post
    BlogPost blogPost = new BlogPost();
    blogPost.setId(1);
    blogPost.setTitle("Test Blog Post");
    blogPost.setContent("This is a test blog post");
    blogPost.setImageUrl("image-url");
    blogPost.setFileUrl("file-url");
    blogPost.setCreatedAt(LocalDateTime.now());

    // Mock the repository save method
    Mockito.when(blogPostRepository.save(blogPost)).thenReturn(blogPost);

    // Create the blog post
    blogService.createBlogPost(blogPost);

    // Verify the repository save method is called with the correct blog post object
    Mockito.verify(blogPostRepository, Mockito.times(1)).save(blogPost);
  }
    @Test
    public void testGetBlogPostById () {
      // Create a blog post
      BlogPost blogPost = new BlogPost();
      blogPost.setId(1);
      blogPost.setTitle("Test Blog Post");
      blogPost.setContent("This is a test blog post");
      blogPost.setImageUrl("image-url");
      blogPost.setFileUrl("file-url");
      blogPost.setCreatedAt(LocalDateTime.now());

      // Mock the repository findById method
      Mockito.when(blogPostRepository.findById(blogPost.getId())).thenReturn(Optional.of(blogPost));

      // Get the blog post by id
      Optional<BlogPost> result = blogService.getBlogPostById(blogPost.getId());

      // Verify the repository findById method is called with the correct id
      Mockito.verify(blogPostRepository, Mockito.times(1)).findById(blogPost.getId());

      // Verify the returned result is the expected blog post
      Assertions.assertEquals(Optional.of(blogPost), result);
    }

  @Test
  public void testUpdateBlogPost() {
    // Create a blog post
    BlogPost blogPost = new BlogPost();
    blogPost.setId(1);
    blogPost.setTitle("Test Blog Post");
    blogPost.setContent("This is a test blog post");
    blogPost.setImageUrl("image-url");
    blogPost.setFileUrl("file-url");
    blogPost.setCreatedAt(LocalDateTime.now());

    // Mock the repository findById and save methods
    Mockito.when(blogPostRepository.findById(blogPost.getId())).thenReturn(Optional.of(blogPost));
    Mockito.when(blogPostRepository.save(Mockito.any(BlogPost.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // Update the blog post
    BlogPost updatedBlogPost = new BlogPost();
    updatedBlogPost.setId(blogPost.getId());
    updatedBlogPost.setTitle("Updated Blog Post");
    updatedBlogPost.setContent("This is an updated blog post");
    updatedBlogPost.setImageUrl("updated-image-url");
    updatedBlogPost.setFileUrl("updated-file-url");
    updatedBlogPost.setCreatedAt(blogPost.getCreatedAt());

    blogService.updateBlogPost(blogPost.getId(), updatedBlogPost);

    // Verify the repository findById method is called with the correct blog post id
    Mockito.verify(blogPostRepository, Mockito.times(1)).findById(blogPost.getId());

    // Verify the repository save method is called with the updated blog post
    Mockito.verify(blogPostRepository, Mockito.times(1)).save(updatedBlogPost);
  }


    @Test
    public void testDeleteBlogPost () {
      // Create a blog post
      BlogPost blogPost = new BlogPost();
      blogPost.setId(1);
      blogPost.setTitle("Test Blog Post");
      blogPost.setContent("This is a test blog post");
      blogPost.setImageUrl("image-url");
      blogPost.setFileUrl("file-url");
      blogPost.setCreatedAt(LocalDateTime.now());

      // Delete the blog post
      blogService.deleteBlogPost(blogPost.getId());

      // Verify the repository deleteById method is called with the correct id
      Mockito.verify(blogPostRepository, Mockito.times(1)).deleteById(blogPost.getId());
    }
    @Test
    public void testAddCommentToBlogPost () {
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
      comment.setCommentid(1);
      comment.setContent("This is a test comment");

      // Mock the repository findById and save methods
      Mockito.when(blogPostRepository.findById(blogPost.getId())).thenReturn(Optional.of(blogPost));
      Mockito.when(blogPostRepository.save(blogPost)).thenReturn(blogPost);

      // Add the comment to the blog post
      blogService.addCommentToBlogPost(blogPost.getId(), comment);

      // Verify the repository findById method is called with the correct id
      Mockito.verify(blogPostRepository, Mockito.times(1)).findById(blogPost.getId());

      // Verify the repository save method is called with the blog post containing the comment
      Mockito.verify(blogPostRepository, Mockito.times(1)).save(blogPost);
    }   @Test
  public void testDeleteCommentFromBlogPost() {
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
    comment.setCommentid(1);
    comment.setContent("This is a test comment");

    // Add the comment to the blog post
    blogPost.addComment(comment);

    // Mock the repository findById and save methods
    Mockito.when(blogPostRepository.findById(blogPost.getId())).thenReturn(Optional.of(blogPost));
    Mockito.when(blogPostRepository.save(blogPost)).thenReturn(blogPost);

    // Delete the comment from the blog post
    blogService.deleteCommentFromBlogPost(blogPost.getId(), comment.getCommentid());

    // Verify the repository findById method is called with the correct blog post id
    Mockito.verify(blogPostRepository, Mockito.times(1)).findById(blogPost.getId());

    // Verify the repository save method is called with the blog post after removing the comment
    Mockito.verify(blogPostRepository, Mockito.times(1)).save(blogPost);
  }
}
