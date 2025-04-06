package in.main.com.dto;
import java.util.Date;
import java.util.List;

public class DepartmentDto {

	
	private Integer id;

	private String name;

	
	private String code;

	private String departmentHead;

	private Date createdDate;
	
	private List<EmployeeDto> employeeDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<EmployeeDto> getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(List<EmployeeDto> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	
	
	

}
