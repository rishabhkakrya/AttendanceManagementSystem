package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import Bean.SubAttendance;
import DAO.SubAttendanceDaoImpl;

import javax.swing.*;

public class Mark extends Thread implements ItemListener,ActionListener{
    JFrame markFrame;
       	JPanel navBarTop, panelleft,panelright,tablePanel;
        JLabel bpit, pname, psem, pyear,pbranch,pid, label1, label2,dateLabel;
        JTable markTable; 
        JButton button1, button2, button3, okButton,backButton;
        JComboBox combobox1, combobox2, combobox3, ssem,syear,sbranch; 
        JTextField sname,sid;
        Date date;
        boolean stop = true;
        Connection con;
        PreparedStatement st;
        ResultSet rs;        
        MyTableModel model;
        String id,name;
        public Mark(String id,String name) throws ClassNotFoundException, SQLException {           
        	this.id = id;
        	this.name = name;
        	initcomponents();
        
        }
    
    public void initcomponents() throws ClassNotFoundException, SQLException {
    
        markFrame = new JFrame();
        markFrame.setBounds(180,20,1000,700); 
        markFrame.getContentPane().setBackground(java.awt.SystemColor.window);
        markFrame.setLayout(null);
        markFrame.setTitle("MARK ATTENDANCE");
      
        
        Font f = new Font("Arial",Font.BOLD,20);
        Font f1 = new Font("Arial",Font.BOLD,10);
        
        
        
        navBarTop= new JPanel();
        navBarTop.setLayout(null);
        navBarTop.setBounds(10,10,963,70);
        navBarTop.setBackground(SystemColor.window.controlHighlight);
        
        panelleft = new JPanel();
        panelleft.setBounds(10,90 ,963, 560);
        panelleft.setLayout(null);
        panelleft.setBackground(SystemColor.window.controlHighlight);
        
        panelright = new JPanel();
        panelright.setBounds(480,30,470,500);
        panelright.setLayout(null);
        panelright.setBackground(SystemColor.window.controlLtHighlight );
        
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBounds(10, 30, 460, 500);
        
        bpit = new JLabel("BPIT",SwingConstants.CENTER);
        bpit.setText("BHAGWAN PARSHURAM INSTITUTE OF TECHNOLOGY");
        bpit.setFont(f);
        bpit.setBounds(10,10,963,70);
        
        
        model = new MyTableModel();
        
       
        markTable = new JTable(model);
        JScrollPane js = new JScrollPane(markTable);
       
        tablePanel.add(js,BorderLayout.CENTER);
        
        panelleft.add(tablePanel);
        
        markFrame.add(navBarTop);
        markFrame.add(panelleft);
        markFrame.add(panelright);
        
               
    	/////////////Date////////////////
    	
    	date = new Date();
    	dateLabel = new JLabel(date.toString());
    	dateLabel.setBounds(300, 10, 200, 30);
    	panelright.add(dateLabel);
    	
    	psem = new JLabel("Subject ID");
    	psem.setBounds(20,90,100,20);
    	panelright.add(psem);

        final String sem[] = {"/","ADA","SE","JAVA"};
        ssem = new JComboBox(sem);
        ssem.setBounds(140, 90, 80, 20);
    	panelright.add(ssem);

    	
    	
    	pbranch = new JLabel("Branch");
    	pbranch.setText("Branch");
    	pbranch.setBounds(20,130,100,20);
    	panelright.add(pbranch);
    	
    	    	 	
        final String branch[] = {"/","CSE","IT","ECE","EEE"};
        sbranch = new JComboBox(branch);
        sbranch.setBounds(140, 130, 80, 20);
    	panelright.add(sbranch);
    	
    	/////////////////////////////////////
    	
    	label1 = new JLabel();
    	label1.setText("MARK ATTENDANCE");
    	label1.setBounds(20, 210, 200, 20);
    	label1.setFont(f1);
    	panelright.add(label1);
    	
    	final String item[] = {"Individual","All-Absent","All-Present"};
        combobox1 = new JComboBox(item);
        combobox1.setBounds(20, 250, 100, 20);
        panelright.add(combobox1);
        
        
        button2 = new JButton("SAVE");
        button2.setBounds(250, 250, 100, 30);
        panelright.add(button2);
      
        /////////////OK BUTTON//////////////////////

    	okButton = new JButton("OK");
    	okButton.setBounds(250,170,100,30);
    	panelright.add(okButton);
    	
    	
        /////// FOR SEARCH ///////////
        
        label2 = new JLabel();
        label2.setText("SEARCH");
        label2.setBounds(20, 290, 100, 20);
        label2.setFont(f1);
        panelright.add(label2);
        
        ///////SEARCH BUTTON//////
        button1 = new JButton("Search");
        button1.setBounds(250,420,100,30);
        panelright.add(button1);

        //////////BACK BUTTON//////
        backButton=new JButton("Back");
        backButton.setBounds(100, 420, 100, 30);
        panelright.add(backButton);
        
    	pname = new JLabel("Name");					
    	pname.setText("Name");
    	pname.setBounds(20,320,100,20);
    	panelright.add(pname);
    	
    	pid = new JLabel("Student ID");
    	pid.setText("Student ID");
    	pid.setBounds(20,360,100,20);
    	panelright.add(pid);
    	
    	
    	
    	sname = new JTextField();
    	sname.setBounds(140, 320, 150, 20);
    	panelright.add(sname);
    	
    	sid = new JTextField();
    	sid.setBounds(140, 360, 150, 20);
    	panelright.add(sid);
    	
    	       
        
        panelleft.add(panelright);
        navBarTop.add(bpit);
        markFrame.setVisible(true);
        
        

        

        combobox1.addItemListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        okButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    
       
    public void button1MouseClicked(java.awt.event.MouseEvent evt) {
    	
    	//paneldisplay.setVisible(true);
    	System.out.println("Testing");
    	
    }
    
    public void ok1MouseClicked(java.awt.event.MouseEvent evt) {
    	String m = (String) syear.getSelectedItem();
    	System.out.println("TEST!");
    	
    	switch(m) {
    		case "1" :  ssem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/", "1", "2"})); //1st way
    					 break;
    		case "2" : ssem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/", "3", "4"})); //2nd way
    					break;
    		case "3" : ssem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/", "5", "6"})); //2nd way
						break;
    		case "4" : ssem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "/", "7", "8"})); //2nd way
						break;
    		default :	System.out.println("TEST");
    					break;
    	
    	}
    
 }    
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        Mark m = new Mark("T001","Anuj Mathur");
        Thread t1 = new Thread(m);
        t1.start();
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == combobox1){
			if(combobox1.getSelectedIndex() == 1){
				for(int i = 0;i<markTable.getRowCount();i++){
					markTable.setValueAt(false, i, 3);
				}
			}
			if(combobox1.getSelectedIndex() == 2){
				for(int i = 0;i<markTable.getRowCount();i++){
					markTable.setValueAt(true, i, 3);
				}
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button1){
			for(int i = 0;i<markTable.getRowCount();i++){
				String str1 = (String) markTable.getValueAt(i,1);
				String str2 = (String) markTable.getValueAt(i,2);
				//System.out.println(str1 + str2);
				if(str1.equals(sid.getText()) && str2.equals(sname.getText()))
						markTable.setRowSelectionInterval(i, i);
			}
			
		}
		if(e.getSource() == button2){	
			String sub = ssem.getSelectedItem().toString();
			SubAttendanceDaoImpl attendance = new SubAttendanceDaoImpl();
			try {
				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				String dayDate = date.toString().substring(8);
				Calendar calendar = Calendar.getInstance();
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				//System.out.println(""+day);
				//int day = date.getDay();
				//System.out.println(day + " Date:" + date);
				int month = date.getMonth();
				//System.out.println(month);
				String str = new String();
				switch(day){
				case 2: str = "Monday";
						break;
				case 3: str = "Tuesday";
						break;
				case 4: str = "Wednesday";
						break;
				case 5: str = "Thursday";
						break;
				case 6: str = "Friday";
						break;
				case 7: str = "Saturday";
						break;
				case 1: str = "Sunday";
				break;
				}
				boolean flag = false;
				str = str + dayDate + month;
			
				//System.out.println("Date " + str);
				flag = attendance.checkColumn(str,sub);
				//System.out.println(flag);
				if(flag == false){
					attendance.generateCurrentAttendanceCol(str, sub);
					
				}
				//System.out.println(flag);
				for(int i = 0;i<markTable.getRowCount();i++){
					if(markTable.getValueAt(i,3).toString().equals("false"))
						attendance.insertAttendance(str,sub,markTable.getValueAt(i, 1).toString(),"A");
					else
						attendance.insertAttendance(str,sub,markTable.getValueAt(i, 1).toString(),"P");
				}
				attendance.updateTotalAttendance(sub);
				JOptionPane.showMessageDialog(markFrame, "Successfully saved!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
		if(e.getSource() == okButton){
			DefaultTableModel dm = (DefaultTableModel)markTable.getModel();
			while(dm.getRowCount() > 0)
			{
			    dm.removeRow(0);
			}
			String sub = ssem.getSelectedItem().toString();
			String tmp = sbranch.getSelectedItem().toString();
			String stream = tmp.substring(0, 2);
			SubAttendanceDaoImpl attendance = new SubAttendanceDaoImpl();
			try {
				ArrayList<SubAttendance> attendanceList = attendance.getAllRecords(sub, stream);
				for(int i = 0;i<attendanceList.size();i++){
					model.addRow(new Object[]{i+1,attendanceList.get(i).getsId(), attendanceList.get(i).getsName(), false});
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		if(e.getSource() == backButton){
			stop = false;
			markFrame.dispose();
			Teacher teacher = new Teacher(id,name,"");
		}
	}
	
	public void run(){
			while(stop){
				Date date = new Date();
				dateLabel.setText(date.toString());
				try{
					sleep(1000);
				}
				catch(InterruptedException e){
					System.out.println(e);
				}
			}
	}
}
class MyTableModel extends DefaultTableModel{
		public MyTableModel() {
	      super(new String[]{"SNo", "ID", "Name","Attendance"}, 0);
	    }

	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	      Class clazz = String.class;
	      switch (columnIndex) {
	        case 0:
	          clazz = Integer.class;
	          break;
	        case 3:
	          clazz = Boolean.class;
	          break;
	      }
	      return clazz;
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) {
	      return column == 3;
	    }

	    @Override
	    public void setValueAt(Object aValue, int row, int column) {
	      if (aValue instanceof Boolean && column == 3) {
	        System.out.println(aValue);
	        Vector rowData = (Vector)getDataVector().get(row);
	        rowData.set(3, (boolean)aValue);
	        fireTableCellUpdated(row, column);
	      }
	    }

}

    
        

