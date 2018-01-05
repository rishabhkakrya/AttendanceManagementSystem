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
import Bean.Subject;
import DAO.SubjectDao;
import DAO.SubjectDaoImpl;

public class SubjectAdd implements ActionListener{
	JFrame jf;
	JTextField subjectID,subjectName;
	JButton addButton,backButton,logOutButton;
	JLabel background,subjectIDLabel,subjectNameLabel;
	public void getLoginForm(){
		jf = new JFrame("Class Add");
		jf.setLayout(null);
		jf.setBounds(180,20,1000,700);
		jf.setResizable(false);
		
		Icon icon = new ImageIcon("Images/books.jpg");
		background = new JLabel(icon);
		jf.setContentPane(background);
		
		Font f = new Font("Times New Roman",Font.BOLD,18);
		
		subjectIDLabel = new JLabel("Subject ID :");
		subjectIDLabel.setFont(f);
		subjectIDLabel.setForeground(Color.WHITE);
		subjectNameLabel = new JLabel("Subject Name :");
		subjectNameLabel.setFont(f);
		subjectNameLabel.setForeground(Color.WHITE);
		
		subjectID = new JTextField(50);
		subjectName = new JTextField(50);
		
		addButton = new JButton("ADD");
		backButton = new JButton("Back");
		logOutButton = new JButton("Logout");
		
		subjectIDLabel.setBounds(350,200, 150, 40);
		subjectNameLabel.setBounds(330,240,150, 40);
		
		subjectID.setBounds(490, 200, 200, 30);
		subjectName.setBounds(490, 250, 200, 30);
		
		addButton.setBounds(430,320,200,50);
		backButton.setBounds(420, 400, 100, 40);
		logOutButton.setBounds(530,400,100,40);
		
		jf.add(subjectIDLabel);
		jf.add(subjectNameLabel);
		jf.add(subjectID);
		jf.add(subjectName);
		jf.add(addButton);
		jf.add(backButton);
		jf.add(logOutButton);
		
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		logOutButton.addActionListener(this);
	}
	public static void main(String args[]){
		SubjectAdd sa = new SubjectAdd();
		sa.getLoginForm();
		sa.jf.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addButton){
		int tmp = 0;
		Subject subject = new Subject();
		subject.setSubID(subjectID.getText());
		subject.setSubName(subjectName.getText());
		SubjectDaoImpl sub = new SubjectDaoImpl();
		try {
			tmp = sub.add(subject);
			if(tmp!=0)
				JOptionPane.showMessageDialog(jf, "Subject successfully added!");
			else
				JOptionPane.showMessageDialog(jf, "Some problem occured!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		subjectID.setText("");
		subjectName.setText("");
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
