package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Image entity for images to be promoted on the website such as galleries and free pictures.
 */
@Entity
@Getter
@Setter
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "imageid")
  private Integer imageId;

  @Column(name = "imagename")
  private String imageName;

  @Column(name = "imageurl")
  private String imageUrl;


  public Image(String fileName, String filePath) {
    this.imageName = fileName;
    this.imageUrl = filePath;
  }
}
