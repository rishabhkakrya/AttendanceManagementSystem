package GUI;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LoginForm1 implements MouseListener {
	public static JFrame basicFrame;
	JButton adminButton;
	JButton facultyButton;
	JButton studentButton;
	JLabel background;
	JLabel adminLabel;
	JLabel facultyLabel;
	JLabel studentLabel;
	LoginForm login1;
	LoginForm login2;
	LoginForm login3;
	public void getLoginForm(){
		basicFrame = new JFrame("Login");
		basicFrame.setBounds(180,20,1000,700);
		basicFrame.setResizable(false);
		basicFrame.setLayout(null);
		basicFrame.getContentPane().setBackground(Color.BLUE);
		adminButton = new JButton("ADMIN");
		facultyButton = new JButton("FACULTY");
		studentButton = new JButton("STUDENT");
		
		Icon bgIcon = new ImageIcon("Images/background.jpg");
		background = new JLabel(bgIcon);
		basicFrame.setContentPane(background);
		Icon icon = new ImageIcon("Images/Admin.png");		
		adminLabel = new JLabel(icon);
		
	
		
		Icon icon1 = new ImageIcon("Images/Parent1.png");
		studentLabel = new JLabel(icon1);
		

		
		Icon icon2 = new ImageIcon("Images/staff.png");
		facultyLabel = new JLabel(icon2);

		adminLabel.setBounds(110, 380, 212, 260);
		facultyLabel.setBounds(390, 380, 212, 260);
		studentLabel.setBounds(670, 380, 212, 260);
		basicFrame.add(adminLabel);
		basicFrame.add(facultyLabel);
		basicFrame.add(studentLabel);
		adminLabel.addMouseListener(this);
		facultyLabel.addMouseListener(this);
		studentLabel.addMouseListener(this);
	}
	public static void main(String args[]){
		LoginForm1 loginForm = new LoginForm1();
		loginForm.getLoginForm();
		LoginForm1.basicFrame.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource() == adminLabel){
			
			login1 = new LoginForm("ADMIN");
			if(login2!=null)
				login2.loginFrame.setVisible(false);
			if(login3!=null)
				login3.loginFrame.setVisible(false);
			login1.getLoginForm();
			login1.loginFrame.setVisible(true);
		}
		
		if(arg0.getSource() == facultyLabel)
		{login2 = new LoginForm("FACULTY");
		if(login1!=null)
			login1.loginFrame.setVisible(false);
		if(login3!=null)
			login3.loginFrame.setVisible(false);
		
		login2.getLoginForm();
		login2.loginFrame.setVisible(true);}
		
		if(arg0.getSource() == studentLabel)
			{login3 = new LoginForm("STUDENT");
			if(login2!=null)
				login2.loginFrame.setVisible(false);
			if(login1!=null)
				login1.loginFrame.setVisible(false);
			//login2.loginFrame.setVisible(false);
			//login1.loginFrame.setVisible(false);
		     login3.getLoginForm();
		     login3.loginFrame.setVisible(true);
			}
		}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
