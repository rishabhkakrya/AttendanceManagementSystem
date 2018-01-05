package GUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Bean.Students;
import Bean.Teachers;
import DAO.StudentsDaoImpl;
import DAO.TeachersDaoImpl;

public class LoginForm implements ActionListener{
	String user;
	String adminUsername = "admin",adminPassword = "admin";
	JFrame loginFrame;
	JLabel username,password,callingUser,bckgnd;
	JTextField userText,passText;
	JButton loginButton,backButton;
	Icon bkIcon;
	public LoginForm(String user) {
		this.user = user;
	}
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	public void getLoginForm(){
		loginFrame = new JFrame();
		loginFrame.getContentPane().setBackground(Color.gray);
		loginFrame.setLayout(null);
		loginFrame.setBounds(500,170,400,400);
		loginFrame.setResizable(false);
		loginFrame.setTitle("Login");
		
		StudentForm sf = new StudentForm();
		
		switch (user)
		    {case "ADMIN" :bkIcon = new ImageIcon("Images/pm1.jpg");
		                   break;
		    
		     
		     
		     case "FACULTY" :bkIcon = new ImageIcon("Images/teacher_img.jpg");
		                     break;
		     
		     
		     
		     
		     case "STUDENT" :bkIcon = new ImageIcon("Images/studentsback.jpg");
		                     break;              
		
		    }
		
		
		bckgnd = new JLabel(bkIcon);
		bckgnd.setBounds(0,0,400,400);
		
		callingUser = new JLabel("Welcome " + user);
		callingUser.setBounds(140, 15, 150, 30);
		username = new JLabel("Username:");
		username.setBounds(160, 50, 70, 20);
		username.setBackground(Color.GREEN);
		password = new JLabel("Password:");
		password.setBounds(160, 80, 70, 20);
		userText = new JTextField();
		userText.setBounds(230, 50, 100, 20);
		passText = new JPasswordField();
		passText.setBounds(230, 80, 100, 20);
		loginButton = new JButton("Login");
		loginButton.setBounds(205, 200, 100, 50);
		backButton = new JButton("Back");
		backButton.setBounds(95, 200, 100, 50);
		loginFrame.add(callingUser);
		loginFrame.add(username);
		loginFrame.add(password);
		loginFrame.add(userText);
		loginFrame.add(passText);
		loginFrame.add(loginButton);
		loginFrame.add(backButton);
		loginFrame.add(bckgnd);
		backButton.addActionListener(this);
		loginButton.addActionListener(this);
	}

	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == backButton){
			
			
			loginFrame.dispose();
		
		}
		if(arg0.getSource() == loginButton){
			if(user.equals("ADMIN")){
				if(userText.getText().equals(adminUsername) && passText.getText().equals(adminPassword)){
					LoginForm1.basicFrame.dispose();
					loginFrame.dispose();
					AdminHome adhome = new AdminHome();
					adhome.getLoginForm();
					adhome.jf.setVisible(true);
				}
			}
			else if(user.equals("FACULTY")){
				boolean flag = false;
				TeachersDaoImpl teachers = new TeachersDaoImpl(); 
				try {
					flag = teachers.findTeacher(userText.getText(), passText.getText());
					if(flag){
						LoginForm1.basicFrame.dispose();
						loginFrame.dispose();
						Teachers teacher = new Teachers();
						teacher = teachers.getRecord(userText.getText()); 
						Teacher tf = new Teacher(teacher.gettID(),teacher.gettPassword(),teacher.gettEmail());
						
					}
					else
						JOptionPane.showMessageDialog(loginFrame, "Either username or password is invalid!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			}
			else if(user.equals("STUDENT")){
				boolean flag = false;
				StudentsDaoImpl students = new StudentsDaoImpl(); 
				try {
					
					flag = students.findStudent(userText.getText(), passText.getText());
						if(flag){
							LoginForm1.basicFrame.dispose();
							loginFrame.dispose();
							Students student = new Students();
							student = students.getRecord(userText.getText());
							StudentForm sf = new StudentForm(student.getSid(),student.getsName(),student.getsClass());
							sf.getLoginForm();
							sf.frame.setVisible(true);
							
						}
						else
							JOptionPane.showMessageDialog(loginFrame, "Either username or password is invalid!");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}	
		}
	}

				
}
