package in.main.com.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.main.com.dto.DepartmentDto;
import in.main.com.dto.EmployeeDto;
import in.main.com.entities.Department;
import in.main.com.entities.Employee;
import in.main.com.repository.DepartementRepository;
import in.main.com.repository.EmployeeRepository;
import in.main.com.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartementRepository departementRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addDepatmentDetails(Department department) {

		if (!isDeptCodeAlreadyExist(department.getCode())) {

			departementRepository.save(department);
		} else {

	        throw new IllegalArgumentException("Department code already exists: " + department.getCode());
		}

	}

	@Override
	public void deleteDepartmentDetails(String deptCode) {

		String code = deptCode;

		List<Employee> employeeList = employeeRepository.findBydeptCode(deptCode);

		if (!employeeList.isEmpty()) {
			throw new IllegalStateException(
					"Cannot delete department '" + deptCode + "'. Employees are already assigned to this department.");
		}

		Department department = departementRepository.findByCode(code);

		if (department != null) {

			departementRepository.delete(department);

		}

		else {

			throw new NoSuchElementException("Department not found with code: " + deptCode);
		}
	}

	@Override
	public Page<Department> getAllDepartmentDetails(Pageable pageable) {

		return departementRepository.findAll(pageable);
	}

	@Override
	public void updateDepartmentDetails(String deptCode, Department department) {

		try {
			if (!deptCode.isBlank() && department != null) {

				Department exitingDepartment = departementRepository.findByCode(deptCode);

				if (null != exitingDepartment) {

					setUpdatedDetails(department, exitingDepartment);

					if (!isDeptCodeAlreadyExist(department.getCode())) {

						departementRepository.save(exitingDepartment);
						
						upadteEmployeeDept(deptCode,exitingDepartment);

					} else {

						throw new IllegalArgumentException("Department code already exists: " + department.getCode());

					}
				}

				else {

					throw new RuntimeException("Department  not found with Code: " + deptCode);

				}

			}

			else {

				throw new IllegalArgumentException("Invalid deptCode or Object");
			}
		}

		catch (Exception e) {

			e.printStackTrace();

			throw e;
		}

	}

	private void setUpdatedDetails(Department department, Department exitingDepartment) {

		if (department.getName() != null && !department.getName().isBlank()) {
			exitingDepartment.setName(department.getName());
		}

		if (department.getCreatedDate() != null ) {
			exitingDepartment.setCreatedDate(department.getCreatedDate());
		}
		
		if (department.getCode() != null && !department.getCode().isBlank()) {
			exitingDepartment.setCode(department.getCode());
		}

		if (department.getDepartmentHead() != null && !department.getDepartmentHead().isBlank()) {
			exitingDepartment.setDepartmentHead(department.getDepartmentHead());
		}
	}

	@Override
	public DepartmentDto getDepartmentWithEmployeeDetails(String deptCode, String expand) {

		DepartmentDto deptDto = new DepartmentDto();

		try {
			if (!deptCode.isEmpty()) {
				Department dept = departementRepository.findByCode(deptCode);

				if (dept != null) {
					deptDto = mapToDepartmentDto(dept);

					if ("employee".equalsIgnoreCase(expand)) {
						List<Employee> employeeList = employeeRepository.findBydeptCode(deptCode);
						List<EmployeeDto> employeeDtoList = employeeList.stream().map(this::mapToEmployeeDto)
								.collect(Collectors.toList());

						deptDto.setEmployeeDetails(employeeDtoList);
					}
				}

				else {

					throw new NoSuchElementException("Department not found with code: " + deptCode);

				}
			} else {

				throw new IllegalArgumentException("Department code cannot be null or empty");

			}
		} catch (Exception e) {

			e.printStackTrace();

			throw e;
		}

		return deptDto;
	}

	private DepartmentDto mapToDepartmentDto(Department dept) {

		DepartmentDto dto = new DepartmentDto();

		dto.setId(dept.getId());

		dto.setName(dept.getName());

		dto.setCode(dept.getCode());

		dto.setCreatedDate(dept.getCreatedDate());

		dto.setDepartmentHead(dept.getDepartmentHead());

		return dto;
	}

	private EmployeeDto mapToEmployeeDto(Employee emp) {

		EmployeeDto dto = new EmployeeDto();

		dto.setId(emp.getId());

		dto.setName(emp.getName());

		dto.setEmployeeId(emp.getEmployeeId());

		dto.setDateOfBirth(emp.getDateOfBirth());

		dto.setDepartment(emp.getDepartment());

		dto.setAddress(emp.getAddress());

		dto.setRole(emp.getRole());

		dto.setJoinDate(emp.getJoinDate());

		dto.setBonus(emp.getBonus());

		dto.setReportingManager(emp.getReportingManager());

		dto.setDeptCode(emp.getDeptCode());

		dto.setActive(emp.getActive());
		return dto;
	}
	
	
	private boolean isDeptCodeAlreadyExist(String deptCode) {
		
	boolean flag = false;
	
	Department department =	departementRepository.findByCode(deptCode);
	
	if(null != department) {
		
		flag= true;
	}
		
		return flag;
	}
	
	private void upadteEmployeeDept(String deptCode ,Department exitingDepartment) {
		
		List<Employee> list = employeeRepository.findBydeptCode(deptCode);
		
		for(Employee emp :list) {
			
			emp.setDepartment(exitingDepartment.getName());
			
			emp.setDeptCode(exitingDepartment.getCode());
			
			employeeRepository.save(emp);
			
		}
	}

}
