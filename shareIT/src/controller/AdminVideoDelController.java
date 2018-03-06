package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminVideoDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminVideoDelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id=0;
		try {
			id = Integer.parseInt(request.getParameter("vid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/video?msg=4");
			return;
		}
		VideoDAO videoDAO = new VideoDAO();
		if (videoDAO.delVideo(id) >0){
			//xoa thanh cong
			response.sendRedirect(request.getContextPath()+"/admin/video?msg=3");
			return;
		} else 
		{
			//xoa that bai
			response.sendRedirect(request.getContextPath()+"/admin/video?msg=0");
			return;
		}
	}

}
