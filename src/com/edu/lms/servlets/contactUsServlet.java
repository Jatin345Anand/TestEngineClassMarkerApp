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

/**
 * Servlet implementation class contactUsServlet
 */
@WebServlet("/contactUsServlet")
public class contactUsServlet extends HttpServlet {
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
		
	      String from=request.getParameter("email").toString();;
	      
			
	      String subject="Learning Management System Contact Us Page !";
	      
	      
	      StringBuilder sb=new StringBuilder();
	      sb.append("Name : "+request.getParameter("name").toString());
	      sb.append("Phone : "+request.getParameter("phone").toString());
	      sb.append("Subject : "+request.getParameter("subject").toString());
	      sb.append("Message : "+request.getParameter("message").toString());
	      
	      String msg=sb.toString();
	      
	      SendEmail mail=new SendEmail();
	      
	      if(mail.sendMail2(from, to, subject, msg)){
	    	  response.sendRedirect("contactus.jsp?status="+"send Successfully !");
	      }
	      else{
	    	  response.sendRedirect("contactus.jsp?status="+"Not Send !");
	      }
	      
	     
	   }
}