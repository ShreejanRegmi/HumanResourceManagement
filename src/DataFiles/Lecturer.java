package DataFiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Lecturer implements Serializable{//Lecturer class
	
	protected int lecturerID;//lecturer id 
	protected String lecturerFullName;//full name of lecturer
	protected String lecturerAddress;//address of lecturer
	protected String lecturerEmail;//email of lecturer
	protected String lecturerContact;//contact of lecturer
	protected String lecturerDateStarted;//start date of lecturer
	protected String lecturerDepartment;//department the lecturer is assigned to
	protected String lecturerGender;//gender of lecturer
	
	//constructor of Lecturer Class
	public Lecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender) {
		setLecturerID(lecturerID);//sets lecturer ID
		setLecturerFullName(lecturerFullName);// sets lecturer full name
		setLecturerAddress(lecturerAddress);// sets lecturer address
		setLecturerEmail(lecturerEmail);//sets lecturer email
		setLecturerContact(lecturerContact);//sets contact of lecturer
		setLecturerDateStarted(lecturerDateStarted);//sets date started of lecturer
		setLecturerDepartment(lecturerDepartment);//sets department of lecturer
		setLecturerGender(lecturerGender);//sets gender of lecturer
	}

	public int getLecturerID() {//returns lecturer id
		return lecturerID;
	}

	public String getLecturerFullName() {//returns full name of lecturer
		return lecturerFullName;
	}

	public String getLecturerAddress() {//returns address of lecturer
		return lecturerAddress;
	}

	public String getLecturerEmail() {//returns email of lecturer
		return lecturerEmail;
	}

	public String getLecturerContact() {//returns contact information of lecturer
		return lecturerContact;
	}

	public String getLecturerDateStarted() {//returns start date of lecturer
		return lecturerDateStarted;
	}

	public String getLecturerDepartment() {//returns department of lecturer
		return lecturerDepartment;
	}

	public String getLecturerGender() {//returns gender of lecturer
		return lecturerGender;
	}

	public void setLecturerID(int lecturerID) {//sets lecturer's ID
		this.lecturerID = lecturerID;
	}

	public void setLecturerFullName(String lecturerFullName) {//setting lecturer's full name
		this.lecturerFullName = lecturerFullName;
	}

	public void setLecturerAddress(String lecturerAddress) {//sets lecturer's address
		this.lecturerAddress = lecturerAddress;
	}

	public void setLecturerEmail(String lecturerEmail) {//sets lecturer's email address
		this.lecturerEmail = lecturerEmail;
	}

	public void setLecturerContact(String lecturerContact) {//sets lecturer's contact information
		this.lecturerContact = lecturerContact;
	}

	public void setLecturerDateStarted(String lecturerDateStarted) {//sets lecturer's date started
		this.lecturerDateStarted = lecturerDateStarted;
	}

	public void setLecturerDepartment(String lecturerDepartment) {//sets lecturer's department
		this.lecturerDepartment = lecturerDepartment;
	}

	public void setLecturerGender(String lecturerGender) {//sets lecturer's gender
		this.lecturerGender = lecturerGender;
	}
}