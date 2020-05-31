package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homework.model.Blank;
import com.homework.model.Choice;
import com.homework.model.Class;
import com.homework.model.File;
import com.homework.model.Judge;
import com.homework.model.Question;

public class QuestionJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();
	ChoiceJdbc choiceJdbc = new ChoiceJdbc();
	BlankJdbc blankJdbc = new BlankJdbc();
	FileJdbc fileJdbc = new FileJdbc();
	JudgeJdbc judgeJdbc = new JudgeJdbc();

	public void updateHomeworkId(Question cla) {
		jdbc_util.Connect();
		String sql = "UPDATE Question SET homeworkid=? WHERE id = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getHomeworkid());
			jdbc_util.stmt.setString(2, cla.getId());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public Question selectById(Question cla) {
		jdbc_util.Connect();
		String sql = "select * from Question where id=?";
		Question a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getId());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Question();
				a.setBlankidid(rs.getString("blankidid"));
				a.setChoiceid(rs.getString("choiceid"));
				a.setFileid(rs.getString("fileid"));
				a.setHomeworkid(rs.getString("homeworkid"));
				a.setId(rs.getString("id"));
				a.setJudgeid(rs.getString("judgeid"));
				a.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public List<Question> selectByHomeworkId(Question cla) {
		jdbc_util.Connect();
		String sql = "select * from Question where homeworkid=?";
		List<Question> list = new ArrayList<>();
		Question a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getHomeworkid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Question();
				a.setBlankidid(rs.getString("blankidid"));
				a.setChoiceid(rs.getString("choiceid"));
				a.setFileid(rs.getString("fileid"));
				a.setHomeworkid(rs.getString("homeworkid"));
				a.setId(rs.getString("id"));
				a.setJudgeid(rs.getString("judgeid"));
				a.setType(rs.getString("type"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		for (Question qu : list) {
			String type = qu.getType();
			if ("1".equals(type)) {
				Choice c = choiceJdbc.selectById(new Choice(qu.getChoiceid()));
				qu.setObject(c);
				qu.setType("选择题");
			}
			if ("2".equals(type)) {
				Blank b = blankJdbc.selectById(new Blank(qu.getBlankidid()));
				qu.setObject(b);
				qu.setType("填空题");
			}
			if ("3".equals(type)) {
				Judge j = judgeJdbc.selectById(new Judge(qu.getJudgeid()));
				qu.setObject(j);
				qu.setType("判断题");
			}
			if ("4".equals(type)) {
				File f = fileJdbc.selectById(new File(qu.getFileid()));
				qu.setObject(f);
				qu.setType("文件上传题");

			}
		}
		return list;
	}

	public boolean delete(Question a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM Question WHERE id = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getId());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}

	public List<Question> selectAll() {
		jdbc_util.Connect();
		String sql = "select * from Question";
		List<Question> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				Question a = new Question();
				a.setId((rs.getString("id")));
				a.setType(rs.getString("type"));
				a.setChoiceid(rs.getString("choiceid"));
				a.setJudgeid(rs.getString("judgeid"));
				a.setFileid(rs.getString("fileid"));
				a.setHomeworkid(rs.getString("homeworkid"));
				a.setBlankidid(rs.getString("blankidid"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		for (Question qu : list) {
			String type = qu.getType();
			if ("1".equals(type)) {
				Choice c = choiceJdbc.selectById(new Choice(qu.getChoiceid()));
				qu.setObject(c);
				qu.setType("选择题");
			}
			if ("2".equals(type)) {
				Blank b = blankJdbc.selectById(new Blank(qu.getBlankidid()));
				qu.setObject(b);
				qu.setType("填空题");
			}
			if ("3".equals(type)) {
				Judge j = judgeJdbc.selectById(new Judge(qu.getJudgeid()));
				qu.setObject(j);
				qu.setType("判断题");
			}
			if ("4".equals(type)) {
				File f = fileJdbc.selectById(new File(qu.getFileid()));
				qu.setObject(f);
				qu.setType("文件上传题");

			}
		}
		return list;
	}

	/**
	 * 1选择 2 填空 3 判断 4文件上传
	 * 
	 * @param a
	 */
	public void insert(Question a) {
		jdbc_util.Connect();
		String sql = "";
		try {
			String parm = "";
			if ("1".equals(a.getType())) {
				sql = "INSERT INTO question (id, type,choiceid) VALUES (?, ?,?)";
				parm = a.getChoiceid();
			}
			if ("2".equals(a.getType())) {
				sql = "INSERT INTO question (id, type,blankidid) VALUES (?, ?,?)";
				parm = a.getBlankidid();
			}
			if ("3".equals(a.getType())) {
				sql = "INSERT INTO question (id, type,judgeid) VALUES (?, ?,?)";
				parm = a.getJudgeid();
			}
			if ("4".equals(a.getType())) {
				sql = "INSERT INTO question (id, type,fileid) VALUES (?, ?,?)";
				parm = a.getFileid();
			}
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getId());
			jdbc_util.stmt.setString(2, a.getType());
			jdbc_util.stmt.setString(3, parm);
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

}
