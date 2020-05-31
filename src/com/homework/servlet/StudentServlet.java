package com.homework.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.AnnouncementJdbc;
import com.homework.dao.ClassJdbc;
import com.homework.dao.CourseJdbc;
import com.homework.dao.UserJdbc;
import com.homework.model.Announcement;
import com.homework.model.Class;
import com.homework.model.Course;
import com.homework.model.Courseware;
import com.homework.model.User;

/**
 * 方法名称以student打头的为学生身份请求的资源
 */
public class StudentServlet extends BaseServlet {
	UserJdbc jdbc = new UserJdbc();
	AnnouncementJdbc announcementJdbc = new AnnouncementJdbc();
	ClassJdbc classJdbc = new ClassJdbc();
	CourseJdbc courseJdbc = new CourseJdbc();

	// post
	public void delete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String id = req.getParameter("id");
		User a = new User();
		a.setStudentid(id);
		a.setType(0);
		boolean delete = jdbc.delete(a);
		if (delete) {
			res.getOutputStream().write("error".getBytes());
		} else
			res.getOutputStream().write("success".getBytes());
	}

	public void addAndUpdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String classname = req.getParameter("classname");
		String classid = req.getParameter("classid");
		User cla = new User();
		cla.setName(name);
		cla.setClassid(classid);
		if ("".equals(id) || null == id) {
			// insert
			cla.setStudentid(UUID.randomUUID().toString().substring(0, 8));
			cla.setType(0);
			jdbc.insertUser(cla);
		} else {
			// update
			cla.setType(0);
			cla.setStudentid(id);
			cla.setClassid(classid);
			jdbc.updateUser(cla);
		}
		List<User> list = jdbc.selectAllStudent();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_studentmanage.jsp").forward(req, res);

	}

	public void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("to");
		req.getRequestDispatcher("/jsp/student/" + page + ".jsp").forward(req, res);
	}

	public void query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		User cla = new User();
		cla.setStudentid(id);
		User select = jdbc.selectById(cla);
		req.setAttribute("student", select);
		req.setAttribute("classList", classJdbc.selectAll());
		System.out.println(select);
		req.getRequestDispatcher("/jsp/manager/m_addstudent.jsp").forward(req, res);
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<User> list = jdbc.selectAllStudent();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_studentmanage.jsp").forward(req, res);
	}

	public void studentHome(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Announcement> selectAll = announcementJdbc.selectAll();
		req.setAttribute("list", selectAll);
		req.getRequestDispatcher("/jsp/student/s_home.jsp").forward(req, res);
	}

	// 课程信息查看
	public void studentScan(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String type = req.getParameter("type");
		Course course = courseJdbc.selectAll().get(0);
		if ("simpleinfo".equals(type)) {
			res.getOutputStream().write(course.getSimpleinfo().getBytes("utf-8"));
		}
		if ("teacherinfo".equals(type)) {
			res.getOutputStream().write(course.getTeacherinfo().getBytes("utf-8"));
		}
		if ("beforelear".equals(type)) {
			res.getOutputStream().write(course.getBeforeclear().getBytes("utf-8"));

		}
		if ("exam".equals(type)) {
			res.getOutputStream().write(course.getExam().getBytes("utf-8"));
		}
		if ("plan".equals(type)) {
			res.getOutputStream().write(course.getPlan().getBytes("utf-8"));
		}
		if ("learnbooks".equals(type)) {
			res.getOutputStream().write(course.getLearnbooks().getBytes("utf-8"));

		}
		if ("courseware".equals(type)) {
			Courseware object = (Courseware) course.getObject();
			res.getOutputStream().write(object.getDescription().getBytes("utf-8"));

		}

	}

}
