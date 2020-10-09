package Model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DataFiles.ContractLecturer;
import DataFiles.Department;
import DataFiles.FullTimeLecturer;
import DataFiles.Lecturer;
import DataFiles.PartTimeLecturer;

public class SecretaryModel {
	public String departmentUsername;//the department username stored
	
	//constructor of secretary model
	public SecretaryModel(String departmentUsername) {//takes argument departmentUsername
		this.departmentUsername=departmentUsername;//sets the class variable
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Department> getDepartments(){//returns all the departments
		ArrayList<Department> departments=new ArrayList<Department>();//creates blank array list
		try {
			FileInputStream departmentFIStream= new FileInputStream("Departments.dat");//creating input stream file
			ObjectInputStream departmentOIStream= new ObjectInputStream(departmentFIStream);//creating object input stream
			departments=(ArrayList<Department>)departmentOIStream.readObject();//the array list is set
			departmentOIStream.close();//stream is closed
		}
		catch (EOFException e) {//catching argument
			
		} 
		catch (ClassNotFoundException e) {//catching argument
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {//catching argument
			e.printStackTrace();
		} 
		catch (IOException e) {//catching argument
			e.printStackTrace();
		}
		return departments;//returns array list
	}
	
	public Department currentDepartment() {//returns the current object department
		ArrayList<Department> departments= getDepartments();//returns all departments
		Department currentDepartment=null;//current department initialization 
		for (Department department : departments) {//for all departments
			if(department.getDepartmentUsername().equals(this.departmentUsername)) {//if condition true
				currentDepartment=department;//value is set
				break;
			}
		}
		return currentDepartment;//returns object
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Lecturer> getLecturers(){//returns all lecturers
		ArrayList<Lecturer> lecturers= new ArrayList<Lecturer>();//creates empty arraylist
		try {
			FileInputStream fTFIStream= new FileInputStream("Lecturers.dat");//to read the file contents
			ObjectInputStream fTOIStream= new ObjectInputStream(fTFIStream);//object inpput stream to read file
			lecturers=(ArrayList<Lecturer>)fTOIStream.readObject();//assigned arraylist value
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
		return lecturers;//catching exception
	}
	
	public ArrayList<FullTimeLecturer> getDepartmentFT(){//returns all full time lecturers of the department
		ArrayList<Lecturer> lecturers= getLecturers();//returns all the lecturers of the department
		ArrayList<FullTimeLecturer> fullTimeLecturers= new ArrayList<FullTimeLecturer>();//creates empty array list
		for (Lecturer lecturer : lecturers) {//iterates all the objects of array
			if (lecturer instanceof FullTimeLecturer) {//if true
				if(lecturer.getLecturerDepartment().equals(currentDepartment().getDepartmentName())) {//if condition true
					fullTimeLecturers.add((FullTimeLecturer) lecturer);//object is added to the array list
				}
			}
		}	
		return fullTimeLecturers;//returns the array list
	}
	
	public ArrayList<PartTimeLecturer> getDepartmentPT(){//returns all part time lecturers of department
		ArrayList<Lecturer> lecturers= getLecturers();//returns all lecturers
		ArrayList<PartTimeLecturer> partTimeLecturers= new ArrayList<PartTimeLecturer>();//creating array list
		
		for (Lecturer lecturer : lecturers) {//for all lecturers
			if (lecturer instanceof PartTimeLecturer) {//if true
				if(lecturer.getLecturerDepartment().equals(currentDepartment().getDepartmentName())) {//if department same
					partTimeLecturers.add((PartTimeLecturer) lecturer);//add lecturer to array list
				}
			}
		}	
		return partTimeLecturers;//return array list
	}
	
	public ArrayList<ContractLecturer> getDepartmentC(){//returns all contract lecturers of the department
		ArrayList<Lecturer> lecturers= getLecturers();//returns all lecturers
		ArrayList<ContractLecturer> contractLecturers= new ArrayList<ContractLecturer>();//empty array list
		
		for (Lecturer lecturer : lecturers) {//for all lecturers
			if (lecturer instanceof ContractLecturer) {//if true
				if(lecturer.getLecturerDepartment().equals(currentDepartment().getDepartmentName())) {//if true
					contractLecturers.add((ContractLecturer) lecturer);//adds to array list
				}
			}
		}	
		return contractLecturers;//returns array list
	}
	
	
	
	//returns the table of full time lecturers
	public JTable getFullTimeLecturersTable() {
		ArrayList<FullTimeLecturer> fTLecturers = getDepartmentFT();//returns full time lecturers of department
		
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender","Salary"};//title of table
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//table model creation
			@Override
			public boolean isCellEditable(int row, int col) {//unchangeable table cells
				return false;
			}
		};
		JTable fTLecturersTable= new JTable(tableModel);//creating table
		
		for (Object lecturer : fTLecturers) {///for all lecturers
			if(lecturer instanceof FullTimeLecturer) {//if true
				int id= ((FullTimeLecturer) lecturer).getLecturerID();//value assigned to variable from object
				String fullName= ((FullTimeLecturer) lecturer).getLecturerFullName();//value assigned to variable from object
				String address= ((FullTimeLecturer) lecturer).getLecturerAddress();//value assigned to variable from object
				String email= ((FullTimeLecturer) lecturer).getLecturerEmail();//value assigned to variable from object
				String contact= ((FullTimeLecturer) lecturer).getLecturerContact();//value assigned to variable from object
				String dateStarted= ((FullTimeLecturer) lecturer).getLecturerDateStarted();//value assigned to variable from object
				String department= ((FullTimeLecturer) lecturer).getLecturerDepartment();//value assigned to variable from object
				String gender= ((FullTimeLecturer) lecturer).getLecturerGender();//value assigned to variable from object
				double salary= ((FullTimeLecturer) lecturer).getFTLecturerSalary();//value assigned to variable from object
				
				Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, salary};//array created
				tableModel.addRow(tableData);//row added
			}
		}
		fTLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		fTLecturersTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		fTLecturersTable.getColumnModel().getColumn(8).setPreferredWidth(4);
		fTLecturersTable.getColumnModel().getColumn(3).setPreferredWidth(150);	
		return fTLecturersTable;//return table
	}
	
	//code to return all part time lecturers table of department
	public JTable getPartTimeLecturersTable() {
		ArrayList<PartTimeLecturer> pTLecturers = getDepartmentPT();//gets part time lecturer
		
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender","Hourly Rate"};//title of table
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//creation of table model
			@Override
			public boolean isCellEditable(int row, int col) {//uneditable cells
				return false;
			}
		};
		JTable pTLecturersTable= new JTable(tableModel);//creation of table 
		
		for (Object lecturer : pTLecturers) {//for all part time lecturers
			if(lecturer instanceof PartTimeLecturer) {//if condition is true
				int id= ((PartTimeLecturer) lecturer).getLecturerID();//value assigned to variable from object
				String fullName= ((PartTimeLecturer) lecturer).getLecturerFullName();//value assigned to variable from object
				String address= ((PartTimeLecturer) lecturer).getLecturerAddress();//value assigned to variable from object
				String email= ((PartTimeLecturer) lecturer).getLecturerEmail();//value assigned to variable from object
				String contact= ((PartTimeLecturer) lecturer).getLecturerContact();//value assigned to variable from object
				String dateStarted= ((PartTimeLecturer) lecturer).getLecturerDateStarted();//value assigned to variable from object
				String department= ((PartTimeLecturer) lecturer).getLecturerDepartment();//value assigned to variable from object
				String gender= ((PartTimeLecturer) lecturer).getLecturerGender();//value assigned to variable from object
				double hourlyRate= ((PartTimeLecturer) lecturer).getPTLecturerHourlyRate();//value assigned to variable from object
				Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, hourlyRate};//array created
				tableModel.addRow(tableData);//row added to table
			}
		}
		pTLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		pTLecturersTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		pTLecturersTable.getColumnModel().getColumn(8).setPreferredWidth(4);
		pTLecturersTable.getColumnModel().getColumn(3).setPreferredWidth(150);	
		return pTLecturersTable;//table returned
	}
	
