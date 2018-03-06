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
import model.bean.Video;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;
import util.AuthUtil;
import util.GetFileNameUtil;

@MultipartConfig

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminVideoAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminVideoAddController() {
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
			RequestDispatcher rd = request.getRequestDispatcher("/admin/video/add.jsp");
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
		String nameVideo = request.getParameter("name");
		String link = request.getParameter("link");
		int danhmuccha = Integer.parseInt(request.getParameter("danhmuccha"));
		int  danhmuccon = Integer.parseInt(request.getParameter("danhmucchil"));
		int show = Integer.parseInt(request.getParameter("show"));
		int cat_id=0;
		if (danhmuccon == 0 ){
			cat_id=danhmuccha;
		} else {
			cat_id= danhmuccon;
		}
		VideoDAO videoDAO = new VideoDAO();
		HttpSession session = request.getSession();
		Users objUerInfor = (Users) session.getAttribute("userInfor");
		Video objVideo = new Video(0, link, cat_id, show, nameVideo, 0, null,objUerInfor.getId());
			if (videoDAO.addVideo(objVideo) > 0) {
				// ThÃªm thÃ nh cÃ´ng
				response.sendRedirect(request.getContextPath() + "/admin/video?msg=1");
				return;
			} else {
				// thÃªm tháº¥t báº¡i
				response.sendRedirect(request.getContextPath() + "/admin/video?msg=0");
				return;
			}
	
	
	}

}
