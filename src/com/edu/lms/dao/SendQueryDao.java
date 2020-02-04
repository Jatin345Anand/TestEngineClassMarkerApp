package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.dto.SendQueryDTO;
import com.edu.lms.dto.courseDTO;
import com.edu.lms.utils.CommonDAO;

public class SendQueryDao {
	public String getEmail(int id) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL query_CRUD(?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "GETEMAIL");
		stmt.setInt(2, 0);		//queryid
		stmt.setInt(3, id);	//userid
		stmt.setString(4, "");	//subject
		stmt.setString(5, "");	//msg
		stmt.setString(6, "");	//reply
		
		ResultSet rs = stmt.executeQuery();
		String result="";
		while(rs.next()){
			result=rs.getString(1);
		}
		
		
		return result;
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
	public int insert(SendQueryDTO object) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL query_CRUD(?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "INSERT");
		stmt.setInt(2, 0);		//queryid
		stmt.setInt(3, object.getUserId());	//userid
		stmt.setString(4, object.getSubject());	//subject
		stmt.setString(5, object.getMsg());	//msg
		stmt.setString(6, "");	//reply
		
		ResultSet rs = stmt.executeQuery();
		int result=0;
		while(rs.next()){
			result=rs.getInt(1);
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
