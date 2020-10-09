package DataFiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Department implements Serializable {//class department 

	private String departmentName;//the name of the department
	private String departmentType;//the department type
	private String departmentWebAddress;//web address of department
	private String departmentUsername;//user name stored in department
	private String departmentPassword;//password stored in department
	
	//constructor of department class
	public Department(String departmentName, String departmentType, String departmentWebAddress,
			String departmentUsername, String departmentPassword) {
		setDepartmentName(departmentName);//sets the department name
		setDepartmentType(departmentType);//sets the department type
		setDepartmentWebAddress(departmentWebAddress);//sets the department web address
		setDepartmentUsername(departmentUsername);//sets the department user name
		setDepartmentPassword(departmentPassword);//sets the department password
	}

	//returns the department name 
	public String getDepartmentName() {
		return departmentName;
	}
	
	//sets the department name
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	//returns the department type
	public String getDepartmentType() {
		return departmentType;
	}
	
	//sets the department type
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}
	
	//returns the department web address
	public String getDepartmentWebAddress() {
		return departmentWebAddress;
	}
	
	//sets the department web address
	public void setDepartmentWebAddress(String departmentWebAddress) {
		this.departmentWebAddress = departmentWebAddress;
	}
	
	//returns the department user name
	public String getDepartmentUsername() {
		return departmentUsername;
	}
	
	//sets the department user name
	public void setDepartmentUsername(String departmentUsername) {
		this.departmentUsername = departmentUsername;
	}
	
	//returns the department password
	public String getDepartmentPassword() {
		return departmentPassword;
	}
	
	//sets the department password
	public void setDepartmentPassword(String departmentPassword) {
		this.departmentPassword = departmentPassword;
	}
}
