package com.example.llproject.ModelsTest;

import com.example.llproject.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CommentTest {

  @Test
  public void testSettersAndGetters() {
    // Create a comment
    Comment comment = new Comment();
    comment.setCommentId(100);
    comment.setContent("Test Comment");
    LocalDateTime createdAt = LocalDateTime.now();
    comment.setCreatedAt(createdAt);

    // Verify the getters
    Assertions.assertEquals(100, comment.getCommentId());
    Assertions.assertEquals("Test Comment", comment.getContent());
    Assertions.assertEquals(createdAt, comment.getCreatedAt());
  }
}

