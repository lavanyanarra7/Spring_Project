package org.jsp.springBootProject.CourseEnrollmentSystem.repository;

import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}

