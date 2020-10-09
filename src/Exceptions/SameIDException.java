package Exceptions;

@SuppressWarnings("serial")
public class SameIDException extends Exception {
	
	public SameIDException(){
		super("The ID is already assigned to another lecturer");
	}

}
