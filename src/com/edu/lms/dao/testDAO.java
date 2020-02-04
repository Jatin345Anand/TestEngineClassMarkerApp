package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.dto.courseDTO;
import com.edu.lms.dto.testDTO;
import com.edu.lms.dto.testQuestionDTO;
import com.edu.lms.utils.CommonDAO;

public class testDAO {
	
	public ResultSet getTest(int id) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Tests_CRUD(?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECTEST");
		stmt.setInt(2, 0);
		stmt.setInt(3, 0);
		stmt.setInt(4, id);
		stmt.setString(5, "");
		stmt.setInt(6, 0);
		stmt.setInt(7, 0);
		stmt.setInt(8, 0);
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public ResultSet selectedTest(int id) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Tests_CRUD(?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECTED");
		stmt.setInt(2, id);
		stmt.setInt(3, 0);
		stmt.setInt(4, 0);
		stmt.setString(5, "");
		stmt.setInt(6, 0);
		stmt.setInt(7, 0);
		stmt.setInt(8, 0);
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public ResultSet select() throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Tests_CRUD(?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECT");
		stmt.setInt(2, 0);
		stmt.setInt(3, 0);
		stmt.setInt(4, 0);
		stmt.setString(5, "");
		stmt.setInt(6, 0);
		stmt.setInt(7, 0);
		stmt.setInt(8, 0);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	public boolean insert(testDTO test) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Tests_CRUD(?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "INSERT");
		//stmt.setInt(2, test.getTestid());
		//stmt.setInt(3, test.getFacultyID());
		stmt.setInt(2, 0);		//testID
		stmt.setInt(3, test.getFacultyID());	//facultyid
		stmt.setInt(4, 17); //courseid
		stmt.setString(5, test.getTestName());
		stmt.setInt(6, test.getTestDuration());
		stmt.setInt(7, test.getMinMarks());
		stmt.setInt(8, test.getTotalMarks());
		
		
		/*
		stmt.setInt(2, 0);
		stmt.setInt(3, 0);
		stmt.setInt(4, 0);
		stmt.setString(5, "");
		stmt.setInt(6, 0);
		stmt.setInt(7, 0);
		stmt.setInt(8, 0);
		*/
		
		ResultSet rs=stmt.executeQuery();
		int id=0;
		if(rs.next()){
			id=rs.getInt(1);
		}
		boolean result =false;
		if(id>0){
			result=true;
		testQuestionDAO qdao=new testQuestionDAO();
		for (testQuestionDTO question : test.getQuestionList()) {
			qdao.insert(question,id);	
		}
		}
		
		
		return result;
	}
	public boolean update(courseDTO course) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Course_CRUD(?,?,?,?,?)}";
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
		
		String query = "{CALL Course_CRUD(?,?,?,?,?)}";
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
