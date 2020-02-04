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

import com.edu.lms.dao.userDAO;
import com.edu.lms.dao.usertestResultDAO;
import com.edu.lms.dto.UserResultsDTO;
import com.edu.lms.dto.userDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class getTestResultServlet
 */
@WebServlet("/getTestResultServlet")
public class getTestResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
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
            	int loginId=Integer.parseInt(session.getAttribute("loginid").toString());
				response.getWriter().write(selectCommon(loginId));	
			} catch (Exception e) {
				e.printStackTrace();
			}
        	break;
        	}
		
	}
	private String selectCommon(int loginId) throws Exception{
		String json="";
		usertestResultDAO dao=new usertestResultDAO();
		ResultSet rs=null;
		try {
			rs=dao.selectedTest(loginId);
			
			ArrayList<UserResultsDTO> resultData=new ArrayList<>();
			UserResultsDTO obj;
			while (rs.next()) {
				obj=new UserResultsDTO();
				obj.setTestId(rs.getInt("TestId"));
				obj.setTestName(rs.getString("TestName"));
                obj.setMarks(rs.getInt("marks"));
                obj.setMinMarks(rs.getInt("MinMarks"));
                obj.setTotalMarks(rs.getInt("TotalMarks"));
                obj.setTest_date(rs.getDate("test_Date"));
                resultData.add(obj);
            }
			 json = new Gson().toJson(resultData);
             //System.out.println(json);
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ResultData in json"+json);
		return json;
	}

}
