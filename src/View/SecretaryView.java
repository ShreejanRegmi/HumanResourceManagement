package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Controller.SecretaryController;
import Model.SecretaryModel;
import images.*;

@SuppressWarnings("serial")
public class SecretaryView extends JFrame{//secretary view inherits JFrame 
	private SecretaryModel secretaryModel;//secretary model global variable
	private SecretaryController secretaryController;//secretary controller global variable
	private Container container;
	private JPanel jPTopPanel;
	private JPanel jPLeftPanel;
	public JPanel jPRightPanel;
	private JLabel jLViewLecturers;
	private JPanel jPSearch;
	private JPanel jPFullTime;
	private JPanel jPPartTime;
	private JPanel jPContract;
	private JPanel jPAll;
	private JButton jBSearch;
	private JButton jBFullTime;
	private JButton jBPartTime;
	private JButton jBContract;
	private JButton jBAll;	
	private JButton jBLogout;
	private JLabel secretaryImage;

	private JLabel jLSearch;
	private JTextField jTSearch;
	private JButton jBPSearch;
	
	private JSeparator firstSeparator;
	private JLabel cross;
	
	private JTable fTLecturersTable;
	private JTable pTLecturersTable;
	private JTable cLecturersTable;
	private JTable allLecturersTable;

	private JLabel header;
	private JLabel secretaryControls;
	public JLabel changeableText;
	private Color colorDark;
	private Color colorForDark;
	private Font fontForButtons;
	public JTable searchTable;
	
