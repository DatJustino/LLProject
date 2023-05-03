package com.example.llproject.controller;

import com.example.llproject.model.BlogPost;
import com.example.llproject.model.Comment;
import com.example.llproject.model.Commission;
import com.example.llproject.model.Course;
import com.example.llproject.model.Image;
import com.example.llproject.model.Product;
import com.example.llproject.service.BlogService;
import com.example.llproject.service.CommissionService;
import com.example.llproject.service.CourseService;
import com.example.llproject.service.ImageService;
import com.example.llproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {

  private final ImageService imageService;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping
  public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {
    Image uploadedImage = imageService.uploadImage(file);
    return ResponseEntity.status(HttpStatus.CREATED).body(uploadedImage);
  }

  @GetMapping("/{imageId}")
  public ResponseEntity<Image> getImageById(@PathVariable("imageId") Integer imageId) {
    Optional<Image> image = imageService.getImageById(imageId);
    return image.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Image>> getAllImages() {
    List<Image> images = imageService.getAllImages();
    return ResponseEntity.ok(images);
  }

  @DeleteMapping("/{imageId}")
  public ResponseEntity<String> deleteImage(@PathVariable("imageId") Integer imageId) {
    Optional<Image> image = imageService.getImageById(imageId);
    if (image.isPresent()) {
      imageService.deleteImage(imageId);
      return ResponseEntity.ok("Image deleted successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
