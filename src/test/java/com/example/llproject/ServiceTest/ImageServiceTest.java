/*
package com.example.llproject.ServiceTest;

import com.example.llproject.model.Image;
import com.example.llproject.repository.ImageRepository;
import com.example.llproject.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageServiceTest {

  @Mock
  private ImageRepository imageRepository;

  @InjectMocks
  private ImageService imageService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testUploadImage() throws IOException {
    // Create a mock MultipartFile
    MockMultipartFile file = new MockMultipartFile(
        "image.jpg",
        "image.jpg",
        "image/jpeg",
        "Some image data".getBytes()
    );

    // Configure the imageRepository.save method to return the saved image
    when(imageRepository.save(any(Image.class))).thenReturn(new Image(1, "image.jpg", "path/to/image.jpg"));

    // Call the uploadImage method
    Image uploadedImage = imageService.uploadImage(file);

    // Verify the imageRepository.save method is called
    verify(imageRepository, times(1)).save(any(Image.class));

    // Verify the returned Image object
    assertNotNull(uploadedImage);
    assertEquals(1, uploadedImage.getImageId());
    assertEquals("image.jpg", uploadedImage.getImageName());
    assertEquals("path/to/image.jpg", uploadedImage.getImageUrl());
  }

  @Test
  void testUploadImage_ValidFile_Success() throws IOException {
    // Create a mock MultipartFile
    MockMultipartFile file = new MockMultipartFile(
        "image.jpg",
        "image.jpg",
        "image/jpeg",
        "Some image data".getBytes()
    );

    // Mock the behavior of the imageRepository.save method
    when(imageRepository.save(any(Image.class))).thenReturn(new Image("image.jpg", "path/to/image.jpg"));

    Image uploadedImage = imageService.uploadImage(file);
    System.out.println("Uploaded image: " + uploadedImage);

    // Debug print statement
    System.out.println("Number of invocations: " + Mockito.mockingDetails(imageRepository).getInvocations().size());

    // Verify the imageRepository.save method is called
    verify(imageRepository, times(1)).save(any(Image.class));

    // Verify the returned Image object
    assertNotNull(uploadedImage);
    assertEquals("image.jpg", uploadedImage.getImageName());
    assertEquals("path/to/image.jpg", uploadedImage.getImageUrl());
  }



  @Test
  void testUploadImage_NullFile_ThrowsIllegalArgumentException() {
    // Call the uploadImage method with a null file
    assertThrows(IllegalArgumentException.class, () -> imageService.uploadImage(null));

    // Verify that imageRepository.save method is not called
    verifyNoInteractions(imageRepository);
  }

  @Test
  void testUploadImage_InvalidFileFormat_ThrowsIllegalArgumentException() throws IOException {
    // Create a mock MultipartFile with an invalid file format (not an image)
    MockMultipartFile file = new MockMultipartFile(
        "document.txt",
        "document.txt",
        "text/plain",
        "Some text data".getBytes()
    );

    // Call the uploadImage method with an invalid file format
    assertThrows(IllegalArgumentException.class, () -> imageService.uploadImage(file));

    // Verify that imageRepository.save method is not called
    verifyNoInteractions(imageRepository);
  }

  @Test
  void testGetImageById_ImageExists_ReturnsImage() {
    // Create a mock Image object
    Image mockImage = new Image(1, "image.jpg", "path/to/image.jpg");

    // Configure the imageRepository.findById method to return the mock Image
    when(imageRepository.findById(1)).thenReturn(Optional.of(mockImage));

    // Call the getImageById method
    Optional<Image> result = imageService.getImageById(1);

    // Verify the imageRepository.findById method is called
    verify(imageRepository, times(1)).findById(1);

    // Verify that the result contains the expected Image object
    assertTrue(result.isPresent());
    assertEquals(mockImage, result.get());
  }
  @Test
  void testGetImageById_ImageDoesNotExist_ReturnsEmptyOptional() {
    // Arrange
    Integer imageId = 1;
    when(imageRepository.findById(imageId)).thenReturn(Optional.empty());

    // Act
    Optional<Image> result = imageService.getImageById(imageId);

    // Assert
    verify(imageRepository, times(1)).findById(imageId);
    assertFalse(result.isPresent());
  }

  @Test
  void testGetAllImages_NoImages_ReturnsEmptyList() {
    // Configure the imageRepository.findAll method to return an empty list
    when(imageRepository.findAll()).thenReturn(new ArrayList<>());

    // Call the getAllImages method
    List<Image> result = imageService.getAllImages();

    // Verify the imageRepository.findAll method is called
    verify(imageRepository, times(1)).findAll();

    // Verify that the result is an empty list
    assertTrue(result.isEmpty());
  }

  @Test
  void testGetAllImages_ImagesExist_ReturnsImageList() {
    // Create a list of mock Image objects
    List<Image> mockImages = new ArrayList<>();
    mockImages.add(new Image(1, "image1.jpg", "path/to/image1.jpg"));
    mockImages.add(new Image(2, "image2.jpg", "path/to/image2.jpg"));

    // Configure the imageRepository.findAll method to return the mock list of Images
    when(imageRepository.findAll()).thenReturn(mockImages);

    // Call the getAllImages method
    List<Image> result = imageService.getAllImages();

    // Verify the imageRepository.findAll method is called
    verify(imageRepository, times(1)).findAll();

    // Verify that the result contains the expected list of Images
    assertEquals(mockImages, result);
  }

  @Test
  void testDeleteImage_ImageExists_SuccessfullyDeleted() {
    // Create a mock Image object
    Image mockImage = new Image(1, "image.jpg", "path/to/image.jpg");

    // Configure the imageRepository.findById method to return the mock Image
    when(imageRepository.findById(1)).thenReturn(Optional.of(mockImage));

    // Call the deleteImage method
    imageService.deleteImageById(1);

    // Verify the imageRepository.deleteById method is called
    verify(imageRepository, times(1)).deleteById(1);
  }

  @Test
  void testDeleteImage_ImageDoesNotExist_ThrowsIllegalArgumentException() {
    // Configure the imageRepository.findById method to return an empty Optional
    when(imageRepository.findById(1)).thenReturn(Optional.empty());

    // Call the deleteImage method and verify that it throws IllegalArgumentException
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> imageService.deleteImageById(1));
    assertEquals("Image not found", exception.getMessage());

    // Verify the imageRepository.findById method is called
    verify(imageRepository, times(1)).findById(1);

    // Verify that the imageRepository.deleteById method is not called
    verifyNoMoreInteractions(imageRepository);
  }
  @Test
  void testCreateImage_Success() {
    Image image = new Image(1, "image.jpg", "path/to/image.jpg");
    when(imageRepository.save(any(Image.class))).thenReturn(image);

    Image createdImage = imageService.createImage(image);

    verify(imageRepository, times(1)).save(any(Image.class));
    assertNotNull(createdImage);
    assertEquals(1, createdImage.getImageId());
    assertEquals("image.jpg", createdImage.getImageName());
    assertEquals("path/to/image.jpg", createdImage.getImageUrl());
  }

  @Test
  void testUpdateImage_Success() {
    // Create a sample image
    Image existingImage = new Image(1, "image.jpg", "path/to/image.jpg");
    Image updatedImage = new Image(2, "image2.jpg", "path/to/image2.jpg");

    // Mock the findById method of ImageRepository to return the existing image
    when(imageRepository.findById(1)).thenReturn(Optional.of(existingImage));

    // Mock the save method of ImageRepository to return the updated image
    when(imageRepository.save(any(Image.class))).thenReturn(updatedImage);

    // Call the updateImage method of the ImageService
    Image result = imageService.updateImage(1, updatedImage);

    // Verify that the findById method of ImageRepository is called
    verify(imageRepository, times(1)).findById(1);

    // Verify that the save method of ImageRepository is called with the updated image
    verify(imageRepository, times(1)).save(any(Image.class));

    // Verify the returned updated image
    assertEquals(updatedImage, result);
  }

  }
*/
