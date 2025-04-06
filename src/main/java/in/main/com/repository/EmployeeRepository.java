package in.main.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.main.com.dto.EmployeeLookupDto;
import in.main.com.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	
	Optional<Employee> findByEmployeeId(String employeeId);
	
	List<Employee> findBydeptCode(String deptCode);
	
	@Query("SELECT new in.main.com.dto.EmployeeLookupDto(e.employeeId, e.name) FROM Employee e")
	
	Page<EmployeeLookupDto> findAllEmployeeNameAndId(Pageable pageable);

}
