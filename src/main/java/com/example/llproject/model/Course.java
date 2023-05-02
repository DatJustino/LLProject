package com.example.llproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
  @Column(name = "coursename")
  private String courseName;

}
