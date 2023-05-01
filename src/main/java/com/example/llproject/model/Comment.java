package com.example.llproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentid;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "blogpostid", referencedColumnName = "blogpostid", nullable = false)
  private BlogPost blogPost;

  public void setBlogPost(BlogPost blogPost) {
    this.blogPost = blogPost;
  }
}
