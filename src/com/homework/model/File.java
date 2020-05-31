package com.homework.model;

/**
 * 文件上传题
 */
public class File {
	private String fileid;
	private String simpleinfo;
	private String description;
	private String answer;
	private String path;

	public File() {
	}

	public File(String fileid) {
		this.fileid = fileid;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}