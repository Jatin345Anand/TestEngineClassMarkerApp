package com.edu.lms.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.lms.dao.SendQueryDao;
import com.edu.lms.dto.SendQueryDTO;
import com.edu.lms.utils.CommonDAO;
import com.edu.lms.utils.SendEmail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Servlet implementation class sendQuery
 */
@WebServlet("/sendQuery")
public class sendQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 String to = "userto@gmail.com";//change accordingly  
	      String from = "userfrom@gmail.com";//change accordingly  
	      String host = "localhost";//or IP address  
	  */
		HttpSession session=request.getSession(false);
	      SendQueryDao dao=new SendQueryDao();
		String to = CommonDAO.getCredentials().getId();
		//String pwd=CommonDAO.getCredentials().getPassword();
		
	      String from="";
	      int userid=Integer.parseInt(session.getAttribute("loginid").toString());
		try {
			from = dao.getEmail(userid);
			
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//change accordingly  
	      
	      String subject=request.getParameter("subject").toString();
	      
	      String msg=request.getParameter("query").toString();
	      
	      SendEmail mail=new SendEmail();
	      
	      if(mail.sendMail2(from, to, subject, msg)){
	    	  SendQueryDTO query=new SendQueryDTO();
	    	  query.setMsg(msg);
	    	  query.setUserId(userid);
	    	  query.setSubject(subject);
	    	  try {
				dao.insert(query);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  response.sendRedirect("sendquery.jsp?status="+"send Successfully !");
	      }
	      else{
	    	  response.sendRedirect("sendquery.jsp?status="+"Not Send !");
	      }
	      
	     
	   }
}