	//method to return all contract lecturers table in the department
	public JTable getContractLecturersTable() {
		ArrayList<ContractLecturer> cLecturers = getDepartmentC();//gets contract lecturers of the department
		
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender", "End Date", "Salary"};//sets columns of table
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//creates table model
			@Override
			public boolean isCellEditable(int row, int col) {//uneditable cells
				return false;
			}
		};
		JTable cLecturersTable= new JTable(tableModel);//creating table
		
		for (Object lecturer : cLecturers) {//for all contract lecturers
		
			if(lecturer instanceof ContractLecturer) {//if true
				int id= ((ContractLecturer) lecturer).getLecturerID();//value assigned to variable from object
				String fullName= ((ContractLecturer) lecturer).getLecturerFullName();//value assigned to variable from object
				String address= ((ContractLecturer) lecturer).getLecturerAddress();//value assigned to variable from object
				String email= ((ContractLecturer) lecturer).getLecturerEmail();//value assigned to variable from object
				String contact= ((ContractLecturer) lecturer).getLecturerContact();//value assigned to variable from object
				String dateStarted= ((ContractLecturer) lecturer).getLecturerDateStarted();//value assigned to variable from object
				String department= ((ContractLecturer) lecturer).getLecturerDepartment();//value assigned to variable from object
				String gender= ((ContractLecturer) lecturer).getLecturerGender();//value assigned to variable from object
				String endDate= ((ContractLecturer) lecturer).getCLecturerEndDate();//value assigned to variable from object
				double salary =((ContractLecturer) lecturer).getCLecturerSalary();//value assigned to variable from object
				Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, endDate, salary};//creating array
				tableModel.addRow(tableData);//row added
			}
		}
		cLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		cLecturersTable.getColumnModel().getColumn(7).setPreferredWidth(5);
		cLecturersTable.getColumnModel().getColumn(3).setPreferredWidth(150);	
		return cLecturersTable;///return table
	}
	
	public JTable getAllLecturersTable() {//returns all lecturers table in department
		ArrayList<Lecturer> lecturers = getLecturers();//returns all lecturers
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender", "Lecturer Type" ,"Salary", "Hourly Rate" ,"End Date"};//sets headings
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//creating table model
			@Override
			public boolean isCellEditable(int row, int col) {//uneditable cells
				return false;
			}
		};
		JTable allLecturersTable= new JTable(tableModel);//creating table
		for (Object lecturer : lecturers) {//for all lecturers
			if(((Lecturer) lecturer).getLecturerDepartment().equals(currentDepartment().getDepartmentName())){//if department same
				if(lecturer instanceof FullTimeLecturer) {//if true
					int id= ((FullTimeLecturer) lecturer).getLecturerID();//value assigned to variable from object
					String fullName= ((FullTimeLecturer) lecturer).getLecturerFullName();//value assigned to variable from object
					String address= ((FullTimeLecturer) lecturer).getLecturerAddress();//value assigned to variable from object
					String email= ((FullTimeLecturer) lecturer).getLecturerEmail();//value assigned to variable from object
					String contact= ((FullTimeLecturer) lecturer).getLecturerContact();//value assigned to variable from object
					String dateStarted= ((FullTimeLecturer) lecturer).getLecturerDateStarted();//value assigned to variable from object
					String department= ((FullTimeLecturer) lecturer).getLecturerDepartment();//value assigned to variable from object
					String gender= ((FullTimeLecturer) lecturer).getLecturerGender();//value assigned to variable from object
					String lecturerType = "Full Time";//if full time lecturer
					double salary= ((FullTimeLecturer) lecturer).getFTLecturerSalary();//value assigned to variable from object
					String hourlyRate="";//no value
					String endDate="";//no value
					Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, lecturerType ,salary, hourlyRate, endDate};
					tableModel.addRow(tableData);//row added
				}
				else if(lecturer instanceof PartTimeLecturer) {//if true
					int id= ((PartTimeLecturer) lecturer).getLecturerID();//value assigned to variable from object
					String fullName= ((PartTimeLecturer) lecturer).getLecturerFullName();//value assigned to variable from object
					String address= ((PartTimeLecturer) lecturer).getLecturerAddress();//value assigned to variable from object
					String email= ((PartTimeLecturer) lecturer).getLecturerEmail();//value assigned to variable from object
					String contact= ((PartTimeLecturer) lecturer).getLecturerContact();//value assigned to variable from object
					String dateStarted= ((PartTimeLecturer) lecturer).getLecturerDateStarted();//value assigned to variable from object
					String department= ((PartTimeLecturer) lecturer).getLecturerDepartment();//value assigned to variable from object
					String gender= ((PartTimeLecturer) lecturer).getLecturerGender();//value assigned to variable from object
					String lecturerType="Part Time";//if part time
					String salary="";//no value
					double hourlyRate= ((PartTimeLecturer) lecturer).getPTLecturerHourlyRate();//value assigned to variable from object
					String endDate="";//no value
					Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, lecturerType , salary, hourlyRate, endDate};
					tableModel.addRow(tableData);//row added
				}
				else if(lecturer instanceof ContractLecturer) {//if true
					int id= ((ContractLecturer) lecturer).getLecturerID();//value assigned to variable from object
					String fullName= ((ContractLecturer) lecturer).getLecturerFullName();//value assigned to variable from object
					String address= ((ContractLecturer) lecturer).getLecturerAddress();//value assigned to variable from object
					String email= ((ContractLecturer) lecturer).getLecturerEmail();//value assigned to variable from object
					String contact= ((ContractLecturer) lecturer).getLecturerContact();//value assigned to variable from object
					String dateStarted= ((ContractLecturer) lecturer).getLecturerDateStarted();//value assigned to variable from object
					String department= ((ContractLecturer) lecturer).getLecturerDepartment();//value assigned to variable from object
					String gender= ((ContractLecturer) lecturer).getLecturerGender();//value assigned to variable from object
					String lecturerType="Contract";//if contract
					String hourlyRate="";//no value
					String endDate= ((ContractLecturer) lecturer).getCLecturerEndDate();//value assigned to variable from object
					double salary =((ContractLecturer) lecturer).getCLecturerSalary();//value assigned to variable from object
					Object tableData[]= {id, fullName, address, email, contact, dateStarted, department, gender, lecturerType, salary, hourlyRate, endDate};
					tableModel.addRow(tableData);//row added
				}
			}
	}
		allLecturersTable.getColumnModel().getColumn(0).setPreferredWidth(1);
		allLecturersTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		allLecturersTable.getColumnModel().getColumn(2).setPreferredWidth(60);	
		return allLecturersTable; //returns table
	}
	
	//method for search
	public Object[] getTableData(int lectID) {
		ArrayList<Lecturer> lecturers = getLecturers();//gets all lecturers
		Object tableData[]= {};//empty array
		for(Lecturer lecturer : lecturers) {///for all lectuers
			if(lecturer.getLecturerID()==lectID && lecturer.getLecturerDepartment().equals(currentDepartment().getDepartmentName())) {//if condition is true
				String lecturerType="";//no value
				String salary="";//no value
				String hourlyRate="";//no value
				String endDate="";//no value
				int id= lecturer.getLecturerID();//value assigned to variable from object
				String fullName=  lecturer.getLecturerFullName();//value assigned to variable from object
				String address=lecturer.getLecturerAddress();//value assigned to variable from object
				String email= lecturer.getLecturerEmail();//value assigned to variable from object
				String contact= lecturer.getLecturerContact();//value assigned to variable from object
				String dateStarted= lecturer.getLecturerDateStarted();//value assigned to variable from object
				String department= lecturer.getLecturerDepartment();//value assigned to variable from object
				String gender=  lecturer.getLecturerGender();//value assigned to variable from object
				if(lecturer instanceof FullTimeLecturer) {//if full time lecturer
					 lecturerType = "Full Time";//no value
					 salary= Double.toString(((FullTimeLecturer) lecturer).getFTLecturerSalary());//value assigned to variable from object
					 hourlyRate="";//no value
					 endDate="";//no value
				}
				else if(lecturer instanceof PartTimeLecturer) {//if part time lecturer
					 lecturerType="Part Time";//no value
					 salary="";//no value
					 hourlyRate= Double.toString(((PartTimeLecturer) lecturer).getPTLecturerHourlyRate());//value assigned to variable from object
					 endDate="";//no value
				}
				else if(lecturer instanceof ContractLecturer) {//if contract lecturer
					 lecturerType="Contract";//no value
					 hourlyRate="";//no value
					 endDate= ((ContractLecturer) lecturer).getCLecturerEndDate();//value assigned to variable from object
					 salary = Double.toString(((ContractLecturer) lecturer).getCLecturerSalary());//value assigned to variable from object
				}
				Object []data= {id, fullName, address, email, contact, dateStarted, department, gender, lecturerType ,salary, hourlyRate, endDate};//array created
				//tableModel.addRow(tableData);//remaining to add the table to panel and run the code
				tableData= data;///
			}
		}
		return tableData;//array returned
	}
}
