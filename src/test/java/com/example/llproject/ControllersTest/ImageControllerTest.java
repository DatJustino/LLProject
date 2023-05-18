/*
package com.example.llproject.ControllersTest;

import com.example.llproject.controller.ImageController;
import com.example.llproject.model.Image;
import com.example.llproject.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ImageControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ImageService imageService;

  private ImageController imageController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    imageController = new ImageController(imageService);
    mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
  }

  @Test
  void testGetImageById_ImageFound() {
    // Create a sample image
    Image image = new Image();
    image.setImageId(1);
    image.setImageName("image.jpg");
    image.setImageData("path/to/image.jpg");

    // Mock the getImageById method of ImageService to return an Optional containing the image
    when(imageService.getImageById(1)).thenReturn(Optional.of(image));

    // Call the getImageById method of ImageController
    ResponseEntity<Image> response = imageController.getImageById(1);

    // Verify that the getImageById method of ImageService is called
    verify(imageService, times(1)).getImageById(1);

    // Verify the response status code and the returned Image object
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(image, response.getBody());
  }

  @Test
  void testGetImageById_ImageNotFound() {
    // Mock the getImageById method of ImageService to return an empty Optional
    when(imageService.getImageById(1)).thenReturn(Optional.empty());

    // Call the getImageById method of ImageController
    ResponseEntity<Image> response = imageController.getImageById(1);

    // Verify that the getImageById method of ImageService is called
    verify(imageService, times(1)).getImageById(1);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(null, response.getBody());
  }

  @Test
  void testUploadImage_Success() throws IOException {
    // Create a sample image
    Image image = new Image();
    image.setImageId(1);
    image.setImageName("image.jpg");
    image.setImageData("path/to/image.jpg");

    // Mock the uploadImage method of ImageService to return the uploaded image
    when(imageService.uploadImage(any())).thenReturn(image);

    // Create a MockMultipartFile for testing
    MockMultipartFile file = new MockMultipartFile(
        "image.jpg",
        "image.jpg",
        "image/jpeg",
        "Some image data".getBytes()
    );

    // Call the uploadImage method of ImageController
    ResponseEntity<Image> response = imageController.uploadImage(file);

    // Verify that the uploadImage method of ImageService is called
    verify(imageService, times(1)).uploadImage(any());

    // Verify the response status code and the returned Image object
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(image, response.getBody());
  }

  @Test
  void testUploadImage_Failure() throws IOException {
    // Mock the uploadImage method of ImageService to return null indicating a failure
    when(imageService.uploadImage(any())).thenReturn(null);

    // Create a MockMultipartFile for testing
    MockMultipartFile file = new MockMultipartFile(
        "image.jpg",
        "image.jpg",
        "image/jpeg",
        "Some image data".getBytes()
    );

    // Call the uploadImage method of ImageController
    ResponseEntity<Image> response = imageController.uploadImage(file);

    // Verify that the uploadImage method of ImageService is called
    verify(imageService, times(1)).uploadImage(any());

    // Verify the response status code
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void testGetImageById_ImageExists() {
    // Create a sample image
    Image image = new Image();
    image.setImageId(1);
    image.setImageName("image.jpg");
    image.setImageData("path/to/image.jpg");

    // Mock the getImageById method of ImageService to return the image
    when(imageService.getImageById(1)).thenReturn(Optional.of(image));

    // Call the getImageById method of ImageController
    ResponseEntity<Image> response = imageController.getImageById(1);

    // Verify that the getImageById method of ImageService is called
    verify(imageService, times(1)).getImageById(1);

    // Verify the response status code and the returned Image object
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(image, response.getBody());
  }

  @Test
  void testGetImageById_ImageDoesNotExist() {
    // Mock the getImageById method of ImageService to return an empty Optional
    when(imageService.getImageById(1)).thenReturn(Optional.empty());

    // Call the getImageById method of ImageController
    ResponseEntity<Image> response = imageController.getImageById(1);

    // Verify that the getImageById method of ImageService is called
    verify(imageService, times(1)).getImageById(1);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void testDeleteImage_ImageExists() {
    // Create a sample image
    Image image = new Image();
    image.setImageId(1);
    image.setImageName("image.jpg");
    image.setImageData("path/to/image.jpg");

    // Mock the deleteImageById method of ImageService to return true
    when(imageService.deleteImageById(1)).thenReturn(true);

    // Call the deleteImage method of ImageController
    ResponseEntity<String> response = imageController.deleteImage(1);

    // Verify that the deleteImageById method of ImageService is called
    verify(imageService, times(1)).deleteImageById(1);

    // Verify the response status code
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void testDeleteImage_ImageDoesNotExist() {
    // Mock the deleteImageById method of ImageService to return false
    when(imageService.deleteImageById(1)).thenReturn(false);

    // Call the deleteImage method of ImageController
    ResponseEntity<String> response = imageController.deleteImage(1);

    // Verify that the deleteImageById method of ImageService is called
    verify(imageService, times(1)).deleteImageById(1);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void testUpdateImage_ImageExists() {
    // Create a sample image
    Image image = new Image();
    image.setImageId(1);
    image.setImageName("image.jpg");
    image.setImageData("path/to/image.jpg");

    // Mock the getImageById method of ImageService to return the existing image
    when(imageService.getImageById(1)).thenReturn(Optional.of(image));

    // Mock the updateImage method of ImageService to return the updated image
    when(imageService.updateImage(eq(1), any(Image.class))).thenReturn(image);

    // Call the updateImage method of ImageController
    ResponseEntity<String> response = imageController.updateImage(1, image);

    // Verify that the getImageById and updateImage methods of ImageService are called
    verify(imageService, times(1)).getImageById(1);
    verify(imageService, times(1)).updateImage(eq(1), any(Image.class));

    // Verify the response status code and the returned message
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Image updated successfully", response.getBody());
  }

}*/
