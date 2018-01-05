package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Bean.SubAttendance;
import Main.Main;

public class SubAttendanceDaoImpl implements SubAttendanceDao{
	
	@Override
	public int generateCurrentAttendanceCol(String str, String sub) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("alter table " + sub + " add " + str + " varchar(1)");
		st.executeUpdate();
		st = Main.getCon().prepareStatement("update " + sub + " set " + str + " = 'A'");
		int row = st.executeUpdate();
		return row;
	}

	@Override
	public int updateTotalAttendance(String sub) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from " + sub);
		ResultSet rs = st.executeQuery();
		ResultSetMetaData rsmt = rs.getMetaData();
		int count = rsmt.getColumnCount();
		int j = 1,rows = 0;
		while(rs.next()){
			int sum = 0;
			for(int i = 4;i<=count;i++){
				if(rs.getString(i).equals("P"))
					sum += 1;
			}
			st = Main.getCon().prepareStatement("Update " + sub + " set sum = " + sum + " where sid = ?");
			st.setString(1, rs.getString(1));
			rows = st.executeUpdate();
		}
		return rows;
	}

	public int insertAttendance(String str, String sub, String sid,String status) throws SQLException {
		PreparedStatement st = Main.getCon().prepareStatement("Update " + sub + " set " + str + " = ? where SID=?");
		st.setString(1,status);
		st.setString(2, sid);
		int rows = st.executeUpdate();		
		return rows;
	}

	@Override
	public boolean checkColumn(String str, String sub) throws SQLException {
		boolean flag = false;
		PreparedStatement st = Main.getCon().prepareStatement("select * from " + sub);
		ResultSet rs = st.executeQuery();
		ResultSetMetaData rsmt = rs.getMetaData();
		int count = rsmt.getColumnCount();
		for(int i = 1;i<=count;i++){
			String name = new String(rsmt.getColumnLabel(i));
			if(name.equalsIgnoreCase(str))
				flag = true;
		}
		return flag;
	}

	@Override
	public SubAttendance getRecord(String sub,String sid) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from " + sub + " where sid = ?");
		st.setString(1, sid);
		ResultSet rs = st.executeQuery();
		SubAttendance adaAttendance = new SubAttendance();
		while(rs.next()){
			adaAttendance.setsId(rs.getString(1));
			adaAttendance.setsName(rs.getString(2));
			adaAttendance.setSum(rs.getInt(3));
			adaAttendance.setCurrentAttendance("A");
		}
		return adaAttendance;
	}

	@Override
	public ArrayList<SubAttendance> getAllRecords(String sub,String branch) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from "+sub+" where SID LIKE 'S"+branch+"%'");
		ResultSet rs = st.executeQuery();
		ArrayList<SubAttendance> attendanceList = new ArrayList<SubAttendance>();
		while(rs.next()){
			SubAttendance adaAttendance = new SubAttendance();
			adaAttendance.setsId(rs.getString(1));
			adaAttendance.setsName(rs.getString(2));
			adaAttendance.setSum(rs.getInt(3));
			adaAttendance.setCurrentAttendance("A");
			attendanceList.add(adaAttendance);
		}
		return attendanceList;
	}


	@Override
	public ArrayList<String> getAllColumns(String sub) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = Main.getCon().prepareStatement("select * from " + sub );
		ResultSet rs = st.executeQuery();
		ResultSetMetaData rsmt = rs.getMetaData();
		int count = rsmt.getColumnCount();
		ArrayList<String> columnList = new ArrayList<String>();
		for(int i = 1;i<=count;i++){
			columnList.add(rsmt.getColumnLabel(i));
		}
		return columnList;
	}

	@Override
	public int countColumns(String sub) throws SQLException {
		PreparedStatement st = Main.getCon().prepareStatement("select * from " + sub);
		ResultSet rs = st.executeQuery();
		ResultSetMetaData rsmt = rs.getMetaData();
		int count = rsmt.getColumnCount();
		return count;
	}
}
