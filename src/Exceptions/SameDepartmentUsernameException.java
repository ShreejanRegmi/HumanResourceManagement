package Exceptions;

@SuppressWarnings("serial")
public class SameDepartmentUsernameException extends Exception{
	public SameDepartmentUsernameException(){
		super("The department username is already assigned");
	}
}
