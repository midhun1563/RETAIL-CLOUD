package in.main.com.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity

@Table(name = "employee_details")

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	@NotEmpty(message = "Employee Name Cannot be Empty")
	private String name;

	@Column(name = "employee_id")
	@NotEmpty(message = "Employee ID Cannot be Empty")
	private String employeeId;

	@NotNull(message = "Employee DOB Cannot be Empty")
	@Column(name = "date_of_birth")

	private Date dateOfBirth;

	@Column
	@NotEmpty(message = "Employee Department Cannot be Empty")

	private String department;

	@Column
	@NotNull(message = "Employee salary Cannot be Empty")

	private Integer salary;

	@Column
	@NotEmpty(message = "Employee address Cannot be Empty")
	private String address;

	@Column
	@NotEmpty(message = "Employee role Cannot be Empty")

	private String role;

	@Column(name = "join_date")
	@NotNull(message = "Join Date Cannot be Empty")
	private Date joinDate;

	@Column
	@NotNull(message = "Bonus Cannot be Empty")
	private Integer bonus;

	@Column(name = "reporting_manager")
	@NotEmpty(message = "Reporting Manage Cannot be Empty")

	private String reportingManager;

	@Column(name = "dept_code")
	@NotEmpty(message = "Department Code Cannot be Empty")

	private String deptCode;

	@Column
	private Boolean active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
