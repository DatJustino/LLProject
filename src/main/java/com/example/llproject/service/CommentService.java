package com.example.llproject.service;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void createComment(BlogPost blogPost, Comment comment) {
    blogPost.addComment(comment);
    commentRepository.save(comment);
  }

  public void updateComment(Integer blogPostId, Integer commentId, Comment updatedComment) {
    Optional<Comment> commentOptional = commentRepository.findById(commentId);
    if (commentOptional.isPresent()) {
      Comment comment = commentOptional.get();
      if (comment.getBlogPost().getId().equals(blogPostId)) {
        comment.setContent(updatedComment.getContent());
        commentRepository.save(comment);
      } else {
        throw new IllegalArgumentException("Comment does not belong to the specified blog post.");
      }
    } else {
      throw new IllegalArgumentException("Comment not found with ID: " + commentId);
    }
  }

  public void deleteComment(Integer commentId) {
    commentRepository.deleteById(commentId);
  }
}
