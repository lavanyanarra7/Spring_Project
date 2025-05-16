package org.jsp.springBootProject.CourseEnrollmentSystem.DAO;
import java.util.List;
import java.util.Optional;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Student;
import org.jsp.springBootProject.CourseEnrollmentSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
	@Autowired
   private StudentRepository studentRepository;
	
	   //-----insert------
		public Student saveStudent(Student student) {
		 return studentRepository.save(student) ;
		}
		//-----fetchAll--------
		public List<Student> getAllStudent(){
			return studentRepository.findAll();
		}
		//---fetch by id-----
		public Optional<Student> getStudentById(Integer id) {
			return studentRepository.findById(id);
		}
		//------update-------
		public Student updateStudent(Student student) {
			return studentRepository.save(student);
		}
		
		//--------delete------
		public void deleteStudent(Student student) {
			studentRepository.delete(student);
		}
}

