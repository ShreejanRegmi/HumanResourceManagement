package Model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DataFiles.ContractLecturer;
import DataFiles.Department;
import DataFiles.FullTimeLecturer;
import DataFiles.Lecturer;
import DataFiles.PartTimeLecturer;

public class HumanResourceModel {//model for administrator
	
	//below contains method to write departments to file
	public void writeDepartment(ArrayList<Department> departments) {//takes parameter departments
		try {
			FileOutputStream departmentFStream= new FileOutputStream("Departments.dat");//fileoutputstream for writing object in file
			ObjectOutputStream departmentOStream = new ObjectOutputStream(departmentFStream);//objectoutputstream for writing object in file
			departmentOStream.writeObject(departments);///the object is written to file
			departmentOStream.close();//input stream is closed
			System.out.println("Writing to file successful");//message is shown
		}
		catch(FileNotFoundException e) {//if exception caught
			JOptionPane.showMessageDialog(null, "Failed! File not found");//message shown
		}
		catch(IOException e) {//if exception caught
			JOptionPane.showMessageDialog(null, "Failed! IO Error has occured while adding department");//message shown
		}
	}
	
	//below contains method to write lecturers
	public void writeLecturer(ArrayList<Lecturer> lecturers) {//takes parameter departments
		try {
			FileOutputStream fTFStream= new FileOutputStream("Lecturers.dat");//fileoutputstream for writing object in file
			ObjectOutputStream fTOStream= new ObjectOutputStream(fTFStream) ;//objectoutputstream for writing object in file	
			fTOStream.writeObject(lecturers);//the object is written in file
			fTOStream.close();//input stream is closed
			System.out.println("Writing to file successful");//message is shown
		}
		catch(FileNotFoundException e) {//if exception caught
			JOptionPane.showMessageDialog(null, "Failed! File not found");//message shown
		}
		catch(IOException e) {//if exception caught
			JOptionPane.showMessageDialog(null, "Failed! IO Error has occured while adding full-time lecturer");//message shown
		}
	}
	
	//below contains method to add department
	public void addDepartment(String name, String type, String webAddress, String username, String password){//method to add department
		ArrayList<Department> departments=getDepartments();//returns all department objects currently in file
		Department newDepartment = new Department(name, type, webAddress, username, password);//creates new department
		departments.add(newDepartment);///adds department to arraylist
		writeDepartment(departments);//the new arraylist is written
	}
	
	
	
	//for the combobox
	public Vector<String> getDepartmentList(){//returns vector of department names
		Vector<String> departmentList = new Vector<String>(20);//new vector is created to store department names
		ArrayList<Department> departments = getDepartments();//the departments are returned
		for (Department department : departments) {//each department is iterated 
			departmentList.add(department.getDepartmentName());//name is added to the vector
		}
		return departmentList;//departments are returned
	}
	
