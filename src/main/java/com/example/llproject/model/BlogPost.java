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
  private Integer blogPostId;

  @Column(name="headertitle")
  private String headerTitle;

  @Column(nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
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

  public Integer getBlogPostId() {
    return blogPostId;
  }

  public void setId(Integer blogPostId) {
    this.blogPostId = blogPostId;
  }

  public String getHeaderTitle() {
    return headerTitle;
  }

  public void setHeaderTitle(String headerTitle) {
    this.headerTitle = headerTitle;
  }
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public void addComment(Comment comment, String userName, String ipAddress) {
    comment.setUserName(userName);
    comment.setIpAddress(ipAddress);
    comments.add(comment);
    comment.setBlogPost(this);
  }
  public void removeComment(Comment comment) {
    comments.remove(comment);
    comment.setBlogPost(null);
  }
}
