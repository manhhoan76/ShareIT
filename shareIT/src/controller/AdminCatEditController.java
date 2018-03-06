package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.dao.CatDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminCatEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCatEditController() {
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
	HttpSession session = request.getSession();
		if (AuthUtil.checkUser(request) == false) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			CatDAO catDao = new CatDAO();
			int cid = Integer.parseInt(request.getParameter("cid"));
			request.setAttribute("objCat", catDao.getItem(cid));
			request.setAttribute("listCat", catDao.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
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
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("cid"));
		CatDAO catDao = new CatDAO();
		Category itemCat = catDao.getItem(id);
		String nameCat = request.getParameter("nameCat");
		int idParent = Integer.parseInt(request.getParameter("danhmuccha"));
		System.out.println(id+" " + nameCat + " "+idParent);
		Category objCat = catDao.checkCat(nameCat,idParent);
		if (objCat != null) {
			response.sendRedirect(request.getContextPath() + "/admin/cat/edit?cid="+id+"&msg=1");
			return;
		} else {
			if (catDao.editCat(id,nameCat,idParent) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/cat/edit?msg=0");
				return;
			}
		}
	}
}
