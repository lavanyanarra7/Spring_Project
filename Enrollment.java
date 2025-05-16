package org.jsp.springBootProject.CourseEnrollmentSystem.Entity;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Enrollment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate enrollDate;
	
	@JoinColumn(name="student_id")
	@ManyToOne
	private Student student;
	
	@JoinColumn(name="course_id")
	@ManyToOne
	private Course course;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(LocalDate enrollDate) {
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", enrollDate=" + enrollDate + "]";
	}
	
}
