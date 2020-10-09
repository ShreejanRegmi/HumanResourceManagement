package View;

import java.awt.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.HumanResourceController;
import DataFiles.ContractLecturer;
import DataFiles.Department;
import DataFiles.FullTimeLecturer;
import DataFiles.Lecturer;
import DataFiles.PartTimeLecturer;
import Exceptions.*;
import Model.HumanResourceModel;
@SuppressWarnings("serial")
public class HumanResourceView extends JFrame {
	
//	private JFrame humanResourceFrame;
	
	private Container container;
	
	//--------------JPanels--------------------------//
	private JPanel jPLeftPanel;
	private JPanel jPTopPanel;
	public JPanel jPRightPanel;
	
	//------------Department Type Final String Values------------//
	private final String SCIENCE= "Science";
	private final String ARTS="Arts";
	private final String ENGINEERING="Engineering";
	private final String ENVIRONMENTAL="Environmental";
	
	//---------------JPanels for Card layout-----------------//
	private JPanel jPAddLecturer;
	
	private JPanel jPPartTime;
	private JPanel jPFullTime;
	private JPanel jPContract;
	//private JPanel jPDeleteLecturer;
	
	private JPanel jPAddDepartment;
	private JPanel jPViewDepartment;
	//private JPanel jPDeleteDepartment;
	
	//------------------JLabels--------------------//
	private JLabel jLLecturers;
	private JButton jBAddLecturer;
	
	private JLabel jLViewLecturers;

	private JButton jBPartTime;
	private JButton jBFullTime;
	private JButton jBContract;
	//private JButton jBDeleteLecturer;
	
	private JLabel jLDepartments;
	
	private JButton jBAddDepartment;
	private JButton jBViewDepartment;
	
	private JButton jBLogOut;
	
	//jLabels & j textpanels of ADD LECTURER panel
	private JLabel jLID, jLFullName, jLAddress, jLEmail, jLContact, jLDateStarted,  jLSubject, jLLecturerType;
	private JLabel jLEndDate, jLSalary, jLHourlyRate, jLGender;
	
	private JTextField jTID, jTFullName, jTAddress, jTEmail, jTContact;
	private JTextField jTHourlyRate, jTSalary; 
	private JToggleButton jBAdd;
	private JRadioButton jRBFemale, jRBMale;
	private ButtonGroup bGGenderGroup;
	public JComboBox<String> cBDepartments;
	public JComboBox<String> cBLecturerType;
	private ArrayList<String> availableLectTypes;
	private JComboBox<Integer> jCBDay, jCBEndDay;
	private JComboBox<String> jCBMonth, jCBEndMonth;
	private JComboBox<Integer> jCBYear, jCBEndYear;
	private final String[] months= {"JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JUL", "AUG", "SEPT", "OCT", "NOV", "DEC"};
	
	//jLabels & jTextPanels of Add Department
	private JLabel jLDepartmentName, jLDepartmentType, jLWebAddress, jLDepartmentUsername, jLDepartmentPassword; 
	private JTextField jTDepartmentName, jTWebAddress, jTDepartmentUsername;
	private JPasswordField jPDepartmentPassword;
	private JToggleButton jBPAddDepartment;
	private JComboBox<String> cBDepartmentType;
	
	public JTable fTLecturersTable;
	public JTable pTLecturersTable;
	public JTable cLecturersTable;
	public JTable departmentsTable;

	private Color colorDark;
	private Color colorForDark;
	private Color colorForWhite;
	
	public ListSelectionModel fTModel;
	public ListSelectionModel pTModel;
	public ListSelectionModel cModel;
	public ListSelectionModel dModel;
	
	private JLabel adminControls;
	public JLabel changeableText;
	private JLabel cross;
	private JLabel header;
	
	private JLabel adminImage;
	private JSeparator firstSeparator;
	private JSeparator secondSeparator;
	private JSeparator thirdSeparator;
	
	private Font fontForButtons;
	private Font fontForTFs;
	
	private HumanResourceModel humanResourceModel;
	private HumanResourceController humanResourceController;
	
