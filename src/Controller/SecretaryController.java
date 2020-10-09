package Controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import Main.Main;
import Model.SecretaryModel;
import View.SecretaryView;

public class SecretaryController implements ActionListener, MouseListener {//secretary model implementing various interfaces
	SecretaryModel secretaryModel;//class variable of secretary model
	SecretaryView secretaryView;//class variable of secretary view
	
	//constructor of secretary controller
	public SecretaryController(SecretaryModel secretaryModel) {///accepts secretary model parameter
		this.secretaryModel=secretaryModel;//assigns value
	}
	
	//method to add view to controller
	public void addView(SecretaryView secretaryView) {//takes parameter of secretary view
		this.secretaryView=secretaryView;//assigns value
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cardContainer = (CardLayout)(secretaryView.jPRightPanel.getLayout());//card layout panel
		
		if (e.getActionCommand().equals("Search")) {//if search button pressed
			secretaryView.resetSearchView();//reset table 
			secretaryView.changeableText.setText("Search");//set text as search
			cardContainer.show(secretaryView.jPRightPanel, "Search");//panel changed
			
		}
		if (e.getActionCommand().equals("Full Time")) {//if full time pressed
			secretaryView.changeableText.setText("Full Time Lecturers");//set text full time lecturers
			cardContainer.show(secretaryView.jPRightPanel, "Full Time");//change panel
		}
		if (e.getActionCommand().equals("Part Time")) {//if part time pressed
			secretaryView.changeableText.setText("Part Time Lecturers");//set text part time lecturers
			cardContainer.show(secretaryView.jPRightPanel, "Part Time");//panel changed
		}
		if (e.getActionCommand().equals("Contract")) {//contract button pressed
			secretaryView.changeableText.setText("Contract Lecturers");//text is set
			cardContainer.show(secretaryView.jPRightPanel, "Contract");//panel is changed
		}
		if (e.getActionCommand().equals("All Lecturers")) {//all lecturers button pressed
			secretaryView.changeableText.setText("All Lecturers");//text changed
			cardContainer.show(secretaryView.jPRightPanel, "All Lecturers");//panel is changed
		}
	
		if(e.getActionCommand().equals("Search ID")) {//search button pressed
			int searchid= secretaryView.getValueofSearch();//search value is taken
			
			if(searchid!=-1) {//if valid
				try {
					((DefaultTableModel) secretaryView.searchTable.getModel()).removeRow(0);//remove the already containing row
				}
				catch (NullPointerException e1) {//catch exception
				
				}
				catch (ArrayIndexOutOfBoundsException e2) {//catch exception
				}
				((DefaultTableModel) secretaryView.searchTable.getModel()).addRow(secretaryModel.getTableData(searchid));//row added
			}
		}
		
		//if log out button pressed
		if(e.getActionCommand().equals("Log Out")){
			secretaryView.dispose();//view is closed
			Main.main(null);//login view opened
		}	
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getSource() instanceof JLabel)
			System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent me) {
			if (me.getSource() instanceof JButton) {
			Color color = ((JButton) me.getSource()).getBackground();
			int r= color.getRed();
			int g= color.getGreen();
			int b= color.getBlue();
			if(r+63<255) {
				((JButton)me.getSource()).setBackground(new Color(r+63, g, b));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent me) {
			if (me.getSource() instanceof JButton) {
			Color color = ((JButton) me.getSource()).getBackground();
			int r= color.getRed();
			int g= color.getGreen();
			int b= color.getBlue();
			if(r+63<255) {
				((JButton)me.getSource()).setBackground(new Color(r-63, g, b));
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
