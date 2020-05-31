package com.homework.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.ClassJdbc;
import com.homework.dao.CourseJdbc;
import com.homework.dao.CoursewareJdbc;
import com.homework.model.Class;
import com.homework.model.Course;
import com.homework.model.Courseware;
import com.mysql.fabric.xmlrpc.base.Data;

public class CourseServlet extends BaseServlet {
	CourseJdbc jdbc = new CourseJdbc();
	ClassJdbc classJdbc = new ClassJdbc();
	CoursewareJdbc coursewareJdbc = new CoursewareJdbc();

	public void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("to");
		List<Class> classList = classJdbc.selectAll();
		req.setAttribute("classList", classList);
		System.out.println(classList);
		List<Course> courseList = jdbc.selectAll();
		req.setAttribute("courseList", courseList);

		req.getRequestDispatcher("/jsp/manager/" + page + ".jsp").forward(req, res);
	}

	public void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String field = req.getParameter("field");
		String id = req.getParameter("courseid");// courseid
		String editorValue = req.getParameter("editorValue");
		String name = req.getParameter("name");
		Course course = new Course(id);
		if ("course".equals(field)) {
			String courceid = UUID.randomUUID().toString().substring(0, 8);
			course.setId(id);
			course.setName(name);
			jdbc.insertIdAndName(course);
		}
		if ("simpleinfo".equals(field)) {
			jdbc.updateByParame(id, field, editorValue);
		}
		if ("teacherinfo".equals(field)) {
			jdbc.updateByParame(id, field, editorValue);
		}
		if ("beforelearn".equals(field)) {
			jdbc.updateByParame(id, field, editorValue);
		}
		if ("exam".equals(field)) {
			jdbc.updateByParame(id, field, editorValue);
		}
		if ("plan".equals(field)) {
			jdbc.updateByParame(id, field, editorValue);
		}
		if ("learnbooks".equals(field)) {
			jdbc.updateByParame(id, field, editorValue);
		}
		if ("coursewareid".equals(field)) {
			Courseware courseware = new Courseware();
			String coursewareid = UUID.randomUUID().toString().substring(0, 8);
			courseware.setCoursewareid(coursewareid);
			courseware.setCreatetime(new Data().toString());
			courseware.setDescription(editorValue);
			coursewareJdbc.insert(courseware);
			// Î¬»¤Ö÷Íâ¼ü

			jdbc.updateByParame(id, "coursewareid", coursewareid);
		}
		System.out.println(editorValue);
		Course currentCource = jdbc.selectById(course);
		List<Course> courseList = jdbc.selectAll();
		req.setAttribute("courseList", courseList);
		List<Class> classList = classJdbc.selectAll();
		req.setAttribute("classList", classList);
		req.setAttribute("course", currentCource);
		System.out.println("currentCource" + currentCource);
		req.getSession().setAttribute("classList", classList);
		res.getWriter().write("<script>parent.window.location.href='jsp/manager/manager.jsp'</script>");
//		req.getRequestDispatcher("/jsp/manager/manager.jsp").forward(req, res);
	}
}
