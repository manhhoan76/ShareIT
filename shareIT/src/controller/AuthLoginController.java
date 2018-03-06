package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;
import model.dao.CatDAO;
import model.dao.UserDAO;
import util.StringLibrary;

/**
 * Servlet implementation class AdminIndexController
 */
public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthLoginController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDAO usersDAO = new UserDAO();
		StringLibrary library = new StringLibrary();
		String username = request.getParameter("username");
		String password = library.md5(request.getParameter("password"));
		HttpSession session = request.getSession();
		Users objUser = usersDAO.getItem(username, password);
		if (usersDAO.getItem(username, password) != null) {
			if (objUser.getActive() == 0) {
				response.sendRedirect(request.getContextPath() + "/login?msg=1");
			} else {
				// Thêm thành công
				session.setAttribute("userInfor", objUser);
				response.sendRedirect(request.getContextPath() + "/admin/index");
				return;
			}
		} else {
			// thêm thất bại
			response.sendRedirect(request.getContextPath() + "/login?msg=0");
			return;
		}
	}
}
