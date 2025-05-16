package org.jsp.springBootProject.CourseEnrollmentSystem.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.CourseDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.EnrollmentDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.StudentDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.ResponseStructure;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Course;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Enrollment;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	 @Autowired
	private EnrollmentDao enrollmentDao;
	 @Autowired
	 private CourseDao courseDao;
	//---------------------save-------------------------
		public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student){
			Student savedstudent=studentDao.saveStudent(student);
			ResponseStructure<Student> structure=new ResponseStructure<Student>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("Success");
			structure.setData(savedstudent);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.CREATED);
		}
		//----------------------fetchAll-------------------------
		public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent(){
			List<Student> list=studentDao.getAllStudent();
			ResponseStructure<List<Student>> structure=new ResponseStructure<List<Student>>();
			if(!list.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.OK);
			}
			else {
				structure.setStatusCode(HttpStatus.NOT_FOUND.value());
				structure.setMessage("Student not found");
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.NOT_FOUND);
			}
	}
		//-----------------fetchById---------------------
		public ResponseEntity<ResponseStructure<Student>> getStudentById(Integer id) {
			Optional<Student> opt=studentDao.getStudentById(id);
			ResponseStructure<Student> structure=new ResponseStructure<Student>();
			if(opt.isPresent()) {
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Student found");
				structure.setData(opt.get());
				return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK) ;
			}
			else {
		        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		        structure.setMessage("Student not found");
		        structure.setData(null);
		        return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.NOT_FOUND);
			}
		    }
		
		//--------------update-----------------
		public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {
				Student  updateStudent=studentDao.saveStudent(student);
				ResponseStructure<Student> structure=new ResponseStructure<Student>();
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Student update successfully");
				structure.setData(updateStudent);
				return new ResponseEntity<>(structure,HttpStatus.OK);
			}
			
		//------------------------delete---------------
		public ResponseEntity<ResponseStructure<Student>> deleteStudent(Integer id) {	
				Optional<Student> opt=studentDao.getStudentById(id);
					ResponseStructure<Student> structure=new ResponseStructure<Student>();
					if(opt.isPresent()) {
						studentDao.deleteStudent(opt.get());
						structure.setStatusCode(HttpStatus.ACCEPTED.value());
						structure.setMessage("Student delete successfully");
						structure.setData(opt.get());
						return new ResponseEntity<>(structure,HttpStatus.ACCEPTED);
					}
					else {
				        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
				        structure.setMessage("Student not found");
			        structure.setData(null);
			        return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
			
				    }
			}
		//--------student by instructor id-------------
		public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(Integer id) {
	        List<Course> courses = courseDao.getCoursesByInstructorId(id);
	        Set<Student> students = new HashSet<>();

	        for (Course course : courses) {
	            List<Enrollment> enrollments = enrollmentDao.getEnrollmentByCourseId(course.getId());
	            for (Enrollment enrollment : enrollments) {
	                students.add(enrollment.getStudent());
	            }
	        }

	        ResponseStructure<List<Student>> response = new ResponseStructure<>();
	        if (!students.isEmpty()) {
	            response.setStatusCode(HttpStatus.OK.value());
	            response.setMessage("Students found");
	            response.setData(new ArrayList<>(students));
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.setStatusCode(HttpStatus.NOT_FOUND.value());
	            response.setMessage("No students found for this instructor");
	            response.setData(null);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	    }
	}
		

