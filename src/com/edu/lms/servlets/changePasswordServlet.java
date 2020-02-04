package com.edu.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.lms.dao.loginDAO;
import com.edu.lms.dao.userDAO;
import com.edu.lms.dto.loginDTO;
import com.edu.lms.utils.Encryption2;

/**
 * Servlet implementation class changePasswordServlet
 */
@WebServlet("/changePasswordServlet")
public class changePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		//session.getAttribute("loginid");
		//session.getAttribute("username");
		String pwd = request.getParameter("oldpwd");
		String npwd = request.getParameter("newpwd");
		
		if(!pwd.equals(npwd)){
		loginDTO user=new loginDTO();
		int role=Integer.parseInt(session.getAttribute("role").toString());
		user.setRoleid(role);
		user.setUsername(session.getAttribute("username").toString());
		String newpwd="";
		try {
			user.setPassword(Encryption2.encrypt(pwd));
			newpwd=Encryption2.encrypt(npwd);
			userDAO dao=new userDAO();
			int result =dao.changePassword(user, newpwd);
			if(result==1){
				String msg="Password changed successfully";
				if(role==1)
    			{
    				response.sendRedirect("Adminresetpassword.jsp?status="+msg);
    			}
    			else if(role==2)
    			{
    				response.sendRedirect("facultyresetpassword.jsp?status="+msg);
    			}
    			else if(role==3)
    				{
        				response.sendRedirect("resetpassword.jsp?status="+msg);
    				}
			}
			else if(result == -1){
				String msg="Enter Correct Old Password !";
				if(role==1)
    			{
    				response.sendRedirect("Adminresetpassword.jsp?status="+msg);
    			}
    			else if(role==2)
    			{
    				response.sendRedirect("facultyresetpassword.jsp?status="+msg);
    			}
    			else if(role==3)
    				{
        				response.sendRedirect("resetpassword.jsp?status="+msg);
    				}
			}
			else {
				String msg="Password Not changed!";
				if(role==1)
    			{
    				response.sendRedirect("Adminresetpassword.jsp?status="+msg);
    			}
    			else if(role==2)
    			{
    				response.sendRedirect("facultyresetpassword.jsp?status="+msg);
    			}
    			else if(role==3)
    				{
        				response.sendRedirect("resetpassword.jsp?status="+msg);
    				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		}
		else{
			System.out.println("old and new password same");
		}
		
	}
   

}

