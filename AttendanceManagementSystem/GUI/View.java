package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.ui.RefineryUtilities;

import Bean.SubAttendance;
import DAO.SubAttendanceDaoImpl;
import Main.Main;

public class View implements ItemListener,ActionListener {
	JFrame vFrame;
	JPanel navBarTop;
	JPanel panel;
	JPanel buttonPanel;
	JPanel tablePanel; 
	JLabel bpit;
	JComboBox subCombo;
	JComboBox branchCombo;
	JButton gButton;
	JButton backButton;
	JButton logoutButton;
	JButton viewButton;
	JTable viewTable;
	Icon img;
	DefaultTableModel model;
	String id,name;
	public String s, sub, branch;
	int columnCount;
	
	public View(String id,String name) {
		this.id = id;
		this.name = name;
		initcomponents();
		
	}
	
	public void initcomponents() {
		
		 	vFrame =  new JFrame();
	        vFrame.setBounds(180,20,1000,700); 
	        vFrame.getContentPane().setBackground(java.awt.SystemColor.window);
	        vFrame.setLayout(null);
	        vFrame.setTitle("VIEW ATTENDANCE");
	      
	        
	        Font f = new Font("Arial",Font.BOLD,20);
	        Font f1 = new Font("Arial",Font.BOLD,10);
	        
	        img = new ImageIcon("Images/stuhead_resize.jpg");
	        
	        navBarTop= new JPanel();
	        navBarTop.setLayout(null);
	        navBarTop.setBounds(10,10,963,70);
	        navBarTop.setBackground(SystemColor.window.controlHighlight);
	        
	        panel = new JPanel();
	        panel.setBounds(10,90 ,963, 560);
	        panel.setLayout(null);
	        panel.setBackground(SystemColor.window.controlHighlight);
	      
	        buttonPanel = new JPanel();
	        buttonPanel.setBounds(10, 10, 943, 70);
	        buttonPanel.setLayout(null);
	        
	        tablePanel = new JPanel(new BorderLayout());
	        tablePanel.setBounds(10, 90, 943, 450);
	        
	        
	        //Label background;
	        bpit = new JLabel(img);
	        bpit.setBounds(0,0,963,70);
	        
	        
	        final String branch[] = {"Class","CSE","IT","ECE","EEE"};
	        branchCombo = new JComboBox(branch);
	        branchCombo.setBounds(20, 20, 100, 30);
	    	buttonPanel.add(branchCombo);
	        
	    	final String sub[] = {"Subject","ADA","SE","JAVA"};
		    subCombo = new JComboBox(sub);
		    subCombo.setBounds(140, 20, 100, 30);
		    buttonPanel.add(subCombo);
		        
	    	
	    	
	    	viewButton = new JButton("Table View");
	        viewButton.setBounds(300,20,150,30);
	        gButton = new JButton("Graphical View");
	        gButton.setBounds(450,20,150,30);
	        
	        
	        backButton = new JButton("BACK");
	        backButton.setBounds(700, 20, 100, 30);
	        
	        logoutButton = new JButton("LOGOUT");
	        logoutButton.setBounds(810,20,100,30);
	        
	        
	        branchCombo.addItemListener(this);
	        subCombo.addItemListener(this);
	        backButton.addActionListener(this);
	        logoutButton.addActionListener(this);
	        viewButton.addActionListener(this);
	        gButton.addActionListener(this);
	      
	        model = new DefaultTableModel();
	        	        
	        viewTable = new JTable(model);
	        
	        
	        JScrollPane js = new JScrollPane(viewTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	       
	        navBarTop.add(bpit);
	        buttonPanel.add(subCombo);
	        buttonPanel.add(gButton);
	        buttonPanel.add(viewButton);
	        buttonPanel.add(backButton);
	        buttonPanel.add(logoutButton);
	        
	        panel.add(buttonPanel);
	        tablePanel.add(js,BorderLayout.CENTER);
	        panel.add(tablePanel);
	        vFrame.add(navBarTop);
	        vFrame.add(panel);
	        vFrame.setVisible(true);
	        
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource() == branchCombo ) {
			s = branchCombo.getSelectedItem().toString();
			branch = s.substring(0, 2);
			System.out.println("Combo Selection event ; "+ branch);
		}
	
		
		if(e.getSource() == subCombo) {
			sub = subCombo.getSelectedItem().toString();			
			System.out.println("Subject combo selection event "+sub);
		}
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			vFrame.dispose();
			System.out.println("Back Button");
			new Teacher(id,name," ");
		}
		else if(e.getSource() == logoutButton) {
			vFrame.dispose();
			LoginForm1 form = new LoginForm1();
			form.getLoginForm();
			LoginForm1.basicFrame.setVisible(true);
		}	
		else if(e.getSource() == viewButton) {
			DefaultTableModel model_info=(DefaultTableModel)viewTable.getModel();
			model_info.setColumnCount(0);
			while(model_info.getRowCount() > 0)
			{
			    model_info.removeRow(0);
			}
			try {
				SubAttendanceDaoImpl attendance = new SubAttendanceDaoImpl();
		        ArrayList<String> columnList = attendance.getAllColumns(sub);
		        columnCount = columnList.size() - 3;
		        for(int i = 0;i<columnList.size();i++){
		        	model.addColumn(columnList.get(i));
		        }
		        int count = attendance.countColumns(sub);
		        PreparedStatement statement = Main.getCon().prepareStatement("select * from "+sub+" where SID LIKE 'S"+branch+"%'");
			    ResultSet rs = statement.executeQuery();
			    while(rs.next()){
			    	Vector<Object> list = new Vector<Object>();
			    	for(int i = 1;i<=count;i++){
			    		if(i == 3){
			    			list.add(rs.getInt(i));
			    		}
			    		else
			    			list.add(rs.getString(i));
			    	}
			    	model.addRow(list);
			    }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource() == gButton) {
			System.out.println("Hello");
			BarChart_AWT demo;
			try {
				demo = new BarChart_AWT("overall","ATTENDANCE", sub, branch,columnCount);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		}	

		
		
	}	
	
	
	
		
		
}


