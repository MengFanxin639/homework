package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.homework.model.Choice;
import com.homework.model.Class;

public class ChoiceJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void insert(Choice a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO choice VALUES (?,?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getChoiceid());
			jdbc_util.stmt.setString(2, a.getSimpleinfo());
			jdbc_util.stmt.setString(3, a.getDescription());
			jdbc_util.stmt.setInt(4, 1);
			jdbc_util.stmt.setString(5, a.getAnswer());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public Choice selectById(Choice cla) {
		jdbc_util.Connect();
		String sql = "select * from choice where choiceid=?";
		Choice a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getChoiceid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Choice();
				a.setAnswer(rs.getString("answer"));
				a.setCount(rs.getInt("count"));
				a.setDescription(rs.getString("description"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setChoiceid(rs.getString("choiceid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public boolean delete(Choice a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM Choice WHERE Choiceid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getChoiceid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}

	public void update(Choice cla) {
		jdbc_util.Connect();
		String sql = "UPDATE Choice SET simpleinfo=?,description=?,count=?,answer=? WHERE choiceid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getSimpleinfo());
			jdbc_util.stmt.setString(2, cla.getDescription());
			jdbc_util.stmt.setInt(3, cla.getCount());
			jdbc_util.stmt.setString(4, cla.getAnswer());
			jdbc_util.stmt.setString(5, cla.getChoiceid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}
}
