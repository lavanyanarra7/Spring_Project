package org.jsp.springBootProject.CourseEnrollmentSystem.DAO;
import java.util.List;
import java.util.Optional;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Instructor;
import org.jsp.springBootProject.CourseEnrollmentSystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorDao {

	@Autowired
	private InstructorRepository instructorRepository;
	//-------insert-------------
	public Instructor saveInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	//-----fetchAll--------
	public List<Instructor> getAllInstructor(){
		return instructorRepository.findAll();
	}
	//----fetch by id-----
	public Optional<Instructor> getInstructorById(Integer id) {
		return instructorRepository.findById(id);
	}
	//------update-------
	public Instructor updateInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	//--------delete------
	public void deleteInstructor(Instructor instructor) {
		instructorRepository.delete(instructor);
	}
}
