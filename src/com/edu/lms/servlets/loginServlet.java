package com.edu.lms.servlets;

import java.io.IOException;
/*import java.io.PrintWriter;*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.lms.dao.loginDAO;
import com.edu.lms.dto.loginDTO;
import com.edu.lms.utils.Encryption2;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*PrintWriter out = response.getWriter();*/
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		int role=Integer.parseInt(request.getParameter("roles"));
		loginDTO logindto= new loginDTO();		
		loginDAO loginDAO = new loginDAO();
		System.out.println("hello");
		logindto.setUsername(username);
		logindto.setRoleid(role);
		
		
		
		System.out.println(logindto.getUsername());
		try {
			logindto.setPassword( pwd);
			/*logindto.setPassword(pwd);*/
//			System.out.println(Encryption2.decrypt("2t3jcv4Q9ze5CqJrLUnmIQ=="));
//			System.out.println(Encryption2.encrypt("password"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(logindto.getPassword());
		//System.out.println(logindto.getRoleid());
		try{
			
			int result=loginDAO.authenticate(logindto);
			logindto.setLoginid(result);
			if(result>0){
				result=1;
			}
			switch (result)
            {
                case 1:
                	System.out.println("login success");
                	
                	if(role==1)
        			{
        				System.out.println("Admin");
        				HttpSession session=request.getSession(true);
        				
        				
        				session.setAttribute("loginid", logindto.getLoginid());
        				
        				session.setAttribute("username", logindto.getUsername());
        				session.setAttribute("role", logindto.getRoleid());
        				session.setMaxInactiveInterval(10*60);
						Cookie userName = new Cookie("username", logindto.getUsername());
						userName.setMaxAge(5*60);
						response.addCookie(userName);
        				response.sendRedirect("AdminPage.jsp");
        			}
        			else if(role==2)
        			{
        				System.out.println("Faculty");
        				HttpSession session=request.getSession(true);
        				session.setAttribute("loginid", logindto.getLoginid());
        				session.setAttribute("username", logindto.getUsername());
        				session.setAttribute("role", logindto.getRoleid());
        				Cookie userName = new Cookie("username", logindto.getUsername());
						userName.setMaxAge(5*60);
						response.addCookie(userName);
        				response.sendRedirect("facultypage.jsp");
        			}
        			else
        			{
        				if(role==3)
        				{
        					System.out.println("Student");
        					HttpSession session=request.getSession(true);
        					session.setAttribute("loginid", logindto.getLoginid());
            				session.setAttribute("username", logindto.getUsername());
            				session.setAttribute("role", logindto.getRoleid());
            				Cookie userName = new Cookie("username", logindto.getUsername());
    						userName.setMaxAge(5*60);
    						response.addCookie(userName);
            				response.sendRedirect("studentpage.jsp");
        				}
        			} 
                    //Session["data"] = null;
                    break;
                case -1:
                	System.out.println("login failed");
                	response.sendRedirect("Login.jsp?error=Invalid User Id Or Password !");
                    //Session["data"] = null;
                    break;

                default:
                	System.out.println("login at error");
                	response.sendRedirect("Login.jsp?error=login at error !");
                    break;
            }
			/*
			if(loginFields==1)
			{
				System.out.println("Admin");
			}
			else if(loginFields.getRoleid()==2)
			{
				System.out.println("Faculty");
			}
			else
			{
				if(loginFields.getRoleid()==3)
				{
					System.out.println("Studnet");
				}
				else
				{
					System.out.println("Please Selecet Role of the user....Thanks!");
				}
			}
			*/
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
