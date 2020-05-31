package com.homework.model;

/**
 * 作业策略类
 */
public class Examstrategy {
	private String examstrategyid;
	private Integer blankperscore;
	private Integer choiceperscore;
	private Integer judgeperscore;
	private String homeworkid;

	public String getExamstrategyid() {
		return examstrategyid;
	}

	public void setExamstrategyid(String examstrategyid) {
		this.examstrategyid = examstrategyid == null ? null : examstrategyid.trim();
	}

	public Integer getBlankperscore() {
		return blankperscore;
	}

	public void setBlankperscore(Integer blankperscore) {
		this.blankperscore = blankperscore;
	}

	public Integer getChoiceperscore() {
		return choiceperscore;
	}

	public void setChoiceperscore(Integer choiceperscore) {
		this.choiceperscore = choiceperscore;
	}

	public Integer getJudgeperscore() {
		return judgeperscore;
	}

	public void setJudgeperscore(Integer judgeperscore) {
		this.judgeperscore = judgeperscore;
	}

	public String getHomeworkid() {
		return homeworkid;
	}

	public void setHomeworkid(String homeworkid) {
		this.homeworkid = homeworkid == null ? null : homeworkid.trim();
	}
}