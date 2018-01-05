package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Bean.Subject;

public interface SubjectDao {
	public int add(Subject subject) throws SQLException;
	public ArrayList<Subject> getAll() throws SQLException;
}
