package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Users;
import model.bean.Video;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminVideoActiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminVideoActiveController() {
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
			id = Integer.parseInt(request.getParameter("vaid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/video?msg=4");
			return;
		}
		int active = 0;
		try {
			active = Integer.parseInt(request.getParameter("vaactive"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/video?msg=4");
			return;
		}
		PrintWriter out = response.getWriter();
		VideoDAO videoDAO = new VideoDAO();
		Video objVideo = (Video) videoDAO.getItemByID(id);
		if (active == 0) {
			videoDAO.activeVideo(id,objVideo.getDate_create());
			out.println("<a onclick='active("+id+",1)'  class='btn btn-success' href='javascript:void(0)' ><i class='icon_check_alt2'></i></a>");
		} else {
			videoDAO.blockVideo(id,objVideo.getDate_create());
			out.println("<a onclick='active("+id+",0)' class='btn btn-warning' href='javascript:void(0)' ><i class='icon_upload'></i></a>");
		}

	}

}
