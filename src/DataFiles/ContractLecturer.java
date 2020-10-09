package DataFiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContractLecturer extends Lecturer implements Serializable{//contract lecturer class
	
	private String cLecturerEndDate;//end date of contract lecturer
	private double cLecturerSalary;//salary of contract lecturer
	
	//constructor of contract lecturer
	public ContractLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, String cLecturerEndDate, double cLecturerSalary) {
		//calling the constructor of super class
		super(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, lecturerContact, lecturerDateStarted,
				lecturerDepartment, lecturerGender);
		this.cLecturerEndDate=cLecturerEndDate;//setting the end date of lecturer
		this.cLecturerSalary=cLecturerSalary;//setting the salary of lecturer
	}

	public String getCLecturerEndDate() {//returns the end date of lecturer
		return cLecturerEndDate;
	}

	//sets the end date of lecturer
	public void setCLecturerEndDate(String cLecturerEndDate) {
		this.cLecturerEndDate = cLecturerEndDate;
	}

	//returns the salary of contract lecturer
	public double getCLecturerSalary() {
		return cLecturerSalary;
	}

	//sets the salary of contract lecturer
	public void setCLecturerSalary(double cLecturerSalary) {
		this.cLecturerSalary = cLecturerSalary;
	}
}
