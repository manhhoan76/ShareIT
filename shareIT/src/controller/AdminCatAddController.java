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
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminCatAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCatAddController() {
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
			CatDAO catDAO = new CatDAO();
			request.setAttribute("listCat", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
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
		int id_dmt = Integer.parseInt(request.getParameter("danhmuc"));
		String nameCat = request.getParameter("nameCat");
		System.out.println(nameCat + " " + id_dmt);
		HttpSession session = request.getSession();
		Users objUserInfor = (Users) session.getAttribute("userInfor");
		CatDAO catDAO = new CatDAO();
		if (catDAO.getCatParent(id_dmt) == null  && objUserInfor.getId() != 1) {
			response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=2");
			return;
		} else {
			if (catDAO.checkCat(nameCat, id_dmt) != null) {
				// đã tồn tại user
				response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=1");
				return;
			} else {
				if (catDAO.addCat(nameCat, id_dmt) > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat?msg=1");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=0");
					return;
				}
			}
		}
	}
}
