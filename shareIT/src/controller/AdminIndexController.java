package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Define;
import model.bean.News;
import model.bean.Users;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.CommentDAO;
import model.dao.CommentVideoDAO;
import model.dao.NewsDAO;
import model.dao.UserDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (AuthUtil.checkUser(request) == false) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
		NewsDAO newsDAO = new NewsDAO();
		CommentDAO commentDAO = new CommentDAO();
		CommentVideoDAO commentVideoDAO = new CommentVideoDAO();
		AdsDAO adsDAO = new AdsDAO();
		UserDAO usersDAO = new UserDAO();
		request.setAttribute("sumUser",  usersDAO.countUser());
		request.setAttribute("sumNew",  newsDAO.countNews());
		request.setAttribute("sumAds", adsDAO.countAds());
		int sumComment = (Integer) commentDAO.countComment(); 
		int sumCommentvideo = (Integer) commentDAO.countComment(); 
		int sum = sumComment + sumCommentvideo;
		request.setAttribute("sumComment", sum);
		request.setAttribute("sumView",  newsDAO.countView());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/index.jsp");
		rd.forward(request, response);
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
