package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Students;
import Bean.Teachers;

public interface StudentsDao {
	public int submit(Students students) throws SQLException;
	public ResultSet view() throws SQLException;
	public int modify(String id,Students students) throws SQLException;
	public Students search(String sID) throws SQLException;
	public int delete(String sID) throws SQLException;
	public boolean findStudent(String sID,String sPassword) throws SQLException;
	public Students getRecord(String sID) throws SQLException;
}
