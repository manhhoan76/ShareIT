package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminCatDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCatDelController() {
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
			id = Integer.parseInt(request.getParameter("cid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=4");
			return;
		}

		System.out.println(id);
		CatDAO catDao = new CatDAO();
		Category objCat = (Category) catDao.getItem(id);
		NewsDAO newsDAO = new NewsDAO();
		if (objCat.getParent_id() == 0) {
			newsDAO.delNewsByCatIdParet(id);
			if (catDao.delCat(id) > 0) {
				// Thêm thành công
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
				return;
			} else {
				// thêm thất bại
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
				return;
			}
		} else {
			newsDAO.delNewsByCatID(id);

			if (catDao.delCat(id) > 0) {
				// Thêm thành công
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
				return;
			} else {
				// thêm thất bại
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
				return;
			}
		}
	}

}
