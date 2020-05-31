package com.homework.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.ClassJdbc;
import com.homework.dao.UserJdbc;
import com.homework.model.Class;

public class ManagerServlet extends BaseServlet {
	UserJdbc userJdbc = new UserJdbc();
	ClassJdbc classJdbc = new ClassJdbc();

//	manager/m_teachermanage
	public void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("to");
		List<Class> selectAll = classJdbc.selectAll();
		req.setAttribute("classList", selectAll);
		req.getRequestDispatcher("/jsp/manager/" + page + ".jsp").forward(req, res);

	}
}
