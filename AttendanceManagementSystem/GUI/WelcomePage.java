package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class WelcomePage extends Thread {
	 	JLabel wel;
	    JLabel wel2;
	    JLabel wel3;
	    JLabel mem1;
	    JFrame jf;
	    JLabel logoLabel;
	    Icon logo ;
	    Font f=new Font("Arial",Font.BOLD,20); 
	    Font f1=new Font("Times new Roman",Font.BOLD,22);
	       
	    boolean stop = true;
		public void run() {    
		   
		while(stop) {       
		    jf = new JFrame();
		    jf.setBounds(180, 20, 1000, 700);
		    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    jf.setTitle("Images/Welcome");
		    jf.setLayout(null);
		 
		    logo = new ImageIcon("Images/welcome.jpg");

		    logoLabel = new JLabel(logo);
		    jf.setContentPane(logoLabel);
		 
		    mem1= new JLabel("\u00a9 Rishabh, Kanak, Prashant");
		    mem1.setBounds(700,650,300, 30);
		    mem1.setFont(f1);
		    
		    
		    jf.setResizable(false);
		    jf.add(mem1);
		    //jf.add(logoLabel);
		    jf.setUndecorated(true);
		    
		    jf.setVisible(true);
		
		    for(int i=0;i<5;i++) {
		        	try {
		        		System.out.println("Count  "+i);
		        		Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
		        	
		    }
		    stop = false;
		        
		}
		    
		System.out.println("OUT");
		jf.dispose();
		LoginForm1 ob = new LoginForm1();
		ob.getLoginForm();
		LoginForm1.basicFrame.setVisible(true);
			
} 
			

		
}



