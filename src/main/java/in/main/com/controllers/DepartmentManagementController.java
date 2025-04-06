package in.main.com.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.main.com.dto.DepartmentDto;
import in.main.com.entities.Department;
import in.main.com.service.DepartmentService;

@RestController
public class DepartmentManagementController {
 
	@Autowired
	private DepartmentService departmentService;
	
	
	
	@PostMapping("/addDepartments")
	
	public ResponseEntity<String> addDepartmentdetails(@RequestBody Department department){
		
		departmentService.addDepatmentDetails(department);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Department Details Added Successfully");
	}
	
	
	@PutMapping("/updateDepartments/{deptCode}")
	
	public ResponseEntity<String> updateDepartments(@PathVariable String deptCode,@RequestBody Department department){
		
		departmentService.updateDepartmentDetails(deptCode, department);
		
	return ResponseEntity.ok().body("Department Details Updated Successfully");
	}
	
	
	@DeleteMapping("/deleteDepartments/{deptCode}")
	
	public ResponseEntity<String> deleteDepatments(@PathVariable String deptCode){
		
		departmentService.deleteDepartmentDetails(deptCode);
		
		return ResponseEntity.ok("Department Details Deleted Successfully");
	}
	
	
	@GetMapping("/getAllDepartments")
	
	public ResponseEntity<Page<Department>> getAllDepartments(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size){
		
		Pageable pageable = PageRequest.of(page, size);

		return ResponseEntity.ok(departmentService.getAllDepartmentDetails(pageable));
	}
	
    @GetMapping("/getDepartmentWithEmployeeDetails/{deptCode}")
	public ResponseEntity<DepartmentDto> getDepatmentWithEmployeeDetails(@PathVariable String deptCode,
			@RequestParam(required= false) String expand){
		
    	return ResponseEntity.ok().body(departmentService.getDepartmentWithEmployeeDetails(deptCode, expand));
	}
	
	
}
