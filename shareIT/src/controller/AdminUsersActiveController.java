package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Users;
import model.dao.CatDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminUsersActiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsersActiveController() {
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
			id = Integer.parseInt(request.getParameter("uid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=4");
			return;
		}
		int active = 0;
		try {
			active = Integer.parseInt(request.getParameter("uactive"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=4");
			return;
		}
		System.out.println(id + "---" + active);
		PrintWriter out = response.getWriter();
		UserDAO userDAO = new UserDAO();
		Users objUser = userDAO.getItem(id);
		if (active == 0) {
			userDAO.ActiveUser(id);
			out.println("<a   onclick='active("+id+",1)' href='javascript:void(0)' >" + "<i class='icon-envelope'></i>" + "Block" + "</a>");
		} else {
			userDAO.BlockUser(id);
			out.println("<a   onclick='active("+id+",0)' href='javascript:void(0)' >" + "<i class='icon-envelope'></i>" + "Active" + "</a>");
		}

	}

}
