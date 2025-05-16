package org.jsp.springBootProject.CourseEnrollmentSystem.repository;

import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
