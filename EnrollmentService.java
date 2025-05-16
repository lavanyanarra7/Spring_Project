package org.jsp.springBootProject.CourseEnrollmentSystem.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure;
import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.CourseDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.EnrollmentDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.StudentDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.EnrollDto;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Course;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Enrollment;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

	@Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;
    //-----insert-------
    public ResponseEntity<ResponseStructure<Enrollment>> enrollStudent(EnrollDto enrollDto) {

        Optional<Student> optionalStudent = studentDao.getStudentById(enrollDto.getStudentId());
        Optional<Course> optionalCourse = courseDao.getCourseById(enrollDto.getCourseId());

        if (optionalStudent.isEmpty() || optionalCourse.isEmpty()) {
            throw new RuntimeException("Invalid student or course ID");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollDate(enrollDto.getEnrollDate());
        enrollment.setStudent(optionalStudent.get());
        enrollment.setCourse(optionalCourse.get());

        Enrollment saved = enrollmentDao.enrollStudent(enrollment);

        ResponseStructure<Enrollment> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Enrollment successful");
        structure.setData(saved);

        return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.CREATED);
    }
    //------fetch All---------
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollment(){
	List<Enrollment> list=enrollmentDao.getAllEnrollment();
	ResponseStructure<List<Enrollment>> structure=new ResponseStructure<List<Enrollment>>();
	if(!list.isEmpty()) {
	structure.setStatusCode(HttpStatus.OK.value());
	structure.setMessage("Success");
	structure.setData(list);
	return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure,HttpStatus.OK);
	}
	else {
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Enrollment not found");
		structure.setData(null);
		return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure,HttpStatus.NOT_FOUND);
	}
}
    //-------fetch by id----------------------
    public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(Integer id) {
	Optional<Enrollment> opt=enrollmentDao.getEnrollmentById(id);
	ResponseStructure<Enrollment> structure=new ResponseStructure<Enrollment>();
	if(opt.isPresent()) {
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Enrollment found");
		structure.setData(opt.get());
		return new ResponseEntity<ResponseStructure<Enrollment>>(structure,HttpStatus.OK) ;
	}
	else {
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        structure.setMessage("Enrollment not found");
        structure.setData(null);
        return new ResponseEntity<ResponseStructure<Enrollment>>(structure,HttpStatus.NOT_FOUND);
	}
    }
	//--------------update-----------------
	public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(Enrollment enrollment) {
		Enrollment  updateEnrollment=enrollmentDao.enrollStudent(enrollment);
			ResponseStructure<Enrollment> structure=new ResponseStructure<Enrollment>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Enrollment update successfully");
			structure.setData(updateEnrollment);
			return new ResponseEntity<>(structure,HttpStatus.OK);
		}
		
	//------------------------delete---------------
	public ResponseEntity<ResponseStructure<Enrollment>> deleteEnrollment(Integer id) {	
			Optional<Enrollment> opt=enrollmentDao.getEnrollmentById(id);
				ResponseStructure<Enrollment> structure=new ResponseStructure<Enrollment>();
				if(opt.isPresent()) {
					enrollmentDao.deleteEnrollment(opt.get());
					structure.setStatusCode(HttpStatus.ACCEPTED.value());
					structure.setMessage("Enrollment delete successfully");
					structure.setData(opt.get());
					return new ResponseEntity<>(structure,HttpStatus.ACCEPTED);
				}
				else {
			        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			        structure.setMessage("Enrollment not found");
		            structure.setData(null);
		        return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
		
			    }
	}
	//-------course by student id---------------------
		public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByStudentId(Integer id){
			ResponseStructure<List<Course>> structure=new ResponseStructure<>();
			List<Enrollment> enrollments=enrollmentDao.findByStudentId(id);
			if (enrollments.isEmpty()) {
		        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		        structure.setMessage("No courses found for student ID: " + id);
		        structure.setData(null);
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		    }
			List<Course> courses = new ArrayList<>();
			for (Enrollment enrollment : enrollments) {
			    courses.add(enrollment.getCourse());
			}
		    structure.setStatusCode(HttpStatus.OK.value());
		    structure.setMessage("Courses retrieved successfully");
		    structure.setData(courses);
		    return new ResponseEntity<>(structure, HttpStatus.OK);
			
		}
	//-------enrollment by student id---------------
		  public ResponseEntity<ResponseStructure<List<Enrollment>>> findByStudentId(Integer id) {
		        List<Enrollment> enrollments = enrollmentDao.findByStudentId(id);
		        ResponseStructure<List<Enrollment>> structure = new ResponseStructure<>();
		        if (!enrollments.isEmpty()) {
		            structure.setStatusCode(HttpStatus.OK.value());
		            structure.setMessage("Enrollments found");
		            structure.setData(enrollments);
		            return new ResponseEntity<>(structure, HttpStatus.OK);
		        } else {
		            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		            structure.setMessage("No enrollments found for student ID: " + id);
		            structure.setData(null);
		            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		        }
		    }	
	//-----enrollment by course id--------------------------
		  public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentByCourseId(Integer id) {
		        List<Enrollment> enrollments = enrollmentDao.getEnrollmentByCourseId(id);
		        ResponseStructure<List<Enrollment>> structure = new ResponseStructure<>();

		        if (!enrollments.isEmpty()) {
		            structure.setStatusCode(HttpStatus.OK.value());
		            structure.setMessage("Enrollments found for course ID: " +id);
		            structure.setData(enrollments);
		            return new ResponseEntity<>(structure, HttpStatus.OK);
		        } else {
		            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		            structure.setMessage("No enrollments found for course ID: " + id);
		            structure.setData(null);
		            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		        }
		    }	  


}
	
	

