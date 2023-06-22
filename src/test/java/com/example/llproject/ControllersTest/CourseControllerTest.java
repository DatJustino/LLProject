package com.example.llproject.ControllersTest;

import com.example.llproject.controller.CourseController;
import com.example.llproject.model.Course;
import com.example.llproject.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@DataJpaTest
class CourseControllerTest {

  @Mock
  private CourseService courseService;

  private CourseController courseController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    courseController = new CourseController(courseService);
  }

  @Test
  void testCreateCourse() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the createCourse method of CourseService
    when(courseService.createCourse(course)).thenReturn(course);

    // Call the createCourse method of CourseController
    ResponseEntity<Course> response = courseController.createCourse(course);

    // Verify that the createCourse method of CourseService is called
    verify(courseService, times(1)).createCourse(course);

    // Verify the response status code and body
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(course, response.getBody());
  }

  @Test
  void testGetCourseById_CourseFound() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the getCourseById method of CourseService to return the Course object
    when(courseService.getCourseById(100)).thenReturn(Optional.of(course));

    // Call the getCourseById method of CourseController
    ResponseEntity<Course> response = courseController.getCourseById(100);

    // Verify that the getCourseById method of CourseService is called
    verify(courseService, times(1)).getCourseById(100);

    // Verify the response status code and body
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(course, response.getBody());
  }

  @Test
  void testGetCourseById_CourseNotFound() {
    // Mock the getCourseById method of CourseService to return an empty Optional
    when(courseService.getCourseById(100)).thenReturn(Optional.empty());

    // Call the getCourseById method of CourseController
    ResponseEntity<Course> response = courseController.getCourseById(100);

    // Verify that the getCourseById method of CourseService is called
    verify(courseService, times(1)).getCourseById(100);

    // Verify the response status code
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}
