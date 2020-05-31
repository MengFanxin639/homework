package com.homework.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.ClassJdbc;
import com.homework.model.Class;
import com.homework.model.User;

public class ClassServlet extends BaseServlet {
	ClassJdbc jdbc = new ClassJdbc();

	public void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("to");
		List<com.homework.model.Class> list = jdbc.selectAll();
		req.setAttribute("list", list);
		req.getSession().setAttribute("classList", list);
		req.getRequestDispatcher("/jsp/manager/" + page + ".jsp").forward(req, res);
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Class> list = jdbc.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_studentsort.jsp").forward(req, res);
	}

	public void query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		Class cla = new Class();
		cla.setClassid(id);
		Class select = jdbc.selectById(cla);
		req.setAttribute("cla", select);
		req.getRequestDispatcher("/jsp/manager/m_addstudentsort.jsp").forward(req, res);
	}

	// post
	public void delete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String id = req.getParameter("id");
		Class a = new Class();
		a.setClassid(id);
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
		User user = (User) req.getSession().getAttribute("user");
		Class cla = new Class();
		cla.setName(name);
		cla.setClassname(classname);
		if ("".equals(id) || null == id) {
			// insert
			cla.setCourseid("");
			cla.setClassid(UUID.randomUUID().toString().substring(0, 8));
			jdbc.insert(cla);
		} else {
			// update
			cla.setClassid(id);
			jdbc.update(cla);
		}
		List<Class> list = jdbc.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_studentsort.jsp").forward(req, res);

	}
}
