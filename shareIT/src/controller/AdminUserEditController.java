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
public class AdminUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserEditController() {
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
		UserDAO useDAO = new UserDAO();
		int id = Integer.parseInt(request.getParameter("uid"));
		request.setAttribute("objUser", useDAO.getItem(id));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/users/edit.jsp");
		rd.forward(request, response);
	}}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StringLibrary library = new StringLibrary();
		int id = Integer.parseInt(request.getParameter("uid"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String caption = request.getParameter("caption");
		System.out.println(phone);
		UserDAO userDAO = new UserDAO();
		// đường dẫn lưu file
		final String path = request.getServletContext().getRealPath("/files");
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		// Xử lý upload ảnh
		final Part part = request.getPart("hinh");
		System.out.println(part);
		// lấy tên file
		String filename = GetFileNameUtil.getFileName(part);
		if (!filename.isEmpty()) {
			if (!"".equals(userDAO.getItem(id).getPicture())) {
				// xoa hinh anh cu
				String urlFileDel = path + File.separator + userDAO.getItem(id).getPicture();
				File delFile = new File(urlFileDel);
				delFile.delete();
			}
			// ghi file
			String filePath = path + File.separator + filename;
			System.out.println(filePath);
			part.write(filePath);
		} else {
			filename = userDAO.getItem(id).getPicture();
		}
		if ("".equals(password)) {
			// lay lai pass cu
			password = userDAO.getItem(id).getPassword();
		} else {
			// cap nhat pass moi
			password = library.md5(password);
		}
		Users objUser = new Users(id, username, password, fullname, 0, filename, address, phone, caption);
		if (userDAO.checkUser(username) != null) {
			if (!filename.equals(userDAO.getItem(id).getPicture())) {
				if (userDAO.editUser(objUser) > 0) {
					// thành công
					response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
					return;
				} else {
					// thất bại
					response.sendRedirect(
							request.getContextPath() + "/admin/user/edit?uid=" + objUser.getId() + "&msg=0");
					return;
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user/edit?uid=" + objUser.getId() + "&msg=1");
			}
		} else {
			if (userDAO.editUser(objUser) > 0) {
				// thành công
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
				return;
			} else {
				// thất bại
				response.sendRedirect(request.getContextPath() + "/admin/user/edit?uid=" + objUser.getId() + "&msg=0");
				return;
			}
		}
	}
}
