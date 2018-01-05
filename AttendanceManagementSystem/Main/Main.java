package Main;
import GUI.LoginForm1;
import GUI.WelcomePage;

import java.sql.Connection;
import java.sql.SQLException;

import Bean.DataSource;
import GUI.LoginForm1;

public class Main {
	static Connection con;
	
	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		Main.con = con;
	}

	public static void main(String[] args) throws SQLException,ClassNotFoundException {
		// TODO Auto-generated method stub
		con=(new DataSource()).getDBConnection();
		WelcomePage welcome=new WelcomePage();
		Thread t1 = new Thread(welcome);
		t1.start();
	}
}
