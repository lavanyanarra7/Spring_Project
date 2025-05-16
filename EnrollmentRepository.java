package org.jsp.springBootProject.CourseEnrollmentSystem.repository;
import java.util.List;

import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	
	public List<Enrollment> findStudentById(Integer id);
	public List<Enrollment> findCourseById(Integer id);
}
