package com.homework.model;

public class Choice {
	private String choiceid;
	private String simpleinfo;
	private String description;
	private Integer count;
	private String answer;

	public Choice(String choiceid) {
		this.choiceid = choiceid;
	}

	@Override
	public String toString() {
		return "Choice [choiceid=" + choiceid + ", simpleinfo=" + simpleinfo + ", description=" + description
				+ ", count=" + count + ", answer=" + answer + "]";
	}

	public Choice() {
	}

	public String getChoiceid() {
		return choiceid;
	}

	public void setChoiceid(String choiceid) {
		this.choiceid = choiceid;
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