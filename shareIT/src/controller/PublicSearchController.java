package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.bean.Ads;
import model.bean.Category;
import model.bean.News;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String key = new String(request.getParameter("key").getBytes("ISO-8859-1"),"UTF-8");
		NewsDAO newsDAO = new NewsDAO();
		CatDAO catDAO = new CatDAO();
		int sumNew = (Integer) newsDAO.countNewsSearchByKey(key);
		int sumPage = (int)Math.ceil((float) sumNew/Define.ROW_COUNT_ADMIN); // code trong do post sao chay dc e? // phai doGet chu, ko thi doGet phai goi doPost 
		request.setAttribute("sumPage", sumPage);
		int current_page=1;
		if (request.getParameter("page")!=null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("current_page", current_page);
		int offset = (current_page-1)* Define.ROW_COUNT_ADMIN;
		ArrayList<News> listNew = null;
			listNew = new ArrayList<>();
			 listNew = (ArrayList<News>) newsDAO.getItemsPaginationByKey(key, offset, Define.ROW_COUNT_ADMIN);
		request.setAttribute("listNew", listNew);
		request.setAttribute("key", key);
		request.setAttribute("sumNew", sumNew);
		RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
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
