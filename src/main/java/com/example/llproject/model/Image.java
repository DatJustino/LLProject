package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Image image = (Image) o;
    return Objects.equals(imageId, image.imageId) && Objects.equals(imageName, image.imageName)
        && Objects.equals(imageUrl, image.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageId, imageName, imageUrl);
  }

  @Override
  public String toString() {
    String imageToString = "Image{" + "imageId=" + imageId + ", imageName="
        + imageName + ", imageUrl=" + imageUrl +'}';

    return imageToString;
  }
}
