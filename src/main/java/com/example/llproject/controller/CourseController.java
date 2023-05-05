package com.example.llproject.controller;

import com.example.llproject.model.Course;
import com.example.llproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RestControllerAdvice
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping
  public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    Course createdCourse = courseService.createCourse(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
  }

  @GetMapping("/{courseId}")
  public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Integer courseId) {
    Optional<Course> course = courseService.getCourseById(courseId);
    return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Course>> getAllCourses() {
    List<Course> courses = courseService.getAllCourses();
    return ResponseEntity.ok(courses);
  }

  @PutMapping("/{courseId}")
  public ResponseEntity<String> updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody Course updatedCourse) {
    Optional<Course> course = courseService.getCourseById(courseId);
    if (course.isPresent()) {
      updatedCourse.setCourseId(courseId);
      courseService.updateCourse(updatedCourse);
      return ResponseEntity.ok("Course updated successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{courseId}")
  public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Integer courseId) {
    Optional<Course> course = courseService.getCourseById(courseId);
    if (course.isPresent()) {
      courseService.deleteCourse(courseId);
      return ResponseEntity.ok("Course deleted successfully");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
