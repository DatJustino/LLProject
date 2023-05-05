package com.example.llproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Table(name = "blogposts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BlogPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "blogpostid")
  private Integer blogpostid;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(name = "imageurl")
  private String imageUrl;

  @Column(name = "fileurl")
  private String fileUrl;

  @Column(name = "createdate", nullable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Comment> comments = new ArrayList<>();

  // Getters and Setters

  @Column(name = "blogpostid")
  public Integer getBlogPostId() {
    return blogpostid;
  }

  public void setId(Integer blogpostid) {
    this.blogpostid = blogpostid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComment(Comment comment) {
    comments.add(comment);
    comment.setBlogPost(this);
  }

  public void removeComment(Comment comment) {
    comments.remove(comment);
    comment.setBlogPost(null);
  }
}
