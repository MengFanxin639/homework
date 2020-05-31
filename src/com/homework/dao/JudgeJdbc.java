package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.homework.model.Blank;
import com.homework.model.Judge;

public class JudgeJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void update(Judge a) {
		jdbc_util.Connect();
		String sql = "UPDATE Judge SET simpleinfo=?,description=?,answer=? WHERE Judgeid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getSimpleinfo());
			jdbc_util.stmt.setString(2, a.getDescription());
			jdbc_util.stmt.setString(3, a.getAnswer());
			jdbc_util.stmt.setString(4, a.getJudgeid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public void insert(Judge a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO Judge VALUES (?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getJudgeid());
			jdbc_util.stmt.setString(2, a.getSimpleinfo());
			jdbc_util.stmt.setString(3, a.getDescription());
			jdbc_util.stmt.setString(4, a.getAnswer());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public Judge selectById(Judge cla) {
		jdbc_util.Connect();
		String sql = "select * from Judge where Judgeid=?";
		Judge a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getJudgeid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Judge();
				a.setAnswer(rs.getString("answer"));
				a.setDescription(rs.getString("descripetion"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setJudgeid(rs.getString("Judgeid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public boolean delete(Judge a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM Judge WHERE Judgeid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getJudgeid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}
}
