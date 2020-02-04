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

import com.edu.lms.dao.userDAO;
import com.edu.lms.dto.userDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class studentFetchServlet
 */
@WebServlet("/studentFetchServlet")
public class studentFetchServlet extends HttpServlet {
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
            try {
				response.getWriter().write(selectCommon());	
			} catch (Exception e) {
				e.printStackTrace();
			}
        	break;
        	}
		
	}
	private String selectCommon() throws Exception{
		String json="";
		userDAO dao=new userDAO();
		ResultSet rs=null;
		try {
			rs=dao.select();
			
			ArrayList<userDTO> courseData=new ArrayList<>();
			userDTO obj;
			while (rs.next()) {
				obj=new userDTO();
				obj.setUserid(rs.getInt("UserId"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setDOB(rs.getDate("dob").toString());
                obj.setGender(rs.getString("gender"));
                obj.setPhone(rs.getString("phone"));
                obj.setInstitute_name(rs.getString("InstituteName"));
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
		System.out.println("Data in json"+json);
		return json;
	}

}
