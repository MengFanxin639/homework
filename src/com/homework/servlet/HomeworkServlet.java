package com.homework.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.ClassJdbc;
import com.homework.dao.HomeworkJdbc;
import com.homework.dao.QuestionJdbc;
import com.homework.model.Class;
import com.homework.model.Homework;
import com.homework.model.Question;
import com.homework.model.User;

public class HomeworkServlet extends BaseServlet {
	HomeworkJdbc jdbc = new HomeworkJdbc();
	ClassJdbc classJdbc = new ClassJdbc();
	QuestionJdbc questionJdbc = new QuestionJdbc();

	public void studentHomework(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String type = req.getParameter("1");
		if ("1".equals(type)) {
			// 当前作业

		} else {

		}
		List<Homework> selectAll = jdbc.selectAll();
		req.setAttribute("list", selectAll);
		req.getRequestDispatcher("/jsp/student/s_homework.jsp").forward(req, res);

	}

	public void addquestiontohomework(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String currenthomeworkid = (String) req.getSession().getAttribute("currenthomeworkid");
		String questionId = (String) req.getParameter("id");
		System.err.println(currenthomeworkid + "/t" + questionId);
		Question question = new Question();
		question.setHomeworkid(currenthomeworkid);
		question.setId(questionId);
		System.err.println(question);
		questionJdbc.updateHomeworkId(question);
		List<Question> selectByHomeworkId = questionJdbc.selectByHomeworkId(question);
		req.setAttribute("currenthomework", selectByHomeworkId);
		List<Question> list = questionJdbc.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_questiontohomework.jsp").forward(req, res);
	}

	public void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String simpleinfo = req.getParameter("simpleinfo");
		String starttime = req.getParameter("starttime");
		String endtime = req.getParameter("endtime");
		String classid = req.getParameter("classid");
		User user = (User) req.getSession().getAttribute("user");
		// 未登录打回
		if (null == user) {
			res.getOutputStream().print("<script>parent.window.location.href='jsp/login.jsp'</script>");
			return;
		}
		System.out.println(user);
		Homework homework = new Homework();
		homework.setContent(simpleinfo);
		homework.setCreater(user.getName());
		homework.setEndtime(endtime);
		homework.setName(name);
		homework.setType("普通作业");
		// 作业状态 1完成 2待提交
		homework.setState(2);
		// 权限:1允许查询 0不允许
		homework.setQuery(1);
		homework.setStarttime(starttime);
		homework.setSimpleinfo(simpleinfo);
		String homeworkid = UUID.randomUUID().toString().substring(0, 8);
		homework.setHomeworkid(homeworkid);
		jdbc.insert(homework);
		List<Question> list = questionJdbc.selectAll();
		req.setAttribute("list", list);
		req.getSession().setAttribute("currenthomeworkid", homeworkid);
		req.getRequestDispatcher("/jsp/manager/m_questiontohomework.jsp").forward(req, res);
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Homework> temp = new ArrayList<>();
		List<Homework> list = jdbc.selectAll();
		for (Homework homework : list) {
			if (!homework.getState().equals("3")) {
				temp.add(homework);
			}
		}
		req.setAttribute("list", temp);
		req.getRequestDispatcher("/jsp/manager/m_homework.jsp").forward(req, res);
	}

	/**
	 * 回收站
	 */
	public void reuse(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Homework> list = jdbc.selectByState(3);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_reuse.jsp").forward(req, res);
	}

	public void query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		Homework cla = new Homework();
		cla.setHomeworkid(id);
		Homework select = jdbc.selectById(cla);
		req.setAttribute("homework", select);
		List<Class> classList = classJdbc.selectAll();
		req.setAttribute("classList", classList);
		req.getRequestDispatcher("/jsp/manager/m_newhomework.jsp").forward(req, res);
	}

	// post
	public void delete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String id = req.getParameter("id");
		Homework a = new Homework();
		a.setHomeworkid(id);
		// 维护主键关系
		// 作业状态 1完成 2待提交 3已删除
		try {
			a.setState(3);
			Homework selectById = jdbc.selectById(a);
			selectById.setState(3);
			jdbc.update(selectById);
		} catch (Exception e) {
			e.printStackTrace();
			res.getOutputStream().write("error".getBytes());
		}
		res.getOutputStream().write("success".getBytes());
	}

}
