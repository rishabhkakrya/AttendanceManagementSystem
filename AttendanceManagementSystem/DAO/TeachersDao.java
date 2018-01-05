package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Teachers;

public interface TeachersDao {
	public int submit(Teachers teachers) throws SQLException;
	public ResultSet view() throws SQLException;
	public int modify(String id,Teachers teachers) throws SQLException;
	public Teachers search(String tID) throws SQLException;
	public int delete(String tID) throws SQLException;
	public boolean findTeacher(String tID,String tPassword) throws SQLException;
	public Teachers getRecord(String tID) throws SQLException;
}
