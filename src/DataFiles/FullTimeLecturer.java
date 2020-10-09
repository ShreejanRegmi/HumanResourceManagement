package DataFiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FullTimeLecturer extends Lecturer implements Serializable {//class of full time lecturer
	private double fTLecturerSalary;//full time lecturer's salary

	///constructor of full time lecturer
	public FullTimeLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, double fTLecturerSalary) {
		//calling the constructor of super class of full time lecturer
		super(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, lecturerContact, lecturerDateStarted,
				lecturerDepartment, lecturerGender);
		this.fTLecturerSalary=fTLecturerSalary;//sets the salary
	}

	public double getFTLecturerSalary() {//returns the salary of full time lecturer
		return fTLecturerSalary;//returns the salary
	}
	
	public void setFTLecturerSalary(double ftLecturerSalary) {//sets the salary of full time lecturer
		this.fTLecturerSalary=ftLecturerSalary;
	}
}
