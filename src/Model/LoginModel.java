package Model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import DataFiles.Department;
import Controller.HumanResourceController;
import Controller.SecretaryController;

import View.HumanResourceView;
import View.LoginView;
import View.SecretaryView;


public class LoginModel {

	private String username;//the username entered in textfield
	private String password;//the password entered in textfield

	public void setUsername(String username) {//setter for username
		this.username=username;//sets the class variable
	}
	
	public void setPassword(String password){//setter for password
		this.password=password;//sets the class variable
	}
	
	@SuppressWarnings("unchecked")
	public void checkAdmin(LoginView loginView) {//checking the type of user and opening his/her view
		ArrayList<Department> departments=new ArrayList<Department>();//creating empty arraylist of departments
		try {
			FileInputStream departmentFIStream= new FileInputStream("Departments.dat");//FileInputStream for department to read file
			ObjectInputStream departmentOIStream= new ObjectInputStream(departmentFIStream);//obj input stream for department to read file
			departments=(ArrayList<Department>)departmentOIStream.readObject();//the arraylist is stored to the empty arraylist
			departmentOIStream.close();//the filed is closed
		}
		catch (EOFException e) {//catches exception
			
		} 
		catch (ClassNotFoundException e) {//catches exception
			e.printStackTrace();//does required functionality after catching exception
		} 
		catch (FileNotFoundException e) {//catches exception
			e.printStackTrace();//does required functionality after catching exception
		} 
		catch (IOException e) {//catches exception
			e.printStackTrace();//does required functionality after catching exception
		}
		
		//start of for loop to check for matching secretary credentials
		for (Department department : departments) {//each department is placed in the department variable
			if (username.equals(department.getDepartmentUsername())) {//if username is equal to any of stored departments
				if (password.equals(department.getDepartmentPassword())) {//if password is equal to any of stored departments
					SecretaryModel secretaryModel = new SecretaryModel(department.getDepartmentUsername());//creating model for secretary
					SecretaryController secretaryController = new SecretaryController(secretaryModel);//creating controller for secretary view
					SecretaryView secretaryView = new SecretaryView(secretaryModel, secretaryController);//creating view for secretary
					loginView.dispose();//closing login frame
					return;//returns
				}
				else {
					JOptionPane.showMessageDialog(null, "Password wrong");//wrong password message is shown
					return;//returns 
				}
			}
		}
		if(username.equals("admin")){//checking username for admin
			if(password.equals("admin")) {//checking password for admin
				HumanResourceModel humanResourceModel = new HumanResourceModel();//creates the model for admin view
				HumanResourceController humanResourceController = new HumanResourceController(humanResourceModel);//creates controller for admin view
				HumanResourceView humanResourceView = new HumanResourceView(humanResourceModel, humanResourceController);//creates view for admin view
				loginView.dispose();//closing login frame
			}
			else {//admin username is right, admin password is wrong
				JOptionPane.showMessageDialog(null, "Password wrong");//message of password wrong
			}
		}
		else{//no matching username
			JOptionPane.showMessageDialog(null, "Username wrong");//message of username wrong
		}
	}
}
