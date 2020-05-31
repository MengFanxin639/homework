package com.homework.model;

public class Announcement {
	private String id;
	private String subjectt;
	private String lasttime;
	private String starttime;
	private String endtime;
	private String content;
	private Integer state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectt() {
		return subjectt;
	}

	public void setSubjectt(String subjectt) {
		this.subjectt = subjectt;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Announcement [id=" + id + ", subjectt=" + subjectt + ", lasttime=" + lasttime + ", starttime="
				+ starttime + ", endtime=" + endtime + ", content=" + content + ", state=" + state + "]";
	}

}