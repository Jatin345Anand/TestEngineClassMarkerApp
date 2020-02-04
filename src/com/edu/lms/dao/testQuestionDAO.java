package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.dto.courseDTO;
import com.edu.lms.dto.testQuestionDTO;
import com.edu.lms.utils.CommonDAO;

public class testQuestionDAO {
	public ResultSet selectedTest(int id) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL TestQuestions_CRUD(?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECTED");
		stmt.setInt(2, 0);
		stmt.setInt(3, id);
		stmt.setString(4, "");
		stmt.setString(5, "");
		stmt.setString(6, "");
		stmt.setString(7, "");
		stmt.setString(8, "");
		stmt.setString(9, "");
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public ResultSet select() throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL TestQuestions_CRUD(?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECT");
		stmt.setInt(2, 0);
		stmt.setString(3, "");
		stmt.setString(4, "");
		stmt.setString(5, "");
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	public boolean insert(testQuestionDTO object,int id) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL TestQuestions_CRUD(?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "INSERT");
		stmt.setInt(2, 0);
		stmt.setInt(3, id);
		stmt.setString(4,object.getQuestion());
		stmt.setString(5,object.getOptionA());
		stmt.setString(6,object.getOptionB());
		stmt.setString(7,object.getOptionC());
		stmt.setString(8,object.getOptionD());
		stmt.setString(9,object.getAnswer());
		
		boolean rs = stmt.execute();
		return rs;
	}
	public boolean update(courseDTO course) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL TestQuestions_CRUD(?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "UPDATE");
		stmt.setInt(2, course.getCourseID());
		stmt.setString(3, course.getTitle());
		stmt.setString(4, course.getDetails());
		stmt.setString(5, course.getImagePath());
		boolean rs = stmt.execute();
		return rs;
	}
	public boolean delete(courseDTO course) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL TestQuestions_CRUD(?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "DELETE");
		stmt.setInt(2, course.getCourseID());
		stmt.setString(3, course.getTitle());
		stmt.setString(4, course.getDetails());
		stmt.setString(5, course.getImagePath());
		boolean rs = stmt.execute();
		
		return rs;
	}
}
