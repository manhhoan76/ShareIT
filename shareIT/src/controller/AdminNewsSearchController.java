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
import model.bean.Category;
import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminNewsSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		if (AuthUtil.checkUser(request) == false) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			String key = new String(request.getParameter("key").getBytes("ISO-8859-1"),"UTF-8");
			int idCat = Integer.parseInt(request.getParameter("catID"));
			
			CatDAO catDAO = new CatDAO();
			ArrayList<Category> listCatChil = (ArrayList<Category>) catDAO.getItemsChil(idCat);
			Users objUserInfor = (Users) session.getAttribute("userInfor");
			int sumNews = 0;
			NewsDAO newsDAO = new NewsDAO();
			if (idCat == 0) {
				if ("admin".equals(objUserInfor.getUsername())) {
					sumNews = newsDAO.countNewsSearchByKey(key);
				} else {
					sumNews = newsDAO.countNewsSearchByKeyAndUserID(key, objUserInfor.getId());
				}

			} else {
				if (listCatChil.size() == 0) {
					if ("admin".equals(objUserInfor.getUsername())) {
						sumNews = newsDAO.countNewsByCatId(idCat, key);
					} else {
						sumNews = newsDAO.countNewsByCatIdAndUserID(idCat, objUserInfor.getId(), key);
					}
				} else {
					if ("admin".equals(objUserInfor.getUsername())) {
						sumNews = newsDAO.countNewsByCatIdParent(idCat, key);
					} else {
						sumNews = newsDAO.countNewsByCatIdParentAndUserID(idCat, objUserInfor.getId(), key);
					}
				}
			}
			int sumPage = (int) Math.ceil((float) sumNews / Define.ROW_COUNT_ADMIN);
			request.setAttribute("sumPage", sumPage);
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			request.setAttribute("current_page", current_page);
			int offset = (current_page - 1) * Define.ROW_COUNT_ADMIN;
			if (idCat == 0) {
				if ("admin".equals(objUserInfor.getUsername())) {
					ArrayList<News> listNews = newsDAO.getItemsPaginationByKey(key, offset, Define.ROW_COUNT_ADMIN);
					request.setAttribute("listNews", listNews);
				} else {
					ArrayList<News> listNews = newsDAO.getItemsPaginationByKeyAndUserID(key, objUserInfor.getId(),
							offset, Define.ROW_COUNT_ADMIN);
					request.setAttribute("listNews", listNews);
				}

			} else {
				if (listCatChil.size() == 0) {
					if ("admin".equals(objUserInfor.getUsername())) {
						ArrayList<News> listNews = newsDAO.getItemsPaginationByIdCat(idCat, key, offset,
								Define.ROW_COUNT_ADMIN);
						request.setAttribute("listNews", listNews);
					} else {
						ArrayList<News> listNews = newsDAO.getItemsPaginationByIdCatAndIdUser(idCat,
								objUserInfor.getId(), key, offset, Define.ROW_COUNT_ADMIN);
						request.setAttribute("listNews", listNews);
					}
				} else {
					if ("admin".equals(objUserInfor.getUsername())) {
						ArrayList<News> listNews = new ArrayList<>();
						listNews = newsDAO.getItemsPaginationByIdCatParent(idCat, key,
								offset, Define.ROW_COUNT_ADMIN);
						request.setAttribute("listNews", listNews);
					} else {
						ArrayList<News> listNews = new ArrayList<>();
						listNews = newsDAO.getItemsPaginationByIdCatParentAndIdUser(
								idCat, objUserInfor.getId(), key, offset, Define.ROW_COUNT_ADMIN);
						request.setAttribute("listNews", listNews);
					}
				}
			}
			System.out.println(key);
			request.setAttribute("listCat", catDAO.getItems());
			request.setAttribute("key", key);
			request.setAttribute("catID", idCat);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/search.jsp");
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
