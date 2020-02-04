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

import com.edu.lms.dao.courseDAO;
import com.edu.lms.dto.courseDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Servlet implementation class courseModifyServlet
 */
@WebServlet("/courseModifyServlet")
public class courseModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public courseModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String json = new Gson();
		/*
		StringBuilder sb = new StringBuilder();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(sb.toString());
		JsonObject root = element.getAsJsonObject();
		JsonObject data = root.getAsJsonObject("data");
		*/
		//String countryCode = request.getParameter("countryCode");
		
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println(sb.toString());
        
        
        JsonParser parser = new JsonParser();
        JsonObject jObj=(JsonObject) parser.parse(sb.toString());
        
        courseDTO course=new courseDTO();
        
        
        String action=jObj.get("action").getAsString();
        
        /*
        course.setCourseID(jObj.get("id").getAsInt()!=0?jObj.get("id").getAsInt() :0);
        course.setDetails(jObj.get("detail").getAsString()!=null?jObj.get("detail").getAsString() :"");
        course.setTitle(jObj.get("title").getAsString()!=null?jObj.get("title").getAsString() :"");
        course.setImagePath(jObj.get("file").getAsString()!=null?jObj.get("file").getAsString() :"");
        */
        
		switch(action){
		case "SELECT":
			response.setContentType("application/json");
            response.getWriter().write(select());
            break;
		case "ADD":
			/*
			course.setCourseID(jObj.get("id").getAsInt());
	        course.setDetails(jObj.get("detail").getAsString());
	        course.setTitle(jObj.get("title").getAsString());
	        course.setImagePath(jObj.get("file").getAsString());
	        */
			
			 course.setCourseID(jObj.get("id").getAsInt()!=0?jObj.get("id").getAsInt() :0);
		        course.setDetails(jObj.get("detail").getAsString()!=null?jObj.get("detail").getAsString() :"");
		        course.setTitle(jObj.get("title").getAsString()!=null?jObj.get("title").getAsString() :"");
		        course.setImagePath(jObj.get("file").getAsString()!=null?jObj.get("file").getAsString() :"");
			response.setContentType("application/json");
            //response.getWriter().write(add(course));
            break;
		case "UPDATE":
			/*
			course.setCourseID(jObj.get("id").getAsInt());
	        course.setDetails(jObj.get("detail").getAsString());
	        course.setTitle(jObj.get("title").getAsString());
	        course.setImagePath(jObj.get("file").getAsString());
	        */
			
			 course.setCourseID(jObj.get("id").getAsInt()!=0?jObj.get("id").getAsInt() :0);
		        course.setDetails(jObj.get("detail").getAsString()!=null?jObj.get("detail").getAsString() :"");
		        course.setTitle(jObj.get("title").getAsString()!=null?jObj.get("title").getAsString() :"");
		        course.setImagePath(jObj.get("file").getAsString()!=null?jObj.get("file").getAsString() :"");
			response.setContentType("application/json");
            response.getWriter().write(update(course));
            break;
		case "DELETE":
			/*
			course.setCourseID(jObj.get("id").getAsInt());
	        course.setDetails(jObj.get("detail").getAsString());
	        course.setTitle(jObj.get("title").getAsString());
	        course.setImagePath(jObj.get("file").getAsString());
	        */
			
			 course.setCourseID(jObj.get("id").getAsInt()==0?0:jObj.get("id").getAsInt() );
		        course.setDetails(jObj.get("detail").getAsString()==null||jObj.get("detail").getAsString()==""?"":jObj.get("detail").getAsString() );
		        course.setTitle(jObj.get("title").getAsString()==null||jObj.get("title").getAsString()==""?"":jObj.get("title").getAsString() );
		        
		        course.setImagePath(jObj.get("file").getAsString()==null||jObj.get("file").getAsString()==""?"":jObj.get("file").getAsString());
			response.setContentType("application/json");
            response.getWriter().write(delete(course));
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
	
	/*private String add(courseDTO course){
		String json="";
		courseDAO dao=new courseDAO();
		
		try {
			if(dao.insert(course)){
				json="[{\"data\":\"Added successfully\"}]";
			}
			else{
				
				json="[{\"data\":\"Not Added\"}]";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}*/
	private String update(courseDTO course){
		String json="";
		courseDAO dao=new courseDAO();
		
		try {
			if(!dao.update(course)){
				json="[{\"data\":\"Updated successfully\"}]";
			}
			else{
				
				json="[{\"data\":\"Not Updated\"}]";
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
	
	private String delete(courseDTO course){
		String json="";
		courseDAO dao=new courseDAO();
		try {
			if(!dao.delete(course)){
				json="[{\"data\":\"Deleted successfully\"}]";
				
			}
			else{
				
				json="[{\"data\":\"Not Deleted\"}]";
			}
			
			
			
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("delete json "+json);
		return json;
	}

}
