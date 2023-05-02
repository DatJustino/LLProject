package com.example.llproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "commentid")
  private Integer commentid;

  @Column(nullable = false)
  private String content;

  @Column(name = "creationtime", nullable = false)
  private LocalDateTime createdAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "blogpostid", referencedColumnName = "blogpostid", nullable = false)
  private BlogPost blogPost;

  public void setBlogPost(BlogPost blogPost) {
    this.blogPost = blogPost;
  }
}
