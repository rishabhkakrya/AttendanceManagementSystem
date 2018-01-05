package GUI;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import Bean.SubAttendance;
import DAO.SubAttendanceDaoImpl; 

public class BarChart_AWT  {
	JFrame jf; 
	public static String title = null;
	public static String branch = null;
	public static String sub = null;
	int total;
	public BarChart_AWT( String applicationTitle , String chartTitle,String sub, String branch,int total ) throws SQLException {       
	  jf = new JFrame("Graphical Representation");
      
      jf.setBounds(400, 250, 560, 367);
      this.sub = sub;
      this.branch = branch;
      this.total = total;
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Students",            
         "Attendance",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
      
      
      System.out.println(sub);
		System.out.println(branch);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      jf.setLayout(new BorderLayout());
      jf.add(chartPanel,BorderLayout.CENTER);
      jf.setVisible(true);
   }
   
   private CategoryDataset createDataset( ) throws SQLException {
           
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      
      try {
          SubAttendanceDaoImpl attendance = new SubAttendanceDaoImpl();
    	  ArrayList<SubAttendance> list = new ArrayList<SubAttendance>();
    	  list = attendance.getAllRecords(sub, branch);
          
    	  for(int i = 0;i<list.size();i++){
    		  String sName = list.get(i).getsName();
              float sAttendance = ((float)list.get(i).getSum()/total) * 100;
              dataset.addValue(sAttendance,sName,sName);
    	  }
          
  }
   catch (Exception i)
   {
System.out.println(i);
   }
                 
      return dataset; 
   }
   
   
}
