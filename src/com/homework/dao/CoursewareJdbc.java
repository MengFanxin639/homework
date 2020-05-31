package com.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.homework.model.Course;
import com.homework.model.Courseware;

public class CoursewareJdbc {
	JdbcUtil jdbc_util = new JdbcUtil();

	public void insert(Courseware a) {
		jdbc_util.Connect();
//isshow path type∆˙”√
		String sql = "INSERT INTO Courseware (Coursewareid,description,createTime) VALUES (?,?,?)";
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, a.getCoursewareid());
			jdbc_util.stmt.setString(2, a.getDescription());
			jdbc_util.stmt.setString(3, a.getCreatetime());
			jdbc_util.stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdbc_util.Close();

	}
//
//	public void updateByParame(String id, String parame, String parameValue) {
//		jdbc_util.Connect();
//		String sql = "UPDATE Courseware SET " + parame + "=? WHERE id = ?";
//		try {
//			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
//			jdbc_util.stmt.setString(1, parameValue);
//			jdbc_util.stmt.setString(2, id);
//			jdbc_util.stmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		jdbc_util.Close();
//	}

	public Courseware selectById(Courseware cla) {
		jdbc_util.Connect();
		String sql = "select * from Courseware where Coursewareid=?";
		Courseware a = null;
		try {
			jdbc_util.stmt = JdbcUtil.conn.prepareStatement(sql);
			jdbc_util.stmt.setString(1, cla.getCoursewareid());
			ResultSet rs = jdbc_util.stmt.executeQuery();
			while (rs.next()) {
				a = new Courseware();
				a.setDescription(rs.getString("description"));
				a.setCoursewareid(rs.getString("coursewareid"));
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
//	public List<Courseware> selectAll() {
//		jdbc_util.Connect();
//		String sql = "select * from Courseware";
//		List<com.homework.model.Course> list = new ArrayList<>();
//		try {
//			jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
//			ResultSet rs = jdbc_util.stmt.executeQuery();
//			Courseware a = null;
//			while (rs.next()) {
//				a = new Courseware();
//				a.setBeforeclear(rs.getString("beforelearn"));
//				a.setCoursewareid(rs.getString("coursewareid"));
//				a.setExam(rs.getString("exam"));
//				a.setId(rs.getString("id"));
//				a.setLearnbooks(rs.getString("learnbooks"));
//				a.setPlan(rs.getString("plan"));
//				a.setSimpleinfo(rs.getString("simpleinfo"));
//				a.setTeacherinfo(rs.getString("teacherinfo"));
//				a.setName(rs.getString("name"));
//				list.add(a);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		jdbc_util.Close();
//		return list;
//	}
}
