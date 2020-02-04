package com.edu.lms.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.lms.dao.SendQueryDao;
import com.edu.lms.dao.userDAO;
import com.edu.lms.dto.SendQueryDTO;
import com.edu.lms.utils.CommonDAO;
import com.edu.lms.utils.Encryption2;
import com.edu.lms.utils.SendEmail;

/**
 * Servlet implementation class sendPasswordServlet
 */
@WebServlet("/sendPasswordServlet")
public class sendPasswordServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 String to = "userto@gmail.com";//change accordingly  
	      String from = "userfrom@gmail.com";//change accordingly  
	      String host = "localhost";//or IP address  
	  */
		HttpSession session=request.getSession(false);
	      
		String to = request.getParameter("email").toString();
		//String pwd=CommonDAO.getCredentials().getPassword();
		
		if(to.trim().equals("")){
			response.sendRedirect("forgetpassword.jsp?status="+"Please Enter Email Address!");
		}
		else{
			userDAO dao=new userDAO();
			ResultSet rs=null;
			try {
				rs=dao.getDetailsByEmail(to);
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(rs.next() && !rs.equals(null)){
					String from=CommonDAO.getCredentials().getId();
				      
				      String subject="Your learning management system id and password!";
				      
				      
				      StringBuilder sb=new StringBuilder();
				      sb.append("UserName : "+rs.getString(1));
				      sb.append("Password : "+Encryption2.decrypt(rs.getString(2)));
				      
				      String msg=sb.toString();
				      
				      SendEmail mail=new SendEmail();
				      
				      if(mail.sendMail2(from, to, subject, msg)){
				    	
				    	  response.sendRedirect("forgetpassword.jsp?status="+"send Successfully !");
				      }
				      else{
				    	  response.sendRedirect("forgetpassword.jsp?status="+"Not Send !");
				      }
				    
				}
				else{
					response.sendRedirect("forgetpassword.jsp?status="+"Email Address Not Valid !");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response.sendRedirect("forgetpassword.jsp?status="+"Server Error !");
				e.printStackTrace();
			}
			
		}
	        
	     
	   }
}