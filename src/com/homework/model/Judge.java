package com.homework.model;

public class Judge {
	private String judgeid;
	private String simpleinfo;
	private String description;
	private String answer;

	public Judge(String judgeid) {
		this.judgeid = judgeid;
	}

	public Judge() {
	}

	public String getJudgeid() {
		return judgeid;
	}

	public void setJudgeid(String judgeid) {
		this.judgeid = judgeid;
	}

	public String getSimpleinfo() {
		return simpleinfo;
	}

	public void setSimpleinfo(String simpleinfo) {
		this.simpleinfo = simpleinfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripetion) {
		this.description = descripetion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}