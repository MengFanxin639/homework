package com.homework.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.ClassJdbc;
import com.homework.dao.UserJdbc;
import com.homework.model.Class;
import com.homework.model.User;

/**
 * 登录、注销
 */
public class LoginServlet extends BaseServlet {
	private String jsp_prefix = "/jsp/";
	UserJdbc userJdbc = new UserJdbc();
	ClassJdbc classJdbc = new ClassJdbc();

	public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String studentid = req.getParameter("studentid");
		String password = req.getParameter("password");
		// Student0 type
		String type = req.getParameter("type");

		User u = new User();
		u.setStudentid(studentid);
		u.setPassword(password);
		User query = userJdbc.selectById(u);
		boolean exist = query == null ? false : true;
		req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
		List<Class> list = classJdbc.selectAll();
		req.setAttribute("classList", list);
		if ("Teacher".equals(type)) {
			if (exist) {
				req.getSession().setAttribute("user", query);
				req.getRequestDispatcher(jsp_prefix + "manager/manager.jsp").forward(req, res);
			} else {
				req.setAttribute("errMsg", "用户不存在");
				req.getRequestDispatcher(jsp_prefix + "login.jsp").forward(req, res);
			}

		}
		if ("Student".equals(type)) {
			if (exist) {
				req.getSession().setAttribute("user", query);
				req.getRequestDispatcher(jsp_prefix + "student/student.jsp").forward(req, res);
			} else {
				req.setAttribute("errMsg", "用户不存在");
				req.getRequestDispatcher(jsp_prefix + "login.jsp").forward(req, res);
			}
		}

	}
}
