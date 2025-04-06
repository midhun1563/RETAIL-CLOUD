package in.main.com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.main.com.dto.EmployeeLookupDto;
import in.main.com.entities.Employee;

public interface EmployeeService {

	public void addEmployeeDetails(Employee employee);
	
	public void updateEmployeeDetails(String employeeId,Employee employee);
	 
	public Page<Employee> getAllEmployeeDetails(Pageable pageable);
	
	public void updateEmployeeDepatmentDetails(String employeeId,String deptCode);
	
	public Page<EmployeeLookupDto> getEmployeeNameAndId(Boolean lookup,Pageable pageable);

}
