package com.example.llproject.ModelsTest;

import com.example.llproject.model.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

  @Test
  void testCourseConstructor() {
    // Create a Course object using the constructor
    Course course = new Course(100, "Mathematics");

    // Verify the course attributes
    assertEquals(100, course.getCourseId());
    assertEquals("Mathematics", course.getCourseName());
  }

  @Test
  void testCourseGetterSetter() {
    // Create a Course object
    Course course = new Course();

    // Set the course attributes using the setter methods
    course.setCourseId(100);
    course.setCourseName("Mathematics");

    // Verify the course attributes using the getter methods
    assertEquals(100, course.getCourseId());
    assertEquals("Mathematics", course.getCourseName());
  }

  @Test
  void testCourseEqualsAndHashCode() {
    // Create two Course objects with the same attribute values
    Course course1 = new Course(100, "Mathematics");
    Course course2 = new Course(100, "Mathematics");

    // Verify that the two courses are equal and have the same hash code
    assertEquals(course1, course2);
    assertEquals(course1.hashCode(), course2.hashCode());
  }

  @Test
  void testCourseToString() {
    // Create a Course object
    Course course = new Course(100, "Mathematics");

    // Verify the toString representation of the course
    String expectedString = "Course(courseId=100, courseName=Mathematics)";
    String actualString = course.toString();

    assertEquals(expectedString, actualString);
  }
}
