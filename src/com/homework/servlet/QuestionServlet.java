package com.homework.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.BlankJdbc;
import com.homework.dao.ChoiceJdbc;
import com.homework.dao.FileJdbc;
import com.homework.dao.JudgeJdbc;
import com.homework.dao.QuestionJdbc;
import com.homework.model.Blank;
import com.homework.model.Choice;
import com.homework.model.File;
import com.homework.model.Judge;
import com.homework.model.Question;

/**
 * ����:����ѡ����գ��жϣ��ļ��ϴ�����
 */
public class QuestionServlet extends BaseServlet {
	QuestionJdbc jdbc = new QuestionJdbc();
	ChoiceJdbc choiceJdbc = new ChoiceJdbc();
	BlankJdbc blankJdbc = new BlankJdbc();
	FileJdbc fileJdbc = new FileJdbc();
	JudgeJdbc judgeJdbc = new JudgeJdbc();

	public void page(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("to");
//		List<com.homework.model.Class> list = jdbc.selectAll();
//		req.setAttribute("list", list);
//		req.getSession().setAttribute("classList", list);
		req.getRequestDispatcher("/jsp/manager/" + page + ".jsp").forward(req, res);
	}

	public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Question> list = jdbc.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_question.jsp").forward(req, res);
	}

	public void query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		Question question = new Question();
		question.setId(id);
		Question selectById = jdbc.selectById(question);
		String type = selectById.getType();
		String address = "/jsp/manager/";
		if ("1".equals(type)) {
			Choice c = choiceJdbc.selectById(new Choice(selectById.getChoiceid()));
			selectById.setObject(c);
			selectById.setType("ѡ����");
			address += "m_addchoice.jsp";
		}
		if ("2".equals(type)) {
			Blank b = blankJdbc.selectById(new Blank(selectById.getBlankidid()));
			selectById.setObject(b);
			selectById.setType("�����");
			address += "m_addblank.jsp";
		}
		if ("3".equals(type)) {
			Judge j = judgeJdbc.selectById(new Judge(selectById.getJudgeid()));
			selectById.setObject(j);
			selectById.setType("�ж���");
			address += "m_addjudge.jsp";
		}
		if ("4".equals(type)) {
			File f = fileJdbc.selectById(new File(selectById.getFileid()));
			selectById.setObject(f);
			selectById.setType("�ļ��ϴ���");
			address += "m_addfile.jsp";
		}
		req.setAttribute("question", selectById);
		req.getRequestDispatcher(address).forward(req, res);
	}

	// post
	public void delete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String id = req.getParameter("id");
		/**
		 * ������������� 1ѡ�� 2 ��� 3 �ж� 4�ļ��ϴ�
		 */
		Question selectQuestion = jdbc.selectById(new Question(id));
		System.err.println(selectQuestion);
		String type = selectQuestion.getType();
		boolean delete = false;
		// ��ɾ�����
		jdbc.delete(selectQuestion);
		if ("1".equals(type)) {
			delete = choiceJdbc.delete(new Choice(id));
		}
		if ("2".equals(type)) {
			delete = blankJdbc.delete(new Blank(id));
		}
		if ("3".equals(type)) {
			delete = judgeJdbc.delete(new Judge(id));
		}
		if ("4".equals(type)) {
			delete = fileJdbc.delete(new File(id));
		}
		delete = jdbc.delete(new Question(id));
		if (delete) {
			res.getOutputStream().write("error".getBytes());
		} else
			res.getOutputStream().write("success".getBytes());
	}

	public void addAndUpdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		/**
		 * ������������� 1ѡ�� 2 ��� 3 �ж� 4�ļ��ϴ�
		 */
		String q = req.getParameter("q");
		String questionId = req.getParameter("id");
		String answer = req.getParameter("answer");
		String simpleinfo = req.getParameter("simpleinfo");
		String description = req.getParameter("editorValue");
		Question question = new Question();

		if ("1".equals(q)) {
			Choice choice = new Choice();
			choice.setAnswer(answer);
			choice.setDescription(description);
			choice.setSimpleinfo(simpleinfo);
			choice.setCount(1);
			String choiceId = UUID.randomUUID().toString().substring(0, 8);
			if (questionId == null || "".equals(questionId)) {
				// add
				choice.setChoiceid(choiceId);
				choiceJdbc.insert(choice);
				// ά�������

				question.setId(UUID.randomUUID().toString().substring(0, 8));
				question.setType("1");
				question.setChoiceid(choiceId);
				jdbc.insert(question);
			} else {
				// update

				choice.setChoiceid(choiceId);
				choiceJdbc.update(choice);

			}
		} else if ("2".equals(q)) {
			String count = req.getParameter("count");
			Blank blank = new Blank();
			blank.setCount(Integer.valueOf(count));
			blank.setDescription(description);
			blank.setSimpleinfo(simpleinfo);
			String id = UUID.randomUUID().toString().substring(0, 8);
			blank.setAnswer(answer);
			if (questionId == null || "".equals(questionId)) {
				blank.setBlankidid(id);
				blankJdbc.insert(blank);

				// ά�������
				question.setId(UUID.randomUUID().toString().substring(0, 8));
				question.setType("2");
				question.setBlankidid(id);
				jdbc.insert(question);
			} else {
				// update
				blank.setBlankidid(id);
				blankJdbc.update(blank);
			}
		} else if ("3".equals(q)) {
			Judge judge = new Judge();
			judge.setAnswer(answer);
			judge.setSimpleinfo(simpleinfo);
			judge.setDescription(description);
			String id = UUID.randomUUID().toString().substring(0, 8);
			if (questionId == null || "".equals(questionId)) {
				judge.setJudgeid(id);
				judgeJdbc.insert(judge);

				// ά�������
				question.setId(UUID.randomUUID().toString().substring(0, 8));
				question.setType("3");
				question.setJudgeid(id);
				jdbc.insert(question);
			} else {
				// update
				judge.setJudgeid(id);
				judgeJdbc.update(judge);
			}
		} else if ("4".equals(q)) {
			File file = new File();
			file.setAnswer(answer);
			file.setDescription(description);// path
			file.setSimpleinfo(simpleinfo);
			String id = UUID.randomUUID().toString().substring(0, 8);
			if (questionId == null || "".equals(questionId)) {
				file.setFileid(id);
				fileJdbc.insert(file);

				// ά�������
				question.setId(UUID.randomUUID().toString().substring(0, 8));
				question.setType("4");
				question.setFileid(id);
				jdbc.insert(question);
			} else {
				// update
				file.setFileid(id);
				fileJdbc.update(file);
			}
		}

		List<Question> list = jdbc.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_question.jsp").forward(req, res);

	}
}
