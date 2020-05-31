package com.homework.model;

import java.io.Serializable;

/**
 * ¿Î¼þ
 */
public class Courseware implements Serializable {
	private String coursewareid;
	private String description;
	private String type;
	private String createtime;
	private Integer isshow;
	private String path;

	public Courseware() {
	}

	public Courseware(String coursewareid) {
		this.coursewareid = coursewareid;
	}

	public String getCoursewareid() {
		return coursewareid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public void setCoursewareid(String coursewareid) {
		this.coursewareid = coursewareid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsshow() {
		return isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}