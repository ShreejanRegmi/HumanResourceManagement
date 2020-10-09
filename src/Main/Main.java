package Main;

import Controller.LoginController;
import Model.LoginModel;
import View.LoginView;

public class Main {
	public static void main(String[] args) {
		LoginModel loginModel = new LoginModel();//calling the login model
		LoginController loginController = new LoginController(loginModel);//calling the login controller with parameter for login model
		LoginView loginView = new LoginView(loginModel, loginController);//calling the login view with parameter for login model and login controller
	}
}