	//constructor of view
	public HumanResourceView(HumanResourceModel humanResourceModel, HumanResourceController humanResourceController) {
		this.humanResourceModel=humanResourceModel;//sets the model
		humanResourceController.addView(this);//sets the view
		this.humanResourceController=humanResourceController;
		
		setSize(1100,800);									
		setResizable(false);//
		setLocationRelativeTo(null);//
		setTitle("Admin View-Human Resource Management");
		
		container= getContentPane();//
		
		jPTopPanel= new JPanel();
		jPLeftPanel= new JPanel();
		jPRightPanel= new JPanel();
		
		container.setLayout(null);
		container.setBackground(Color.white);
		
		colorDark = new Color(54, 33, 89);
		colorForDark = new Color(204, 204, 204);
		colorForWhite= new Color(102,102,102);
		
		fontForButtons = new Font("Sogoe UI", Font.BOLD, 15);
		fontForTFs= new Font("Sogoe UI", Font.PLAIN, 16);
		
		cross = new JLabel("x");
		cross.setBounds(1064, 0, 36, 43);
		cross.setFont(new Font("Sogoe UI", Font.BOLD, 32));		
		cross.setHorizontalAlignment(JLabel.CENTER);
		cross.setVerticalAlignment(JLabel.CENTER);
		cross.setForeground(new Color(204,204,204));
		cross.setBackground(new Color(54, 33, 89));
		cross.setOpaque(true);
		cross.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cross.addMouseListener(humanResourceController);
		container.add(cross);
		
		header= new JLabel("Welcome, Mr./Ms. Admin");
		header.setFont(new Font("Edwardian Script ITC", Font.BOLD, 38));		
		header.setForeground(colorForWhite);
		header.setBounds(420, 30, 420, 50);
		container.add(header);
		
		jPTopPanel.setBounds(200,100, 900,150);
		jPTopPanel.setBackground(new Color(110, 89, 222));
		jPTopPanel.setLayout(null);
		container.add(jPTopPanel);
		
		adminControls= new JLabel("Admin Controls");
		adminControls.setFont(new Font("Sogoe UI", Font.BOLD, 16));
		adminControls.setForeground(new Color(220,220, 220));
		adminControls.setBounds(30, 25, 160, 30);
		jPTopPanel.add(adminControls);
		
		changeableText= new JLabel("Add Lecturer");
		changeableText.setFont(new Font("Sogoe UI", Font.PLAIN, 32));
		changeableText.setForeground(new Color(220,220, 220));
		changeableText.setBounds(30, 83, 500, 35);
		jPTopPanel.add(changeableText);
		
		
		jPLeftPanel.setBounds(0, 0, 200, 800);
		jPLeftPanel.setBackground(colorDark);
		container.add(jPLeftPanel);
		
		jPRightPanel.setBounds(200, 250, 900, 550);
		jPRightPanel.setBackground(Color.white);
		container.add(jPRightPanel);
		
		jPLeftPanel.setLayout(null);
		
		adminImage = new JLabel(new ImageIcon("src/images/admin.png"));
		adminImage.setBounds(50, 30, 100, 100);
		adminImage.setBorder(BorderFactory.createLineBorder(colorForDark, 2, true));
		jPLeftPanel.add(adminImage);
		
		
		jLLecturers= new JLabel("<html><b><p style='font-size: 16px;'>Lecturers</p></b></html>");
		jLLecturers.setForeground(colorForDark);
		jLLecturers.setFont(new Font("Sogoe UI", Font.BOLD, 32));
		jLLecturers.setBounds(50,180,100,20);
		
		firstSeparator = new JSeparator();
		firstSeparator.setBounds(15, 220, 175, 5);
		firstSeparator.setPreferredSize(new Dimension(50,5));
		firstSeparator.setBackground(colorForDark);
		jPLeftPanel.add(firstSeparator);
		
		
		jLViewLecturers= new JLabel("<html><b style='font-size: 16px;'>View / Edit</b></html>");
		jLViewLecturers.setForeground(colorForDark);
		jLViewLecturers.setFont(new Font("Sogoe UI", Font.BOLD, 32));
		jLViewLecturers.setBounds(40,270,150,40);
		
		thirdSeparator = new JSeparator();
		thirdSeparator.setBounds(32, 315, 125, 5);
		thirdSeparator.setPreferredSize(new Dimension(50,5));
		thirdSeparator.setBackground(colorForDark);
		jPLeftPanel.add(thirdSeparator);
		
		jLDepartments= new JLabel("<html><b><p style='font-size: 16px;'>Departments</p></b></html>");
		jLDepartments.setForeground(colorForDark);
		jLDepartments.setFont(new Font("Sogoe UI", Font.BOLD, 32));
		jLDepartments.setBounds(25,430,150,40);
		
		secondSeparator = new JSeparator();
		secondSeparator.setBounds(15, 480, 175, 5);
		secondSeparator.setPreferredSize(new Dimension(50,5));
		secondSeparator.setBackground(colorForDark);
		jPLeftPanel.add(secondSeparator);
		
		
		jBAddLecturer = new JButton("Add Lecturer", new ImageIcon("src/images/add.png"));
		jBPartTime= new JButton("Part Time", new ImageIcon("src/images/parttime.png"));
		jBFullTime= new JButton("Full Time", new ImageIcon("src/images/fulltime.png"));
		jBContract= new JButton("Contract", new ImageIcon("src/images/contract.png"));
		
		jBAddDepartment= new JButton("Add Department", new ImageIcon("src/images/add.png"));
		jBViewDepartment= new JButton("View/Edit Department", new ImageIcon("src/images/department.png"));
		jBViewDepartment.setActionCommand("View Department");
		jBLogOut= new JButton("Log out", new ImageIcon("src/images/logout.png"));
		jBLogOut.addActionListener(humanResourceController);
		
		jPLeftPanel.add(jLLecturers);
		jPLeftPanel.add(jBAddLecturer);

		jPLeftPanel.add(jLViewLecturers);
		jPLeftPanel.add(jBPartTime);
		jPLeftPanel.add(jBFullTime);
		jPLeftPanel.add(jBContract);
		
		jPLeftPanel.add(jLDepartments);
		jPLeftPanel.add(jBAddDepartment);
		jPLeftPanel.add(jBViewDepartment);
		
		jPLeftPanel.add(jBLogOut);
		
		
		jBAddLecturer.setBounds(0, 230, 200,30);
		setDesignForButtons(jBAddLecturer);
		jBAddLecturer.setIconTextGap(50);
		jBAddLecturer.addActionListener(humanResourceController);
		
		jBPartTime.setBounds(0, 320, 200, 30);
		setDesignForButtons(jBPartTime);
		jBPartTime.setIconTextGap(78);
		jBPartTime.addActionListener(humanResourceController);
		
		jBFullTime.setBounds(0, 350, 200, 30);
		setDesignForButtons(jBFullTime);
		jBFullTime.setIconTextGap(82);
		jBFullTime.addActionListener(humanResourceController);

		jBContract.setBounds(0, 380, 200, 30);
		setDesignForButtons(jBContract);
		jBContract.setIconTextGap(85);
		jBContract.addActionListener(humanResourceController);
		
		jBAddDepartment.setBounds(0, 500, 200, 30);
		setDesignForButtons(jBAddDepartment);
		jBAddDepartment.setIconTextGap(34);
		jBAddDepartment.addActionListener(humanResourceController);
		
		
		jBViewDepartment.setBounds(0, 530, 200, 30);
		setDesignForButtons(jBViewDepartment);
		jBViewDepartment.setIconTextGap(10);
		jBViewDepartment.addActionListener(humanResourceController);
		jBViewDepartment.setFont(new Font("Sogoe UI", Font.BOLD, 13));
				
		jBLogOut.setBounds(0, 600, 200, 30);
		jBLogOut.setBackground(Color.RED);
		jBLogOut.setForeground(Color.WHITE);
		jBLogOut.setFocusable(false);
		jBLogOut.setFont(fontForButtons);
		jBLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jBLogOut.setBorderPainted(false);
		jBLogOut.setIconTextGap(60);
		//the one with card layout
		jPRightPanel.setLayout(new CardLayout());
		
		//cards 
	
		jPAddLecturer= new JPanel();
		jPAddLecturer.setLayout(null);
		
		jPPartTime= new JPanel();
		
		jPFullTime= new JPanel();
		jPFullTime.setLayout(new FlowLayout());
		
	
		jPContract= new JPanel();
		
		jPAddDepartment= new JPanel();
		jPAddDepartment.setLayout(null);
		jPViewDepartment= new JPanel();
		
		//////////////////////////////////////			Code for ADD LECTURER PANEL   ///////////////////////
		addLecturerPanel();
		addDepartmentPanel();
		viewFullTimeLecturersPanel();
		viewPartTimeLecturersPanel();
		viewContractLecturersPanel();
		viewDepartmentsPanel();
		//specifying the specified cards to show
		jPRightPanel.add(jPAddLecturer, jBAddLecturer.getActionCommand());
		
		jPRightPanel.add(jPPartTime, jBPartTime.getActionCommand());
		jPRightPanel.add(jPFullTime, jBFullTime.getActionCommand());
		jPRightPanel.add(jPContract, jBContract.getActionCommand());
		
		jPRightPanel.add(jPAddDepartment, jBAddDepartment.getActionCommand());
		jPRightPanel.add(jPViewDepartment, jBViewDepartment.getActionCommand());
		
		setUndecorated(true);
		setVisible(true);//
	}
	
