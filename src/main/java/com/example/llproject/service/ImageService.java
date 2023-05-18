package com.example.llproject.service;

import com.example.llproject.model.Image;
import com.example.llproject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

  private final ImageRepository imageRepository;

  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }
// TODO: delete createimage.
  public Image createImage(Image image) {
    return imageRepository.save(image);
  }

  public Image uploadImage(MultipartFile file) throws IOException {
    validateImageFile(file);
    System.out.println(file.getOriginalFilename() + file.getContentType());
    try {
/*
      String fileName = generateFileName(Objects.requireNonNull(file.getOriginalFilename()));
*/
      String fileName = Objects.requireNonNull(file.getOriginalFilename());
      byte[] fileData = file.getBytes();
      Image image = new Image(fileName, fileData);
      return imageRepository.save(image); // save image details in the database and return the saved image
    } catch (IOException e) {
      throw new RuntimeException("Failed to upload image", e);
    }
  }

  public Optional<Image> getImageById(Integer imageId) {
    return imageRepository.findById(imageId);
  }

  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }

  public Image updateImage(Integer imageId, Image updatedImage) {
    try {
      // Check if the image exists
      Optional<Image> existingImage = imageRepository.findById(imageId);
      if (existingImage.isPresent()) {
        Image image = existingImage.get();
        image.setImageName(updatedImage.getImageName());
        image.setImageData(updatedImage.getImageData());
        return imageRepository.save(image);
      } else {
        throw new IllegalArgumentException("Image not found");
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to update image", e);
    }
  }

  public boolean deleteImageById(Integer imageId) {
    System.out.println("Calling deleteImage method with imageId: " + imageId);

    Optional<Image> image = imageRepository.findById(imageId);
    if (image.isPresent()) {
      imageRepository.deleteById(imageId);
      System.out.println("Image deleted successfully");
      return true;
    } else {
      throw new IllegalArgumentException("Image not found");
    }
  }

  private void validateImageFile(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
      throw new IllegalArgumentException("Image file is required");
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      throw new IllegalArgumentException("Invalid image file format");
    }
  }

  private String generateFileName(String originalFilename) {
    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    return UUID.randomUUID().toString() + extension;
  }
}
