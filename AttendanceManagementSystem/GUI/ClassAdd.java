package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Bean.DataSource;
import Bean.StudentClass;
import Bean.Subject;
import DAO.StudentClassDaoImpl;
import DAO.SubjectDao;
import DAO.SubjectDaoImpl;

public class ClassAdd implements ActionListener{
	JFrame jf;
	JTextField classID,className;
	JButton addButton,backButton,logOutButton;
	JLabel background,classIDLabel,classNameLabel;
	public void getLoginForm(){
		jf = new JFrame("Class Add");
		jf.setLayout(null);
		jf.setBounds(180,20,1000,700);
		jf.setResizable(false);
		
		Icon icon = new ImageIcon("Images/images.jpeg");
		background = new JLabel(icon);
		jf.setContentPane(background);
		
		Font f = new Font("Times New Roman",Font.BOLD,18);
		
		classIDLabel = new JLabel("Class ID :");
		classIDLabel.setFont(f);
		classIDLabel.setForeground(Color.BLACK);
		classNameLabel = new JLabel("Class Name :");
		classNameLabel.setFont(f);
		classNameLabel.setForeground(Color.BLACK);
		
		classID = new JTextField(50);
		className = new JTextField(50);
		
		addButton = new JButton("ADD");
		backButton = new JButton("Back");
		logOutButton = new JButton("Logout");
		
		classIDLabel.setBounds(350,200, 150, 40);
		classNameLabel.setBounds(330,240,150, 40);
		
		classID.setBounds(490, 200, 200, 30);
		className.setBounds(490, 250, 200, 30);
		
		addButton.setBounds(430,320,200,50);
		backButton.setBounds(420, 400, 100, 40);
		logOutButton.setBounds(530,400,100,40);
		
		jf.add(classIDLabel);
		jf.add(classNameLabel);
		jf.add(classID);
		jf.add(className);
		jf.add(addButton);
		jf.add(backButton);
		jf.add(logOutButton);
		
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		logOutButton.addActionListener(this);
	}
	public static void main(String args[]){
		ClassAdd ca = new ClassAdd();
		ca.getLoginForm();
		ca.jf.setVisible(true);
		DataSource ob = new DataSource();
		try {
			ob.getDBConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addButton){
		int tmp = 0;
		StudentClass studentClass = new StudentClass();
		studentClass.setCID(classID.getText());
		studentClass.setCName(className.getText());
		StudentClassDaoImpl sclass = new StudentClassDaoImpl();
		try {
			tmp = sclass.add(studentClass);
			if(tmp!=0)
				JOptionPane.showMessageDialog(jf, "Class successfully added!");
			else
				JOptionPane.showMessageDialog(jf, "Some problem occured!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		classID.setText("");
		className.setText("");
		}
		if(e.getSource() == backButton){
			jf.dispose();
			AdminHome home = new AdminHome();
			home.getLoginForm();
			home.jf.setVisible(true);
		}
		if(e.getSource() == logOutButton){
			JOptionPane.showMessageDialog(jf, "Successfully loged out!");
			jf.dispose();
			DataSource ds = new DataSource();
			LoginForm1 form = new LoginForm1();
			form.getLoginForm();
			LoginForm1.basicFrame.setVisible(true);
		}
	}
}
