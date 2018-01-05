package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import Bean.SubAttendance;
import DAO.SubAttendanceDaoImpl;

public class SubjectWise {
	JFrame jf;
	JButton logout, Overall;
	JPanel cPanel,titlePanel,buttonPanel;
	JLabel cName;
	String id;
	Connection con; 
	int total;
	public SubjectWise( String id,String applicationTitle , String chartTitle,int total ) {        
	      jf = new JFrame("Graphical Representation");
	      
	      jf.setBounds(400, 250, 560, 367);
	      this.id = id;
	      this.total = total;
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Subjects",            
	         "Attendance",            
	         createDataset(),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      jf.setLayout(new BorderLayout());
	      jf.add(chartPanel,BorderLayout.CENTER);
	      jf.setVisible(true);
	   }
	
	private CategoryDataset createDataset( ) {
	      final String ada = "ADA";        
	      final String se = "SE";        
	      final String java = "Java";                
	              
	      final DefaultCategoryDataset dataset = 
	      new DefaultCategoryDataset( );  
	      try{
	    	  
	    	  SubAttendance attendance1 =  new SubAttendance();
	    	  SubAttendance attendance2 =  new SubAttendance();
	    	  SubAttendance attendance3 =  new SubAttendance();
	    	  SubAttendanceDaoImpl attendance = new SubAttendanceDaoImpl();
	    	  attendance1 = attendance.getRecord(ada, id);
	    	  attendance2 = attendance.getRecord(se, id);
	    	  attendance3 = attendance.getRecord(java, id);
	    	  dataset.addValue( ((float)attendance1.getSum()/total)*100 , ada, ada );                    

    		  dataset.addValue( ((float)attendance2.getSum()/total)*100 , se , se );        
	      

    		  dataset.addValue( ((float)attendance3.getSum()/total)*100 , java , java );
	      } 
	      catch(SQLException e){
	    	  System.out.println(e);
	      }
	              
	      
	      return dataset; 
	   }
	
	
}
