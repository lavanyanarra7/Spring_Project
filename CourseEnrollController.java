package org.jsp.springBootProject.CourseEnrollmentSystem.Controller;
import java.util.List;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.CourseDto;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.EnrollDto;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.ResponseStructure;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Course;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Enrollment;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Instructor;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Student;
import org.jsp.springBootProject.CourseEnrollmentSystem.Service.CourseService;
import org.jsp.springBootProject.CourseEnrollmentSystem.Service.EnrollmentService;
import org.jsp.springBootProject.CourseEnrollmentSystem.Service.InstructorService;
import org.jsp.springBootProject.CourseEnrollmentSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CourseEnrollController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private InstructorService instructorService;
	 @Autowired
	 private CourseService courseService;
	 @Autowired
	  private EnrollmentService enrollmentService;

	 //------insert---------
    @PostMapping("/students")
    public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
    	 return studentService.saveStudent(student);
    }
    @PostMapping("/instructors")
    public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(@RequestBody Instructor instructor){
    return 	instructorService.saveInstructor(instructor);
    }
    @PostMapping("/courses")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Course>> createCourse( @RequestBody CourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }
    @PostMapping("/enroll")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Enrollment>> enrollStudent(@RequestBody EnrollDto enrollDto){
    	return enrollmentService.enrollStudent(enrollDto);
    }
    //-----fetch All-------------
    @GetMapping("/fetchAllStudents")
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent(){
	return studentService.getAllStudent();
	}
    @GetMapping("/fetchAllInstructor")
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructor(){
	return instructorService.getAllInstructor();
	}
    @GetMapping("/fetchAllcourses")
	public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<List<Course>>> getAllCourse(){
	return courseService.getAllCourse();
	}
    @GetMapping("/fetchAllEnrollments")
	public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<List<Enrollment>>> getAllEnrollment(){
	return enrollmentService.getAllEnrollment();
	}
    //---------fetch by id-------------------------
    @GetMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable Integer id) {
		return studentService.getStudentById(id);
	}
    @GetMapping("/instructor/{id}")
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(@PathVariable Integer id) {
		return instructorService.getInstructorById(id);
	}
    @GetMapping("/course/{id}")
	public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Course>> getCourseById(@PathVariable Integer id){
	return courseService.getCourseById(id);
	}
    @GetMapping("/enroll/{id}")
	public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Enrollment>> getEnrollmentById(@PathVariable Integer id){
	return enrollmentService.getEnrollmentById(id);
	}
    //------------------update----------------
    @PutMapping("updateStudent")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}
    @PutMapping("updateInstructor")
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(@RequestBody Instructor instructor) {
		return instructorService.updateInstructor(instructor);
	}
    @PutMapping("updateCourse")
	public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Course>> updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}
    @PutMapping("updateEnrollment")
	public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Enrollment>> updateEnrollment(@RequestBody Enrollment enrollment) {
		return enrollmentService.updateEnrollment(enrollment);
	}
    //----------------------delete---------------------------
    @DeleteMapping("/Student/{id}")
    public ResponseEntity<ResponseStructure<Student>> deleteStudent(@PathVariable Integer id) {
 		return studentService.deleteStudent(id);
 	}
    @DeleteMapping("/Instructor/{id}")
    public ResponseEntity<ResponseStructure<Instructor>> deleteInstructor(@PathVariable Integer id) {
 		return instructorService.deleteInstructor(id);
 	}
    @DeleteMapping("/Course/{id}")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Course>> deleteCourse(@PathVariable Integer id) {
 		return courseService.deleteCourse(id);
 	}
    @DeleteMapping("/Enrollment/{id}")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<Enrollment>> deleteEnrollment(@PathVariable Integer id) {
 		return enrollmentService.deleteEnrollment(id);
 	}
    //------fetch course by student id----------------
    @GetMapping("/student/{id}/courses")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<List<Course>>> getCoursesByStudentId(@PathVariable Integer id) {
        return enrollmentService.getCoursesByStudentId(id);
    }
    //-----------fetch student by instructor id-----------------
    @GetMapping("/instructor/{id}/students")
    public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(@PathVariable Integer id) {
        return studentService.getStudentsByInstructorId(id);
    }
    //----- fetch course by instructor id-------------
    @GetMapping("/instructor/{id}/courses")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<List<Course>>> getCoursesByInstructorId(@PathVariable Integer id) {
        return courseService.getCoursesByInstructorId(id);
    }
    //-----fetch enrollment by student id--------
    @GetMapping("/student/{id}")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<List<Enrollment>>> findByStudentId(@PathVariable Integer id) {
        return enrollmentService.findByStudentId(id);
    }
    //---------------fetch enrollment by course id-----------------
    @GetMapping("/course/{id}")
    public ResponseEntity<org.jsp.springBoot.SpringBoot_ExecutionTest.dto.ResponseStructure<List<Enrollment>>> getEnrollmentByCourseId(@PathVariable int id) {
        return enrollmentService.getEnrollmentByCourseId(id);
    }
}
    

