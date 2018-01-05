package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	public Connection getDBConnection() throws ClassNotFoundException, SQLException{
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "system";
		String pwd = "system";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url,user,pwd);
	}
}
