package com.example.llproject.ServiceTest;

import com.example.llproject.model.Course;
import com.example.llproject.repository.CourseRepository;
import com.example.llproject.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

  @Mock
  private CourseRepository courseRepository;

  private CourseService courseService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    courseService = new CourseService(courseRepository);
  }

  @Test
  void testCreateCourse() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the save method of CourseRepository
    when(courseRepository.save(course)).thenReturn(course);

    // Call the createCourse method
    Course createdCourse = courseService.createCourse(course);

    // Verify that the save method of CourseRepository is called
    verify(courseRepository, times(1)).save(course);

    // Verify that the returned Course object is the same as the created one
    assertEquals(course, createdCourse);
  }

  @Test
  void testUpdateCourse_CourseFound() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the findById method of CourseRepository to return the Course object
    when(courseRepository.findById(100)).thenReturn(Optional.of(course));

    // Mock the save method of CourseRepository
    when(courseRepository.save(course)).thenReturn(course);

    // Call the updateCourse method
    courseService.updateCourse(course);

    // Verify that the findById method of CourseRepository is called
    verify(courseRepository, times(1)).findById(100);

    // Verify that the save method of CourseRepository is called
    verify(courseRepository, times(1)).save(course);
  }

  @Test
  void testUpdateCourse_CourseNotFound() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the findById method of CourseRepository to return an empty Optional
    when(courseRepository.findById(100)).thenReturn(Optional.empty());

    // Call the updateCourse method and expect an exception
    assertThrows(IllegalArgumentException.class, () -> courseService.updateCourse(course));

    // Verify that the findById method of CourseRepository is called
    verify(courseRepository, times(1)).findById(100);

    // Verify that the save method of CourseRepository is not called
    verify(courseRepository, never()).save(any(Course.class));
  }
  @Test
  void testGetCourseById_CourseFound() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the findById method of CourseRepository to return the Course object
    when(courseRepository.findById(100)).thenReturn(Optional.of(course));

    // Call the getCourseById method of CourseService
    Optional<Course> foundCourse = courseService.getCourseById(100);

    // Verify that the findById method of CourseRepository is called
    verify(courseRepository, times(1)).findById(100);

    // Verify the returned Optional object
    assertTrue(foundCourse.isPresent());
    assertEquals(course, foundCourse.get());
  }

  @Test
  void testGetCourseById_CourseNotFound() {
    // Mock the findById method of CourseRepository to return an empty Optional
    when(courseRepository.findById(100)).thenReturn(Optional.empty());

    // Call the getCourseById method of CourseService
    Optional<Course> foundCourse = courseService.getCourseById(100);

    // Verify that the findById method of CourseRepository is called
    verify(courseRepository, times(1)).findById(100);

    // Verify the returned Optional object
    assertFalse(foundCourse.isPresent());
  }

  @Test
  void testGetAllCourses() {
    // Create a list of Course objects
    List<Course> courses = Arrays.asList(
        new Course(100, "Mathematics"),
        new Course(200, "Physics")
    );

    // Mock the findAll method of CourseRepository to return the list of Course objects
    when(courseRepository.findAll()).thenReturn(courses);

    // Call the getAllCourses method of CourseService
    List<Course> retrievedCourses = courseService.getAllCourses();

    // Verify that the findAll method of CourseRepository is called
    verify(courseRepository, times(1)).findAll();

    // Verify the returned list of Course objects
    assertEquals(courses.size(), retrievedCourses.size());
    assertEquals(courses, retrievedCourses);
  }

  @Test
  void testUpdateCourse() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Mock the findById method of CourseRepository to return the Course object
    when(courseRepository.findById(100)).thenReturn(Optional.of(course));

    // Call the updateCourse method of CourseService
    courseService.updateCourse(course);

    // Verify that the save method of CourseRepository is called
    verify(courseRepository, times(1)).save(course);
  }

  @Test
  void testCreateCourse_NullCourse() {
    // Call the createCourse method with a null Course object
    assertThrows(IllegalArgumentException.class, () -> courseService.createCourse(null));

    // Verify that the save method of CourseRepository is not called
    verify(courseRepository, never()).save(any(Course.class));
  }


  @Test
  void testDeleteCourse_CourseFound() {
    // Create a Course object
    Course course = new Course();
    course.setCourseId(100);

    // Mock the findById method of CourseRepository to return the Course object
    when(courseRepository.findById(100)).thenReturn(Optional.of(course));

    // Call the deleteCourse method
    courseService.deleteCourse(100);

    // Verify that the deleteById method of CourseRepository is called
    verify(courseRepository, times(1)).deleteById(100);
  }

  @Test
  void testDeleteCourse_CourseNotFound() {
    // Mock the findById method of CourseRepository to return an empty Optional
    when(courseRepository.findById(100)).thenReturn(Optional.empty());

    // Call the deleteCourse method and expect an exception
    assertThrows(IllegalArgumentException.class, () -> courseService.deleteCourse(100));

    // Verify that the deleteById method of CourseRepository is not called
    verify(courseRepository, never()).deleteById(anyInt());
  }
}