package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Define;
import model.bean.Message;
import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.MessageDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminUsersMessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsersMessController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (AuthUtil.checkUser(request) == false) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			int uid1 = Integer.parseInt(request.getParameter("uid1"));
			int uid2 = Integer.parseInt(request.getParameter("uid2"));
			UserDAO usersDAO = new UserDAO();
			MessageDAO messageDAO = new MessageDAO();
			request.setAttribute("objUser2", usersDAO.getItem(uid2));
			request.setAttribute("listMess", messageDAO.getItemsMess(uid1, uid2));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/users/mess.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String textChat = request.getParameter("utextChat");
		int uid1 = Integer.parseInt(request.getParameter("uuid1"));
		int uid2 = Integer.parseInt(request.getParameter("uuid2"));
		UserDAO userDAO = new UserDAO();
		Users objUser1 = (Users) userDAO.getItem(uid1);
		MessageDAO messageDAO = new MessageDAO();
		Message objMess = new Message(0, uid1, uid2, textChat, null);
		messageDAO.addMess(objMess);
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String date_create = sdf.format(date1);
		PrintWriter out = response.getWriter();
		out.println("<div class='media-body'>" + "<div class='media'>" + "<a class='pull-left' href='#'>"
				+ "<img style='width: 45px; height: 45px;' class='media-object img-circle'  src='"+request.getContextPath()+"/files/"+ objUser1.getPicture() + "'>" + "</a>" + "<div class='media-body'>" + textChat + "<br>"
				+ "<small class='text-muted'>" + objUser1.getFullname() + " | " + date_create + "</small>" + "<hr>"
				+ "</div>" + "</div>" + "</div>");

	}

}
