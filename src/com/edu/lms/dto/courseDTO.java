package com.edu.lms.dto;

import java.util.Date;

public class courseDTO {
		private int courseID;
		private String title;
		private String imagePath;
		private String Details;
		private Date ragister_date;
		public int getCourseID() {
			return courseID;
		}
		public void setCourseID(int courseID) {
			this.courseID = courseID;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
		public String getDetails() {
			return Details;
		}
		public void setDetails(String details) {
			Details = details;
		}
		public Date getRagister_date() {
			return ragister_date;
		}
		public void setRagister_date(Date ragister_date) {
			this.ragister_date = ragister_date;
		}
		
}
