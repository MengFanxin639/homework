package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homework.model.Class;
import com.homework.model.Class;
import com.homework.model.Homework;

public class ClassJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void insert(com.homework.model.Class a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO class (classid,classname,teacherid,name,courseid) VALUES (?,?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getClassid());
			jdbc_util.stmt.setString(2, a.getClassname());
			jdbc_util.stmt.setString(3, a.getTeacherid());
			jdbc_util.stmt.setString(4, a.getName());
			jdbc_util.stmt.setString(5, a.getCourseid());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public boolean delete(Class a) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM class WHERE classid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getClassid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}

	public Class selectById(Class cla) {
		jdbc_util.Connect();
		String sql = "select * from class where classid=?";
		Class a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getClassid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Class();
				a.setClassid(rs.getString("classid"));
				a.setClassname(rs.getString("classname"));
				a.setName(rs.getString("name"));
				a.setTeacherid(rs.getString("teacherid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public Class selectBycoutseId(Class cla) {
		jdbc_util.Connect();
		String sql = "select * from class where courseid=?";
		Class a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getCourseid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a.setClassid(rs.getString("classid"));
				a.setClassname(rs.getString("classname"));
				a.setName(rs.getString("name"));
				a.setTeacherid(rs.getString("teacherid"));
				a.setCourseid(rs.getString("courseid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public void update(Class cla) {
		jdbc_util.Connect();
		String sql = "UPDATE class SET classname=?,name=?,teacherid=? WHERE classid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getClassname());
			jdbc_util.stmt.setString(2, cla.getName());
			jdbc_util.stmt.setString(3, cla.getTeacherid());
			jdbc_util.stmt.setString(4, cla.getClassid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public List<com.homework.model.Class> selectAll() {
		jdbc_util.Connect();
		String sql = "select * from class";
		List<com.homework.model.Class> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				com.homework.model.Class a = new com.homework.model.Class();
				a.setClassid(rs.getString("classid"));
				a.setClassname(rs.getString("classname"));
				a.setName(rs.getString("name"));
				a.setTeacherid(rs.getString("teacherid"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return list;
	}
}
