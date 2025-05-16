package org.jsp.springBootProject.CourseEnrollmentSystem.DAO;
import java.util.List;
import java.util.Optional;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Course;
import org.jsp.springBootProject.CourseEnrollmentSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDao {

	@Autowired
	private CourseRepository courseRepository;
	//---insert------
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
	//-----fetchAll--------
	public List<Course> getAllCourse(){
		return courseRepository.findAll();
	}
	//----fetch by id-----
	public Optional<Course> getCourseById(Integer id) {
		return courseRepository.findById(id);
	}
	//------update-------
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}
	//--------delete------
	public void deleteCourse(Course course) {
		courseRepository.delete(course);
	}
	//-----------courses by instructor id-------------
	public List<Course> getCoursesByInstructorId(Integer id){
		return courseRepository.findInstructorId(id);
	}
}