	public void setDesignForButtons(JButton button) {
		button.setBorderPainted(false);
		button.setBackground(colorDark);
		button.setForeground(colorForDark);
		button.setFocusable(false);
		button.setFont(fontForButtons);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addMouseListener(humanResourceController);
	}
	
	public void setDesignForFormLabels(JLabel label) {
		label.setFont(fontForTFs);
		label.setForeground(colorForWhite);	
	}
	
	public void setDesignForFormTF(JTextField tf) {
		tf.setFont(fontForTFs);
		tf.setForeground(new Color(51,51,51));
		tf.setBorder(BorderFactory.createLineBorder(new Color(51,51,51)));
	}
	
	
	public void addLecturerPanel() {
		
		jLID= new JLabel("<html><b style='font-size: 12px;'>ID :</b></html>");
		jLID.setBounds(20,45,40, 15);
		setDesignForFormLabels(jLID);
		
		jTID= new JTextField(20);
		jTID.setBounds(150, 41, 90,23);
		setDesignForFormTF(jTID);
		
		jLFullName= new JLabel("<html><b style='font-size: 12px;'>Full Name :</b></html>");
		jLFullName.setBounds(20, 80, 100, 15);
		setDesignForFormLabels(jLFullName);
		
		jTFullName= new JTextField(20);
		jTFullName.setBounds(150, 76, 200, 23);
		setDesignForFormTF(jTFullName);
		
		jLAddress= new JLabel("<html><b style='font-size: 12px;'>Address :</b></html>");
		jLAddress.setBounds(20, 115, 80 ,15);
		setDesignForFormLabels(jLAddress);
		
		jTAddress= new JTextField(20);
		jTAddress.setBounds(150, 111, 200, 23);
		setDesignForFormTF(jTAddress);
		
		jLEmail= new JLabel("<html><b style='font-size: 12px;'>Email :</b></html>");
		jLEmail.setBounds(20, 150, 80, 15);
		setDesignForFormLabels(jLEmail);
		
		jTEmail= new JTextField(20);
		jTEmail.setBounds(150, 146, 200, 23);
		setDesignForFormTF(jTEmail);
		
		jLContact= new JLabel("<html><b style='font-size: 12px;'>Contact :</b></html>");
		jLContact.setBounds(20, 185, 80, 15);
		setDesignForFormLabels(jLContact);
		
		jTContact= new JTextField(20);
		jTContact.setBounds(150, 181, 200, 23);
		setDesignForFormTF(jTContact);
		
		jLDateStarted= new JLabel("<html><b style='font-size: 12px;'>Date Started :</b></html>");
		jLDateStarted.setBounds(20, 220, 110, 15);
		setDesignForFormLabels(jLDateStarted);
		
		//Start of code for adding date
		jCBDay= new JComboBox<Integer>();
		jCBDay.setBounds(150, 220, 50, 20);
		jCBDay.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		jCBMonth = new JComboBox<String>(months);
		jCBMonth.setBounds(220, 220, 60, 20);
		jCBMonth.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		jCBYear = new JComboBox<Integer>();
		jCBYear.setBounds(300, 220, 60, 20);
		jCBYear.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		
		jCBEndDay=new JComboBox<Integer>();
		jCBEndMonth= new JComboBox<String>(months);
		jCBEndYear= new JComboBox<Integer>();
		
		for (int i = 1; i <= 31; i++) {
			jCBEndDay.addItem(i);
			jCBDay.addItem(i);
		}
		for(int i =1980; i<=2019; i++){
			jCBEndYear.addItem(i);
			jCBYear.addItem(i);
		}
		
		jLSalary= new JLabel("<html><b style='font-size: 12px;'>Salary(£) :</b></html>");
		jLSalary.setBounds(20, 255, 80, 20);
		setDesignForFormLabels(jLSalary);
		
		jTSalary= new JTextField(20);
		jTSalary.setBounds(150, 251, 200, 23);
		setDesignForFormTF(jTSalary);
		
		jLSubject= new JLabel("<html><b style='font-size: 12px;'>Department :</b></html>");
		jLSubject.setBounds(20, 290, 120, 20);
		setDesignForFormLabels(jLSubject);
		
		try {
			cBDepartments= new JComboBox<String>(humanResourceModel.getDepartmentList());
			cBDepartments.setBounds(150, 290, 160, 20);
			cBDepartments.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		}
		catch (NullPointerException e) {
			
		}
		
		jLLecturerType= new JLabel("<html><b style='font-size: 12px;'>Lecturer Type :</b></html>");
		jLLecturerType.setBounds(20, 325, 120, 20);
		setDesignForFormLabels(jLLecturerType);
		
		cBLecturerType= new JComboBox<String>();
		cBLecturerType.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		
		availableLectTypes = new ArrayList<String>();
		availableLectTypes.add("Full-Time");//full time type
		availableLectTypes.add("Part-Time");//part time type
		availableLectTypes.add("Contract");//contract type
		
		//iterating through an array
		for (String string : availableLectTypes) {//for all types
			cBLecturerType.addItem(string);//adds to drop down
		}
		
		cBLecturerType.setSelectedIndex(0);
		cBLecturerType.setBounds(150, 324, 160, 23);
		cBLecturerType.addActionListener(humanResourceController);
		
		//System.out.println(cBLecturerType.getActionCommand());
		
		jLEndDate= new JLabel("<html><b style='font-size: 12px;'>End Date :</b></html>");
		jLEndDate.setBounds(20, 365, 80, 15);
		setDesignForFormLabels(jLEndDate);
		
		jCBEndDay.setBounds(150, 365, 50, 20);
		jCBEndDay.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		jCBEndMonth.setBounds(220, 365, 60, 20);
		jCBEndMonth.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		jCBEndYear.setBounds(300, 365, 60, 20);
		jCBEndYear.setFont(new Font("Sogoe UI", Font.PLAIN, 12));

		jLHourlyRate= new JLabel("<html><b style='font-size: 12px;'>Hourly Rate :</b></html>");
		jLHourlyRate.setBounds(20, 400, 110, 20);
		setDesignForFormLabels(jLHourlyRate);
		
		jTHourlyRate= new JTextField(20);
		jTHourlyRate.setBounds(150, 396, 200, 23);
		setDesignForFormTF(jTHourlyRate);
		
		jLGender= new JLabel("<html><b style='font-size: 12px;'>Gender :</b></html>");
		jLGender.setBounds(20, 440, 80, 15);
		setDesignForFormLabels(jLGender);
		
		jRBFemale= new JRadioButton("Female");
		jRBFemale.setSelected(true);
		jRBFemale.setBounds(120, 440, 80, 15);
		jRBFemale.setBackground(Color.white);
		jRBFemale.setFont(fontForTFs);
		
		jRBMale= new JRadioButton("Male");
		jRBMale.setBounds(220, 440, 80, 15);
		jRBMale.setBackground(Color.white);
		jRBMale.setFont(fontForTFs);
		
		bGGenderGroup= new ButtonGroup();
		bGGenderGroup.add(jRBFemale);
		bGGenderGroup.add(jRBMale);
		
		jBAdd= new JToggleButton("Add", new ImageIcon("src/images/add.png"));
		jBAdd.setActionCommand("Add Lecturer Button");
		jBAdd.addActionListener(humanResourceController);
		jBAdd.setBounds(150, 470, 90, 40);
		jBAdd.setBorderPainted(false);
		jBAdd.setBackground(colorDark);
		jBAdd.setForeground(colorForDark);
		jBAdd.setFocusable(false);
		jBAdd.setFont(fontForButtons);
		jBAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jBAdd.addMouseListener(humanResourceController);
		//jBAdd.setIcon(new ImageIcon("src/images/edit.png"));
		
		//setting visibility false for fulltime lecturer contents
		jLEndDate.setVisible(false);
		jCBEndDay.setVisible(false);
		jCBEndMonth.setVisible(false);
		jCBEndYear.setVisible(false);

		jLHourlyRate.setVisible(false);
		jTHourlyRate.setVisible(false);
		
		jPAddLecturer.add(jLID);
		jPAddLecturer.add(jLFullName);
		jPAddLecturer.add(jLAddress);
		jPAddLecturer.add(jLEmail);
		jPAddLecturer.add(jLContact);
		jPAddLecturer.add(jLDateStarted);
		jPAddLecturer.add(jLSalary);
		jPAddLecturer.add(jLSubject);
		jPAddLecturer.add(cBDepartments);
		jPAddLecturer.add(jLLecturerType);
		jPAddLecturer.add(cBLecturerType);
		jPAddLecturer.add(jLEndDate);
		jPAddLecturer.add(jLHourlyRate);
		jPAddLecturer.add(jLGender);
		jPAddLecturer.add(jRBFemale);
		jPAddLecturer.add(jRBMale);
		
		jPAddLecturer.add(jTID);
		jPAddLecturer.add(jTFullName);
		jPAddLecturer.add(jTAddress);
		jPAddLecturer.add(jTEmail);
		jPAddLecturer.add(jTContact);
		jPAddLecturer.add(jCBDay);
		jPAddLecturer.add(jCBMonth);
		jPAddLecturer.add(jCBYear);
		jPAddLecturer.add(jTSalary);
		jPAddLecturer.add(jCBEndDay);
		jPAddLecturer.add(jCBEndMonth);
		jPAddLecturer.add(jCBEndYear);
		jPAddLecturer.add(jTHourlyRate);
		jPAddLecturer.add(jBAdd);
		
		jPAddLecturer.setBackground(Color.white);
	}
	
