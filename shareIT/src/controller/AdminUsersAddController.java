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

import model.bean.Users;
import model.dao.UserDAO;
import util.AuthUtil;
import util.GetFileNameUtil;
import util.StringLibrary;

@MultipartConfig

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminUsersAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsersAddController() {
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
			RequestDispatcher rd = request.getRequestDispatcher("/admin/users/add.jsp");
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
		String username = request.getParameter("username");
		String password = library.md5(request.getParameter("password"));
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String caption = request.getParameter("caption");
		UserDAO userDao = new UserDAO();
		final Part part = request.getPart("hinhanh");
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
		System.out.println(filename);
		Users objUser = new Users(0, username, password, fullname, 0, filename, address, phone, caption);
		if (userDao.checkUser(username) != null) {
			// Ä‘Ã£ tá»“n táº¡i user
			response.sendRedirect(request.getContextPath() + "/admin/users/add?msg=0");
			return;
		} else {
			// ChÆ°a cÃ³ user, thÃªm user
			if (userDao.addUser(objUser) > 0) {
				// ThÃªm thÃ nh cÃ´ng
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=1");
				return;
			} else {
				// thÃªm tháº¥t báº¡i
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=0");
				return;
			}
		}

	}

}
