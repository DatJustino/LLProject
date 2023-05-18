package com.example.llproject.controller;

import com.example.llproject.model.Image;
import com.example.llproject.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RestControllerAdvice
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
    RequestMethod.OPTIONS}, allowedHeaders =
    {"Content-Type", "Authorization, X-Requested-With, Accept, Origin, Access-Control-Request-Method," +
        " Access-Control-Request-Headers, Access-Control-Allow-Origin, Access-Control-Allow-Credentials, " +
        "Access-Control-Allow-Headers"})
@RequestMapping("/api/images")
public class ImageController {

  private final ImageService imageService;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping
  public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
    Image uploadedImage = imageService.uploadImage(file);
    if (uploadedImage != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(uploadedImage);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  //TODO: CHECK MORE IMAGETYPES: PNG, GIF, ETC, JPG.
  @GetMapping("/{imageId}")
  public ResponseEntity<byte[]> getImageDataById(@PathVariable("imageId") Integer imageId) {
    Optional<Image> image = imageService.getImageById(imageId);
    if (image.isPresent()) {
      byte[] imageData = image.get().getImageData();
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate content type
      return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping
  public ResponseEntity<List<Image>> getAllImages() {
    List<Image> images = imageService.getAllImages();
    return ResponseEntity.ok(images);
  }
  @PutMapping("/image/{imageId}")
  public ResponseEntity<String> updateImage(@PathVariable("imageId") Integer imageId,
                                            @RequestBody Image updatedImage) {
    Optional<Image> image = imageService.getImageById(imageId);
    if (image.isPresent()) {
      imageService.updateImage(imageId, updatedImage);
      return ResponseEntity.ok("Image updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @DeleteMapping("/{imageId}")
  public ResponseEntity<String> deleteImage(@PathVariable("imageId") Integer imageId) {
    boolean deleted = imageService.deleteImageById(imageId);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  }

