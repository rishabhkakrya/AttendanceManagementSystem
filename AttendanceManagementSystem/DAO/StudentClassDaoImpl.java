package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Bean.DataSource;
import Bean.StudentClass;
import Main.Main;

public class StudentClassDaoImpl implements StudentClassDao {

	@Override
	public int add(StudentClass studentClass) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("insert into class values(?,?)");
		st.setString(1, studentClass.getCID());
		st.setString(2, studentClass.getCName());
		int rows = st.executeUpdate();
		return rows;
	}
	
}
