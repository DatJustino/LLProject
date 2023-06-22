package com.example.llproject.ModelsTest;

import com.example.llproject.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CourseTest {

  @Test
  void testCourseConstructor() {
    Course course = new Course(100, "Mathematics", "Advanced Drawing Class", "aaasdasdsd.com");

    assertEquals(100, course.getCourseId());
    assertEquals("Mathematics", course.getCourseName());
  }

  @Test
  void testCourseGetterSetter() {
    Course course = new Course();

    course.setCourseId(100);
    course.setCourseName("Mathematics");

    assertEquals(100, course.getCourseId());
    assertEquals("Mathematics", course.getCourseName());
  }


  @Test
  void testCourseToString() {
    // Create a Course object
    Course course = new Course(100, "Mathematics", "Advanced Drawing Class", "aaasdasdsd.com");

    // Verify the toString representation of the course
    String expectedString = "Course(courseId=100, courseName=Mathematics)";
    String actualString = course.toString();

    assertEquals(expectedString, actualString);
  }
}