	public void setTableDesign(JTable table) {
		table.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		table.getTableHeader().setFont(new Font("Sogoe UI", Font.BOLD, 13));
		table.getTableHeader().setBackground(Color.WHITE);
		table.setFont(new Font("Sogoe UI", Font.BOLD, 13));
		table.setIntercellSpacing(new Dimension(0,0));
		table.setRowHeight(30);
		table.setSelectionBackground(new Color(110, 89, 222));
		table.setGridColor(Color.WHITE);
	}
	
	public void viewFullTimeLecturersPanel() {
		fTLecturersTable= humanResourceModel.getFTLecturersTable();
 		jPFullTime.setLayout(new BorderLayout());
		jPFullTime.add(fTLecturersTable.getTableHeader(),BorderLayout.PAGE_START);
		
		setTableDesign(fTLecturersTable);
		fTLecturersTable.setFillsViewportHeight(true);
		jPFullTime.add(new JScrollPane(fTLecturersTable));
		
		fTModel = fTLecturersTable.getSelectionModel();
		fTModel.addListSelectionListener(humanResourceController);
	
	}
	
	//part time panel
	public void viewPartTimeLecturersPanel() {
		pTLecturersTable= humanResourceModel.getPTLecturersTable();//gets part time table
 		jPPartTime.setLayout(new BorderLayout());//new layout
		jPPartTime.add(pTLecturersTable.getTableHeader(),BorderLayout.PAGE_START);	
		setTableDesign(pTLecturersTable);
		pTLecturersTable.setFillsViewportHeight(true);//takes full height
		jPPartTime.add(new JScrollPane(pTLecturersTable));//added table to scroll pane
		pTModel = pTLecturersTable.getSelectionModel();//gets the list selection model
		pTModel.addListSelectionListener(humanResourceController);//adds list selection listener
	}
	
