package in.main.com.serviceimpl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.main.com.dto.EmployeeLookupDto;
import in.main.com.entities.Department;
import in.main.com.entities.Employee;
import in.main.com.repository.DepartementRepository;
import in.main.com.repository.EmployeeRepository;
import in.main.com.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartementRepository departementRepository;

	@Override
	public void addEmployeeDetails(Employee employee) {

		if (!isEmployeeIdAlreadyExist(employee.getEmployeeId())) {

			employeeRepository.save(employee);

		} else {

			throw new IllegalArgumentException("Employee ID already exists: " + employee.getEmployeeId());

		}

	}

	@Override
	public void updateEmployeeDetails(String employeeId, Employee employee) {

		try {
			if (!employeeId.isBlank() && employee != null) {

				Optional<Employee> exitingEmployee = employeeRepository.findByEmployeeId(employeeId);

				if (exitingEmployee.isPresent()) {

					setUpdatedDetails(employee, exitingEmployee.get());

					if (!isEmployeeIdAlreadyExist(employee.getEmployeeId())) {

						employeeRepository.save(exitingEmployee.get());

					} else {

						throw new IllegalArgumentException("Employee ID already exists: " + employee.getEmployeeId());

					}
				}

				else {

					throw new NoSuchElementException("Employee not found with ID: " + employeeId);

				}

			}

			else {

				throw new IllegalArgumentException("Invalid EmployeeId or Object");
			}
		}

		catch (Exception e) {

			e.printStackTrace();

			throw e;
		}
	}

	private void setUpdatedDetails(Employee employee, Employee exitingEmployee) {

		if (employee.getName() != null && !employee.getName().isBlank()) {
			exitingEmployee.setName(employee.getName());
		}

		if (employee.getDateOfBirth() != null) {
			exitingEmployee.setDateOfBirth(employee.getDateOfBirth());
		}

		if (employee.getDepartment() != null) {
			exitingEmployee.setDepartment(employee.getDepartment());
		}

		if (employee.getAddress() != null && !employee.getAddress().isBlank()) {
			exitingEmployee.setAddress(employee.getAddress());
		}

		if (employee.getRole() != null) {
			exitingEmployee.setRole(employee.getRole());
		}

		if (employee.getJoinDate() != null) {
			exitingEmployee.setJoinDate(employee.getJoinDate());
		}

		if (employee.getBonus() != null) {
			exitingEmployee.setBonus(employee.getBonus());
		}

		if (employee.getReportingManager() != null) {
			exitingEmployee.setReportingManager(employee.getReportingManager());
		}

		if (employee.getDeptCode() != null && !employee.getDeptCode().isBlank()) {
			exitingEmployee.setDeptCode(employee.getDeptCode());
		}
	}

	@Override
	public Page<Employee> getAllEmployeeDetails(Pageable pageable) {

		return employeeRepository.findAll(pageable);
	}

	@Override
	public void updateEmployeeDepatmentDetails(String employeeId, String deptCode) {

		try {

			if (!employeeId.isEmpty() && !deptCode.isEmpty()) {

				Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);

				if (employee.isPresent()) {

					Department dept = departementRepository.findByCode(deptCode);

					if (null != dept) {

						employee.get().setDeptCode(deptCode);

						if (!dept.getName().isEmpty()) {

							employee.get().setDepartment(dept.getName());

						}

						employeeRepository.save(employee.get());

					} else {

						throw new NoSuchElementException("Department Not Found for Code" + deptCode);

					}
				} else {

					throw new NoSuchElementException("Employee Not Found for EmployeeId" + employeeId);
				}

			}

			else {

				throw new RuntimeException("Invalid EmployeeId or deptCode");
			}
		}

		catch (Exception e) {

			e.printStackTrace();

			throw e;
		}

	}

	@Override
	public Page<EmployeeLookupDto> getEmployeeNameAndId(Boolean lookup, Pageable pageable) {

		if (Boolean.TRUE.equals(lookup)) {

			return employeeRepository.findAllEmployeeNameAndId(pageable);
		}

		return Page.empty();
	}

	private boolean isEmployeeIdAlreadyExist(String employeeId) {

		boolean flag = false;

		Optional<Employee> department = employeeRepository.findByEmployeeId(employeeId);

		if (department.isPresent()) {

			flag = true;
		}

		return flag;
	}

}
