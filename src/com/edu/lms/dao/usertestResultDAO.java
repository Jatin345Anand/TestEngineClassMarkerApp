package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.dto.testDTO;
import com.edu.lms.dto.testQuestionDTO;
import com.edu.lms.dto.usertestResultDTO;
import com.edu.lms.utils.CommonDAO;

public class usertestResultDAO {
	public boolean insert(usertestResultDTO test) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL UserTestResults_CRUD(?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "INSERT");
		stmt.setInt(2, 0);		//resultID
		stmt.setInt(3, test.getTestid());	//testid
		stmt.setInt(4, test.getLoginId()); //courseid
		stmt.setInt(5,test.getMarks());
		
		ResultSet rs=stmt.executeQuery();
		int id=0;
		if(rs.next()){
			id=rs.getInt(1);
		}
		boolean result =false;
		if(id>0){
			result=true;
		}
		return result;
	}
	
	public ResultSet selectedTest(int loginId) throws ClassNotFoundException, SQLException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL UserTestResults_CRUD(?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECTED");
		stmt.setInt(2, 0);		//resultID
		stmt.setInt(3, 0);	//testid
		stmt.setInt(4, loginId); //courseid
		stmt.setInt(5,0);
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
}
