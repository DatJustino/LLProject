package com.example.llproject.service;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.BlogPostRepository;
import com.example.llproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final BlogPostRepository blogPostRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository, BlogPostRepository blogPostRepository) {
    this.commentRepository = commentRepository;
    this.blogPostRepository = blogPostRepository;
  }

  public void createComment(Integer blogPostId, Comment comment, String userName, String ipAddress) {
    Optional<BlogPost> blogPostOptional = blogPostRepository.findById(blogPostId);
    if (blogPostOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
      blogPost.addComment(comment, userName, ipAddress);
      commentRepository.save(comment);
    }
  }

  public Optional<Comment> getCommentById(Integer commentId) {
    return commentRepository.findById(commentId);
  }

  public List<Comment> getCommentsByBlogPostId(Integer blogPostId) {
    return blogPostRepository.findCommentsByBlogPostId(blogPostId);
  }

  public void updateComment(Integer commentId, Comment updatedComment) {
    Optional<Comment> commentOptional = commentRepository.findById(commentId);
    if (commentOptional.isPresent()) {
      Comment comment = commentOptional.get();
      comment.setContent(updatedComment.getContent());
      commentRepository.save(comment);
    } else {
      throw new IllegalArgumentException("Comment not found with ID: " + commentId);
    }
  }

  public void deleteComment(Integer commentId) {
    commentRepository.deleteById(commentId);
  }


  public void deleteCommentFromBlogPost(Integer blogPostId, Integer commentId) {
    Optional<BlogPost> blogPostOptional = blogPostRepository.findById(blogPostId);
    Optional<Comment> commentOptional = commentRepository.findById(commentId);

    if (blogPostOptional.isPresent() && commentOptional.isPresent()) {
      BlogPost blogPost = blogPostOptional.get();
      Comment comment = commentOptional.get();

      // Check if the comment belongs to the specified blog post
      if (comment.getBlogPost().getBlogPostId().equals(blogPostId)) {
        blogPost.removeComment(comment);
        commentRepository.delete(comment);
      } else {
        throw new IllegalArgumentException("Comment does not belong to the specified blog post");
      }
    } else {
      throw new IllegalArgumentException("Comment or blog post not found with the specified IDs");
    }
  }
}


