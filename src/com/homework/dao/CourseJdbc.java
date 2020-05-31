package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.homework.model.Course;
import com.homework.model.Courseware;

public class CourseJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();
	CoursewareJdbc coursewareJdbc = new CoursewareJdbc();

	public void insertIdAndName(Course a) {
		jdbc_util.Connect();

		String sql = "INSERT INTO Course (id,name) VALUES (?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getId());
			jdbc_util.stmt.setString(2, a.getName());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}

	public void updateByParame(String id, String parame, String parameValue) {
		jdbc_util.Connect();
		String sql = "UPDATE Course SET " + parame + "=? WHERE id = ?";
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, parameValue);
			jdbc_util.stmt.setString(2, id);
			jdbc_util.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
	}

	public Course selectById(Course cla) {
		jdbc_util.Connect();
		String sql = "select * from Course where id=?";
		Course a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getId());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Course();
				a.setBeforeclear(rs.getString("beforelearn"));
				a.setCoursewareid(rs.getString("coursewareid"));
				a.setExam(rs.getString("exam"));
				a.setId(rs.getString("id"));
				a.setLearnbooks(rs.getString("learnbooks"));
				a.setPlan(rs.getString("plan"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setTeacherinfo(rs.getString("teacherinfo"));
				a.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		return a;
	}

//
//	public boolean delete(Course a) {
//		jdbc_util.Connect();
//		boolean flag = false;
//		String sql = "DELETE FROM Course WHERE Courseid = ? ";
//		try {
//			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
//			jdbc_util.stmt.setString(1, a.getCourseid());
//			flag = JdbcUtil.stmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		jdbc_util.Close();
//		return flag;
//	}
//
//
	public List<Course> selectAll() {
		jdbc_util.Connect();
		String sql = "select * from Course";
		List<com.homework.model.Course> list = new ArrayList<>();
		try {
			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
			ResultSet rs = jdbc_util.stmt.executeQuery();
			Course a = null;
			while (rs.next()) {
				a = new Course();
				a.setBeforeclear(rs.getString("beforelearn"));
				a.setCoursewareid(rs.getString("coursewareid"));
				a.setExam(rs.getString("exam"));
				a.setId(rs.getString("id"));
				a.setLearnbooks(rs.getString("learnbooks"));
				a.setPlan(rs.getString("plan"));
				a.setSimpleinfo(rs.getString("simpleinfo"));
				a.setTeacherinfo(rs.getString("teacherinfo"));
				a.setName(rs.getString("name"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbc_util.Close();
		for (Course course : list) {
			if (!"".equals(course.getCoursewareid())) {
				Courseware selectById = coursewareJdbc.selectById(new Courseware(course.getCoursewareid()));
				course.setObject(selectById);
			}
		}
		return list;
	}

}
