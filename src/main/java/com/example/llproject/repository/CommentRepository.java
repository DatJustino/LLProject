package com.example.llproject.repository;

import com.example.llproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByBlogPostId(Integer blogPostId);
}