	//constructor
	public SecretaryView(SecretaryModel secretaryModel, SecretaryController secretaryController) {//takes parameter model and controller
		this.secretaryModel=secretaryModel;//model is set
		secretaryController.addView(this);//view is added to controller
		this.secretaryController=secretaryController;//controller is set
		
		setSize(1100,750);	
		setResizable(false);
		setLocationRelativeTo(null);
		container= getContentPane();
		
		header= new JLabel("Welcome, Mr./Ms. Secretary");
		header.setFont(new Font("Edwardian Script ITC", Font.BOLD, 38));		
		header.setForeground( new Color(102, 102, 102));
		header.setBounds(420, 30, 420, 50);
		container.add(header);
		
		
		colorDark = new Color(54, 33, 89);
		jPTopPanel= new JPanel();
		jPTopPanel.setBackground(new Color(110, 89, 222));
		jPTopPanel.setLayout(null);
		jPLeftPanel= new JPanel();
		jPLeftPanel.setBackground(colorDark);
		
		jPRightPanel= new JPanel();
		jPRightPanel.setBackground(Color.WHITE);
		
		
		container.setLayout(null);
		container.setBackground(Color.WHITE);
		
		secretaryControls= new JLabel("Secretary Controls");
		secretaryControls.setFont(new Font("Sogoe UI", Font.BOLD, 16));
		secretaryControls.setForeground(new Color(220,220, 220));
		secretaryControls.setBounds(30, 25, 160, 30);
		jPTopPanel.add(secretaryControls);
		
		changeableText= new JLabel("Search");
		changeableText.setFont(new Font("Sogoe UI", Font.PLAIN, 32));
		changeableText.setForeground(new Color(220,220, 220));
		changeableText.setBounds(30, 83, 400, 35);
		jPTopPanel.add(changeableText);
		
		
		
		cross = new JLabel("x");
		cross.setBounds(1064, 0, 36, 43);
		cross.setFont(new Font("Sogoe UI", Font.BOLD, 32));		
		cross.setHorizontalAlignment(JLabel.CENTER);
		cross.setVerticalAlignment(JLabel.CENTER);
		cross.setForeground(new Color(204,204,204));
		cross.setBackground(new Color(54, 33, 89));
		cross.setOpaque(true);
		cross.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cross.addMouseListener(secretaryController);
		container.add(cross);
		
		secretaryImage = new JLabel(new ImageIcon("src/images/secretary2.png"));
		secretaryImage.setBounds(50, 50, 100, 100);
		secretaryImage.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 2, true));
		jPLeftPanel.add(secretaryImage);
				
		jPTopPanel.setBounds(200,100, 900,150);
		
		container.add(jPTopPanel);
		
		jPLeftPanel.setBounds(0, 0, 200, 750);
		
		container.add(jPLeftPanel);
		
		jPRightPanel.setBounds(200, 250, 900, 500);
		jPRightPanel.setLayout(new CardLayout());
		
		jPSearch= new JPanel();
		jPSearch.setBackground(Color.WHITE);
		jPFullTime = new JPanel();
		jPFullTime.setBackground(Color.WHITE);
		jPPartTime= new JPanel();
		jPPartTime.setBackground(Color.WHITE);
		jPContract= new JPanel();
		jPContract.setBackground(Color.WHITE);
		jPAll= new JPanel();
		jPAll.setBackground(Color.WHITE);
		
		jLViewLecturers= new JLabel("<html><b style='font-size: 16px;'>View Lecturers</b></html>");
		//jLViewLecturers= new JLabel("View Lecturers");
		jLViewLecturers.setFont(new Font("Sogoe UI", Font.BOLD, 32));
		colorForDark = new Color(204, 204, 204);
		jLViewLecturers.setForeground(colorForDark);
		
		firstSeparator = new JSeparator();
		firstSeparator.setBounds(15, 240, 175, 5);
		firstSeparator.setPreferredSize(new Dimension(50,5));
		firstSeparator.setBackground(colorForDark);
		jPLeftPanel.add(firstSeparator);

		fontForButtons = new Font("Sogoe UI", Font.BOLD, 15);
		
		
		jBSearch = new JButton("Search", new ImageIcon("src/images/search.png"));
		jBSearch.addActionListener(secretaryController);
		jBSearch.setIconTextGap(73);
		setDesignForButtons(jBSearch);
		
		jBFullTime = new JButton("Full Time", new ImageIcon("src/images/fulltime.png"));
		jBFullTime.addActionListener(secretaryController);
		jBFullTime.setIconTextGap(56);
		setDesignForButtons(jBFullTime);
		
		jBPartTime = new JButton("Part Time", new ImageIcon("src/images/parttime.png"));
		jBPartTime.addActionListener(secretaryController);
		jBPartTime.setIconTextGap(54);
		setDesignForButtons(jBPartTime);
		
		jBContract = new JButton("Contract", new ImageIcon("src/images/contract.png"));
		jBContract.addActionListener(secretaryController);
		jBContract.setIconTextGap(61);
		setDesignForButtons(jBContract);
		
		jBAll = new JButton("All Lecturers", new ImageIcon("src/images/all.png"));
		jBAll.addActionListener(secretaryController);
		jBAll.setIconTextGap(32);
		setDesignForButtons(jBAll);
		
		jBLogout= new JButton("Log Out", new ImageIcon("src/images/logout.png"));
		jBLogout.addActionListener(secretaryController);
		jBLogout.setBackground(Color.RED);
		jBLogout.setForeground(Color.WHITE);
		jBLogout.setBorderPainted(false);
		jBLogout.setFocusable(false);
		jBLogout.setFont(fontForButtons);
		jBLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jBLogout.addMouseListener(secretaryController);
		
		jPRightPanel.add(jPSearch, jBSearch.getActionCommand());
		jPRightPanel.add(jPFullTime, jBFullTime.getActionCommand());
		jPRightPanel.add(jPPartTime, jBPartTime.getActionCommand());
		jPRightPanel.add(jPContract, jBContract.getActionCommand());
		jPRightPanel.add(jPAll, jBAll.getActionCommand());
		
		container.add(jPRightPanel);
		
		jPLeftPanel.setLayout(null);
		
		jPLeftPanel.add(jLViewLecturers);
		jLViewLecturers.setBounds(20,200,200,20);
		
		jPLeftPanel.add(jBSearch);
		jBSearch.setBounds(0, 270, 200,30);
		
		jPLeftPanel.add(jBFullTime);
		jBFullTime.setBounds(0, 310, 200,30);
		
		jPLeftPanel.add(jBPartTime);
		jBPartTime.setBounds(0, 350, 200,30);
		
		jPLeftPanel.add(jBContract);
		jBContract.setBounds(0, 390, 200,30);
		
		jPLeftPanel.add(jBAll);
		jBAll.setBounds(0, 430, 200,30);
		
		jPLeftPanel.add(jBLogout);
		jBLogout.setBounds(0, 650, 200,30);
		
		setViewForSearchPanel();
		setViewForFTPanel();
		setViewForPTPanel();
		setViewForCPanel();
		setViewForAll();
		setUndecorated(true);
		setVisible(true);
	}
	
	public void setDesignForButtons(JButton button) {
		button.setBorderPainted(false);
		button.setBackground(colorDark);
		button.setForeground(colorForDark);
		button.setFocusable(false);
		button.setFont(fontForButtons);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addMouseListener(secretaryController);
	}
	
	
	public void setViewForSearchPanel() {
		jPSearch.setLayout(null);
		
		jLSearch = new JLabel("Enter ID :");
		jLSearch.setFont(new Font("Sogoe UI", Font.BOLD, 18));
		jLSearch.setForeground(new Color(102, 102, 102));
		jLSearch.setBounds(30, 20, 200, 20);
		jPSearch.add(jLSearch);
		
		jTSearch = new JTextField(5);
		jTSearch.setBounds(200, 20, 80, 25);
		//jTSearch.setBounds(40, 300, 300, 40);
		jTSearch.setFont(new Font("Sogoe UI", Font.PLAIN, 18));
		jTSearch.setForeground(new Color(51,51,51));
		jTSearch.setBorder(BorderFactory.createLineBorder(new Color(51,51,51)));

		jBPSearch= new JButton("Search",  new ImageIcon("src/images/search.png"));
		jBPSearch.setActionCommand("Search ID");
		jBPSearch.setBounds(20, 60, 130 ,30);
		jBPSearch.addActionListener(secretaryController);
		setDesignForButtons(jBPSearch);
		jBPSearch.setFont(new Font("Sogoe UI", Font.BOLD, 13));
		
		jPSearch.add(jTSearch);
		jPSearch.add(jBPSearch);
		
		String columns[]= {"ID", "Name", "Address", "Email", "Contact", "DateStarted", "Department", "Gender", "Lecturer Type" ,"Salary", "Hourly Rate" ,"End Date"};//titles
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {//table model is created
			@Override
			public boolean isCellEditable(int row, int col) {//not editable cells
				return false;
			}
			
		};
		searchTable= new JTable(tableModel);//table is created
		searchTable.getTableHeader().setBounds(0,100, 900, 30);
		jPSearch.add(searchTable.getTableHeader());
		searchTable.setBounds(0, 130, 900, 40);
		setTableDesign(searchTable);
		jPSearch.add(searchTable);
		
	}
	
	//method to return search value 
	public int getValueofSearch(){
		int num=-1;//initial value is set
		if(jTSearch.getText().equals("")) {//if field is empty
			JOptionPane.showMessageDialog(null, "Please enter an ID");//show error
			return -1;
		}
		try {
			num= Integer.parseInt(jTSearch.getText());//converting number to integer
		}
		catch (NumberFormatException e) {//catch exception
			JOptionPane.showMessageDialog(null, "ID should be numeric");//show error
			return -1;
		}
		if(num<0) {//if number is negative
			JOptionPane.showMessageDialog(null, "ID should be non-negative");//show error
			return -1;
		}
		return num;//return the integer
	}
	
	///method to make search field empty
	public void resetSearchView() {
		jTSearch.setText("");
	}
	
	public void setViewForFTPanel() {
		fTLecturersTable= secretaryModel.getFullTimeLecturersTable();//create lecturers table
 		jPFullTime.setLayout(new BorderLayout());//setting the layout as border layout
		jPFullTime.add(fTLecturersTable.getTableHeader(),BorderLayout.PAGE_START);//table header is added to the panel
		setTableDesign(fTLecturersTable);
		fTLecturersTable.setFillsViewportHeight(true);//let the table fill the total area
		jPFullTime.add(new JScrollPane(fTLecturersTable));//scroll pane is added to the panel
		jPFullTime.add(fTLecturersTable);//table is added to panel
	}
	
	public void setViewForPTPanel() {
		pTLecturersTable= secretaryModel.getPartTimeLecturersTable();//create part time lecturers table
 		jPPartTime.setLayout(new BorderLayout());///set layout as border layout
		jPPartTime.add(pTLecturersTable.getTableHeader(),BorderLayout.PAGE_START);//set table header in panel
		setTableDesign(pTLecturersTable);
		pTLecturersTable.setFillsViewportHeight(true);//set full view of table
		jPPartTime.add(new JScrollPane(pTLecturersTable));//scroll pane is added
		jPPartTime.add(pTLecturersTable);//table is added
	}
	
	public void setViewForCPanel() {
		cLecturersTable= secretaryModel.getContractLecturersTable();//creating table
 		jPContract.setLayout(new BorderLayout());//setting layout
		jPContract.add(cLecturersTable.getTableHeader(),BorderLayout.PAGE_START);//adding table header
		
		setTableDesign(cLecturersTable);
		
		cLecturersTable.setFillsViewportHeight(true);//set full view of table
		jPContract.add(new JScrollPane(cLecturersTable));//scroll pane is added
		
		jPContract.add(cLecturersTable);//table is added
	}
	
	public void setViewForAll() {
		allLecturersTable= secretaryModel.getAllLecturersTable();//creating table 
 		jPAll.setLayout(new BorderLayout());//set layout
		jPAll.add(allLecturersTable.getTableHeader(),BorderLayout.PAGE_START);//adding header
		
		setTableDesign(allLecturersTable);
		
		allLecturersTable.setFillsViewportHeight(true);//set full view of table 
		jPAll.add(new JScrollPane(allLecturersTable));//scroll pane is added
		
		jPAll.add(allLecturersTable);//table is added
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
}
