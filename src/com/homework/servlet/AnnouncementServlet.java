package com.homework.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.dao.AnnouncementJdbc;
import com.homework.model.Announcement;

/**
 * 登录、注销
 */
public class AnnouncementServlet extends BaseServlet {
	AnnouncementJdbc jdbc = new AnnouncementJdbc();

	public void addAndUpdate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {

		String id = req.getParameter("id");
		String content = req.getParameter("editorValue");
		String starttime_str = req.getParameter("starttime");
		String endtime_str = req.getParameter("endtime");
		String subject = req.getParameter("subject");
		Announcement announcement = new Announcement();
		announcement.setState(1);
		announcement.setSubjectt(subject);
		announcement.setContent(content);
		announcement.setStarttime(starttime_str);
		announcement.setEndtime(endtime_str);
		announcement.setLasttime(new Date().toString());
		if ("".equals(id) || null == id) {
			// insert
			announcement.setId(UUID.randomUUID().toString().substring(0, 8));
			jdbc.insert(announcement);
		} else {
			// update
			announcement.setId(id);
			jdbc.update(announcement);
		}
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date starttime = dateFormat.parse(starttime_str);
//		Date endtime = dateFormat.parse(endtime_str);
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(starttime));
		// 1可用 0过时
		List<Announcement> list = jdbc.selectAll();
		req.setAttribute("list", list);
		res.getWriter().write("<script>parent.window.location.href='jsp/manager/manager.jsp'</script>");
//		req.getRequestDispatcher("/jsp/manager/manager.jsp").forward(req, res);
	}

	public void list(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		List<Announcement> list = jdbc.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager/m_announcement.jsp").forward(req, res);
	}

	public void query(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String id = req.getParameter("id");
		Announcement announcement = new Announcement();
		announcement.setId(id);
		Announcement select = jdbc.selectById(announcement);
		req.setAttribute("announcement", select);
		req.getRequestDispatcher("/jsp/manager/m_addannouncement.jsp").forward(req, res);
	}

	// post
	public void delete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, ParseException {
		String id = req.getParameter("id");
		Announcement announcement = new Announcement();
		announcement.setId(id);
		boolean delete = jdbc.delete(announcement);
		if (delete) {
			res.getOutputStream().write("error".getBytes());
		} else
			res.getOutputStream().write("success".getBytes());
	}
}
