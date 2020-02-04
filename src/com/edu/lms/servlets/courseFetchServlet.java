package com.edu.lms.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.lms.dao.courseDAO;
import com.edu.lms.dto.courseDTO;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class courseFetchServlet
 */
@WebServlet("/courseFetchServlet")
public class courseFetchServlet extends HttpServlet {
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
        
        
        
        String action=jObj.get("action").getAsString();
        
        
        switch(action){
        case "COMMONVALUES":
        	response.setContentType("application/json");
            response.getWriter().write(selectCommon());
        	break;
        	}
		
	}
	private String selectCommon(){
		String json="";
		courseDAO dao=new courseDAO();
		ResultSet rs=null;
		try {
			rs=dao.select();
			
			ArrayList<courseDTO> courseData=new ArrayList<>();
			courseDTO obj;
			while (rs.next()) {
				obj=new courseDTO();
                obj.setCourseID(rs.getInt("CourseId"));
                obj.setTitle(rs.getString("Title"));
                obj.setDetails(rs.getString("Details"));
                obj.setRagister_date(rs.getDate("Register_Date"));
                obj.setImagePath(rs.getString("ImagePath"));
                courseData.add(obj);
            }
			 json = new Gson().toJson(courseData);
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
