package Controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Main.Main;
import Model.HumanResourceModel;
import View.HumanResourceView;

public class HumanResourceController implements ActionListener, ListSelectionListener, MouseListener {//controller implementing various interfaces
	HumanResourceModel humanResourceModel;//the current instance of human resource model
	HumanResourceView humanResourceView;//the current instance of human resource view
	
	//constructor of controller
	public HumanResourceController(HumanResourceModel humanResourceModel) {//takes parameter model
		this.humanResourceModel=humanResourceModel;//model is set
	}
	
	//method to add the view to controller
	public void addView(HumanResourceView humanResourceView) {//takes view parameter
		this.humanResourceView=humanResourceView;//sets view
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cardContainer = (CardLayout)(humanResourceView.jPRightPanel.getLayout());//gets the card layout container
		
		if(e.getActionCommand().equals("Add Lecturer")) {//if add lecturer button pressed
			humanResourceView.resetFieldsForLAdd();//reset all fields
			humanResourceView.changeableText.setText("Add Lecturer");//sets the changing text
			cardContainer.show(humanResourceView.jPRightPanel, "Add Lecturer");//respective panel is shown
		}
		
		if(e.getActionCommand().equals("Full Time")) {// if full time button pressed
			cardContainer.show(humanResourceView.jPRightPanel, "Full Time");//sets changeable text
			humanResourceView.changeableText.setText("View/Edit Full Time Lecturers");// panel is shown
		}
		
		if(e.getActionCommand().equals("Part Time")) {// if part time button pressed
			cardContainer.show(humanResourceView.jPRightPanel, "Part Time");//sets changeable text
			humanResourceView.changeableText.setText("View/Edit Part Time Lecturers");//its panel is shown
		}
		
		if(e.getActionCommand().equals("Contract")) {//if contract button pressed
			cardContainer.show(humanResourceView.jPRightPanel, "Contract");//sets changeable text
			humanResourceView.changeableText.setText("View/Edit Contract Lecturers");//its panel is shown
		}
		
		if(e.getActionCommand().equals("Add Department")){//if add department pressed 
			humanResourceView.resetFieldsForDAdd();//sets fields empty
			cardContainer.show(humanResourceView.jPRightPanel, "Add Department");//panel is shown
			humanResourceView.changeableText.setText("Add Department");//sets changeable text
			
		}
		
		if(e.getActionCommand().equals("View Department")){
			cardContainer.show(humanResourceView.jPRightPanel, "View Department");
			humanResourceView.changeableText.setText("View/Edit Departments");
		}
		
		if(e.getSource().equals(humanResourceView.cBLecturerType)){
			@SuppressWarnings("unchecked")
			String selectedItem =(String) (((JComboBox<String>)(e.getSource())).getSelectedItem());//the selected item in string
			humanResourceView.setFields(selectedItem);//sets fields
		}
		
		if(e.getActionCommand().equals("Add Department Button")) {//if add department button is pressed
			dCurrentDepartmentIndex=-1;//sets flag
			String details[] = humanResourceView.getDepartmentDetails();//array of details
			try {
				this.humanResourceModel.addDepartment(details[0], details[1] , details[2], details[3] , details[4]);//add method is called
				((DefaultTableModel) humanResourceView.departmentsTable.getModel()).addRow(new String[] {details[0], details[1] , details[2], details[3] , details[4]});//row is added onto the table
				humanResourceView.cBDepartments.addItem(details[0]);//new item is added to combo box
				JOptionPane.showMessageDialog(null, "Department Added");//message is shown
				
			} 
			catch(NullPointerException e2) { //caught null pointer exception
				
			}
		}
		
		if(e.getActionCommand().equals("Add Lecturer Button")) {//if add lecturer button pressed
			String lectType= (String) humanResourceView.cBLecturerType.getSelectedItem();//gets the selected item in combo box
			try {
				String[] lecturerDetails= humanResourceView.getLecturerDetails();//gets the array of entered field values
				if(lectType.equals("Full-Time")) {//if full time
					humanResourceModel.addFTLecturer(Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
							lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], Double.parseDouble(lecturerDetails[7]));//adds full time lecturer
					
					((DefaultTableModel) humanResourceView.fTLecturersTable.getModel()).addRow(new Object[]{Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
						lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], lecturerDetails[7]});//adds row
				}
				else if(lectType.equals("Part-Time")) {//if part time lecturer
					humanResourceModel.addPTLecturer(Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
							lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], Double.parseDouble(lecturerDetails[9]));//adds part time lecturer
					
					((DefaultTableModel) humanResourceView.pTLecturersTable.getModel()).addRow(new Object[]{Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
							lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], lecturerDetails[9]});//adds row
				}
				else if(lectType.equals("Contract")) {//if contract lectuerer
					humanResourceModel.addCLecturer(Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
							lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], lecturerDetails[8] ,Double.parseDouble(lecturerDetails[7]));//adds contract lecturer
					
					((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).addRow(new Object[]{Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
							lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], lecturerDetails[8], lecturerDetails[7]});//adds row
				}
				JOptionPane.showMessageDialog(null, "Lecturer added successfully");
			}
			catch (NullPointerException ne) {//null pointer is caught
				
			}
		}
		
		//Edit Codes
		//Edit Department Code
		if(e.getActionCommand().equals("Edit Department Button")) {
			int index= humanResourceView.editIndexDepartment;//selected row
			try{
				String data[]=humanResourceView.getDepartmentDetails();//gets all entered fields
				humanResourceView.setEditedDepartmentInOtherTable(index,data[0]);//updates other table
				humanResourceModel.editDepartment(data[0], data[1] , data[2], data[3] , data[4], index);//method to edit department called
				for (int i = 0; i < data.length; i++) {
					((DefaultTableModel) humanResourceView.departmentsTable.getModel()).setValueAt(data[i], index, i);//sets column value in each column
				}
				humanResourceView.cBDepartments.removeItemAt(index);//removes the item in selected index in combo box
				humanResourceView.cBDepartments.insertItemAt(data[0], index);//sets new item
				JOptionPane.showMessageDialog(null, "Department edited successfully");//message shown
			}
			catch (NullPointerException ne) {
				
			}
		}
		
		//Edit FullTime Lecturer Code
		if(e.getActionCommand().equals("Edit FT Lecturer")) {
			int index= humanResourceView.editFTLecturerIndex;//selected row
			try {
				String lecturerDetails[]=humanResourceView.getLecturerDetails(); //array of all fields entered
				humanResourceModel.editFTLecturer(Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
						lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], Double.parseDouble(lecturerDetails[7]), index);//edits the lecturer
				for (int i = 1; i <=6; i++) {
					((DefaultTableModel) humanResourceView.fTLecturersTable.getModel()).setValueAt(lecturerDetails[i], fTSelectedRow, i);//updates columns
				}
				((DefaultTableModel) humanResourceView.fTLecturersTable.getModel()).setValueAt(Integer.parseInt(lecturerDetails[0]), fTSelectedRow, 0);//setting changes in table
				((DefaultTableModel) humanResourceView.fTLecturersTable.getModel()).setValueAt(lecturerDetails[10], fTSelectedRow, 7);//setting changes in table
				((DefaultTableModel) humanResourceView.fTLecturersTable.getModel()).setValueAt(Double.parseDouble(lecturerDetails[7]), fTSelectedRow, 8);//setting changes in table
				JOptionPane.showMessageDialog(null, "Lecturer edited successfully");//edited message shown
			}
			catch (NullPointerException ne) {//catching exception
				
			}
			
		}
		
		if(e.getActionCommand().equals("Edit PT Lecturer")) {
			int index= humanResourceView.editPTLecturerIndex;//selected row
			
			try {
				String lecturerDetails[]=humanResourceView.getLecturerDetails();//array of all fields entered
				humanResourceModel.editPTLecturer(Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
						lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], Double.parseDouble(lecturerDetails[9]), index);//edits the lecturer
				for (int i = 1; i <=6; i++) {
					((DefaultTableModel) humanResourceView.pTLecturersTable.getModel()).setValueAt(lecturerDetails[i], pTSelectedRow, i);//updates columns
				}
				((DefaultTableModel) humanResourceView.pTLecturersTable.getModel()).setValueAt(Integer.parseInt(lecturerDetails[0]), pTSelectedRow, 0);//setting changes in table	
				((DefaultTableModel) humanResourceView.pTLecturersTable.getModel()).setValueAt(lecturerDetails[10], pTSelectedRow, 7);	//setting changes in table
				((DefaultTableModel) humanResourceView.pTLecturersTable.getModel()).setValueAt(Double.parseDouble(lecturerDetails[9]), pTSelectedRow, 8);//setting changes in table
				JOptionPane.showMessageDialog(null, "Lecturer edited successfully");//success message
			}
			catch (NullPointerException ne) {//catching exception
				
			}
		}
		
		if(e.getActionCommand().equals("Edit C Lecturer")) {
			int index= humanResourceView.editCLecturerIndex;//selected row
			
			try {
				String lecturerDetails[]=humanResourceView.getLecturerDetails();//array of fields values
				humanResourceModel.editCLecturer(Integer.parseInt(lecturerDetails[0]), lecturerDetails[1], lecturerDetails[2], lecturerDetails[3],
						lecturerDetails[4], lecturerDetails[5], lecturerDetails[6], lecturerDetails[10], lecturerDetails[8] ,Double.parseDouble(lecturerDetails[7]), index);//edits lecturer
				for (int i = 1; i <=6; i++) {
					((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).setValueAt(lecturerDetails[i], cSelectedRow, i);//updates columns
				}
				((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).setValueAt(Integer.parseInt(lecturerDetails[0]), cSelectedRow, 0);	//setting changes in table
				((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).setValueAt(lecturerDetails[10], cSelectedRow, 7);	//setting changes in table
				((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).setValueAt(lecturerDetails[8], cSelectedRow, 8);	//setting changes in table
				((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).setValueAt(Double.parseDouble(lecturerDetails[7]), cSelectedRow, 9);//setting changes in table
				JOptionPane.showMessageDialog(null, "Lecturer edited successfully");//message shown
			}
			catch (NullPointerException nee) {//catching exception
				
			}
		}
		
		
		if(e.getActionCommand().equals("Log out")){//if logout button pressed
				humanResourceView.dispose();//view is closed
				Main.main(null);//login frame is opened
		}
		
	}
	private int fTSelectedRow;
	private int pTSelectedRow;
	private int cSelectedRow;
	
	public int dCurrentDepartmentIndex; 
	
	//list selection listener
	@Override
	public void valueChanged(ListSelectionEvent e) {//when pressed on any one of row
		String options[]= {"Edit", "Delete", "Cancel"};//options for dialog box
		CardLayout cardContainer = (CardLayout)(humanResourceView.jPRightPanel.getLayout());//gets card layout 
		//for full time lecturers table
		if(e.getSource()==humanResourceView.fTModel){//gets table model
			if(!humanResourceView.fTModel.isSelectionEmpty()){//if any row is chosen
				int fTOption = JOptionPane.showOptionDialog(null, "What would you like to do?", "Action", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);//shows dialog box
				int selectedRow=humanResourceView.fTModel.getMinSelectionIndex();//sets selected row
				fTSelectedRow=selectedRow;//sets value
				int lectId= (int) humanResourceView.fTLecturersTable.getModel().getValueAt(selectedRow, 0);//the lecturer id
				if(fTOption==0) { //edit
					humanResourceView.setFieldsForFTEdit(lectId);//sets fields
					cardContainer.show(humanResourceView.jPRightPanel, "Add Lecturer"); //using the same form as add lecturer
				}
				else if(fTOption==1) {//delete
					humanResourceModel.deleteLecturer(lectId);//deletes lecturer
					((DefaultTableModel) humanResourceView.fTLecturersTable.getModel()).removeRow(fTSelectedRow);//removes selected row of table
				}
			}	
			//humanResourceView.fTModel.clearSelection();
		}
		//for part time lecturers table
		else if(e.getSource()==humanResourceView.pTModel){//when pressed on any row
			if(!humanResourceView.pTModel.isSelectionEmpty()){//options shown
				int pTOption = JOptionPane.showOptionDialog(null, "What would you like to do?", "Action", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				int selectedRow=humanResourceView.pTModel.getMinSelectionIndex();//sets selected row
				pTSelectedRow=selectedRow;//sets value
				int lectId=(int) humanResourceView.pTLecturersTable.getModel().getValueAt(selectedRow, 0);//lecturer id
				if(pTOption==0) {
					humanResourceView.setFieldsForPTEdit(lectId);//sets fields
					cardContainer.show(humanResourceView.jPRightPanel, "Add Lecturer");//same form
				}
				else if(pTOption==1) {//delete
					humanResourceModel.deleteLecturer(lectId);//deletes lecturer
					((DefaultTableModel) humanResourceView.pTLecturersTable.getModel()).removeRow(pTSelectedRow);//removes selected row
				}
			}	
		}
		//for contract lecturers table
		else if(e.getSource()==humanResourceView.cModel){//when pressed on any row
			if(!humanResourceView.cModel.isSelectionEmpty()){//options shown
				int cOption = JOptionPane.showOptionDialog(null, "What would you like to do?", "Action", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				int selectedRow=humanResourceView.cModel.getMinSelectionIndex();//sets selected row
				cSelectedRow=selectedRow;//sets value
				int lectId=(int) humanResourceView.cLecturersTable.getModel().getValueAt(selectedRow, 0);//lecturer id
				if(cOption==0) {
					humanResourceView.setFieldsForCEdit(lectId);//same form
					cardContainer.show(humanResourceView.jPRightPanel, "Add Lecturer");
				}
				else if(cOption==1) {//delete
					humanResourceModel.deleteLecturer(lectId);//deletes lecturer
					((DefaultTableModel) humanResourceView.cLecturersTable.getModel()).removeRow(cSelectedRow);//removes selected row
				}
			//	humanResourceView.cModel.clearSelection();
			}	
		}
		//for departments table
		else if(e.getSource()==humanResourceView.dModel){//when pressed on any row
			if(!humanResourceView.dModel.isSelectionEmpty()){//options shonw
				int dOption = JOptionPane.showOptionDialog(null, "What would you like to do?", "Action", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				int selectedIndex=humanResourceView.dModel.getMinSelectionIndex();//sets selected row
				if(dOption==0) {//edit
					dCurrentDepartmentIndex=selectedIndex;//sets index
					humanResourceView.setFieldsForDEdit(selectedIndex);//sets fields
					cardContainer.show(humanResourceView.jPRightPanel, "Add Department");//shows panel
				}
				else if(dOption==1) {//delete
					humanResourceView.setDepartmentNullInTable(selectedIndex);//updates lecturer table
					humanResourceModel.deleteDepartment(selectedIndex);//deletes department
					((DefaultTableModel) humanResourceView.departmentsTable.getModel()).removeRow(selectedIndex);//removes selected row
					humanResourceView.cBDepartments.removeItemAt(selectedIndex);//removes from drop down
				}
			}	
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JLabel)
			System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getSource() instanceof JButton || me.getSource() instanceof JToggleButton) {
			Color color =(me.getSource() instanceof JButton)? ((JButton) me.getSource()).getBackground():((JToggleButton) me.getSource()).getBackground();
			int r= color.getRed();
			int g= color.getGreen();
			int b= color.getBlue();
			if(r+63<255) {
				if(me.getSource() instanceof JButton)
					((JButton)me.getSource()).setBackground(new Color(r+63, g, b));
				else if(me.getSource() instanceof JToggleButton)
					((JToggleButton)me.getSource()).setBackground(new Color(r+63, g, b));
				
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() instanceof JButton || me.getSource() instanceof JToggleButton ) {
			Color color =(me.getSource() instanceof JButton)? ((JButton) me.getSource()).getBackground():((JToggleButton) me.getSource()).getBackground();
			int r= color.getRed();
			int g= color.getGreen();
			int b= color.getBlue();
			if(r+63<255) {
				if(me.getSource() instanceof JButton)
					((JButton)me.getSource()).setBackground(new Color(r-63, g, b));
				else if(me.getSource() instanceof JToggleButton)
					((JToggleButton)me.getSource()).setBackground(new Color(r-63, g, b));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
	
