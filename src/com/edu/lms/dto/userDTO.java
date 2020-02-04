package com.edu.lms.dto;

import java.util.Date;

//import java.util.Date;

public class userDTO {
		private String userName;
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		private int roleID;
		private String password;
		private String email;
		
		private String name;
		private String gender;
		//private Date DOB;
		private String DOB;
		
		private int userid;
		
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		
		public String getDOB() {
			return DOB;
		}
		public void setDOB(String dOB) {
			DOB = dOB;
		}
		private String address;
		private String phone;
		private String ImagePath;
		private String Institute_name;
		
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getRoleID() {
			return roleID;
		}
		public void setRoleID(int roleID) {
			this.roleID = roleID;
		}
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getImagePath() {
			return ImagePath;
		}
		public void setImagePath(String imagePath) {
			ImagePath = imagePath;
		}
		public String getInstitute_name() {
			return Institute_name;
		}
		public void setInstitute_name(String institute_name) {
			Institute_name = institute_name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}	

}
