package com.example.llproject.service;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BlogService {
  @Autowired
  private BlogPostRepo blogPostRepo;


  public BlogService(BlogPostRepo blogPostRepo) {
    this.blogPostRepo = blogPostRepo;
  }

  public void createBlogPost(BlogPost blogPost) {
    blogPost.setCreatedAt(LocalDateTime.now());
    blogPostRepo.save(blogPost);
  }

  public Optional<BlogPost> getBlogPostById(Integer id) {
    return blogPostRepo.findById(id);
  }

  public void updateBlogPost(Integer id, BlogPost updatedBlogPost) {
    Optional<BlogPost> blogPostOptional = blogPostRepo.findById(id);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
      blogPost.setTitle(updatedBlogPost.getTitle());
      blogPost.setContent(updatedBlogPost.getContent());
      blogPost.setImageUrl(updatedBlogPost.getImageUrl());
      blogPost.setFileUrl(updatedBlogPost.getFileUrl());
      blogPost.setComments(updatedBlogPost.getComments()); // Updated the setter name to setComments
      blogPostRepo.save(blogPost);
    }
  }

  public void deleteBlogPost(Integer id) {
    blogPostRepo.deleteById(id);
  }

  public void addCommentToBlogPost(Integer blogPostId, Comment comment) {
    Optional<BlogPost> blogPostOptional = blogPostRepo.findById(blogPostId);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
     blogPost.addComment(comment);
      blogPostRepo.save(blogPost);
    }
  }

  public void deleteCommentFromBlogPost(Integer blogPostId, Integer commentId) {
    Optional<BlogPost> blogPostOptional = blogPostRepo.findById(blogPostId);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
     Comment comment = blogPost.getComments().stream()
         .filter(c -> c.getCommentid().equals(commentId))
          .findFirst()
          .orElse(null);
      if (comment != null) {
        blogPost.removeComment(comment);
        blogPostRepo.save(blogPost);
      }
    }
  }
}
