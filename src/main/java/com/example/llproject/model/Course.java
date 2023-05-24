package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Course entity for course information.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "courseid")
  private Integer courseId;
  @Column(name = "coursename", columnDefinition = "varchar(100)")
  private String courseName;
  @Column(name = "coursedescription", columnDefinition = "varchar(3000)")
  private String courseDescription;
  @Column(name = "courseimageurl")
  private String courseImageUrl;


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Course other = (Course) obj;
    return courseId.equals(other.courseId) && courseName.equals(other.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseName);
  }

  @Override
  public String toString() {
    return "Course(courseId=" + courseId + ", courseName=" + courseName + ")";
  }
}
