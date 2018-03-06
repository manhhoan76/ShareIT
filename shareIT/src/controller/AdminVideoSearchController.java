package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Define;
import model.bean.Ads;
import model.bean.Users;
import model.bean.Video;
import model.dao.AdsDAO;
import model.dao.VideoDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminVideoSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminVideoSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (AuthUtil.checkUser(request) == false) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			String key = new String(request.getParameter("key").getBytes("ISO-8859-1"),"UTF-8");
			Users objUserInfor = (Users) session.getAttribute("userInfor");
			int sumVideo =0;
			VideoDAO videoDAO = new VideoDAO();
			if ("admin".equals(objUserInfor.getUsername())) {
				sumVideo = videoDAO.countVideoByKey(key);
			} else {
				sumVideo = videoDAO.countVideoByIdUserAndKey(key,objUserInfor.getId());
			}
			int sumPage = (int) Math.ceil((float) sumVideo / Define.ROW_COUNT_ADMIN); 
			request.setAttribute("sumPage", sumPage);
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			request.setAttribute("current_page", current_page);
			int offset = (current_page - 1) * Define.ROW_COUNT_ADMIN;
			if ("admin".equals(objUserInfor.getUsername())) {
				ArrayList<Video> listVideo = new ArrayList<>();
				listVideo = videoDAO.getItemsPaginationByKey(key,offset, Define.ROW_COUNT_ADMIN);
				request.setAttribute("listVideo", listVideo);
			} else {
				ArrayList<Video> listVideo = new ArrayList<>();
				 listVideo = videoDAO.getItemsPaginationByIdUserAndKey(key,objUserInfor.getId(),offset, Define.ROW_COUNT_ADMIN);
				request.setAttribute("listVideo", listVideo);
			}
			request.setAttribute("key", key);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/video/search.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
