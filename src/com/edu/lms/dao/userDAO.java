package com.edu.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.edu.lms.dto.loginDTO;
import com.edu.lms.dto.userDTO;
import com.edu.lms.utils.CommonDAO;

public class userDAO {
	
	public ResultSet getDetailsByEmail(String email) throws ClassNotFoundException, SQLException, ParseException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL DetailsByEmail(?)}";
		CallableStatement stmt = con.prepareCall(query);
		
		stmt.setString(1, email);
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public int changePassword(loginDTO user,String newpwd) throws ClassNotFoundException, SQLException, ParseException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL User_changepassword(?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		
		stmt.setInt(1, user.getRoleid());
		stmt.setString(2, user.getUsername());
		stmt.setString(3, user.getPassword());
		stmt.setString(4, newpwd);
		
		ResultSet rs = stmt.executeQuery();
		int result=0;
		while(rs.next()){
			result=rs.getInt(1);
		}
		return result;
	}
	
	public ResultSet selected(String id) throws ClassNotFoundException, SQLException, ParseException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL UserDetails_CRUD(?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECTED");
		stmt.setInt(2, 0);
		stmt.setString(3, id);
		stmt.setString(4, "");
		stmt.setString(5, "");
		
		 DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date dd=new java.util.Date();
		 dd.setDate(2);
		 dd.setMonth(2);
		 dd.setYear(2016);
			Date dob =  df.parse(dd.toString());
		   	
			java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
			
		stmt.setDate(6,sqlDate);
		
		stmt.setString(7, "");
		stmt.setString(8, "");
		stmt.setString(9, "");
		stmt.setString(10, "");
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	public ResultSet select() throws ClassNotFoundException, SQLException, ParseException{
		Connection con= CommonDAO.getConnection();
		
		String query = "{CALL UserDetails_CRUD(?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setString(1, "SELECT");
		stmt.setInt(2, 0);
		stmt.setString(3, "");
		stmt.setString(4, "");
		stmt.setString(5, "");
		/*DateFormat df=new SimpleDateFormat("yyyy-MM-dd");*/
		 java.util.Date dd=new java.util.Date();
/*		 dd.setDate(2);
		 dd.setMonth(2);
		 dd.setYear(2016);
			Date dob =  df.parse(dd.toString());*/
		   	
			java.sql.Date sqlDate = new java.sql.Date(dd.getTime());
			
		stmt.setDate(6,sqlDate);
		/*stmt.setString(6, "");*/
		stmt.setString(7, "");
		stmt.setString(8, "");
		stmt.setString(9, "");
		stmt.setString(10, "");
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	public static ResultSet register(userDTO uDTO) throws ClassNotFoundException,SQLException{
		ResultSet isFound=null; 
		try{
	   	  Connection con=CommonDAO.getConnection();
	   	  	CallableStatement csetmt=con.prepareCall("{call UserLogin_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
	         /* PreparedStatement pstmt = con.prepareStatement("insert into registration_master(first_name,last_name,date_of_birth,user_name,password,re_enter_password) values(?,?,?,?,?,?)");*/
	   	csetmt.setString(1, uDTO.getUserName());
	   	
	   	System.out.println("in dao user name :"+uDTO.getName());
	   	csetmt.setInt(2, uDTO.getRoleID());
	   	
	   	csetmt.setString(3, uDTO.getPassword());
	   	csetmt.setString(4, uDTO.getEmail());
	   	csetmt.setString(5, uDTO.getName());
	   	csetmt.setString(6, uDTO.getGender());
	   	
	   	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   	
	   	//
	  DateFormat df=new SimpleDateFormat("YYYY-MM-DD");
		Date age =  df.parse(uDTO.getDOB());
	   	
		java.sql.Date sqlDate = new java.sql.Date(age.getTime());
		
		csetmt.setDate(7,sqlDate);
	   	//
	   	//csetmt.setDate(7,(Date)sdf.parse(uDTO.getDOB()));
	   	
	   	
	   	
	   	csetmt.setString(8, uDTO.getAddress());
	   	csetmt.setString(9, uDTO.getPhone());
	   	csetmt.setString(10, uDTO.getImagePath());
	   	csetmt.setString(11, uDTO.getInstitute_name());
	   	
	   	System.out.println("register query is"+csetmt.toString());
	   	
	   	isFound=csetmt.executeQuery();
			 //con.close();
	      }
		 catch(Exception e)
	     {
	   	 e.printStackTrace(); 
	     }
		 return isFound;
	}
	public static ResultSet update(userDTO uDTO) throws ClassNotFoundException,SQLException{
		ResultSet isFound=null; 
		try{
	   	  Connection con=CommonDAO.getConnection();
	   	  	CallableStatement csetmt=con.prepareCall("{call UserLogin_Update(?,?,?,?,?,?,?)}");
	         /* PreparedStatement pstmt = con.prepareStatement("insert into registration_master(first_name,last_name,date_of_birth,user_name,password,re_enter_password) values(?,?,?,?,?,?)");*/
	   	csetmt.setInt(1, uDTO.getUserid());
	   	csetmt.setString(2, uDTO.getName());
	   	
	   	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   	
	  DateFormat df=new SimpleDateFormat("YYYY-MM-DD");
		Date age =  df.parse(uDTO.getDOB());
	   	
		java.sql.Date sqlDate = new java.sql.Date(age.getTime());
		
		csetmt.setDate(3,sqlDate);
	   	//
	   	//csetmt.setDate(7,(Date)sdf.parse(uDTO.getDOB()));
	   	
	   	
	   	
	   	csetmt.setString(4, uDTO.getAddress());
	   	csetmt.setString(5, uDTO.getPhone());
	   	csetmt.setString(6, uDTO.getImagePath());
	   	csetmt.setString(7, uDTO.getInstitute_name());
	   	
	   	System.out.println("register query is"+csetmt.toString());
	   	
	   	isFound=csetmt.executeQuery();
			 //con.close();
	      }
		 catch(Exception e)
	     {
	   	 e.printStackTrace(); 
	     }
		 return isFound;
	}
}
