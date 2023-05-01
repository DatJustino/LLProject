package com.example.llproject.repository;

import com.example.llproject.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer> {

}