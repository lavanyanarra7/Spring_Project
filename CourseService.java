package org.jsp.springBootProject.CourseEnrollmentSystem.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure;
import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.CourseDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.CourseDto;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Course;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Enrollment;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Instructor;
import org.jsp.springBootProject.CourseEnrollmentSystem.repository.CourseRepository;
import org.jsp.springBootProject.CourseEnrollmentSystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private InstructorRepository instructorRepository;
//------------------ insert-------------
	public ResponseEntity<ResponseStructure<Course>> createCourse(CourseDto courseDto) {
	    ResponseStructure<Course> structure = new ResponseStructure<>();

	    Instructor instructor = instructorRepository.findById(courseDto.getInstructorId())
	            .orElseThrow(() -> new RuntimeException("Instructor not found"));

	    Course course = new Course();
	    course.setTitle(courseDto.getTitle());
	    course.setDuration(courseDto.getDuration());
	    course.setFee(courseDto.getFee());
	    course.setInstructor(instructor);

	    Course savedCourse = courseRepository.save(course);

	    structure.setStatusCode(HttpStatus.CREATED.value());
	    structure.setMessage("Course created successfully");
	    structure.setData(savedCourse);

	    return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}
	//----------------------fetchAll-------------------------
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourse(){
		List<Course> list=courseDao.getAllCourse();
		ResponseStructure<List<Course>> structure=new ResponseStructure<List<Course>>();
		if(!list.isEmpty()) {
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(list);
		return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.OK);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Course not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.NOT_FOUND);
		}
}
	//-----------------fetchById---------------------
	public ResponseEntity<ResponseStructure<Course>> getCourseById(Integer id) {
		Optional<Course> opt=courseDao.getCourseById(id);
		ResponseStructure<Course> structure=new ResponseStructure<Course>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Course found");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.OK) ;
		}
		else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Instructor not found");
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Course>>(structure,HttpStatus.NOT_FOUND);
		}
	    }
	
	//--------------update-----------------
	public ResponseEntity<ResponseStructure<Course>> updateCourse(Course course) {
		Course  updateCourse=courseDao.createCourse(course);
			ResponseStructure<Course> structure=new ResponseStructure<Course>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Course update successfully");
			structure.setData(updateCourse);
			return new ResponseEntity<>(structure,HttpStatus.OK);
		}
		
	//------------------------delete---------------
	public ResponseEntity<ResponseStructure<Course>> deleteCourse(Integer id) {	
			Optional<Course> opt=courseDao.getCourseById(id);
				ResponseStructure<Course> structure=new ResponseStructure<Course>();
				if(opt.isPresent()) {
					courseDao.deleteCourse(opt.get());
					structure.setStatusCode(HttpStatus.ACCEPTED.value());
					structure.setMessage("Course delete successfully");
					structure.setData(opt.get());
					return new ResponseEntity<>(structure,HttpStatus.ACCEPTED);
				}
				else {
			        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			        structure.setMessage("Course not found");
		            structure.setData(null);
		        return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
		
			    }
		}
	
//-----------course by instructor by id -------------------
	public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(Integer instructorId) {
        List<Course> courses = courseDao.getCoursesByInstructorId(instructorId);

        ResponseStructure<List<Course>> response = new ResponseStructure<>();
        if (!courses.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Courses found");
            response.setData(courses);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("No courses found for the given instructor ID");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }





}
