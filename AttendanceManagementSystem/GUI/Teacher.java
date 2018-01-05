package GUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;

public class Teacher implements ItemListener {
			JFrame tFrame;
			JPanel navBarTop;
			JPanel navBarRight;
			JLabel tName;
			JLabel tId;
			JLabel bpit;
			JLabel tEmail;
			JLabel background;
			JLabel imgLabel;
			JButton view, mark,logOutButton;
			JComboBox tClass;
			JComboBox tSubject;
			String name,id,Email;
			public Teacher(String id,String name,String Email) {           
				this.id = id;
				this.name = name;
				this.Email = Email;
				initcomponents();
    
			}
			public Teacher(){
				initcomponents();
			}
public void initcomponents() {
    
    
			tFrame =  new JFrame();
			tFrame.setBounds(180,20,1000,700); 
			tFrame.getContentPane().setBackground(Color.DARK_GRAY);
			tFrame.setLayout(null);
			tFrame.setTitle("TEACHER ACCOUNT");
			tFrame.setResizable(false);
    
			
			
			navBarTop= new JPanel();
			navBarTop.setLayout(null);
			navBarTop.setBounds(0,0,1000,80);
			navBarTop.setBackground(java.awt.SystemColor.activeCaption);
			
			
			
			
			navBarRight = new JPanel();
			navBarRight.setLayout(null);
			navBarRight.setBounds(0,80,1000,700);
			navBarRight.setBackground(java.awt.SystemColor.controlLtHighlight);
			
			bpit = new JLabel("BHAGWAN PARSHURAM INSTITUTE OF TECHNOLOGY",SwingConstants.CENTER);
			tName = new JLabel(name.toUpperCase(),SwingConstants.CENTER);
			tEmail = new JLabel(Email,SwingConstants.CENTER);
			tId = new JLabel(id.toUpperCase(),SwingConstants.CENTER);
			
			
			////////// combobox //////////
			
			tClass = new JComboBox();
			tClass.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"CSE","IT","ECE","EEE"}));
			
			tSubject = new JComboBox();
			
            
			////////// Button //////////
    
			view = new JButton("VIEW");
			mark = new JButton("MARK");
			logOutButton = new JButton("Logout");
			
			////// Button Bounds ////// 
			view.setBounds(450,300,100,30);
			mark.setBounds(450,350,100,30);
			tClass.setBounds(400,180,200,40);
			logOutButton.setBounds(450,400, 100,30);
    
            
			
    
			Font f = new Font("Arial",Font.BOLD,20);
			Font lf = new Font("Arial",Font.BOLD,20);
			
			bpit.setFont(f);
			tName.setFont(lf);
			tId.setFont(lf);
			tEmail.setFont(lf);
    
			 
			tFrame.add(navBarTop);
			tFrame.add(navBarRight);
			
			navBarTop.add(bpit);
			
			navBarRight.add(tName);
			navBarRight.add(tEmail);
			navBarRight.add(view);
			navBarRight.add(mark);
			navBarRight.add(logOutButton);
			navBarRight.add(tId);
			
			tName.setBackground(Color.DARK_GRAY);
			tEmail.setBackground(Color.LIGHT_GRAY);
    
			bpit.setBounds(10,10,1000,20);
			tName.setBounds(400,30,200,40);
			tId.setBounds(400,70,200, 70);
			tFrame.setVisible(true);
			
			
    
			mark.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					try {
						markmouseClicked(evt);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}    
			});
			
			view.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					try {
						viewmouseClicked(evt);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}    
			});
			
			logOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					JOptionPane.showMessageDialog(tFrame, "Successfully logout!");
					tFrame.dispose();
					LoginForm1 form = new LoginForm1();
					form.getLoginForm();
					LoginForm1.basicFrame.setVisible(true);
				}    
			});
			
			tClass.addItemListener(this);
			

}

 
 
 private void markmouseClicked(java.awt.event.MouseEvent evt) throws ClassNotFoundException, SQLException {
     		  	
     			tFrame.dispose();
     			Mark m = new Mark(id,name);
     			Thread t1 = new Thread(m);
     			t1.start();
     			
}
 private void viewmouseClicked(java.awt.event.MouseEvent evt) throws ClassNotFoundException, SQLException {
	  	
		tFrame.dispose();
		new View(id,name);
		
}

@Override
public void itemStateChanged(ItemEvent arg0) {
	

	
}
}
