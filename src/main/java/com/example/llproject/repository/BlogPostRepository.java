package com.example.llproject.repository;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
  @Query("SELECT c FROM BlogPost bp JOIN bp.comments c WHERE bp.blogPostId = :blogPostId")
  List<Comment> findCommentsByBlogPostId(@Param("blogPostId") Integer blogPostId);

}