	//full-time
	//below contains code to add fulltime lecturer
	public void addFTLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, double fTLecturerSalary){
		ArrayList<Lecturer> lecturers= getLecturers();//all the lecturers are returned
		FullTimeLecturer fTLecturer = new FullTimeLecturer(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, 
				lecturerContact, lecturerDateStarted, lecturerDepartment, lecturerGender, fTLecturerSalary);//new fulltime lecturer object is created
		lecturers.add(fTLecturer);//object is added to arraylist
		writeLecturer(lecturers);//the lecturers are written to file
		
	}
	
	
	//for generating lecturers list
	//below contains the code to generate table for full time lecturers
	public JTable getFTLecturersTable() {
	String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender","Salary"};//the columns of the table
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//default table model is created
			@Override
			public boolean isCellEditable(int row, int col) {//making non editable cells
				return false;
			}
		};
		JTable fTLecturersTable= new JTable(tableModel);//new table is created
		
		ArrayList<Lecturer> lecturers = getLecturers();//all lecturers are returned
		
		for (Object lecturer : lecturers) {//each lecturer is iterated
			if(lecturer instanceof FullTimeLecturer) {//if lecturer is full-time 
				int id= ((FullTimeLecturer) lecturer).getLecturerID();//sets the value in object to the assigned variable
				String fullName= ((FullTimeLecturer) lecturer).getLecturerFullName();//sets the value in object to the assigned variable
				String address= ((FullTimeLecturer) lecturer).getLecturerAddress();//sets the value in object to the assigned variable
				String email= ((FullTimeLecturer) lecturer).getLecturerEmail();//sets the value in object to the assigned variable
				String contact= ((FullTimeLecturer) lecturer).getLecturerContact();//sets the value in object to the assigned variable
				String dateStarted= ((FullTimeLecturer) lecturer).getLecturerDateStarted();//sets the value in object to the assigned variable
				String department= ((FullTimeLecturer) lecturer).getLecturerDepartment();//sets the value in object to the assigned variable
				String gender= ((FullTimeLecturer) lecturer).getLecturerGender();//sets the value in object to the assigned variable
				double salary= ((FullTimeLecturer) lecturer).getFTLecturerSalary();//sets the value in object to the assigned variable
				Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, salary};//creates an array of the values
				tableModel.addRow(tableData);//adds the row to the table at last
			}
		}
		fTLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);//sets the preferred width of each column
		fTLecturersTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		fTLecturersTable.getColumnModel().getColumn(8).setPreferredWidth(4);
		fTLecturersTable.getColumnModel().getColumn(3).setPreferredWidth(150);	
		return fTLecturersTable;//returns the table
	}
	
	//part=time
	//below contains code to add parttime lecturer
	public void addPTLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, double pTLecturerHourlyRate){
		ArrayList<Lecturer> lecturers= getLecturers();//returns all lectuerers
		PartTimeLecturer pTLecturer = new PartTimeLecturer(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, 
				lecturerContact, lecturerDateStarted, lecturerDepartment, lecturerGender, pTLecturerHourlyRate);//creates new lecturer object
		lecturers.add(pTLecturer);//adds the new lecturer object to arraylist
		writeLecturer(lecturers);//writes the arraylist
	}
	
	//code to return table 
	public JTable getPTLecturersTable() {//returns the table of parttime lecturers
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender","Hourly Rate"};//sets columns
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//default table model is created
			@Override
			public boolean isCellEditable(int row, int col) {//editable cells- false
				return false;
			}
		};
		JTable pTLecturersTable= new JTable(tableModel);//new table is created
		ArrayList<Lecturer> lecturers =getLecturers();//all lecturers are get
		for (Object lecturer : lecturers) {//each lecturer is iterated
			if(lecturer instanceof PartTimeLecturer) {//if part time
				int id= ((PartTimeLecturer) lecturer).getLecturerID();//sets the value in object to the assigned variable
				String fullName= ((PartTimeLecturer) lecturer).getLecturerFullName();//sets the value in object to the assigned variable
				String address= ((PartTimeLecturer) lecturer).getLecturerAddress();//sets the value in object to the assigned variable
				String email= ((PartTimeLecturer) lecturer).getLecturerEmail();//sets the value in object to the assigned variable
				String contact= ((PartTimeLecturer) lecturer).getLecturerContact();//sets the value in object to the assigned variable
				String dateStarted= ((PartTimeLecturer) lecturer).getLecturerDateStarted();//sets the value in object to the assigned variable
				String department= ((PartTimeLecturer) lecturer).getLecturerDepartment();//sets the value in object to the assigned variable
				String gender= ((PartTimeLecturer) lecturer).getLecturerGender();//sets the value in object to the assigned variable
				double hourlyRate= ((PartTimeLecturer) lecturer).getPTLecturerHourlyRate();//sets the value in object to the assigned variable
				Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, hourlyRate};//creates row array
				tableModel.addRow(tableData);//adds row
			}
		}
		pTLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		pTLecturersTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		pTLecturersTable.getColumnModel().getColumn(8).setPreferredWidth(4);
		pTLecturersTable.getColumnModel().getColumn(3).setPreferredWidth(150);	
		return pTLecturersTable;
	}
	
	//contract
	//method to add contract lectuer
	public void addCLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, String cLecturerEndDate, double cLecturerSalary) {
		ArrayList<Lecturer> lecturers= getLecturers();//all lecturers are returned
		ContractLecturer cLecturer = new ContractLecturer(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, 
				lecturerContact, lecturerDateStarted, lecturerDepartment, lecturerGender, cLecturerEndDate, cLecturerSalary);///new object created
		lecturers.add(cLecturer);//added to arraylist
		writeLecturer(lecturers);//lecturers written
	}
	
	public JTable getCLecturersTable(){//returns table
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender","End Date", "Salary"};//sets columns
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//default table model created
			@Override
			public boolean isCellEditable(int row, int col) {//non editable cells
				return false;
			}
		};
		JTable cLecturersTable= new JTable(tableModel);//new table created
		
		ArrayList<Lecturer> lecturers =getLecturers();//returns all lecturers in file
		
		for (Object lecturer : lecturers) {//iterates each of lecturer present in file
			if(lecturer instanceof ContractLecturer) {//if contract lecturer's type
				int id= ((ContractLecturer) lecturer).getLecturerID();//sets the value in object to the assigned variable
				String fullName= ((ContractLecturer) lecturer).getLecturerFullName();//sets the value in object to the assigned variable
				String address= ((ContractLecturer) lecturer).getLecturerAddress();//sets the value in object to the assigned variable
				String email= ((ContractLecturer) lecturer).getLecturerEmail();//sets the value in object to the assigned variable
				String contact= ((ContractLecturer) lecturer).getLecturerContact();//sets the value in object to the assigned variable
				String dateStarted= ((ContractLecturer) lecturer).getLecturerDateStarted();//sets the value in object to the assigned variable
				String department= ((ContractLecturer) lecturer).getLecturerDepartment();//sets the value in object to the assigned variable
				String gender= ((ContractLecturer) lecturer).getLecturerGender();//sets the value in object to the assigned variable
				String endDate= ((ContractLecturer) lecturer).getCLecturerEndDate();//sets the value in object to the assigned variable
				double salary =((ContractLecturer) lecturer).getCLecturerSalary();//sets the value in object to the assigned variable
				Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, endDate, salary};//creates array for row
				tableModel.addRow(tableData);//the row is added
			}
		}
		cLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		cLecturersTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		cLecturersTable.getColumnModel().getColumn(3).setPreferredWidth(150);	
		return cLecturersTable;
	}
	
	//method to return departments table
	public JTable getDepartmentsTable() {
		String columns[]= {"Name", "Type", "Web Address", "Username", "Password"};//columns of table
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//table model created
			@Override
			public boolean isCellEditable(int row, int col) {//cells editable false set
				return false;
			}
		};
		JTable departmentsTable= new JTable(tableModel);//new table created
		ArrayList<Department> departments = getDepartments();//all departments are returned
		for (Department department : departments) {//iterates each of department
				String name= ((Department) department).getDepartmentName();//sets the value in object to the assigned variable
				String type= ((Department) department).getDepartmentType();//sets the value in object to the assigned variable
				String webAddress= ((Department) department).getDepartmentWebAddress();//sets the value in object to the assigned variable
				String username= ((Department) department).getDepartmentUsername();//sets the value in object to the assigned variable
				String password= ((Department) department).getDepartmentPassword();//sets the value in object to the assigned variable
				Object tableData[]= {name, type, webAddress, username, password};//created array
				tableModel.addRow(tableData);//adds row
		}
		
		return departmentsTable;//returns table
	}
	
	//method to return all departments in file
	@SuppressWarnings("unchecked")
	public ArrayList<Department> getDepartments() {
		ArrayList<Department> departments = new ArrayList<Department>();//new arraylist created
		try {
			FileInputStream dFIStream= new FileInputStream("Departments.dat");//file input stream created
			ObjectInputStream dFOStream= new ObjectInputStream(dFIStream);//object stream is created
			departments= (ArrayList<Department>)(dFOStream.readObject());//array list is set
			dFOStream.close();//the object stream is closed
		} 
		catch(EOFException e) {//catching exception
			
		}
		catch (FileNotFoundException e) {//catching exception
			JOptionPane.showMessageDialog(null, "Failed! File not found");
		} 
		catch (IOException e) {//catching exception
			JOptionPane.showMessageDialog(null, "Failed! IO Error has occured while listing department");
		} 
		catch (ClassNotFoundException e) {//catching exception
			JOptionPane.showMessageDialog(null, "Failed! Class not found");
		}
		return departments;
	}
	
	//method to edit department
	public void editDepartment(String name, String type, String webAddress, String username, String password, int index) {
		ArrayList<Department> departments = getDepartments();//returns all departments
		ArrayList<Lecturer> lecturers= getLecturers();//returns all lecturers
		String oldDepartName = departments.get(index).getDepartmentName();//old department name
		int i=0;//initialize counter variable
		for (Lecturer lecturer : lecturers) {//iterates array
			if(lecturer.getLecturerDepartment().equals(oldDepartName)) {//if old name and existing name same
				lecturers.get(i).setLecturerDepartment(name);//set new name
			}
			i++;//counter +
		}
		writeLecturer(lecturers);//write object to file
		Department editedDepartment = new Department(name, type, webAddress, username, password);//new department created
		departments.set(index, editedDepartment);//replace old department
		writeDepartment(departments);//write to file
	}
	
	//method to delete department
	public void deleteDepartment(int index) {//takes parameter index
		ArrayList<Department> departments = getDepartments();//returns all departments
		Department toDeleteDepartment= departments.get(index);//the department to be deleted
		ArrayList<Lecturer> lecturers= getLecturers();//returns all lecturers
		int i=0;//counter
		for (Lecturer lecturer : lecturers) {//iterates all lectuerers
			if(lecturer.getLecturerDepartment().equals(toDeleteDepartment.getDepartmentName())) {//old and existing department name same
				lecturers.get(i).setLecturerDepartment("");//set new department name
			}
			i++;//counter increase
		}
		writeLecturer(lecturers);//writes to file
		departments.remove(index);//removes the object
		writeDepartment(departments);//writes departments
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Lecturer> getLecturers(){//returns all lecturers
		ArrayList<Lecturer> lecturers= new ArrayList<Lecturer>();//new array list created
		try {
			FileInputStream fTFIStream= new FileInputStream("Lecturers.dat");//file output stream created
			ObjectInputStream fTOIStream= new ObjectInputStream(fTFIStream);//object output stream created
			lecturers=(ArrayList<Lecturer>)fTOIStream.readObject();//all lectuers are stored in array list
			fTOIStream.close();//stream closed
		}
		catch (EOFException e) {//catching exception
			
		}
		catch(ClassNotFoundException e) {//catching exception
			JOptionPane.showMessageDialog(null, "Error! Class not found");
		} 
		catch (FileNotFoundException e) {//catching exception
			e.printStackTrace();
		} 
		catch (IOException e) {//catching exception
			e.printStackTrace();
		}
		return lecturers;
	}
	
	//code to edit full time lecturer
	public void editFTLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, double fTLecturerSalary, int index) {
		ArrayList<Lecturer> lecturers = getLecturers();//returns all lecturers
		FullTimeLecturer editedLecturer = new FullTimeLecturer(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, 
				lecturerContact, lecturerDateStarted, lecturerDepartment, lecturerGender, fTLecturerSalary);//new lecturer object
		lecturers.set(index, editedLecturer);//sets edited lecturer
		writeLecturer(lecturers);//writes to file
	}
	
	public void editPTLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, double pTLecturerHourlyRate, int index) {
		ArrayList<Lecturer> lecturers = getLecturers();//returns all lecturers
		PartTimeLecturer editedLecturer = new PartTimeLecturer(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, 
				lecturerContact, lecturerDateStarted, lecturerDepartment, lecturerGender, pTLecturerHourlyRate);//new lecturer object
		lecturers.set(index, editedLecturer);//sets edited lecturer
		writeLecturer(lecturers);//writes to file
	}
	
	public void editCLecturer(int lecturerID, String lecturerFullName, String lecturerAddress, String lecturerEmail,
			String lecturerContact, String lecturerDateStarted, String lecturerDepartment, String lecturerGender, String cLecturerEndDate, double cLecturerSalary, int index) {
		ArrayList<Lecturer> lecturers = getLecturers();//returns all lecturers
		ContractLecturer editedLecturer = new ContractLecturer(lecturerID, lecturerFullName, lecturerAddress, lecturerEmail, 
				lecturerContact, lecturerDateStarted, lecturerDepartment, lecturerGender, cLecturerEndDate, cLecturerSalary);//new lecturer object
		lecturers.set(index, editedLecturer);//sets edited lecturer
		writeLecturer(lecturers);//writes to file
	}
	
	//method to delete lecturer
	public void deleteLecturer(int lectID) {
		ArrayList<Lecturer> lecturers = getLecturers();//returns all lecturers
		int lecturerIndex = 0;
		for (Lecturer le : lecturers) {//iterating all lecturers
			if(le.getLecturerID()==lectID) {//if id is same
				lecturerIndex = lecturers.indexOf(le);//set lecturer index
				break;//break the loop
			}
		}
		lecturers.remove(lecturerIndex);//removes object
		writeLecturer(lecturers);//writes to file
	}
}