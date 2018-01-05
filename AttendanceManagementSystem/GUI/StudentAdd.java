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

import Bean.Students;
import Bean.Teachers;
import DAO.StudentsDaoImpl;
import DAO.TeachersDaoImpl;

public class StudentAdd implements ActionListener{
	JFrame jf;
	JLabel sId,sName,sClass,sPass,sPhone,sEmail,cName;
	JTextField tId,tName,tPhone,tEmail;
	JButton submitButton,viewButton,deleteButton,searchButton,alterButton,backButton;
	JTable sTable;
	Panel p1,p2,p3,p4;
	JComboBox classBox;
	JTextField sPassword;
	DefaultTableModel dtm;
	public void getForm() throws ClassNotFoundException, SQLException{
		jf = new JFrame("Adding Student");
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
		sId = new JLabel("Student Id :");
		sName = new JLabel("Name :");
		sClass = new JLabel("Class/Stream :");
		sPass = new JLabel("Password :");
		sPhone = new JLabel("Phone No :");
		sEmail = new JLabel("Email :");
		cName = new JLabel("BHAGWAN PARSHURAM INSTITUTE OF TECHNOLOGY",SwingConstants.CENTER);
		sId.setBounds(40, 40, 100, 20);
		sName.setBounds(40, 100, 100, 20);
		sClass.setBounds(550, 40, 100, 20);
		sPass.setBounds(550, 100, 100, 20);
		sPhone.setBounds(40, 160, 100, 20);
		sEmail.setBounds(40, 220, 100, 20);
		cName.setBounds(10,10,973,70);
		cName.setFont(f);
		
		//TextFields
		tId = new JTextField(50);
		tName = new JTextField(50);
		tPhone = new JTextField(50);
		tEmail = new JTextField(50);
		tId.setBounds(170,40,200,30);
		tName.setBounds(170,100,200,30);
		tPhone.setBounds(170,160,200,30);
		tEmail.setBounds(170,220,200,30);
		
		//ComboBox
		classBox = new JComboBox();
		classBox.addItem("CSE");
		classBox.addItem("IT");
		classBox.addItem("ECE");
		classBox.addItem("EEE");
		
		//Passwordfield
		sPassword = new JTextField();
		
		classBox.setBounds(680,40 , 200, 30);
		sPassword.setBounds(680,100,200,30);
		
		//Button
		submitButton = new JButton("Submit");
		submitButton.setBounds(853, 220, 100, 40);
		viewButton = new JButton("View");
		viewButton.setBounds(853, 10, 100, 40);
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(853, 220, 100, 40);
		deleteButton.setToolTipText("Enter the student ID and student Name!!");
		searchButton = new JButton("Search");
		searchButton.setBounds(853, 80, 100, 40);
		searchButton.setToolTipText("Enter the student ID and student Name!!");
		alterButton = new JButton("Modify");
		alterButton.setBounds(853, 150, 100, 40);
		alterButton.setToolTipText("Enter the student ID and student Name!!");
		backButton = new JButton("Back");
		backButton.setBounds(853,150,100,40);
		//Tables
		dtm = new DefaultTableModel();
		sTable = new JTable(dtm);

		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Class");
		dtm.addColumn("Password");
		dtm.addColumn("Phone No");
		dtm.addColumn("Email");
		
		//Adding labels
		p1.add(sId);
		p1.add(sName);
		p1.add(sClass);
		p1.add(sPass);
		//p1.add(sSection);
		p1.add(sPhone);
		p1.add(sEmail);
		p1.add(classBox);
		p1.add(sPassword);
		
		//Adding TextFields
		p1.add(tId);
		p1.add(tName);
		p1.add(tPhone);
		p1.add(tEmail);
		p3.add(cName);
		
		//Adding buttons
		p1.add(submitButton);
		p1.add(backButton);
		p2.add(viewButton);
		
		//Adding Table
		JScrollPane js = new JScrollPane(sTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		alterButton.addActionListener(this);
		backButton.addActionListener(this);
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		StudentAdd obj1 = new StudentAdd();
		obj1.getForm();
		obj1.jf.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		
		if(arg0.getSource() == submitButton){
			int tmp = 0;
			Students students = new Students();
			students.setSid(tId.getText());
			students.setsName(tName.getText());
			students.setsEmail(tEmail.getText());
			students.setsPassword(sPassword.getText());
			students.setsClass(classBox.getSelectedItem().toString());
			students.setsPhoneno(Long.parseLong(tPhone.getText()));
			StudentsDaoImpl studentImpl = new StudentsDaoImpl();
			try {
				tmp = studentImpl.submit(students);
				if(tmp!=0)
					JOptionPane.showMessageDialog(jf, "Record successfuly submitted!");
				else
					JOptionPane.showMessageDialog(jf, "Some Problem occured");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tId.setText("");
			tName.setText("");
			tEmail.setText("");
			sPassword.setText("");
			classBox.setSelectedIndex(0);
			tPhone.setText("");
		}
		if(arg0.getSource() == viewButton){
			DefaultTableModel dm = (DefaultTableModel)sTable.getModel();
			while(dm.getRowCount() > 0)
			{
			    dm.removeRow(0);
			}
			try{
	            int r=1;
	            StudentsDaoImpl studentImpl = new StudentsDaoImpl();
	            ResultSet rs = studentImpl.view();
	            while(rs.next()){
	            	dtm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getString(6)});
	           }
	            
	        }
	        catch(SQLException ex){
	            System.out.println(ex);
	        }
		}
		if(arg0.getSource() == deleteButton){
			if(((tId.getText().equals("")))){
				JOptionPane.showMessageDialog(jf, "Student id field is empty");
			}
			else{
				int tmp = 0;
				StudentsDaoImpl studentImpl = new StudentsDaoImpl();
				try {
					tmp = studentImpl.delete(tId.getText());
					if(tmp!=0)
						JOptionPane.showMessageDialog(jf, "Record Successfully Deleted!");
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
			sPassword.setText("");
			classBox.setSelectedIndex(0);
			tPhone.setText("");
		}
		if(arg0.getSource() == searchButton){
			if(((tId.getText().equals(""))) || (tName.getText().equals(""))){
				JOptionPane.showMessageDialog(jf, "Either Student id or Student Name field is empty");
			}
			else{
				StudentsDaoImpl studentImpl = new StudentsDaoImpl();
				Students students;
				try {
					students = studentImpl.search(tId.getText());
					tName.setText(students.getsName());
					tEmail.setText(students.getsEmail());
					sPassword.setText(students.getsPassword());
					tPhone.setText(String.valueOf(students.getsPhoneno()));
					classBox.setSelectedItem(students.getsClass());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		if(arg0.getSource() == alterButton){
			int tmp = 0;
			StudentsDaoImpl studentsImpl = new StudentsDaoImpl();
			Students students = new Students();
			students.setSid(tId.getText());
			students.setsName(tName.getText());
			students.setsEmail(tEmail.getText());
			students.setsPassword(sPassword.getText());
			students.setsPhoneno(Long.parseLong(tPhone.getText()));
			students.setsClass(classBox.getSelectedItem().toString());
			try {
				tmp = studentsImpl.modify(tId.getText(), students);
				if(tmp!=0)
					JOptionPane.showMessageDialog(jf, "Details Successfully modified!");
				else
					JOptionPane.showMessageDialog(jf, "Some Problem occured!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tId.setText("");
			tName.setText("");
			tEmail.setText("");
			sPassword.setText("");
			classBox.setSelectedIndex(0);
			tPhone.setText("");
		}
		
		if(arg0.getSource() == backButton){
			jf.dispose();
			AdminHome home = new AdminHome();
			home.getLoginForm();
			home.jf.setVisible(true);
		}
	}
}
	