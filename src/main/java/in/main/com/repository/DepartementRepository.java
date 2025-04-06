package in.main.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.main.com.entities.Department;

@Repository
public interface DepartementRepository extends JpaRepository<Department, Integer> {

	
	
	 
	@Query("SELECT d FROM Department d WHERE d.code = :code")
	
	Department findByCodeCustom(@Param("code") String code);
	 
	 Department findByCode(String code);
}
