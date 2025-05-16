package org.jsp.springBootProject.CourseEnrollmentSystem.DAO;
import java.util.List;
import java.util.Optional;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Enrollment;
import org.jsp.springBootProject.CourseEnrollmentSystem.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentDao {

	@Autowired
	private EnrollmentRepository enrollRepository;
	//-------insert------------
	public Enrollment enrollStudent(Enrollment enrollment) {
		return enrollRepository.save(enrollment);
	}
	//-----fetchAll--------
	public List<Enrollment> getAllEnrollment(){
		return enrollRepository.findAll();
	}
	//----fetch by id-----
	public Optional<Enrollment> getEnrollmentById(Integer id) {
		return enrollRepository.findById(id);
	}
	//------update-------
	public Enrollment updateEnrollment(Enrollment enrollment) {
		return enrollRepository.save(enrollment);
	}
	//--------delete------
	public void deleteEnrollment(Enrollment enrollment) {
		enrollRepository.delete(enrollment);
	}	
	//---------------find by student id-----------
	public List<Enrollment> findByStudentId(Integer id){
		return enrollRepository.findStudentById(id);
	}
	//--------find course id------------------
	public List<Enrollment> getEnrollmentByCourseId(Integer id){
		return enrollRepository.findCourseById(id);
	}
	
	
}
