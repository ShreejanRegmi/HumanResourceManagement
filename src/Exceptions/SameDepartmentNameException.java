package Exceptions;

@SuppressWarnings("serial")
public class SameDepartmentNameException extends Exception {

	public SameDepartmentNameException(){
		super("A department with this name is already added");
	}
}
