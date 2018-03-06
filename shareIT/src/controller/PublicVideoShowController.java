package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
public class PublicVideoShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicVideoShowController() {
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
			id = Integer.parseInt(request.getParameter("vid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		System.out.println(id);
		PrintWriter out = response.getWriter();
		VideoDAO videoDAO = new VideoDAO();
		Video objVideo = (Video) videoDAO.getItemByID(id);
		if (objVideo != null) {
			out.println("<iframe  width='343' height='225' src='"+objVideo.getLink()+"' frameborder='0' allowfullscreen></iframe>");
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/video");
			return;
		}

	}

}
