package com.edu.lms.servlets;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.edu.lms.dao.userDAO;
import com.edu.lms.dto.userDTO;
import com.edu.lms.utils.Encryption2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	
	private static final String UPLOAD_DIRECTORY = "upload";

	
	private static final int MEMORY_THRESHOLD 	= 1024 * 1024 * 3; 	// 3MB
	private static final int MAX_FILE_SIZE 		= 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE	= 1024 * 1024 * 50; // 50MB

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		userDAO userdao=new userDAO();
		userDTO userdto = new userDTO();
		if (!ServletFileUpload.isMultipartContent(request)) {
			
			PrintWriter writer = response.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		}
		
		HashMap<String,String> value=new HashMap<>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		
		upload.setSizeMax(MAX_REQUEST_SIZE);

		
		String uploadPath = getServletContext().getRealPath("")
				+ File.separator + UPLOAD_DIRECTORY;
		
		
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
			
				for (FileItem item : formItems) {
			
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
							
						System.out.println("file path :"+storeFile);
			
						//my code
						
						String folderPath = uploadPath + File.separator;
						
						int index=fileName.lastIndexOf(".");
						String fileN=fileName.substring(0, index);
						String extension=fileName.substring(index,fileName.length());
						
						System.out.println("file name :"+ fileN);
						System.out.println("file extension :"+ extension);
						
						
						
						File storeFileNew=new File(filePath);
						for(int i=1;i<=100000;i++){
						if(storeFileNew.exists()){
							storeFileNew = new File(folderPath+fileN+i+extension);
							continue;
						}
							
							storeFile.renameTo(storeFileNew);
							userdto.setImagePath(UPLOAD_DIRECTORY+"/"+storeFileNew.getName());
							break;
						
						}
						
						
						//
						
						item.write(storeFile);
						request.setAttribute("message",
							"Upload has been done successfully!");
					}
					
					if (item.isFormField()) 
                    {
						
                        String name = item.getFieldName();
                        String value2 = item.getString();
                        value.put(name,value2);
                        System.out.println(name+":"+value2);
                    }
				}
				
			}
			//System.out.println("user Name : "+value.get("userName"));
			//System.out.println("phone : "+value.get("phone"));
			
			String username = value.get("username");
			String name = value.get("inputname");
			String age=value.get("Age");
			//DateFormat df=new SimpleDateFormat("mm-dd-yyyy");
			//Date age =  df.parse(value.get("Age"));
			
			
			
			String password = value.get("password");
			String mobileno = value.get("mobileno");
			String institute = value.get("institute");
			//String filepath = request.getParameter("filePath");
			String address = value.get("address");
			String Gender= value.get("gender");
			String email= value.get("email");
			
			
			System.out.println("value in name variable : "+name);
			
			userdto.setName(name);
			userdto.setUserName(username);
			userdto.setGender(Gender);
			//userdto.setImagePath(filepath);
			userdto.setDOB(age);
			userdto.setPhone(mobileno);
			userdto.setInstitute_name(institute);
			
			try {
				userdto.setPassword(password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			userdto.setAddress(address);
			userdto.setEmail(email);
			
		} catch (Exception ex) {
			request.setAttribute("message",
					"There was an error: " + ex.getMessage());
		}
		
		
		try {
			userdto.setRoleID(3);
			ResultSet rs= userdao.register(userdto);
			int result=0;
			while(rs.next()){
				System.out.println(rs.getInt(1));
				result=rs.getInt(1);
			}
			//response.sendRedirect("register.jsp?error=Invalid User Id Or Password !");
			if(result>0){
				response.sendRedirect("registersuccess.jsp");
				//System.out.println("register successfully");
			}
			else if(result==-1){
				response.sendRedirect("register.jsp?error=user name already exist !");
			}
			else if(result==-2){
				response.sendRedirect("register.jsp?error=email already exist !");
				
			}
			else if(result==0){
				response.sendRedirect("register.jsp?error=Server Error !");
				
			}
			else{
				response.sendRedirect("register.jsp?error=Server Error !");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
