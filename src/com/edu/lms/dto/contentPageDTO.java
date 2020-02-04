package com.edu.lms.dto;

import java.util.ArrayList;

public class contentPageDTO {
	private ArrayList<courseContentDTO> content;
	public ArrayList<courseContentDTO> getContent() {
		return content;
	}
	public void setContent(ArrayList<courseContentDTO> content) {
		this.content = content;
	}
	public courseDTO getCourse() {
		return course;
	}
	public void setCourse(courseDTO course) {
		this.course = course;
	}
	public testDTO getTest() {
		return test;
	}
	public void setTest(testDTO test) {
		this.test = test;
	}
	private courseDTO course;
	private testDTO test;
}
