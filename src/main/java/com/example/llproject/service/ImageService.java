package com.example.llproject.service;

import com.example.llproject.model.Image;
import com.example.llproject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

  private final ImageRepository imageRepository;

  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image createImage(Image image) {
    return imageRepository.save(image);
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
