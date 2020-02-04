package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.utils.CommonDAO;

public class pageValueDAO {
	public ResultSet getValue() throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL pageValue()}";
		CallableStatement stmt = con.prepareCall(query);
		
		System.out.println("pageValue dao stmt"+stmt.toString());
		
		ResultSet rs2 = stmt.executeQuery();
	
		System.out.println("pageValue dao result"+rs2.toString());
		
		return rs2;
	}
}
