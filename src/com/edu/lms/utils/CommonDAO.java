package com.edu.lms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.edu.lms.dto.EmailCredentials;

public class CommonDAO {
	private CommonDAO() {
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Class.forName(rb.getString("drivername"));
		Connection con = DriverManager.getConnection(rb.getString("dburl"), rb.getString("userid"),
				rb.getString("password"));
		return con;
	}
	public static EmailCredentials  getCredentials() {
		EmailCredentials cred=new EmailCredentials();
		ResourceBundle rb = ResourceBundle.getBundle("db");
		cred.setId(rb.getString("email"));
		cred.setPassword(rb.getString("emailpwd"));
		return cred;
	}

}
