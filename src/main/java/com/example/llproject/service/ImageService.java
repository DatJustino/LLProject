package com.example.llproject.service;

import com.example.llproject.model.Image;
import com.example.llproject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

  private static final java.util.UUID UUID = java.util.UUID.randomUUID();
  private final ImageRepository imageRepository;

  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image createImage(Image image) {
    return imageRepository.save(image);
  }

  private static final String UPLOAD_DIR = "images";

  public Image uploadImage(MultipartFile file) {
    try {
      String fileName = generateFileName(file.getOriginalFilename());
      String filePath = UPLOAD_DIR + File.separator + fileName;
      Path targetPath = Paths.get(filePath);
      Files.createDirectories(targetPath.getParent());
      Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
      return new Image(fileName, filePath);
    } catch (IOException e) {
      throw new RuntimeException("Failed to upload image", e);
    }
  }

  private String generateFileName(String originalFilename) {
    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    return UUID.randomUUID().toString() + extension;
  }

  public Optional<Image> getImageById(Integer imageId) {
    return imageRepository.findById(imageId);
  }

  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }

  public void updateImage(Image image) {
    imageRepository.save(image);
  }

  public void deleteImage(Integer imageId) {
    imageRepository.deleteById(imageId);
  }
}
