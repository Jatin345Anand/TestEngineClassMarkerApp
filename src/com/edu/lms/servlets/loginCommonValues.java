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
import javax.servlet.http.HttpSession;

import com.edu.lms.dao.courseDAO;
import com.edu.lms.dao.roleDAO;
import com.edu.lms.dto.courseDTO;
import com.edu.lms.dto.roleDTO;
import com.edu.lms.dto.testDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class loginCommonValues
 */
@WebServlet("/loginCommonValues")
public class loginCommonValues extends HttpServlet {
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
        
        
        testDTO test=new testDTO();
        
        
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
		roleDAO dao=new roleDAO();
		ResultSet rs=null;
		try {
			rs=dao.select();
			
			ArrayList<roleDTO> roleData=new ArrayList<>();
			roleDTO obj;
			while (rs.next()) {
				obj=new roleDTO();
                obj.setIdentity(rs.getInt("roleId"));
                obj.setRoleName(rs.getString("roleName"));
                roleData.add(obj);
            }
			 json = new Gson().toJson(roleData);
             //System.out.println(json);
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		return json;
	}

}
