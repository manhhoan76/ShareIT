package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Ads;
import model.dao.AdsDAO;
import util.AuthUtil;
import util.GetFileNameUtil;

@MultipartConfig
/**
 * Servlet implementation class AdminIndexController
 */
public class AdminAdsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAdsEditController() {
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
		AdsDAO adsDAO = new AdsDAO();
		int id = Integer.parseInt(request.getParameter("aid"));
		request.setAttribute("objAds", adsDAO.getItem(id));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ads/edit.jsp");
		rd.forward(request, response);
	}}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("aid"));
		String name = request.getParameter("name");
		System.out.println(name);
		String link = request.getParameter("link");
		AdsDAO adsDAO = new AdsDAO();
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
			if (!"".equals(adsDAO.getItem(id).getPicture())) {
				// xoa hinh anh cu
				String urlFileDel = path + File.separator + adsDAO.getItem(id).getPicture();
				File delFile = new File(urlFileDel);
				delFile.delete();
			}
			// ghi file
			String filePath = path + File.separator + filename;
			part.write(filePath);
		} else {
			filename = adsDAO.getItem(id).getPicture();
		}
		Ads objAds = new Ads(id, name, filename, link, 0);
		if (adsDAO.checkAdsName(name) != null) {
			response.sendRedirect(request.getContextPath() + "/admin/ads/edit?aid=" + objAds.getId() + "&msg=1");
		} else {
			if (adsDAO.editAds(objAds) > 0) {
				// thành công
				response.sendRedirect(request.getContextPath() + "/admin/ads?msg=2");
				return;
			} else {
				// thất bại
				response.sendRedirect(request.getContextPath() + "/admin/ads/edit?aid=" + objAds.getId() + "&msg=0");
				return;
			}
		}
	}
}
