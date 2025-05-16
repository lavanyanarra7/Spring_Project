package org.jsp.springBootProject.CourseEnrollmentSystem.Service;
import java.util.List;
import java.util.Optional;

import org.jsp.springBootProject.CourseEnrollmentSystem.DAO.InstructorDao;
import org.jsp.springBootProject.CourseEnrollmentSystem.DTO.ResponseStructure;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Instructor;
import org.jsp.springBootProject.CourseEnrollmentSystem.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

	@Autowired
	private InstructorDao instructorDao;
	
	//-------insert-----------
	public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(Instructor instructor){
		Instructor savedInstructor=instructorDao.saveInstructor(instructor);
		ResponseStructure<Instructor> structure=new ResponseStructure<Instructor>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(savedInstructor);
		return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.CREATED);
	}
	//----------------------fetchAll-------------------------
	public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructor(){
		List<Instructor> list=instructorDao.getAllInstructor();
		ResponseStructure<List<Instructor>> structure=new ResponseStructure<List<Instructor>>();
		if(!list.isEmpty()) {
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(list);
		return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure,HttpStatus.OK);
		}
		else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Instructor not found");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure,HttpStatus.NOT_FOUND);
		}
}
	//-----------------fetchById---------------------
	public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(Integer id) {
		Optional<Instructor> opt=instructorDao.getInstructorById(id);
		ResponseStructure<Instructor> structure=new ResponseStructure<Instructor>();
		if(opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Instructor found");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.OK) ;
		}
		else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Instructor not found");
	        structure.setData(null);
	        return new ResponseEntity<ResponseStructure<Instructor>>(structure,HttpStatus.NOT_FOUND);
		}
	    }
	
	//--------------update-----------------
	public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(Instructor instructor) {
		Instructor  updateInstructor=instructorDao.saveInstructor(instructor);
			ResponseStructure<Instructor> structure=new ResponseStructure<Instructor>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Instructor update successfully");
			structure.setData(updateInstructor);
			return new ResponseEntity<>(structure,HttpStatus.OK);
		}
		
	//------------------------delete---------------
	public ResponseEntity<ResponseStructure<Instructor>> deleteInstructor(Integer id) {	
			Optional<Instructor> opt=instructorDao.getInstructorById(id);
				ResponseStructure<Instructor> structure=new ResponseStructure<Instructor>();
				if(opt.isPresent()) {
					instructorDao.deleteInstructor(opt.get());
					structure.setStatusCode(HttpStatus.ACCEPTED.value());
					structure.setMessage("Instructor delete successfully");
					structure.setData(opt.get());
					return new ResponseEntity<>(structure,HttpStatus.ACCEPTED);
				}
				else {
			        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			        structure.setMessage("Instructor not found");
		            structure.setData(null);
		        return new ResponseEntity<>(structure,HttpStatus.NOT_FOUND);
		
			    }
		}
	

}
