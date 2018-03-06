package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Users;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminNewsActiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsActiveController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("nid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=4");
			return;
		}
		int active = 0;
		try {
			active = Integer.parseInt(request.getParameter("nactive"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=4");
			return;
		}
		PrintWriter out = response.getWriter();
		NewsDAO newsDAO = new NewsDAO();
		if (active == 0) {
			newsDAO.activeNews(id);
			out.println("<a onclick='active("+id+",1)'  class='btn btn-success' href='javascript:void(0)' ><i class='icon_check_alt2'></i></a>");
		} else {
			newsDAO.blockNews(id);
			out.println("<a onclick='active("+id+",0)' class='btn btn-warning' href='javascript:void(0)' ><i class='icon_upload'></i></a>");
		}

	}

}