	//contract panel
	public void viewContractLecturersPanel() {
		cLecturersTable= humanResourceModel.getCLecturersTable();///gets contract table
 		jPContract.setLayout(new BorderLayout());//new layout
		jPContract.add(cLecturersTable.getTableHeader(),BorderLayout.PAGE_START);
		setTableDesign(cLecturersTable);
		cLecturersTable.setFillsViewportHeight(true);//takes full height
		jPContract.add(new JScrollPane(cLecturersTable));//added table to scroll panel
		cModel = cLecturersTable.getSelectionModel();//gets list selection model
		cModel.addListSelectionListener(humanResourceController);//adds list selection listener
	}
	
	//departments panel
	public void viewDepartmentsPanel() {
		departmentsTable= humanResourceModel.getDepartmentsTable();//gets departments table
 		jPViewDepartment.setLayout(new BorderLayout());//new layout
		jPViewDepartment.add(departmentsTable.getTableHeader(),BorderLayout.PAGE_START);
		setTableDesign(departmentsTable);///sets table design
		departmentsTable.setFillsViewportHeight(true);//takes full height
		jPViewDepartment.add(new JScrollPane(departmentsTable));//added table to scroll pane
		dModel = departmentsTable.getSelectionModel();//gets list selection model
		dModel.addListSelectionListener(humanResourceController);//adds list selection listener
		jPViewDepartment.add(departmentsTable);	
	}
 	
	
	public void addDepartmentPanel() {
		jLDepartmentName = new JLabel("<html><b style='font-size: 12px;'>Department Name :</b></html>");
		jLDepartmentName.setBounds(20,55,160, 20);
		setDesignForFormLabels(jLDepartmentName);
		
		jTDepartmentName= new JTextField();
		jTDepartmentName.setBounds(200,55,200, 23);
		setDesignForFormTF(jTDepartmentName);
		
		jLDepartmentType = new JLabel("<html><b style='font-size: 12px;'>Department Type :</b></html>");
		jLDepartmentType.setBounds(20,95,160, 20);
		setDesignForFormLabels(jLDepartmentType);
		
		cBDepartmentType= new JComboBox<String>();
		cBDepartmentType.setFont(new Font("Sogoe UI", Font.PLAIN, 12));
		cBDepartmentType.addItem(SCIENCE);
		cBDepartmentType.addItem(ARTS);
		cBDepartmentType.addItem(ENGINEERING);
		cBDepartmentType.addItem(ENVIRONMENTAL);
		
		cBDepartmentType.setSelectedIndex(0);
		cBDepartmentType.setBounds(200, 95, 140, 20);
		
		jLWebAddress = new JLabel("<html><b style='font-size: 12px;'>Web Address :</b></html>");
		jLWebAddress.setBounds(20,135,160, 20);
		setDesignForFormLabels(jLWebAddress);
		
		jTWebAddress= new JTextField();
		jTWebAddress.setBounds(200,135,200, 23);
		setDesignForFormTF(jTWebAddress);
		
		jLDepartmentUsername= new JLabel("<html><b style='font-size: 12px;'>Username :</b></html>");
		jLDepartmentUsername.setBounds(20,175,160, 20);
		setDesignForFormLabels(jLDepartmentUsername);
		
		jTDepartmentUsername= new JTextField();
		jTDepartmentUsername.setBounds(200,175,200, 23);
		setDesignForFormTF(jTDepartmentUsername);
		
		jLDepartmentPassword = new JLabel("<html><b style='font-size: 12px;'>Password :</b></html>");
		jLDepartmentPassword.setBounds(20,215,160, 20);
		setDesignForFormLabels(jLDepartmentPassword);
		
		jPDepartmentPassword= new JPasswordField();
		jPDepartmentPassword.setBounds(200,215,200, 23);
		setDesignForFormTF(jPDepartmentPassword);
		
		jBPAddDepartment= new JToggleButton("Add Department", new ImageIcon("src/images/add.png"));
		jBPAddDepartment.setBounds(200, 270, 190, 40);
		jBPAddDepartment.setActionCommand("Add Department Button");
		jBPAddDepartment.addActionListener(humanResourceController);
		jBPAddDepartment.setBorderPainted(false);
		jBPAddDepartment.setBackground(colorDark);
		jBPAddDepartment.setForeground(colorForDark);
		jBPAddDepartment.setFocusable(false);
		jBPAddDepartment.setFont(fontForButtons);
		jBPAddDepartment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jBPAddDepartment.addMouseListener(humanResourceController);
		
		jPAddDepartment.add(jLDepartmentName);
		jPAddDepartment.add(jTDepartmentName);
		
		jPAddDepartment.add(jLDepartmentType);
		jPAddDepartment.add(cBDepartmentType);
		
		jPAddDepartment.add(jLWebAddress);
		jPAddDepartment.add(jTWebAddress);
		
		jPAddDepartment.add(jLDepartmentUsername);
		jPAddDepartment.add(jTDepartmentUsername);
		
		jPAddDepartment.add(jLDepartmentPassword);
		jPAddDepartment.add(jPDepartmentPassword);
		
		jPAddDepartment.add(jBPAddDepartment);	
		
		jPAddDepartment.setBackground(Color.white);
	}
	
	public void setFields(String lecturerType) {
		if(lecturerType.equals("Part-Time")) {
			jLEndDate.setVisible(false);
			jCBEndDay.setVisible(false);
			jCBEndMonth.setVisible(false);
			jCBEndYear.setVisible(false);
			
			jLSalary.setVisible(false);
			jTSalary.setVisible(false);
			
			jLHourlyRate.setVisible(true);
			jTHourlyRate.setVisible(true);
		}
		else if(lecturerType.equals("Full-Time")) {
			jLEndDate.setVisible(false);
			jCBEndDay.setVisible(false);
			jCBEndMonth.setVisible(false);
			jCBEndYear.setVisible(false);
			
			jLHourlyRate.setVisible(false);
			jTHourlyRate.setVisible(false);
			
			jLSalary.setVisible(true);
			jTSalary.setVisible(true);
		}
		else if(lecturerType.equals("Contract")) {
			jLEndDate.setVisible(true);
			jCBEndDay.setVisible(true);
			jCBEndMonth.setVisible(true);
			jCBEndYear.setVisible(true);
			
			jLSalary.setVisible(true);
			jTSalary.setVisible(true);
			
			jLHourlyRate.setVisible(false);
			jTHourlyRate.setVisible(false);
		}
	}
	
