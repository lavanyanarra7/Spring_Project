package org.jsp.springBootProject.CourseEnrollmentSystem.repository;

import java.util.List;

import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	public List<Course> findInstructorId(Integer id);
	
}
