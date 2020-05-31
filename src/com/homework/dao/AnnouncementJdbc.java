package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homework.model.Announcement;
import com.homework.model.Homework;

public class AnnouncementJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void insert(Announcement a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO announcement VALUES (?,?,?,?,?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getId());
			jdbc_util.stmt.setString(2, a.getSubjectt());
			jdbc_util.stmt.setString(3, a.getStarttime());
			jdbc_util.stmt.setString(4, a.getStarttime());
			jdbc_util.stmt.setString(5, a.getEndtime());
			jdbc_util.stmt.setString(6, a.getContent());
			jdbc_util.stmt.setInt(7, a.getState());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public Announcement selectById(Announcement announcement) {
		jdbc_util.Connect();
		String sql = "select * from announcement where id=?";
		Announcement a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, announcement.getId());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Announcement();
				a.setId(rs.getString("id"));
				a.setContent(rs.getString("content"));
				a.setEndtime(rs.getString("endtime"));
				a.setLasttime(rs.getString("lasttime"));
				a.setStarttime(rs.getString("lasttime"));
				a.setSubjectt(rs.getString("subjectt"));
				a.setState(rs.getInt("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

	public List<Announcement> selectAll() {
		jdbc_util.Connect();
		String sql = "select * from announcement";
		List<Announcement> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				Announcement a = new Announcement();
				a.setId(rs.getString("id"));
				a.setContent(rs.getString("content"));
				a.setEndtime(rs.getString("endtime"));
				a.setLasttime(rs.getString("lasttime").trim());
				a.setStarttime(rs.getString("starttime").trim());
				a.setSubjectt(rs.getString("subjectt"));
				a.setState(rs.getInt("state"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return list;
	}

	public boolean delete(Announcement announcement) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM announcement WHERE id = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, announcement.getId());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;
	}

	public void update(Announcement a) {
		jdbc_util.Connect();
		String sql = "UPDATE announcement SET subjectt=?,content=?,endtime=?,lasttime=?, starttime=? WHERE id = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getSubjectt());
			jdbc_util.stmt.setString(2, a.getContent());
			jdbc_util.stmt.setString(3, a.getEndtime());
			jdbc_util.stmt.setString(4, a.getLasttime());
			jdbc_util.stmt.setString(5, a.getStarttime());
			jdbc_util.stmt.setString(6, a.getId());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}
}
