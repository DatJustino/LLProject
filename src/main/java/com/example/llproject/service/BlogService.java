package com.example.llproject.service;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

  private final BlogPostRepository blogPostRepository;

  @Autowired
  public BlogService(BlogPostRepository blogPostRepository) {
    this.blogPostRepository = blogPostRepository;
  }

  public BlogPost createBlogPost(BlogPost blogPost) {
    blogPost.setCreatedAt(LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
    blogPostRepository.save(blogPost);
    return blogPost;
  }

  public List<BlogPost> getAllBlogPosts() {
    return blogPostRepository.findAll();
  }

  public Optional<BlogPost> getBlogPostById(Integer id) {
    return blogPostRepository.findById(id);
  }

  public void updateBlogPost(Integer id, BlogPost updatedBlogPost) {
    Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
      blogPost.setId(id);
      blogPost.setHeaderTitle(updatedBlogPost.getHeaderTitle());
      blogPost.setTitle(updatedBlogPost.getTitle());
      blogPost.setDescription(updatedBlogPost.getDescription());
      blogPost.setContent(updatedBlogPost.getContent());
      blogPost.setImageUrl(updatedBlogPost.getImageUrl());
      blogPost.setFileUrl(updatedBlogPost.getFileUrl());
      blogPostRepository.save(blogPost);
    }
  }

  public void deleteBlogPost(Integer id) {
    blogPostRepository.deleteById(id);
  }

  public void addCommentToBlogPost(Integer blogPostId, Comment comment, String userName, String ipAddress) {
    Optional<BlogPost> blogPostOptional = blogPostRepository.findById(blogPostId);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
      comment.setCreatedAt(LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS)); // Set the creation time here
      comment.setUserName(userName);
      comment.setIpAddress(ipAddress);
      blogPost.addComment(comment, userName, ipAddress);
      blogPostRepository.save(blogPost);
    }
  }
  public void deleteCommentFromBlogPost(Integer blogPostId, Integer commentId) {
    Optional<BlogPost> blogPostOptional = blogPostRepository.findById(blogPostId);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
      Comment comment = blogPost.getComments().stream()
          .filter(c -> c.getCommentId().equals(commentId))
          .findFirst()
          .orElse(null);
      if (comment != null) {
        blogPost.removeComment(comment);
        blogPostRepository.save(blogPost);
      }
    }
  }

  public List<Comment> getCommentsByBlogPostId(Integer blogPostId) {
    Optional<BlogPost> blogPostOptional = blogPostRepository.findById(blogPostId);
    return blogPostOptional.map(BlogPost::getComments).orElseGet(List::of);
  }
}
