package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homework.model.Blank;
import com.homework.model.Choice;
import com.homework.model.File;
import com.homework.model.Homework;
import com.homework.model.Judge;
import com.homework.model.Question;

public class HomeworkJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();
	ChoiceJdbc choiceJdbc = new ChoiceJdbc();
	BlankJdbc blankJdbc = new BlankJdbc();
	FileJdbc fileJdbc = new FileJdbc();
	JudgeJdbc judgeJdbc = new JudgeJdbc();

	public void insert(Homework a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO Homework VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getHomeworkid());
			jdbc_util.stmt.setString(2, a.getName());
			jdbc_util.stmt.setString(3, a.getType());
			jdbc_util.stmt.setString(4, a.getSimpleinfo());
			jdbc_util.stmt.setString(5, a.getContent());
			jdbc_util.stmt.setString(6, a.getStarttime());
			jdbc_util.stmt.setString(7, a.getEndtime());
			jdbc_util.stmt.setInt(8, a.getState());
			jdbc_util.stmt.setString(9, a.getCreater());
			jdbc_util.stmt.setInt(10, a.getQuery());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public boolean delete(Homework a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM Homework WHERE homeworkid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getHomeworkid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}

	public Homework selectById(Homework a) {
		jdbc_util.Connect();
		String sql = "select * from Homework where homeworkid=?";
		Homework homework = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getHomeworkid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				homework = new Homework();
				homework.setHomeworkid(rs.getString("homeworkid"));
				homework.setCreater(rs.getString("creater"));
				homework.setEndtime(rs.getString("endtime"));
				homework.setName(rs.getString("name"));
				homework.setQuery(rs.getInt("query"));
				homework.setSimpleinfo(rs.getString("simpleinfo"));
				homework.setStarttime(rs.getString("starttime"));
				homework.setState(rs.getInt("state"));
				homework.setType(rs.getString("type"));
				homework.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return homework;
	}

	public void update(Homework a) {
		jdbc_util.Connect();
		String sql = "UPDATE Homework SET creater=?,endtime=?,"
				+ "name=?,query=?,simpleinfo=?,starttime=?,state=?,type=?,content=? WHERE homeworkid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getCreater());
			jdbc_util.stmt.setString(2, a.getEndtime());
			jdbc_util.stmt.setString(3, a.getName());
			jdbc_util.stmt.setInt(4, a.getQuery());
			jdbc_util.stmt.setString(5, a.getSimpleinfo());
			jdbc_util.stmt.setString(6, a.getStarttime());
			jdbc_util.stmt.setInt(7, a.getState());
			jdbc_util.stmt.setString(8, a.getType());
			jdbc_util.stmt.setString(9, a.getContent());
			jdbc_util.stmt.setString(10, a.getHomeworkid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public List<Homework> selectAll() {
		jdbc_util.Connect();
		String sql = "select * from Homework";
		List<Homework> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				Homework a = new Homework();
				a.setHomeworkid(rs.getString("homeworkid"));
				a.setCreater(rs.getString("creater"));
				a.setEndtime(rs.getString("endtime"));
				a.setName(rs.getString("name"));
				a.setQuery(rs.getInt("query"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setStarttime(rs.getString("starttime"));
				a.setState(rs.getInt("state"));
				a.setType(rs.getString("type"));
				a.setContent(rs.getString("content"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return list;
	}

	public List<Homework> selectByState(int state) {
		jdbc_util.Connect();
		String sql = "select * from Homework where state=?";
		List<Homework> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setInt(1, state);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				Homework a = new Homework();
				a.setHomeworkid(rs.getString("homeworkid"));
				a.setCreater(rs.getString("creater"));
				a.setEndtime(rs.getString("endtime"));
				a.setName(rs.getString("name"));
				a.setQuery(rs.getInt("query"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setStarttime(rs.getString("starttime"));
				a.setState(rs.getInt("state"));
				a.setType(rs.getString("type"));
				a.setContent(rs.getString("content"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return list;
	}
}
