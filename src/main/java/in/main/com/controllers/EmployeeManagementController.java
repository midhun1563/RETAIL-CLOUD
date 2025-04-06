package in.main.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.main.com.dto.EmployeeLookupDto;
import in.main.com.entities.Employee;
import in.main.com.service.EmployeeService;

@RestController
public class EmployeeManagementController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addEmployees")
	public ResponseEntity<String> addEmployeeDetails( @RequestBody Employee employee) {

		employeeService.addEmployeeDetails(employee);

		return ResponseEntity.status(HttpStatus.CREATED).body("Employee Details Added Successfully");
	}

	@PutMapping("/updateEmployees/{employeeId}")
	public ResponseEntity<String> updateEmployeeDetails(@PathVariable String employeeId,
			@RequestBody Employee employee) {

		employeeService.updateEmployeeDetails(employeeId, employee);

		return ResponseEntity.status(HttpStatus.OK).body("Employee Updated Successfully");
	}
	
	
	@GetMapping("/getAllEmployees")
	
	public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "20") int size){
		
		Pageable pageable = PageRequest.of(page, size);

		
		return ResponseEntity.ok(employeeService.getAllEmployeeDetails(pageable));
	}
	
	
	@PutMapping("/updateEmployeeDepartment/{employeeId}")
	
	public ResponseEntity<String> updateEmployeeDepatment(@PathVariable String employeeId,@RequestParam String deptCode){
		
		employeeService.updateEmployeeDepatmentDetails(employeeId, deptCode);
		
		return ResponseEntity.ok("Employee Department is updated");
		
	}
	
	@GetMapping("/getEmployeeNamesAndId")
	
	public ResponseEntity<Page<EmployeeLookupDto>> getEmployeeNamesAndId(@RequestParam(required=false) Boolean lookup,
			@RequestParam(name="page" ,defaultValue="0")int page,@RequestParam(name="size",defaultValue="20")int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		return ResponseEntity.ok(employeeService.getEmployeeNameAndId(lookup,pageable));
		
	}
	
	
}