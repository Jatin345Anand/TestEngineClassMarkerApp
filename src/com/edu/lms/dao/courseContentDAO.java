package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.dto.courseContentDTO;
import com.edu.lms.dto.courseDTO;
import com.edu.lms.utils.CommonDAO;

public class courseContentDAO {
	public ResultSet selected(int id) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Content_CRUD(?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECTED");
		stmt.setInt(2, 0);
		stmt.setInt(3, id);
		stmt.setString(4, "");
		stmt.setString(5, "");
		stmt.setString(6, "");
		System.out.println("content dao stmt"+stmt.toString());
		
		ResultSet rs2 = stmt.executeQuery();
		//con.close();
		System.out.println("content dao result"+rs2.toString());
		
		return rs2;
	}
	
	public ResultSet select() throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Course_CRUD(?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECT");
		stmt.setInt(2, 0);
		stmt.setString(3, "");
		stmt.setString(4, "");
		stmt.setString(5, "");
		System.out.println("course dao stmt"+stmt.toString());
		
		ResultSet rs2 = stmt.executeQuery();
		//con.close();
		System.out.println("course dao result"+rs2.toString());
		
		return rs2;
	}
	public int insert(courseContentDTO course) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Content_CRUD(?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "INSERT");
		stmt.setInt(2, 0);
		stmt.setInt(3,course.getCourseID() );
		stmt.setString(4, course.getTitle());
		stmt.setString(5, course.getDetails());
		stmt.setString(6, course.getFilePath());
		
		ResultSet rs = stmt.executeQuery();
		int result=0;
		while(rs.next()){
			result=rs.getInt(1);
		}
		return result;
	}
	public boolean update(courseContentDTO course) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Course_CRUD(?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "UPDATE");
		stmt.setInt(2, course.getCourseID());
		stmt.setString(3, course.getTitle());
		stmt.setString(4, course.getDetails());
		stmt.setString(5, course.getFilePath());
		boolean rs = stmt.execute();
		return rs;
	}
	public boolean delete(courseContentDTO course) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL Course_CRUD(?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "DELETE");
		stmt.setInt(2, course.getCourseID());
		stmt.setString(3, course.getTitle());
		stmt.setString(4, course.getDetails());
		stmt.setString(5, course.getFilePath());
		boolean rs = stmt.execute();
		
		return rs;
	}
}
