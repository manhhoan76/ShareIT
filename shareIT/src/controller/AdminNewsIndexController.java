package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Define;
import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminNewsIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsIndexController() {
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
			Users objUserInfor = (Users) session.getAttribute("userInfor");
			int sumNews = 0;
			NewsDAO newsDAO = new NewsDAO();
			if ("admin".equals(objUserInfor.getUsername())) {
				sumNews = newsDAO.countNews();
			} else {
				sumNews = newsDAO.countNewsByUserId(objUserInfor.getId());
			}
			int sumPage = (int) Math.ceil((float) sumNews / Define.ROW_COUNT_ADMIN);
			request.setAttribute("sumPage", sumPage);
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			request.setAttribute("current_page", current_page);
			int offset = (current_page - 1) * Define.ROW_COUNT_ADMIN;

			if ("admin".equals(objUserInfor.getUsername())) {
				ArrayList<News> listNews = new ArrayList<>();
				listNews = newsDAO.getItemsPagination(offset, Define.ROW_COUNT_ADMIN);
				request.setAttribute("listNews", listNews);
			} else {
				ArrayList<News> listNews = new ArrayList<>();
				listNews = newsDAO.getItemsPaginationById(objUserInfor.getId(), offset,
						Define.ROW_COUNT_ADMIN);
				request.setAttribute("listNews", listNews);
			}
			CatDAO catDAO = new CatDAO();
			request.setAttribute("listCat", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
