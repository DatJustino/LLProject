package com.example.llproject.service;

import com.example.llproject.model.Comment;
import com.example.llproject.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

  private final CommentRepo commentRepo;

  @Autowired
  public CommentService(CommentRepo commentRepo) {
    this.commentRepo = commentRepo;
  }

  public Comment createComment(Comment comment) {
    return commentRepo.save(comment);
  }

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
