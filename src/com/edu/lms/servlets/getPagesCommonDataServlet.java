package com.edu.lms.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.lms.dao.courseDAO;
import com.edu.lms.dao.pageValueDAO;
import com.edu.lms.dto.courseDTO;
import com.edu.lms.dto.pageValueDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class getPagesCommonDataServlet
 */
@WebServlet("/getPagesCommonDataServlet")
public class getPagesCommonDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println(sb.toString());
        
        
        JsonParser parser = new JsonParser();
        JsonObject jObj=(JsonObject) parser.parse(sb.toString());
        
        HttpSession session=request.getSession(false);
        
        String id=session.getAttribute("role").toString();
        if(!id.equals("") && !id.equals(null)){
        	int action=Integer.parseInt(session.getAttribute("role").toString());
             	response.setContentType("application/json");
                 response.getWriter().write(selectCommon(action));
        }
	}
	private String selectCommon(int role){
		String json="";
		pageValueDAO dao=new pageValueDAO();
		ResultSet rs=null;
		try {
			rs=dao.getValue();
			
			pageValueDTO obj =new pageValueDTO();
			while (rs.next()) {
				obj.setStudent(rs.getInt("student"));
				if(role==1){
                obj.setFaculty(rs.getInt("teacher"));
                obj.setQuery(rs.getInt("query"));
                obj.setTest(rs.getInt("test"));
				}
				if(role==2){
					obj.setAdmin(rs.getInt("admin"));
					obj.setTest(rs.getInt("test"));
				}
				if(role==3){
					obj.setFaculty(rs.getInt("teacher"));
				}
                
            }
			 json = new Gson().toJson(obj);
             //System.out.println(json);
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		return json;
	}

}
