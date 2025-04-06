package in.main.com.dto;

public class EmployeeLookupDto {

	
	private String employeeName;
	
	private String employeeId;
	
	 public EmployeeLookupDto(String employeeId, String employeeName) {
	        this.employeeId = employeeId;
	        this.employeeName = employeeName;
	    }

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
