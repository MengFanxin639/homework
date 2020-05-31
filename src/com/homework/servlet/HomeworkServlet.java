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
			// ��ǰ��ҵ

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
		// δ��¼���
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
		homework.setType("��ͨ��ҵ");
		// ��ҵ״̬ 1��� 2���ύ
		homework.setState(2);
		// Ȩ��:1�����ѯ 0������
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
	 * ����վ
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
		// ά��������ϵ
		// ��ҵ״̬ 1��� 2���ύ 3��ɾ��
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
