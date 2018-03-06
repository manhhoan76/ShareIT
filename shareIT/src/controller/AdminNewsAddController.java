package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import util.AuthUtil;
import util.GetFileNameUtil;

@MultipartConfig

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminNewsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (AuthUtil.checkUser(request) == false) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			CatDAO catDAO = new CatDAO();
			request.setAttribute("listCatParent", catDAO.getItemsParent());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
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
		HttpSession session = request.getSession();
		Users objUser = (Users) session.getAttribute("userInfor");
		int user_id = objUser.getId();
		String tentin = request.getParameter("tentin");
		String mota = request.getParameter("mota");
		String chitiet = request.getParameter("chitiet");
		int danhmuccha = Integer.parseInt(request.getParameter("danhmuccha"));
		int  danhmuccon = Integer.parseInt(request.getParameter("danhmucchil"));
		int slide = Integer.parseInt(request.getParameter("slide"));
		int cat_id=0;
		if (danhmuccon == 0 ){
			cat_id=danhmuccha;
		} else {
			cat_id= danhmuccon;
		}
		NewsDAO newsDAO = new NewsDAO(); 
		final Part part = request.getPart("hinh");
		// lấy tên file
		String filename = GetFileNameUtil.getFileName(part);
		if (!filename.isEmpty()) {
			// đường dẫn lưu file
			final String path = request.getServletContext().getRealPath("/files");
			System.out.println(path);
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
			// ghi file
			String filePath = path + File.separator + filename;
			part.write(filePath);
		}
		News objNews = new News(0, tentin, mota, chitiet,null , filename, cat_id, null, slide, 0,user_id, null, danhmuccha);
			if (newsDAO.addNew(objNews) > 0) {
				// ThÃªm thÃ nh cÃ´ng
				response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
				return;
			} else {
				// thÃªm tháº¥t báº¡i
				response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
				return;
			}
	
	
	}

}
