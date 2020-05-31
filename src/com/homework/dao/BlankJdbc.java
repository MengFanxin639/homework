package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.homework.model.Blank;
import com.homework.model.Choice;

public class BlankJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void update(Blank a) {
		jdbc_util.Connect();
		String sql = "UPDATE Choice SET simpleinfo=?,description=?,count=?,answer=? WHERE blankidid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getSimpleinfo());
			jdbc_util.stmt.setString(2, a.getDescription());
			jdbc_util.stmt.setInt(3, a.getCount());
			jdbc_util.stmt.setString(4, a.getAnswer());
			jdbc_util.stmt.setString(5, a.getBlankidid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public void insert(Blank a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO Blank VALUES (?,?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getBlankidid());
			jdbc_util.stmt.setString(2, a.getSimpleinfo());
			jdbc_util.stmt.setString(3, a.getDescription());
			jdbc_util.stmt.setInt(4, a.getCount());
			jdbc_util.stmt.setString(5, a.getAnswer());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public Blank selectById(Blank cla) {
		jdbc_util.Connect();
		String sql = "select * from Blank where blankidid=?";
		Blank a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getBlankidid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Blank();
				a.setAnswer(rs.getString("answer"));
				a.setCount(rs.getInt("count"));
				a.setDescription(rs.getString("descripetion"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setBlankidid(rs.getString("blankidid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public boolean delete(Blank a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM Blank WHERE blankidid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getBlankidid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}
}
