package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Teachers;
import Main.Main;

public class TeachersDaoImpl implements TeachersDao {
	@Override
	public int submit(Teachers teachers) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("insert Into teachers values(?,?,?,?)");
		st.setString(1, teachers.gettID());
		st.setString(2, teachers.gettName());
		st.setString(3, teachers.gettEmail());
		st.setString(4, teachers.gettPassword());
		int rows = st.executeUpdate();
		return rows;
	}


	@Override
	public int modify(String id, Teachers teachers) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement st = Main.getCon().prepareStatement("Update teachers set tname = ?,temail = ?,tpassword = ? where tid = ?");
		st.setString(1, teachers.gettName());
		st.setString(2, teachers.gettEmail());
		st.setString(3, teachers.gettPassword());
		st.setString(4, teachers.gettID());
		int rows = st.executeUpdate();
		return rows;
	}

	@Override
	public Teachers search(String tID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from teachers where tid = ?");
		st.setString(1, tID);
		ResultSet rs= st.executeQuery();
		Teachers teachers = new Teachers();
		if(rs.next())
		{
			teachers.settID(rs.getString(1));
			teachers.settName(rs.getString(2));
			teachers.settEmail(rs.getString(3));
			teachers.settPassword(rs.getString(4));
		}
		return teachers;
	}


	@Override
	public int delete(String tID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("delete from teachers where tid = ?");
		st.setString(1, tID);
		int rows = st.executeUpdate();
		return rows;
	}


	@Override
	public ResultSet view() throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from teachers");
		ResultSet rs = st.executeQuery();
		return rs;
	}


	@Override
	public boolean findTeacher(String tID, String tPassword) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		PreparedStatement st = Main.getCon().prepareStatement("Select * from teachers where tid = ? and tpassword = ?");
		st.setString(1, tID);
		st.setString(2, tPassword);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			flag = true;
			break;
		}
		return flag;
	}


	@Override
	public Teachers getRecord(String tID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from teachers where tid = ?");
		st.setString(1, tID);
		ResultSet rs = st.executeQuery();
		Teachers teachers = new Teachers();
		while(rs.next()){
			teachers.settID(rs.getString(1));
			teachers.settName(rs.getString(2));
			teachers.settEmail(rs.getString(3));
			teachers.settPassword(rs.getString(4));
		}
		return teachers;
	}

}
