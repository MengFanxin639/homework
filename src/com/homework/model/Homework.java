package com.homework.model;

/**
 * 作业
 */
public class Homework {

	private String homeworkid;
	private String name;
	private String type;
	private String simpleinfo;
	private String content;
	private String starttime;
	private String endtime;
	// 作业状态 1完成 2待提交 3已删除
	private Integer state;
	private String creater;
	// 权限:1允许查询 0不允许
	private Integer query;
	// 方便引用
	private Question question;

	public Question getQuestion() {
		return question;
	}

	public Homework(String currenthomeworkid) {
		this.homeworkid = currenthomeworkid;
	}

	public Homework() {
	}

	@Override
	public String toString() {
		return "Homework [homeworkid=" + homeworkid + ", name=" + name + ", type=" + type + ", simpleinfo=" + simpleinfo
				+ ", content=" + content + ", starttime=" + starttime + ", endtime=" + endtime + ", state=" + state
				+ ", creater=" + creater + ", query=" + query + "]";
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getHomeworkid() {
		return homeworkid;
	}

	public void setHomeworkid(String homeworkid) {
		this.homeworkid = homeworkid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSimpleinfo() {
		return simpleinfo;
	}

	public void setSimpleinfo(String simpleinfo) {
		this.simpleinfo = simpleinfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Integer getQuery() {
		return query;
	}

	public void setQuery(Integer query) {
		this.query = query;
	}
}