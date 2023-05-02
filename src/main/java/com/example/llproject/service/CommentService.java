package com.example.llproject.service;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

  private final CommentRepo commentRepo;

  private final BlogService blogService; // Add a reference to the BlogPostService

  @Autowired
  public CommentService(CommentRepo commentRepo, BlogService blogService) {
    this.commentRepo = commentRepo;
    this.blogService = blogService;
  }

  public Comment createComment(Integer blogPostId, Comment comment) {
    // Retrieve the associated BlogPost
    BlogPost blogPost = blogService.getBlogPostById(blogPostId)
        .orElseThrow(() -> new IllegalArgumentException("BlogPost not found with ID: " + blogPostId));

    comment.setBlogPost(blogPost);
    return commentRepo.save(comment);
  }

/*  public void createComment(BlogPost blogPost, Comment comment) {
    comment.setBlogPost(blogPost.getId());
    commentRepo.save(comment);
  }*/

  public Optional<Comment> getCommentById(Integer id) {
    return commentRepo.findById(id);
  }

  public List<Comment> getAllComments() {
    return commentRepo.findAll();
  }

  public void updateComment(Comment comment) {
    commentRepo.save(comment);
  }

  public void deleteComment(Integer id) {
    commentRepo.deleteById(id);
  }
}
