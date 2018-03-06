package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CommentVideo;
import model.dao.CommentDAO;
import model.dao.CommentVideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminCommentVideoActiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCommentVideoActiveController() {
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
			id = Integer.parseInt(request.getParameter("covid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/commentVideo?msg=4");
			return;
		}
		int active = 0;
		try {
			active = Integer.parseInt(request.getParameter("covactive"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/commentVideo?msg=4");
			return;
		}
		System.out.println(id + "---" + active);
		PrintWriter out = response.getWriter();
		CommentVideoDAO commentDAO = new CommentVideoDAO();
		if (active == 0) {
			commentDAO.activecommentvideo(id);
			out.println("<a onclick='activeVideo("+id+",1)'  class='btn btn-success' href='javascript:void(0)' ><i class='icon_check_alt2'></i></a>");
		} else {
			commentDAO.blockcommentvideo(id);
			out.println("<a onclick='activeVideo("+id+",0)' class='btn btn-warning' href='javascript:void(0)' ><i class='icon_upload'></i></a>");
		}

	}

}
