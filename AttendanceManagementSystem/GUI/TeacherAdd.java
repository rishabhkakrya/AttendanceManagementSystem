package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Bean.Teachers;
import DAO.TeachersDaoImpl;

public class TeacherAdd implements ActionListener{
	JFrame jf;
	JLabel teacherId,teacherName,teacherPass,teacherEmail,tComboID,cName;
	JTextField tId,tName,tComboIDField,tEmail;
	JButton submitButton,viewButton,deleteButton,searchButton,alterButton,backButton;
	JTable teacherTable;
	Panel p1,p2,p3,p4;
	JTextField tPassword;
	DefaultTableModel dtm;
	public void getForm() throws ClassNotFoundException, SQLException{
		jf = new JFrame("Adding Teacher");
		jf.setLayout(null);
		jf.setBounds(180,20,1000,700);
		jf.setResizable(false);
		
		//Font
		Font f = new Font("Times New Roman", Font.BOLD, 30);
		
		//Panels
		p1 = new Panel();
		p1.setBounds(10, 90, 973, 280);
		p1.setLayout(null);
		p1.setBackground(Color.LIGHT_GRAY);
		p2 = new Panel();
		p2.setBounds(10, 380, 973, 280);
		p2.setLayout(null);
		p2.setBackground(Color.LIGHT_GRAY);
		p3 = new Panel();
		p3.setBounds(10, 10, 973, 70);
		p3.setLayout(null);
		p3.setBackground(Color.LIGHT_GRAY);
		p4 = new Panel(new BorderLayout());
		p4.setBackground(Color.LIGHT_GRAY);
		p4.setBounds(10, 10, 833, 260);
		
		//Labels
		teacherId = new JLabel("Teacher Id :");
		teacherName = new JLabel("Name :");
		teacherPass = new JLabel("Password :");
		teacherEmail = new JLabel("Email :");
		cName = new JLabel("BHAGWAN PARSHURAM INSTITUTE OF TECHNOLOGY",SwingConstants.CENTER);
		teacherId.setBounds(40, 40, 100, 20);
		teacherName.setBounds(40, 100, 100, 20);
		teacherPass.setBounds(550, 40, 100, 20);
		teacherEmail.setBounds(40, 160, 100, 20);
		cName.setBounds(10,10,973,70);
		cName.setFont(f);
		
		//TextFields
		tId = new JTextField(50);
		tName = new JTextField(50);
		tComboIDField = new JTextField(50);
		tEmail = new JTextField(50);
		tId.setBounds(170,40,200,30);
		tName.setBounds(170,100,200,30);
		tEmail.setBounds(170,160,200,30);
		
		//Passwordfield
		tPassword = new JTextField();
		
		tPassword.setBounds(680,40,200,30);
		
		//Button
		submitButton = new JButton("Submit");
		submitButton.setBounds(853, 220, 100, 40);
		viewButton = new JButton("View");
		viewButton.setBounds(853, 10, 100, 40);
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(853, 220, 100, 40);
		deleteButton.setToolTipText("Enter the teacher ID!!");
		searchButton = new JButton("Search");
		searchButton.setBounds(853, 80, 100, 40);
		searchButton.setToolTipText("Enter the teacher ID!!");
		alterButton = new JButton("Modify");
		alterButton.setBounds(853, 150, 100, 40);
		alterButton.setToolTipText("Enter the teacher ID!!");
		backButton = new JButton("Back");
		backButton.setBounds(853,150,100,40);
		
		dtm = new DefaultTableModel();
		teacherTable = new JTable(dtm);
		
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Email");
		dtm.addColumn("Password");
		
		//Adding labels
		p1.add(teacherId);
		p1.add(teacherName);
		p1.add(teacherPass);
		p1.add(teacherEmail);
		p1.add(tPassword);
		
		//Adding TextFields
		p1.add(tId);
		p1.add(tName);
		p1.add(tComboIDField);
		p1.add(tEmail);
		p3.add(cName);
		
		//Adding buttons
		p1.add(submitButton);
		p2.add(viewButton);
		p1.add(backButton);
		
		//Adding Table
		JScrollPane js = new JScrollPane(teacherTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p4.add(js,BorderLayout.CENTER);
		p2.add(p4);
		p2.add(deleteButton);
		p2.add(searchButton);
		p2.add(alterButton);
		
		//Adding panels
		jf.add(p1);
		jf.add(p2);
		jf.add(p3);
		
		submitButton.addActionListener(this);
		viewButton.addActionListener(this);
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		alterButton.addActionListener(this);
		backButton.addActionListener(this);
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		TeacherAdd obj1 = new TeacherAdd();
		obj1.getForm();
		obj1.jf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource() == submitButton){
			int tmp = 0;
			Teachers teachers = new Teachers();
			teachers.settID(tId.getText());
			teachers.settName(tName.getText());
			teachers.settEmail(tEmail.getText());
			teachers.settPassword(tPassword.getText());
			TeachersDaoImpl teacherImpl = new TeachersDaoImpl();
			try {
				tmp = teacherImpl.submit(teachers);
				if(tmp!=0)
					JOptionPane.showMessageDialog(jf, "Record successfully submitted!");
				else
					JOptionPane.showMessageDialog(jf, "Some problem occured!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tId.setText("");
			tName.setText("");
			tEmail.setText("");
			tPassword.setText("");
		}
		if(arg0.getSource() == viewButton){
			DefaultTableModel dm = (DefaultTableModel)teacherTable.getModel();
			while(dm.getRowCount() > 0)
			{
			    dm.removeRow(0);
			}
			try{
				TeachersDaoImpl teacherImpl = new TeachersDaoImpl();
	            ResultSet rs = teacherImpl.view();
	            while(rs.next()){
	            	dtm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
	           }
	            
	        }
	        catch(SQLException ex){
	            System.out.println(ex);
	        }
		}
		if(arg0.getSource() == deleteButton){
			if(((tId.getText().equals("")))){
				JOptionPane.showMessageDialog(jf, "Teacher ID field is empty");
			}
			else{
				int tmp = 0;
				TeachersDaoImpl teacherImpl = new TeachersDaoImpl();
				try {
					tmp = teacherImpl.delete(tId.getText());
					if(tmp!=0)
						JOptionPane.showMessageDialog(jf, "Record successfully deleted!");
					else
						JOptionPane.showMessageDialog(jf, "Some Problem occured!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			tId.setText("");
			tName.setText("");
			tEmail.setText("");
			tPassword.setText("");
		}
		if(arg0.getSource() == searchButton){
			if(((tId.getText().equals("")))){
				JOptionPane.showMessageDialog(jf, "Teacher id field is empty");
			}
			else{
				TeachersDaoImpl teacherImpl = new TeachersDaoImpl();
				Teachers teachers;
				try {
					teachers = teacherImpl.search(tId.getText());
					tName.setText(teachers.gettName());
					tEmail.setText(teachers.gettEmail());
					tPassword.setText(teachers.gettPassword());
					//JOptionPane.showMessageDialog(jf, "Details successfully showed!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		if(arg0.getSource() == alterButton){
			int tmp = 0;
			TeachersDaoImpl teachersImpl = new TeachersDaoImpl();
			Teachers teachers = new Teachers();
			teachers.settID(tId.getText());
			teachers.settName(tName.getText());
			teachers.settEmail(tEmail.getText());
			teachers.settPassword(tPassword.getText());
			try {
				tmp = teachersImpl.modify(tId.getText(), teachers);
				if(tmp!=0)
					JOptionPane.showMessageDialog(jf, "Details Successfully modified!");
				else
					JOptionPane.showMessageDialog(jf, "Some problem occured!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tId.setText("");
			tName.setText("");
			tEmail.setText("");
			tPassword.setText("");
		}
		if(arg0.getSource() == backButton){
			jf.dispose();
			AdminHome home = new AdminHome();
			home.getLoginForm();
			home.jf.setVisible(true);
		}
	}
}