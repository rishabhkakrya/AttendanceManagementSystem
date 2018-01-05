package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Students;
import Bean.Teachers;
import Main.Main;

public class StudentsDaoImpl implements StudentsDao {
	@Override
	public int submit(Students students) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("insert Into students values(?,?,?,?,?,?)");
		st.setString(1, students.getSid());
		st.setString(2, students.getsName());
		st.setString(3, students.getsClass());
		st.setString(4, students.getsPassword());
		st.setLong(5, students.getsPhoneno());
		st.setString(6, students.getsEmail());
		int rows = st.executeUpdate();
		return rows;
	}


	@Override
	public int modify(String id, Students students) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("Update students set sname = ?,sclass = ?,spassword = ?,sphoneno = ?,semail = ? where sid = ?");
		st.setString(1, students.getsName());
		st.setString(2, students.getsClass());
		st.setString(3, students.getsPassword());
		st.setLong(4, students.getsPhoneno());
		st.setString(5, students.getsEmail());
		st.setString(6, students.getSid());
		int rows = st.executeUpdate();
		return rows;
	}

	@Override
	public Students search(String sID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from students where sid = ?");
		st.setString(1, sID);
		ResultSet rs= st.executeQuery();
		Students students = new Students();
		if(rs.next())
		{
			students.setSid(rs.getString(1));
			students.setsName(rs.getString(2));
			students.setsClass(rs.getString(3));
			students.setsPassword(rs.getString(4));
			students.setsPhoneno(rs.getLong(5));
			students.setsEmail(rs.getString(6));
		}
		return students;
	}


	@Override
	public int delete(String sID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("delete from students where sid = ?");
		st.setString(1, sID);
		int rows = st.executeUpdate();
		return rows;
	}


	@Override
	public ResultSet view() throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from students");
		ResultSet rs = st.executeQuery();
		return rs;
	}


	@Override
	public boolean findStudent(String sID, String sPassword) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		PreparedStatement st = Main.getCon().prepareStatement("Select * from students where sid = ? and spassword = ?");
		st.setString(1, sID);
		st.setString(2, sPassword);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			flag = true;
			break;
		}
		return flag;
	}


	@Override
	public Students getRecord(String sID) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from students where sid = ?");
		st.setString(1, sID);
		ResultSet rs = st.executeQuery();
		Students students = new Students();
		while(rs.next()){
			students.setSid(rs.getString(1));
			students.setsName(rs.getString(2));
			students.setsClass(rs.getString(3));
			students.setsPassword(rs.getString(4));
			students.setsPhoneno(rs.getLong(5));
			students.setsEmail(rs.getString(6));
		}
		return students;
	}
}
