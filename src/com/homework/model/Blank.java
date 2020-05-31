package com.homework.model;

public class Blank {
	private String blankidid;
	private String simpleinfo;
	private String description;
	private Integer count;
	private String answer;

	public Blank(String blankidid) {
		this.blankidid = blankidid;
	}

	public Blank() {

	}

	public String getBlankidid() {
		return blankidid;
	}

	public void setBlankidid(String blankidid) {
		this.blankidid = blankidid;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}