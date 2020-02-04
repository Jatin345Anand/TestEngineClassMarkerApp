package com.edu.lms.dto;

import java.util.ArrayList;

public class finalResultDTO {
	private int totalMarks;
	private int marks;
	private int correctAnswer;
	private int wrongAnswer;
	private int skipQuestion;
	private String status;
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public int getWrongAnswer() {
		return wrongAnswer;
	}
	public void setWrongAnswer(int wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}
	public int getSkipQuestion() {
		return skipQuestion;
	}
	public void setSkipQuestion(int skipQuestion) {
		this.skipQuestion = skipQuestion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<resultDTO> getResult() {
		return result;
	}
	public void setResult(ArrayList<resultDTO> result) {
		this.result = result;
	}
	private ArrayList<resultDTO> result;

}
