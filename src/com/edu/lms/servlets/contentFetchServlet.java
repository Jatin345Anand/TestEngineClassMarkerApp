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

import com.edu.lms.dao.courseContentDAO;
import com.edu.lms.dao.courseDAO;
import com.edu.lms.dao.testDAO;
import com.edu.lms.dto.contentPageDTO;
import com.edu.lms.dto.courseContentDTO;
import com.edu.lms.dto.courseDTO;
import com.edu.lms.dto.testDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class contentFetchServlet
 */
@WebServlet("/contentFetchServlet")
public class contentFetchServlet extends HttpServlet {
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
        
        
        
        String action=jObj.get("id").getAsString();
        
        
        	response.setContentType("application/json");
            response.getWriter().write(selectCommon(Integer.parseInt(action)));
		
	}
	private String selectCommon(int courseid){
		
		String json="";
		try{
		courseDAO dao=new courseDAO();
		ResultSet rsCourse=null;
		
		rsCourse=dao.selected(courseid);
		
		courseDTO obj=new courseDTO();
		while (rsCourse.next()) {
			
            obj.setCourseID(rsCourse.getInt("CourseId"));
            obj.setTitle(rsCourse.getString("Title"));
            obj.setDetails(rsCourse.getString("Details"));
            obj.setRagister_date(rsCourse.getDate("Register_Date"));
            obj.setImagePath(rsCourse.getString("ImagePath"));
        }
		
		testDAO testdao=new testDAO();
		ResultSet rstest=null;
		
		testDTO test=new testDTO();
		rstest=testdao.getTest(courseid);
		while (rstest.next()) {
			test.setTestid(rstest.getInt("TestId"));
			test.setFacultyID(rstest.getInt("FacultyId"));
			test.setCourseid(rstest.getInt("CourseId"));
			test.setTestName(rstest.getString("TestName"));
			test.setTestDuration(rstest.getInt("testDuration"));
			test.setMinMarks(rstest.getInt("MinMarks"));
			test.setTotalMarks(rstest.getInt("TotalMarks"));
        }
		
		
		
		ArrayList<courseContentDTO> contentData=new ArrayList<>();
			
			courseContentDAO contentdao=new courseContentDAO();
			ResultSet rscontent=null;
			
			rscontent=contentdao.selected(courseid);
			courseContentDTO objcontent;
			while (rscontent.next()) {
				objcontent=new courseContentDTO();
				objcontent.setCourseID(rscontent.getInt("CourseId"));
				objcontent.setTitle(rscontent.getString("Title"));
				objcontent.setDetails(rscontent.getString("Details"));
				objcontent.setFilePath(rscontent.getString("DataPath"));
                contentData.add(objcontent);
            }
			contentPageDTO contentDATAOBJ=new contentPageDTO();
			contentDATAOBJ.setCourse(obj);
			contentDATAOBJ.setTest(test);
			contentDATAOBJ.setContent(contentData);
			
			 json = new Gson().toJson(contentDATAOBJ);
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
