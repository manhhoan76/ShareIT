package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.GetFileNameUtil;

@MultipartConfig
/**
 * Servlet implementation class AdminIndexController
 */
public class AdminNewsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsEditController() {
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
			NewsDAO newsDAO = new NewsDAO();
		int id = Integer.parseInt(request.getParameter("nid"));
		request.setAttribute("objNew", newsDAO.getItem(id));
		CatDAO catDAO = new CatDAO();
		request.setAttribute("listCatParent", catDAO.getItemsParent());
		Category objCat = catDAO.getItem(newsDAO.getItem(id).getCat_id());
		Category catParent = catDAO.getCatParent(objCat.getParent_id());
		if(catParent != null){
			request.setAttribute("catParent", catParent);
			ArrayList<Category> listCatChild = catDAO.getItemsChil(catParent.getId());
			request.setAttribute("listCatChild", listCatChild);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
	}}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("nid"));
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
		// đường dẫn lưu file
		final String path = request.getServletContext().getRealPath("/files");
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		// Xử lý upload ảnh
		final Part part = request.getPart("hinh");
		// lấy tên file
		String filename = GetFileNameUtil.getFileName(part);
		if (!filename.isEmpty()) {
			if (!"".equals(newsDAO.getItem(id).getPicture())) {
				// xoa hinh anh cu
				String urlFileDel = path + File.separator + newsDAO.getItem(id).getPicture();
				File delFile = new File(urlFileDel);
				delFile.delete();
			}
			// ghi file
			String filePath = path + File.separator + filename;
			part.write(filePath);
		} else {
			filename = newsDAO.getItem(id).getPicture();
		}
		News objNew = new News(id, tentin, mota, chitiet, newsDAO.getItem(id).getDate_create(), filename, cat_id, null, slide, 0, 0, null,danhmuccha);
		if (newsDAO.checkNews(tentin) != null) {
			if (!filename.equals(newsDAO.getItem(id).getPicture())) {
				if (newsDAO.editNew(objNew) > 0) {
					// thành công
					response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
					return;
				} else {
					// thất bại
					response.sendRedirect(
							request.getContextPath() + "/admin/news/edit?nid=" + objNew.getId() + "&msg=0");
					return;
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/news/edit?nid=" + objNew.getId() + "&msg=1");
			}
		} else {
			if (newsDAO.editNew(objNew) > 0) {
				// thành công
				response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
				return;
			} else {
				// thất bại
				response.sendRedirect(request.getContextPath() + "/admin/news/edit?nid=" + objNew.getId() + "&msg=0");
				return;
			}
		}
	}
}
