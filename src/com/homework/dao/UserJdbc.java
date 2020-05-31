package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homework.model.Announcement;
import com.homework.model.Class;
import com.homework.model.User;

public class UserJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public List<User> selectAllStudent() {
		jdbc_util.Connect();
		String sql = "select * from student where type=?";
		List<User> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setInt(1, 0);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setAvatarpath(rs.getString("avatarpath"));
				u.setClassid(rs.getString("classid"));
				u.setEmail(rs.getString("email"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setStudentid(rs.getString("studentid"));
				u.setTel(rs.getString("tel"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return list;
	}

	public User selectById(User u) {
		jdbc_util.Connect();
		String sql = "select * from student where studentid=?";
		User user = null;
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, u.getStudentid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setAvatarpath(rs.getString("avatarPath"));
				user.setClassid(rs.getString("classId"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStudentid(rs.getString("studentid"));
				user.setTel(rs.getString("tel"));
				user.setType(rs.getInt("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return user;
	}

	public boolean delete(User user) {
		boolean flag = false;
		// 教师
		if (user.getType() == 1) {
			flag = deleteTeacher(user);
		} else {
			flag = deleteStudent(user);
		}
		return flag;
	}

	private boolean deleteTeacher(User user) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM student WHERE studentid = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, user.getStudentid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return flag;

	}

	private boolean deleteStudent(User user) {
		jdbc_util.Connect();
		boolean flag = false;
		String sql = "DELETE FROM student WHERE studentId = ? ";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, user.getStudentid());
			flag = JdbcUtil.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		jdbc_util.Close();
		return flag;
	}

	public boolean querySubmit(User user) {
		jdbc_util.Connect();
		String sql = "select * from student where studentid=? and password=?";
		Integer number = 0;
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
//			jdbc_util.stmt.setString(1, homework_title);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();
		if (number == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void insertUser(User user) {
		// 教师
		if (user.getType() == 1) {
			insertTeacher(user);
		} else {
			insertStudent(user);
		}
	}

	private void insertStudent(User user) {
		jdbc_util.Connect();

		String sql;
		sql = "INSERT INTO STUDENT (studentid,name,classid,type) VALUES (?,?,?,?)";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, user.getStudentid());
			jdbc_util.stmt.setString(2, user.getName());
			jdbc_util.stmt.setString(3, user.getClassid());
			jdbc_util.stmt.setInt(4, user.getType());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();
	}

	private void insertTeacher(User user) {
		jdbc_util.Connect();

		String sql;
		sql = "INSERT INTO STUDENT VALUES (?)";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();
	}

	public void updateUser(User user) {
		// 教师
		if (user.getType() == 1) {
			updateTeacher(user);
		} else {
			updateStudent(user);
		}

	}

	private void updateStudent(User user) {
		jdbc_util.Connect();
		String sql = "UPDATE Student SET name=?,classid=?,type? WHERE studentid = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, user.getName());
			jdbc_util.stmt.setString(2, user.getClassid());
			jdbc_util.stmt.setInt(3, user.getType());
			jdbc_util.stmt.setString(4, user.getStudentid());
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();

	}

	private void updateTeacher(User user) {

	}

}
