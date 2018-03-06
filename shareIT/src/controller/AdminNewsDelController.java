package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Ads;
import model.bean.Users;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminNewsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNewsDelController() {
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
			id = Integer.parseInt(request.getParameter("nid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=4");
			return;
		}
		NewsDAO newsDAO = new NewsDAO();
		//Tên hình ảnh cũ
		String pictureOld = newsDAO.getItemFileName(id);
		if(!pictureOld.isEmpty()) {
			//Xóa ảnh
			String filePath = request.getServletContext().getRealPath("/files")+File.separator+pictureOld;
			File file = new File(filePath);
			file.delete();
		}
		if (newsDAO.delNews(id) >0){
			//ThÃªm thÃ nh cÃ´ng
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=3");
			return;
		} else 
		{
			//thÃªm tháº¥t báº¡i
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=0");
			return;
		}
	}

}