	public String[] getDepartmentDetails() {//returns all entered fields
		ArrayList<Department> departments= humanResourceModel.getDepartments();//gets all departments
		
		if(jTDepartmentName.getText().isEmpty()) {//if empty
			JOptionPane.showMessageDialog(null, "Name cannot be empty");//show message
			return null;
		}
		for (Department department : departments) {//for all departments
			if(jTDepartmentName.getText().equals(department.getDepartmentName())){//check name
				if(departments.indexOf(department)!=humanResourceController.dCurrentDepartmentIndex) {//if not the current index
					try {
						throw new SameDepartmentNameException();//throw exception 
					} 
					catch (SameDepartmentNameException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());//show message
						return null;
					}
				}
			}
		}
		if(jTWebAddress.getText().isEmpty()) {//if field value empty
			JOptionPane.showMessageDialog(null, "Web Address cannot be empty");//message shown
			return null;
		}
		else if(jTDepartmentUsername.getText().isEmpty()) {//if field value empty
			JOptionPane.showMessageDialog(null, "Username cannot be empty");//message shown
			return null;
		}
		for (Department department : departments) {//for all departments
			if(jTDepartmentUsername.getText().equals(department.getDepartmentUsername())) {//if same username stored
				if(departments.indexOf(department)!=humanResourceController.dCurrentDepartmentIndex) {//if not the current department 
					try {
						throw new SameDepartmentUsernameException();//throw exception
					}
					catch (SameDepartmentUsernameException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						return null;
					}
				}
			}
		}
		
