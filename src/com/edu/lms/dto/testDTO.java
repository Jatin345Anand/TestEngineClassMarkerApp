package com.edu.lms.dto;

import java.sql.Date;
import java.util.ArrayList;

public class testDTO {
	private int testid;
	private int facultyID;
	private int courseid;
	private String testName;
	private int testDuration;
	private int MinMarks;
	private int totalMarks;
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	private Date register_date;
	private ArrayList<testQuestionDTO> questionList;
	
	
	public ArrayList<testQuestionDTO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList<testQuestionDTO> questionList) {
		this.questionList = questionList;
	}
	public int getTestid() {
		return testid;
	}
	public void setTestid(int testid) {
		this.testid = testid;
	}
	public int getFacultyID() {
		return facultyID;
	}
	public void setFacultyID(int facultyID) {
		this.facultyID = facultyID;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	public int getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}
	public int getMinMarks() {
		return MinMarks;
	}
	public void setMinMarks(int minMarks) {
		MinMarks = minMarks;
	}
	
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date ragister_date) {
		this.register_date = ragister_date;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
}
