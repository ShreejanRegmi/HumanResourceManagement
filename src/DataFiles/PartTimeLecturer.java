package DataFiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PartTimeLecturer extends Lecturer implements Serializable {//part time lecturer class
	private double pTLecturerHourlyRate;//hourly rate of part time lecturer
	
	//constructor of part time lecturer
	public PartTimeLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, double pTLecturerHourlyRate) {
		//calling the constructor of super class 
		super(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, lecturerContact, lecturerDateStarted,
				lecturerDepartment, lecturerGender);
		this.pTLecturerHourlyRate=pTLecturerHourlyRate;//sets the hourly rate
	}

	//returns the hourly rate
	public double getPTLecturerHourlyRate() {
		return pTLecturerHourlyRate;
	}

	//sets the hourly rate
	public void setPTLecturerHourlyRate(double pTLecturerHourlyRate) {
		this.pTLecturerHourlyRate = pTLecturerHourlyRate;
	}

}
