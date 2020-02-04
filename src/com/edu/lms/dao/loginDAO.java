package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.lms.dto.loginDTO;
import com.edu.lms.utils.CommonDAO;

public class loginDAO {
	public int authenticate(loginDTO logindto) throws ClassNotFoundException, SQLException{
		//String sql  = CommonSQLConstants.LOGIN_SQLADMIN;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//loginDTO loginDTOUser=null;
		int result=0;
		try{
			con= CommonDAO.getConnection();
			
//			String query = "{CALL UserLogin_Validate(?,?,?)}";
//			CallableStatement stmt = con.prepareCall(query);
//			stmt.setInt(1, logindto.getRoleid());
//			stmt.setString(2, logindto.getUsername());
//			stmt.setString(3, logindto.getPassword());
//			rs=stmt.executeQuery();
			pstmt = con.prepareStatement("select * from userlogin where Username=? and Password =?");
			pstmt.setString(1, logindto.getUsername());
			pstmt.setString(2, logindto.getPassword());
			rs= pstmt.executeQuery();
			System.out.println(rs.toString());
			if(rs.next()){
			result=1;
			}
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		return result;
	}

}
