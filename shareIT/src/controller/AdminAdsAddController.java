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
import model.bean.Users;
import model.dao.AdsDAO;
import model.dao.UserDAO;
import util.AuthUtil;
import util.GetFileNameUtil;
import util.StringLibrary;

@MultipartConfig

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminAdsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAdsAddController() {
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
			RequestDispatcher rd = request.getRequestDispatcher("/admin/ads/add.jsp");
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
		StringLibrary library = new StringLibrary();
		String name = request.getParameter("name");
		String link = request.getParameter("link");
		AdsDAO adsDao= new AdsDAO();
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
		Ads objAds = new Ads(0, name, filename, link, 0);
		if (adsDao.checkAdsName(name) != null) {
			// Ä‘Ã£ tá»“n táº¡i user
			response.sendRedirect(request.getContextPath() + "/admin/ads/add?msg=0");
			return;
		} else {
			// ChÆ°a cÃ³ user, thÃªm user
			if (adsDao.addAds(objAds) > 0) {
				// ThÃªm thÃ nh cÃ´ng
				response.sendRedirect(request.getContextPath() + "/admin/ads?msg=1");
				return;
			} else {
				// thÃªm tháº¥t báº¡i
				response.sendRedirect(request.getContextPath() + "/admin/ads?msg=0");
				return;
			}
		}

	}

}
