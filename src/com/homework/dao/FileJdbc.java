package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.homework.model.Choice;
import com.homework.model.File;

public class FileJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void update(File a) {
		jdbc_util.Connect();
		String sql = "UPDATE Choice SET simpleinfo=?,description=? WHERE fileid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getSimpleinfo());
			jdbc_util.stmt.setString(2, a.getDescription());
			jdbc_util.stmt.setString(3, a.getFileid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public void insert(File a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO File VALUES (?,?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getFileid());
			jdbc_util.stmt.setString(2, a.getSimpleinfo());
			jdbc_util.stmt.setString(3, a.getDescription());
			jdbc_util.stmt.setString(4, a.getAnswer());
			jdbc_util.stmt.setString(5, a.getPath());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public File selectById(File cla) {
		jdbc_util.Connect();
		String sql = "select * from File where Fileid=?";
		File a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getFileid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new File();
				a.setAnswer(rs.getString("answer"));
				a.setDescription(rs.getString("description"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setFileid(rs.getString("Fileid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public boolean delete(File a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM File WHERE Fileid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getFileid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}
}
