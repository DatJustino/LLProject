package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "imageid")
  private Integer imageId;

  @Column(name = "imagename")
  private String imageName;

  @Lob
  @Column(name = "imagedata", columnDefinition = "LONGBLOB")
  private byte[] imageData;

  public Image() {
  }

  public Image(String imageName, byte[] imageData) {
    this.imageName = imageName;
    this.imageData = imageData;
  }

  // Getters and setters

  public Integer getImageId() {
    return imageId;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public byte[] getImageData() {
    return imageData;
  }

  public void setImageData(byte[] imageUrl) {
    this.imageData = imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Image image = (Image) o;
    return Objects.equals(imageId, image.imageId) && Objects.equals(imageName, image.imageName)
        && Objects.equals(imageData, image.imageData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageId, imageName, imageData);
  }

  @Override
  public String toString() {
    return "Image{" +
        "imageId=" + imageId +
        ", imageName='" + imageName + '\'' +
        ", imageUrl='" + imageData + '\'' +
        '}';
  }
}