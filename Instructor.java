package org.jsp.springBootProject.CourseEnrollmentSystem.Entity;
import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Instructor {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
   private String name;
   private String email;
   private String specialization;
   
   @JsonIgnore
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "instructor")
   private List<Course> courses;
   
public List<Course> getCourses() {
	return courses;
}
public void setCourses(List<Course> courses) {
	this.courses = courses;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSpecialization() {
	return specialization;
}
public void setSpecialization(String specialization) {
	this.specialization = specialization;
}
@Override
public String toString() {
	return "Instructor [id=" + id + ", name=" + name + ", email=" + email + ", specialization=" + specialization + "]";
}

   
}
