package com.example.llproject.service;

import com.example.llproject.model.Course;
import com.example.llproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  @Autowired
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public Optional<Course> getCourseById(Integer courseId) {
    return courseRepository.findById(courseId);
  }

  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  public void updateCourse(Course course) {
    courseRepository.save(course);
  }

  public void deleteCourse(Integer courseId) {
    courseRepository.deleteById(courseId);
  }
}
