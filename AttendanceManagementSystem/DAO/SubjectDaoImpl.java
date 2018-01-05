package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.DataSource;
import Bean.Subject;
import Main.Main;

public class SubjectDaoImpl implements SubjectDao {

	@Override
	public int add(Subject subject) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("insert into subject values(?,?)");
		st.setString(1, subject.getSubID());
		st.setString(2, subject.getSubName());
		int rows = st.executeUpdate();
		return rows;
		
	}

	@Override
	public ArrayList<Subject> getAll() throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from subject");
		ResultSet rs = st.executeQuery();
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		while(rs.next()){
			Subject subject = new Subject();
			subject.setSubID(rs.getString(1));
			subject.setSubName(rs.getString(2));
			subjects.add(subject);
		}
		return subjects;
	}

}
