package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Bean.SubAttendance;

public interface SubAttendanceDao {
	public int generateCurrentAttendanceCol(String str,String sub) throws SQLException;
	public int updateTotalAttendance(String sub) throws SQLException;
	public int insertAttendance(String str, String sub, String sid,String status) throws SQLException;
	public boolean checkColumn(String str, String sub) throws SQLException;
	public ArrayList<String> getAllColumns(String sub) throws SQLException;
	public SubAttendance getRecord(String sub,String sid) throws SQLException;
	public ArrayList<SubAttendance> getAllRecords(String sub,String branch) throws SQLException;
	public int countColumns(String sub) throws SQLException;
}
