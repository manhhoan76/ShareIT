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
import model.bean.Users;
import model.bean.Video;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.VideoDAO;
import util.AuthUtil;
import util.GetFileNameUtil;

@MultipartConfig
/**
 * Servlet implementation class AdminIndexController
 */
public class AdminVideoEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminVideoEditController() {
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
			VideoDAO videoDAO = new VideoDAO();
			int id = Integer.parseInt(request.getParameter("vid"));
			request.setAttribute("objVideo", videoDAO.getItemByID(id));
			CatDAO catDAO = new CatDAO();
			request.setAttribute("listCatParent", catDAO.getItemsParent());
			Category objCat = catDAO.getItem(videoDAO.getItemByID(id).getCat_id());
			Category catParent = catDAO.getCatParent(objCat.getParent_id());
			if (catParent != null) {
				request.setAttribute("catParent", catParent);
				ArrayList<Category> listCatChild = catDAO.getItemsChil(catParent.getId());
				request.setAttribute("listCatChild", listCatChild);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/admin/video/edit.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int idVideo = Integer.parseInt(request.getParameter("vid"));
		String nameVideo = request.getParameter("nameVideo");
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
		HttpSession session = request.getSession();
		Users objUerInfor = (Users) session.getAttribute("userInfor");
		VideoDAO videoDAO = new VideoDAO();
		Video objVideo = new Video(idVideo, link, cat_id, show, nameVideo, 0, videoDAO.getItemByID(idVideo).getDate_create(),objUerInfor.getId());
		if (videoDAO.checkVideoName(nameVideo) != null) {
			
				response.sendRedirect(request.getContextPath() + "/admin/video/edit?vid=" + objVideo.getId() + "&msg=1");
			
		} else {
			if (videoDAO.editVideo(objVideo) > 0) {
				// thành công
				response.sendRedirect(request.getContextPath() + "/admin/video?msg=2");
				return;
			} else {
				// thất bại
				response.sendRedirect(request.getContextPath() + "/admin/video/edit?vid=" + objVideo.getId() + "&msg=0");
				return;
			}
		}
	}
}
