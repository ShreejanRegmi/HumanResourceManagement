package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Model.LoginModel;
import View.LoginView;

public class LoginController implements ActionListener,KeyListener,MouseListener {//class login controller acting as the implemented interfaces
	
	LoginView loginView;//the current login view
	LoginModel loginModel;//the current login model
	
	public LoginController(LoginModel loginModel) {//model setter
		this.loginModel=loginModel;//class variable is set
	}
	
	//method to set the current view 
	public void addView(Object view) {//view setter
		this.loginView=(LoginView) view;//class variable is set
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		this.loginModel.setUsername(this.loginView.getUsername());//the class variable is set of login model
		this.loginModel.setPassword(this.loginView.getPassword());//class variable is set of login view
		this.loginModel.checkAdmin(loginView);//the type of user information entered in login is checked
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()==KeyEvent.VK_ENTER) {//if enter button pressed, call action listener method
			this.actionPerformed(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JLabel)
			System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof JToggleButton) {
			loginView.getjPPassword().setEchoChar((char)0);//changes the icon of eye image and shows the password
			loginView.getjBPassword().setSelectedIcon(new ImageIcon("src/images/eye-dark.png"));
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() instanceof JToggleButton) {
			loginView.getjPPassword().setEchoChar(('•'));//changes the icon of eye image and hides the password
			loginView.getjBPassword().setSelectedIcon(new ImageIcon("src/images/eye.png"));
		}
	
	}
	
}
