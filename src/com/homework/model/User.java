package com.homework.model;

public class User {
	private String studentid;
	private String name;
	private String password;
	private String classid;
	private String avatarpath;
	private String email;
	private String tel;
	// 1教师 0学生
	private Integer type;

	public String getStudentid() {
		return studentid;
	}

	@Override
	public String toString() {
		return "User [studentid=" + studentid + ", name=" + name + ", password=" + password + ", classid=" + classid
				+ ", avatarpath=" + avatarpath + ", email=" + email + ", tel=" + tel + ", type=" + type + "]";
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getAvatarpath() {
		return avatarpath;
	}

	public void setAvatarpath(String avatarpath) {
		this.avatarpath = avatarpath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}