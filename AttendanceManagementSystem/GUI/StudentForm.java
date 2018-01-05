package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.ui.RefineryUtilities;

import Bean.SubAttendance;
import Bean.Subject;
import DAO.SubAttendanceDaoImpl;
import DAO.SubjectDaoImpl;

public class StudentForm implements ActionListener{
	JFrame frame;
	JLabel sName;
	JLabel sEnrollNo;
	JLabel sClass;
	JLabel sAverage;
	JLabel sPercentage;
	JLabel sNameValue;
	JLabel sEnrollNoValue;
	JLabel sClassValue;
	JLabel sAverageValue;
	JLabel sPercentageValue,headImage;
	String name, sid,className,section;
	JTable sTable;
	JButton subjectWiseButton,logOutButton;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	JPanel tablePanel,headPanel;
	DefaultTableModel model;
	float average = 0,percentage = 0;
	int columnCount;
	public StudentForm(String sid, String name, String className) {
		super();
		this.name = name;
		this.sid = sid;
		this.className = className;
	}
	public StudentForm(){
		name = "Rishabh";
		sid = "SCS001";
		className= "Computer Science and Engineering";
		section = "A";
	}
	
	public void getTabel(Connection con) throws SQLException{
		SubjectDaoImpl subject = new SubjectDaoImpl();
		model.addColumn("Subject ID");
		model.addColumn("Subject Name");
		model.addColumn("Attended");
		model.addColumn("Total");
		ArrayList<Subject> subjects = subject.getAll();
		SubAttendanceDaoImpl adaAttendance = new SubAttendanceDaoImpl();
		ArrayList<String> columnList = adaAttendance.getAllColumns("ada");
		columnCount = columnList.size() - 3;
		SubAttendance attendanceAda = adaAttendance.getRecord("ADA",sid);
		SubAttendance attendanceSe = adaAttendance.getRecord("SE",sid);
		SubAttendance attendanceJava = adaAttendance.getRecord("JAVA",sid);
		System.out.println(subjects.get(0).getSubID() + subjects.get(0).getSubName() + attendanceAda.getSum() + "30");
		model.addRow(new Object[]{subjects.get(0).getSubID(),subjects.get(0).getSubName(),attendanceAda.getSum(),columnCount});
		model.addRow(new Object[]{subjects.get(1).getSubID(),subjects.get(1).getSubName(),attendanceSe.getSum(),columnCount});
		model.addRow(new Object[]{subjects.get(2).getSubID(),subjects.get(2).getSubName(),attendanceJava.getSum(),columnCount});
	}
	public void closeAll() throws SQLException{
		pstmt.close();
		rs.close();
		con.close();
	}
	
	public void getLoginForm(){
		frame = new JFrame("Student Attendance");
		
		//Initialising labels
		sName = new JLabel("NAME:");
		sEnrollNo = new JLabel("ENROLL NO:");
		sClass = new JLabel("CLASS:");
		sAverage = new JLabel("AVERAGE:");
		sPercentage = new JLabel("PERCENTAGE:");
		sNameValue = new JLabel(name);
		sEnrollNoValue = new JLabel(sid);
		sClassValue = new JLabel(className);
		sAverageValue = new JLabel(String.valueOf(average));
		sPercentageValue = new JLabel(String.valueOf(percentage));
		
		Icon icon = new ImageIcon("Images/stuhead.jpg");
		headImage = new JLabel(icon);
		
		//Initialising table
		model = new DefaultTableModel();
		sTable = new JTable(model);
		
		//Initialising Button
		subjectWiseButton = new JButton("Graphical");
		logOutButton = new JButton("Logout");
		
		//Initialising panel
		tablePanel = new JPanel(new BorderLayout());
		headPanel = new JPanel();
		
		//Setting frame properties
		frame.setBounds(180, 20, 1000, 700);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		
		//Setting bounds of Label
		sName.setBounds(20, 200, 100, 20);
		sNameValue.setBounds(120, 200, 200, 20);
		sEnrollNo.setBounds(20, 230, 100, 20);
		sEnrollNoValue.setBounds(120, 230, 200, 20);
		sClass.setBounds(20, 260 , 100 , 20);
		sClassValue.setBounds(120, 260, 200, 20);
		sAverage.setBounds(20, 290, 100, 20);
		sAverageValue.setBounds(120, 290, 200, 20);
		sPercentage.setBounds(20, 320, 100, 20);
		sPercentageValue.setBounds(120, 320, 100, 20);
		headImage.setBounds(0,0,963,170);
		
		//setting bounds of table
		tablePanel.setBounds(20,380,960,250);
		JScrollPane js = new JScrollPane(sTable);
		tablePanel.add(js,BorderLayout.CENTER);
		headPanel.setBounds(10, 10, 973, 120);
		headPanel.add(headImage);
		
		//setting bounds of button
		subjectWiseButton.setBounds(800,280,150,50);
		//subjectWiseButton.setBackground(Color.lightGray);
		logOutButton.setBounds(825,200,100,40);
		
		//Adding components to frame
		frame.add(sEnrollNo);
		frame.add(sEnrollNoValue);
		frame.add(sName);
		frame.add(sNameValue);
		frame.add(sClass);
		frame.add(sClassValue);
		frame.add(sAverage);
		frame.add(sAverageValue);
		frame.add(sPercentage);
		frame.add(sPercentageValue);
		frame.add(tablePanel);
		frame.add(subjectWiseButton);
		frame.add(logOutButton);
		frame.add(headPanel);
		subjectWiseButton.addActionListener(this);
		logOutButton.addActionListener(this);
		try {
			getTabel(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getAverage();
		System.out.println(average + " " + percentage);
		if(percentage < 75){
			sPercentageValue.setForeground(Color.RED);
			sAverageValue.setForeground(Color.RED);
		}
		sAverageValue.setText(String.valueOf(average));
		sPercentageValue.setText(String.valueOf(percentage + "%"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == subjectWiseButton){
			//frame.dispose();
			SubjectWise chart = new SubjectWise(sid,"Student Attendance", 
			         "Attendance(%)",columnCount);
			      /*chart.pack( );        
			      RefineryUtilities.centerFrameOnScreen( chart );        
			      chart.setVisible( true );*/
		}
		if(e.getSource() == logOutButton){
			JOptionPane.showMessageDialog(frame, "Successfully logout!");
			frame.dispose();
			LoginForm1 form = new LoginForm1();
			form.getLoginForm();
			LoginForm1.basicFrame.setVisible(true);
		}
	}
	public void getAverage(){
		int count = sTable.getRowCount();
		float sum = 0,total = 0;
		for(int i = 0;i<count;i++){
			sum += Float.parseFloat(sTable.getValueAt(i, 2).toString());
			total += Float.parseFloat(sTable.getValueAt(i, 3).toString());
		}
		average = sum/count;
		percentage = (sum/total) * 100;
	}
}
