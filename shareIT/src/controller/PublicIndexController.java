package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Ads;
import model.bean.Category;
import model.bean.News;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		NewsDAO newsDAO = new NewsDAO();
		AdsDAO adsDAO = new AdsDAO();
		CatDAO catDAO = new CatDAO();
		ArrayList<Category> listCat = (ArrayList<Category>) catDAO.getItems();
		ArrayList<Ads> listAds = (ArrayList<Ads>) adsDAO.getItemsShow();
		ArrayList<News> listNewSlide = (ArrayList<News>) newsDAO.getItemsSlide();
		request.setAttribute("listSlide", listNewSlide);
		request.setAttribute("listAds", listAds);
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
