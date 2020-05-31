package com.homework.model;

/**
 * 课程
 */
public class Course {
	private String id;
	private String name;
	private String simpleinfo;
	private String teacherinfo;
	private String beforeclear;
	private String exam;
	private String plan;
	private String learnbooks;
	private String coursewareid;
	// 表示属于哪个班
	private Object object;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", simpleinfo=" + simpleinfo + ", teacherinfo=" + teacherinfo
				+ ", beforeclear=" + beforeclear + ", exam=" + exam + ", plan=" + plan + ", learnbooks=" + learnbooks
				+ ", coursewareid=" + coursewareid + ", object=" + object + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getBeforeclear() {
		return beforeclear;
	}

	public void setBeforeclear(String beforeclear) {
		this.beforeclear = beforeclear;
	}

	public Course(String id) {
		this.id = id;
	}

	public Course() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSimpleinfo() {
		return simpleinfo;
	}

	public void setSimpleinfo(String simpleinfo) {
		this.simpleinfo = simpleinfo;
	}

	public String getTeacherinfo() {
		return teacherinfo;
	}

	public void setTeacherinfo(String teacherinfo) {
		this.teacherinfo = teacherinfo;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getLearnbooks() {
		return learnbooks;
	}

	public void setLearnbooks(String learnbooks) {
		this.learnbooks = learnbooks;
	}

	public String getCoursewareid() {
		return coursewareid;
	}

	public void setCoursewareid(String coursewareid) {
		this.coursewareid = coursewareid;
	}
}