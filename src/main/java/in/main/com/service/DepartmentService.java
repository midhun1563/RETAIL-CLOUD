package in.main.com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.main.com.dto.DepartmentDto;
import in.main.com.entities.Department;

public interface DepartmentService {

	
	public void addDepatmentDetails(Department department);
	
	public void deleteDepartmentDetails(String deptCode);
	
	public void updateDepartmentDetails(String deptCode,Department department);

	public Page<Department> getAllDepartmentDetails(Pageable pageable);
	
	public DepartmentDto getDepartmentWithEmployeeDetails(String deptCode,String expand);
	
	
}
