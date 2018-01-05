package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AdminHome implements ActionListener{
	JFrame jf;
	JButton studentInfo,teacherInfo,classInfo,subjectInfo,logOutButton;
	JLabel bground;
	public void getLoginForm(){
		jf = new JFrame("Admin Home");
		jf.setLayout(null);
		jf.setBounds(180,20,1000,700);
		
		studentInfo = new JButton("Student Info");
		teacherInfo = new JButton("Teacher Info");
		classInfo = new JButton("Class Info");
		subjectInfo = new JButton("Subject Info");
		logOutButton = new JButton("logout");
		studentInfo.setBounds(400,150,200,50);
		teacherInfo.setBounds(400,250,200,50);
		classInfo.setBounds(400, 350, 200, 50);
		subjectInfo.setBounds(400, 450, 200, 50);
		logOutButton.setBounds(450, 550, 100, 40);
		
		Icon icon = new ImageIcon("Images/adminbackground.png");
		bground = new JLabel(icon);
		jf.setContentPane(bground);
		
		jf.add(studentInfo);
		jf.add(teacherInfo);
		jf.add(classInfo);
		jf.add(subjectInfo);
		jf.add(logOutButton);
		
		studentInfo.addActionListener(this);
		teacherInfo.addActionListener(this);
		classInfo.addActionListener(this);
		subjectInfo.addActionListener(this);
		logOutButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == studentInfo){
			jf.dispose();
			StudentAdd sa = new StudentAdd();
				try {
					sa.getForm();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sa.jf.setVisible(true);
		}
		if(arg0.getSource() == teacherInfo){
			jf.dispose();
			TeacherAdd sa = new TeacherAdd();
				try {
					sa.getForm();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sa.jf.setVisible(true);
		}
		if(arg0.getSource() == classInfo){
			jf.dispose();
			ClassAdd sa = new ClassAdd();
			sa.getLoginForm();
			sa.jf.setVisible(true);
		}
		if(arg0.getSource() == subjectInfo){
			jf.dispose();
			SubjectAdd sa = new SubjectAdd();
			sa.getLoginForm();
			sa.jf.setVisible(true);
		}
		if(arg0.getSource() == logOutButton){
			JOptionPane.showMessageDialog(jf, "Successfully logout!");
			jf.dispose();
			LoginForm1 ob = new LoginForm1();
			ob.getLoginForm();
			LoginForm1.basicFrame.setVisible(true);
		}
	}
}
