package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminNewsSelectCatEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsSelectCatEditController() {
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
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=4");
			return;
		}
		int nid = 0;
		try {
			nid = Integer.parseInt(request.getParameter("nnid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=4");
			return;
		}
		System.out.println(id);
		PrintWriter out = response.getWriter();
		CatDAO catDAO = new CatDAO();
		NewsDAO newsDAO = new NewsDAO();
		News objNew = (News) newsDAO.getItem(nid);
		String select="";
		ArrayList<Category> listCatChil = catDAO.getItemsChil(id);
		if (listCatChil.size() != 0) {
			for (Category objCat : listCatChil) {
				 if (objNew.getCat_id() == objCat.getId()){
						select = "selected ='selected'";
					}else 
					{
						select="";
					}
				out.println(" <option "+select+" value='"+objCat.getId()+"'>"+objCat.getName()+"</option>");
			}
			out.println(" <option value='0'>--Tin chung--</option>");
		}
		else {
			out.println(" <option value='0'>--Không có danh mục con--</option>");
		}
	}
}
