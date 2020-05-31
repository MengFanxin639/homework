package com.homework.model;

import java.io.Serializable;

public class Class implements Serializable {
	private String classid;
	private String classname;
	private String teacherid;
	private String name;
	private String courseid;

	public Class(String id) {
		this.classid = id;
	}

	public Class() {
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Class [classid=" + classid + ", classname=" + classname + ", teacherid=" + teacherid + ", name=" + name
				+ "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}
}