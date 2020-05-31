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
import com.homework.dao.HomeworkJdbc;
import com.homework.dao.QuestionJdbc;
import com.homework.dao.UserJdbc;
import com.homework.model.Announcement;
import com.homework.model.Class;
import com.homework.model.Course;
import com.homework.model.Courseware;
import com.homework.model.Homework;
import com.homework.model.Question;
import com.homework.model.User;

/**
 * 方法名称以student打头的为学生身份请求的资源
 */
public class StudentexamscoreServlet extends BaseServlet {
	QuestionJdbc questionJdbc = new QuestionJdbc();

	public void studentToExam(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String homeworkId = req.getParameter("id");
		Question question = new Question();
		question.setHomeworkid(homeworkId);
		List<Question> questions = questionJdbc.selectByHomeworkId(question);
		System.out.println(questions);
		req.setAttribute("list", questions);
		req.getRequestDispatcher("/jsp/student/s_exam.jsp").forward(req, res);

	}

}
