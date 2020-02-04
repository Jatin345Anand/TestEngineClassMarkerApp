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

import org.apache.catalina.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.edu.lms.dao.courseDAO;
import com.edu.lms.dao.testDAO;
import com.edu.lms.dto.courseDTO;
import com.edu.lms.dto.testDTO;
import com.edu.lms.dto.testQuestionDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class addTestServlet
 */
@WebServlet("/addTestServlet")
public class addTestServlet extends HttpServlet {
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
        HttpSession session=request.getSession(false);
        
        switch(action){
        case "COMMONVALUES":
        	response.setContentType("application/json");
        	
        	//String user=session.getAttribute("username").toString();
        	String user="sunil";
            response.getWriter().write(selectCommon(user));
        	break;
        	
		case "SELECT":
			response.setContentType("application/json");
			
            response.getWriter().write(select());
            
            break;
		case "INSERT":
			JsonObject testData=jObj.get("test").getAsJsonObject();
	        
	        //(courseId,facultyId,testName,questions,testTime,minMarks,totalMarks)
	        
	        
	        //test.setCourseid(testData.get("courseId").getAsInt());
	        //test.setFacultyID(testData.get("facultyId").getAsInt());
			
			test.setFacultyID(Integer.parseInt(session.getAttribute("loginid").toString()));
	        test.setTestName(testData.get("testName").getAsString());
	       
	        test.setMinMarks(testData.get("minMarks").getAsInt());
	        test.setTotalMarks(testData.get("totalMarks").getAsInt());
	        test.setTestDuration(testData.get("testTime").getAsInt());
	        
	        JsonArray questions=testData.get("questions").getAsJsonArray();
	        
	        ArrayList<testQuestionDTO> questionList = new ArrayList<>();
	        
	        
	        
	        if (questions.size() > 0) {
	        	testQuestionDTO q;
	    		for (int i = 0;i < questions.size();i++) {
	    			try {
	    				//JsonElement q = questions.get(i);
	    				JsonObject object= (JsonObject)questions.get(i);
	    				
	    				q=new testQuestionDTO();
	    				
	    				 //q.setQuestionNo(object.get("questionNumber").getAsInt());
	 		            q.setQuestion( object.get("question").getAsString());
	 		            q.setOptionA( object.get("optionA").getAsString());
	 		            q.setOptionB(object.get("optionB").getAsString());
	 		            q.setOptionC(object.get("optionC").getAsString());
	 		            q.setOptionD(object.get("optionD").getAsString());
	 		            q.setAnswer( object.get("answer").getAsString());
	 		            
	 		           questionList.add(q);
	    				
	    			} catch (Exception e) {
	    				
	    				
	    			}
	    		}
	    		}
	        test.setQuestionList(questionList);
	        
			
				add(test);
            break;
        }
	}
        private String select(){
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
                    obj.setImagePath(rs.getString("ImagePath"));
                    courseData.add(obj);
                }
    			 json = new Gson().toJson(courseData);
                 System.out.println(json);
                
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return json;
    	}
    	
        private String selectCommon(String id){
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
                    courseData.add(obj);
                }
    			 json = new Gson().toJson(courseData);
                 System.out.println(json);
                
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return json;
    	}
        
    	private String add(testDTO test){
    		
    		String json="";
    		
    		testDAO dao=new testDAO();
    		
    		
    		try {
    			if(dao.insert(test)){
    				json="{\"data\":\"Added successfully\"}";
    			}
    			else{
    				
    				json="{\"data\":\"Not Added\"}";
    			}
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		return json;
    	}
}
