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
import model.bean.Video;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminVideoSelectCatEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminVideoSelectCatEditController() {
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
		int vid = 0;
		try {
			vid = Integer.parseInt(request.getParameter("vvid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=4");
			return;
		}
		System.out.println(id);
		PrintWriter out = response.getWriter();
		VideoDAO videoDAO = new VideoDAO();
		CatDAO catDAO = new CatDAO();
		Video objVideo = (Video) videoDAO.getItemByID(vid);
		String select="";
		ArrayList<Category> listCatChil = catDAO.getItemsChil(id);
		if (listCatChil.size() != 0) {
			for (Category objCat : listCatChil) {
				 if (objVideo.getCat_id() == objCat.getId()){
						select = "selected ='selected'";
					}else 
					{
						select="";
					}
				out.println(" <option "+select+" value='"+objCat.getId()+"'>"+objCat.getName()+"</option>");
				
			}
		}
		else {
			out.println(" <option value='0'>--Không có danh mục con--</option>");
		}
	}
}