		if(String.valueOf(jPDepartmentPassword.getPassword()).isEmpty()) {//if field value empty
			JOptionPane.showMessageDialog(null, "Password cannot be empty");//show message
			return null;
		}
		else if(!jTWebAddress.getText().contains(".com") && !jTWebAddress.getText().contains(".net") && !jTWebAddress.getText().contains(".org")
				&& !jTWebAddress.getText().contains(".ac.uk") ) {//if wrong format URL
			JOptionPane.showMessageDialog(null, "Web Address should be in url format");//show message
			return null;
		}
		else {
			String [] details = {jTDepartmentName.getText(), (String)cBDepartmentType.getSelectedItem(), jTWebAddress.getText(), jTDepartmentUsername.getText(),
							String.valueOf(jPDepartmentPassword.getPassword())};//array of fields created
			return details;
		}
	}
	
	public String[] getLecturerDetails() { //Returning form data For lecturer adding form
		int startDay= (int) jCBDay.getSelectedItem();//day of adding
		String startMonth = (String) jCBMonth.getSelectedItem();//month of adding
		int startYear= (int) jCBYear.getSelectedItem();//year of adding
		
		String startDate = Integer.toString(startDay)+"-"+startMonth+"-"+Integer.toString(startYear);//start date is made
		
		String salary=jTSalary.isVisible()?jTSalary.getText():"";
		
		int endDay=0, endYear=0; String endMonth="";
		if(jCBEndDay.isVisible() && jCBEndMonth.isVisible() && jCBEndYear.isVisible()) {//if visible 
			endDay= (int) jCBEndDay.getSelectedItem();//sets value in variable
			endMonth= (String) jCBEndMonth.getSelectedItem();//sets value in variable
			endYear= (int)jCBEndYear.getSelectedItem();//sets value in variable
		}
		String endDate= Integer.toString(endDay)+"-"+endMonth+"-"+Integer.toString(endYear);//sets value in variable
		
		String gender="";//sets value in variable
		
		if(jRBMale.isSelected()) {//if condition is true
			gender=jRBMale.getText();//sets value in variable
		}
		else if(jRBFemale.isSelected()) {//if condition is true
			gender=jRBFemale.getText();//sets value in variable
		}
		//input validation
		if(jTID.getText().equals("")) {//if condition is true
			JOptionPane.showMessageDialog(null, "Enter the ID");
			return null;
		}
		try {
			if(Integer.parseInt(jTID.getText())<0){//if condition is true
				JOptionPane.showMessageDialog(null, "ID cannot be negative");//shows respective message
				return null;
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The value should be numeric");//shows respective message
			return null;
		}
		
		if(jTID.isEditable()) {//if condition is true
			ArrayList<Lecturer> lecturers= humanResourceModel.getLecturers();//sets value in variable
			for (Lecturer lecturer : lecturers) {
				if(Integer.parseInt(jTID.getText())==(lecturer.getLecturerID())){//if condition is true
					try {
						throw new SameIDException();
					} 
					catch (SameIDException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());//shows respective message
						return null;
					}
				}
			}
		}
		
		if(jTFullName.getText().equals("")) {//if condition is true
			JOptionPane.showMessageDialog(null, "Enter Fullname");//shows respective message
			return null;
		}
		else if(jTAddress.getText().equals("")) {//if condition is true
			JOptionPane.showMessageDialog(null, "Enter Address");//shows respective message
			return null;
		}
		else if(jTEmail.getText().equals("") || !jTEmail.getText().contains("@")) {//if condition is true
			JOptionPane.showMessageDialog(null, "Enter valid email address");//shows respective message
			return null;
		}
		
		try {
			String contact = jTContact.getText();
			if(contact.charAt(0)!='+' || contact.length()>14) {//if condition is true
				JOptionPane.showMessageDialog(null, "Invalid contact number. Format: +CountrycodeNumber\nEg:+9771234567890");//shows respective message	
				return null;
			}
			Long.parseLong(contact.substring(1));
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid contact number. Format: +CountrycodeNumber\nEg:+9771234567890");//shows respective message
			return null;
		}
		
		if(jTSalary.isVisible()) {//if condition is true
			try {
				if(Double.parseDouble(salary)<0) {//if condition is true
					JOptionPane.showMessageDialog(null,"Enter valid salary");//shows respective message
					return null;
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Enter valid salary");//shows respective message
				return null;
			}
		}
		if(jTHourlyRate.isVisible()) {//if condition is true
			try {
				if(Double.parseDouble(jTHourlyRate.getText())<0) {//if condition is true
					JOptionPane.showMessageDialog(null,"Enter valid hourly rate");//shows respective message
					return null;
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Enter valid hourly rate");//shows respective message
				return null;
			}
		}
		String[] details= {jTID.getText(), jTFullName.getText(), jTAddress.getText(), jTEmail.getText(), jTContact.getText(), startDate,(String) cBDepartments.getSelectedItem(),
				salary, endDate, jTHourlyRate.getText(), gender};//creates array of entered field values
		return details;//returns the array
	}
	
	public int editFTLecturerIndex;
	public void setFieldsForFTEdit(int id) {
		
		ArrayList<Lecturer> lecturers = humanResourceModel.getLecturers();
		FullTimeLecturer lecturer=null;
		for (Lecturer le : lecturers) {
			if(le instanceof FullTimeLecturer && le.getLecturerID()==id) {
				editFTLecturerIndex = lecturers.indexOf(le);
				lecturer= (FullTimeLecturer)le;
				break;
			}
		}
		
		jTID.setEditable(false);
		jTID.setText(Integer.toString(lecturer.getLecturerID()));
		
		jTFullName.setText(lecturer.getLecturerFullName());
		jTAddress.setText(lecturer.getLecturerAddress());
		jTEmail.setText(lecturer.getLecturerEmail());
		jTContact.setText(lecturer.getLecturerContact());
		
		String dates[]= lecturer.getLecturerDateStarted().split("-");
		jCBDay.setSelectedItem(Integer.parseInt(dates[0]));
		jCBMonth.setSelectedItem(dates[1]);
		jCBYear.setSelectedItem(Integer.parseInt(dates[2]));
		
		jLSalary.setVisible(true);
		jTSalary.setVisible(true);
		jTSalary.setText(Double.toString(lecturer.getFTLecturerSalary()));
		
		cBDepartments.setSelectedItem(lecturer.getLecturerDepartment());
		
		if(lecturer.getLecturerGender().equals("Male"))
			jRBMale.setSelected(true);
		else
			jRBFemale.setSelected(true);

		jLLecturerType.setVisible(false);
		cBLecturerType.setVisible(false);
		jLHourlyRate.setVisible(false);
		jTHourlyRate.setVisible(false);
		jLEndDate.setVisible(false);
		jCBEndDay.setVisible(false);
		jCBEndMonth.setVisible(false);
		jCBEndYear.setVisible(false);
		
		jBAdd.setText("Edit");
		
		jBAdd.setIcon(new ImageIcon("src/images/edit.png"));
		jBAdd.setActionCommand("Edit FT Lecturer");
		
	}
	
	public int editPTLecturerIndex;
	public void setFieldsForPTEdit(int id) {
		
		ArrayList<Lecturer> lecturers = humanResourceModel.getLecturers();
		PartTimeLecturer lecturer=null;
		for (Lecturer le : lecturers) {
			if(le instanceof PartTimeLecturer && le.getLecturerID()==id) {
				editPTLecturerIndex = lecturers.indexOf(le);
				lecturer= (PartTimeLecturer)le;
				break;
			}
		}
		
		jTID.setEditable(false);
		jTID.setText(Integer.toString(lecturer.getLecturerID()));

		jTFullName.setText(lecturer.getLecturerFullName());
		jTAddress.setText(lecturer.getLecturerAddress());
		jTEmail.setText(lecturer.getLecturerEmail());
		jTContact.setText(lecturer.getLecturerContact());
		
		String dates[]= lecturer.getLecturerDateStarted().split("-");
		jCBDay.setSelectedItem(Integer.parseInt(dates[0]));
		jCBMonth.setSelectedItem(dates[1]);
		jCBYear.setSelectedItem(Integer.parseInt(dates[2]));
		
		cBDepartments.setSelectedItem(lecturer.getLecturerDepartment());
		
		
		jLLecturerType.setVisible(false);
		cBLecturerType.setVisible(false);
		
		jLHourlyRate.setVisible(true);
		jTHourlyRate.setVisible(true);
		jTHourlyRate.setText(Double.toString(lecturer.getPTLecturerHourlyRate()));
		
		if(lecturer.getLecturerGender().equals("Male"))
			jRBMale.setSelected(true);
		else
			jRBFemale.setSelected(true);

		
		jLSalary.setVisible(false);
		jTSalary.setVisible(false);
		jLEndDate.setVisible(false);
		jCBEndDay.setVisible(false);
		jCBEndMonth.setVisible(false);
		jCBEndYear.setVisible(false);
		jBAdd.setText("Edit");
		jBAdd.setIcon(new ImageIcon("src/images/edit.png"));
		jBAdd.setActionCommand("Edit PT Lecturer");
	}
	
	public int editCLecturerIndex;
	public void setFieldsForCEdit(int id) {
		
		ArrayList<Lecturer> lecturers = humanResourceModel.getLecturers();
		ContractLecturer lecturer=null;
		for (Lecturer le : lecturers) {
			if(le instanceof ContractLecturer && le.getLecturerID()==id) {
				editCLecturerIndex = lecturers.indexOf(le);
				lecturer= (ContractLecturer)le;
				break;
			}
		}
		
		jTID.setEditable(false);
		jTID.setText(Integer.toString(lecturer.getLecturerID()));

		jTFullName.setText(lecturer.getLecturerFullName());
		jTAddress.setText(lecturer.getLecturerAddress());
		jTEmail.setText(lecturer.getLecturerEmail());
		jTContact.setText(lecturer.getLecturerContact());
		
		String dates[]= lecturer.getLecturerDateStarted().split("-");
		jCBDay.setSelectedItem(Integer.parseInt(dates[0]));
		jCBMonth.setSelectedItem(dates[1]);
		jCBYear.setSelectedItem(Integer.parseInt(dates[2]));
		
		cBDepartments.setSelectedItem(lecturer.getLecturerDepartment());

		
		jLLecturerType.setVisible(false);
		cBLecturerType.setVisible(false);
		
		jLHourlyRate.setVisible(false);
		jTHourlyRate.setVisible(false);
		
		jLSalary.setVisible(true);
		jTSalary.setVisible(true);
		jTSalary.setText(Double.toString(lecturer.getCLecturerSalary()));
		
		String endDates[]= lecturer.getCLecturerEndDate().split("-");
		jCBEndDay.setSelectedItem(Integer.parseInt(endDates[0]));
		jCBEndMonth.setSelectedItem(endDates[1]);
		jCBEndYear.setSelectedItem(Integer.parseInt(endDates[2]));
		
		if(lecturer.getLecturerGender().equals("Male"))
			jRBMale.setSelected(true);
		else
			jRBFemale.setSelected(true);

		
		jLEndDate.setVisible(true);
		
		jCBEndDay.setVisible(true);
		jCBEndMonth.setVisible(true);
		jCBEndYear.setVisible(true);
		jBAdd.setText("Edit");
		jBAdd.setIcon(new ImageIcon("src/images/edit.png"));
		jBAdd.setActionCommand("Edit C Lecturer");
	}
	
	public void resetFieldsForLAdd() {		
		jTID.setText("");
		jTID.setEditable(true);
		
		jTFullName.setText("");
		jTAddress.setText("");
		jTEmail.setText("");
		jTContact.setText("");
		jCBDay.setSelectedIndex(0);
		jCBMonth.setSelectedIndex(0);
		jCBYear.setSelectedIndex(0);
		jTSalary.setText("");
		try {
			cBDepartments.setSelectedIndex(0);
		}
		catch (IllegalArgumentException e) {
		
		}
		cBLecturerType.setSelectedIndex(0);
		jTHourlyRate.setText("");
		jCBEndDay.setSelectedIndex(0);
		jCBEndMonth.setSelectedIndex(0);
		jCBEndYear.setSelectedIndex(0);
		jRBFemale.setSelected(true);
		
		jLLecturerType.setVisible(true);
		cBLecturerType.setVisible(true);
		
		jLHourlyRate.setVisible(false);
		jTHourlyRate.setVisible(false);
		jLEndDate.setVisible(false);
		jCBEndDay.setVisible(false);
		jCBEndMonth.setVisible(false);
		jCBEndYear.setVisible(false);
		jBAdd.setText("Add");
		jBAdd.setIcon(new ImageIcon("src/images/add.png"));
		jBAdd.setActionCommand("Add Lecturer Button");
		
	}
	
	public int editIndexDepartment;
	public void setFieldsForDEdit(int index) {
		ArrayList<Department> departments = humanResourceModel.getDepartments();
		Department department = departments.get(index);
		this.editIndexDepartment=index;
		
		jTDepartmentName.setText(department.getDepartmentName());
		cBDepartmentType.setSelectedItem(department.getDepartmentType());
		jTWebAddress.setText(department.getDepartmentWebAddress());
		jTDepartmentUsername.setText(department.getDepartmentUsername());
		jPDepartmentPassword.setText(department.getDepartmentPassword());
		
		jBPAddDepartment.setText("Edit Department");
		jBPAddDepartment.setIcon(new ImageIcon("src/images/edit.png"));
		jBPAddDepartment.setActionCommand("Edit Department Button");
	}
	
	public void resetFieldsForDAdd() {
		jTDepartmentName.setText("");
		cBDepartmentType.setSelectedIndex(0);
		jTWebAddress.setText("");
		jTDepartmentUsername.setText("");
		jPDepartmentPassword.setText("");
		jBPAddDepartment.setText("Add Department");
		jBPAddDepartment.setIcon(new ImageIcon("src/images/add.png"));
		jBPAddDepartment.setActionCommand("Add Department Button");
	}
	
	public void setDepartmentNullInTable(int deptIndex) {
		ArrayList<Department> departments = humanResourceModel.getDepartments();//value is assigned onto the variable
		String toDeleteDepartmentName= departments.get(deptIndex).getDepartmentName();//value is assigned onto the variable
		
		int rowFT= fTLecturersTable.getModel().getRowCount();//value is assigned onto the variable
		int rowPT= pTLecturersTable.getModel().getRowCount();//value is assigned onto the variable
		int rowC= cLecturersTable.getModel().getRowCount();//value is assigned onto the variable
		
		for (int i = 0; i < rowFT; i++) {//if this condition asserts true
			if(fTLecturersTable.getModel().getValueAt(i, 6).equals(toDeleteDepartmentName)) {//if this condition asserts true
				fTLecturersTable.getModel().setValueAt("", i, 6);//updates column
			}
		}
		
		for (int i = 0; i < rowPT; i++) {//if this condition asserts true
			if(pTLecturersTable.getModel().getValueAt(i, 6).equals(toDeleteDepartmentName)) {//if this condition asserts true
				pTLecturersTable.getModel().setValueAt("", i, 6);//updates column
			}
		}
		
		for (int i = 0; i < rowC; i++) {//if this condition asserts true
			if(cLecturersTable.getModel().getValueAt(i, 6).equals(toDeleteDepartmentName)) {//if this condition asserts true
				cLecturersTable.getModel().setValueAt("", i, 6);//updates column
			}
		}
	}
	
	public void setEditedDepartmentInOtherTable(int index, String newName){
		ArrayList<Department> departments = humanResourceModel.getDepartments();//assigns this variable designated value
		String toEditDepartmentName= departments.get(index).getDepartmentName();//assigns this variable designated value
		
		if(!toEditDepartmentName.equals(newName)) {//if this condition asserts true
			int rowFT= fTLecturersTable.getModel().getRowCount();//assigns this variable designated value
			int rowPT= pTLecturersTable.getModel().getRowCount();//assigns this variable designated value
			int rowC= cLecturersTable.getModel().getRowCount();//assigns this variable designated value
			
			for (int i = 0; i < rowFT; i++) {//for loop
				if(fTLecturersTable.getModel().getValueAt(i, 6).equals(toEditDepartmentName)) {//if this condition asserts true
					fTLecturersTable.getModel().setValueAt(newName, i, 6);//updates column
				}
			}
			
			for (int i = 0; i < rowPT; i++) {//for loop
				if(pTLecturersTable.getModel().getValueAt(i, 6).equals(toEditDepartmentName)) {//if this condition asserts true
					pTLecturersTable.getModel().setValueAt(newName, i, 6);//updates column
				}
			}
			
			for (int i = 0; i < rowC; i++) {//for loop
				if(cLecturersTable.getModel().getValueAt(i, 6).equals(toEditDepartmentName)) {//if this condition asserts true
					cLecturersTable.getModel().setValueAt(newName, i, 6);//updates column
				}
			}
		}
	}
}
