package DAO;

import java.sql.SQLException;

import Bean.StudentClass;

public interface StudentClassDao {
	public int add(StudentClass studentClass) throws SQLException;
}
