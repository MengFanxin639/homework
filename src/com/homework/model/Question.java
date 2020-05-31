package com.homework.model;

public class Question {
	private String id;
	/**
	 * 1选择 2 填空 3 判断 4文件上传
	 */
	private String type;
	private String choiceid;
	private String judgeid;
	private String fileid;
	private String homeworkid;
	private String blankidid;
	// 方便页面显示增加引用
	private Object object;

	public Question(String id) {
		this.id = id;
	}

	public Question() {

	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getObject() {
		return object;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", type=" + type + ", choiceid=" + choiceid + ", judgeid=" + judgeid + ", fileid="
				+ fileid + ", homeworkid=" + homeworkid + ", blankidid=" + blankidid + ", object=" + object + "]";
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChoiceid() {
		return choiceid;
	}

	public void setChoiceid(String choiceid) {
		this.choiceid = choiceid;
	}

	public String getJudgeid() {
		return judgeid;
	}

	public void setJudgeid(String judgeid) {
		this.judgeid = judgeid;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getHomeworkid() {
		return homeworkid;
	}

	public void setHomeworkid(String homeworkid) {
		this.homeworkid = homeworkid;
	}

	public String getBlankidid() {
		return blankidid;
	}

	public void setBlankidid(String blankidid) {
		this.blankidid = blankidid;
	}
}