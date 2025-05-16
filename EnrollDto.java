package org.jsp.springBootProject.CourseEnrollmentSystem.DTO;

import java.time.LocalDate;

public class EnrollDto {

	private LocalDate enrollDate;
	private Integer courseId;
	private Integer studentId;
	
	public LocalDate getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(LocalDate enrollDate) {
		this.enrollDate = enrollDate;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
}
