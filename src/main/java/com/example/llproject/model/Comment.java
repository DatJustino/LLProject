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

  public Comment(String content, LocalDateTime createdAt, String userName) {
    this.content = content;
    this.createdAt = createdAt;
    this.userName = userName;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "commentid")
  private Integer commentId;

  @Column(nullable = false, columnDefinition = "varchar (500)")
  private String content;

  @Column(name = "creationtime", nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false, name = "username", columnDefinition = "varchar (50)")
  private String userName;

  //IP Address so that we can add functionality for ipbanning, not used for now.
  @Column(name = "ipaddress", columnDefinition = "varchar (20)")
  private String ipAddress;

  @ManyToOne(optional = false)
  @JoinColumn(name = "blogpostid", referencedColumnName = "blogpostid", nullable = false)
  private BlogPost blogPost;

  public void setBlogPost(BlogPost blogPost) {
    this.blogPost = blogPost;
  }
}
