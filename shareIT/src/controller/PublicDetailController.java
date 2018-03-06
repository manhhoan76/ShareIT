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
import model.dao.CommentDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int nid = Integer.parseInt(request.getParameter("nid"));
		CommentDAO commentDAO = new CommentDAO();
		NewsDAO newsDAO = new NewsDAO();
		News objNew = (News) newsDAO.getItem(nid);
		int view = objNew.getView();
		view +=1;
		News objNewEditView = new News(nid, null, null, null, objNew.getDate_create(), null, 0, null, 0, view, 0, null, 0);
		newsDAO.editView(objNewEditView);
		ArrayList<News> listNewsAdd =(ArrayList<News>) newsDAO.getItemsADD(objNew.getCat_id(),nid);
		request.setAttribute("numberComment", commentDAO.countCommentByIdNews(nid));
		request.setAttribute("objNew", objNew);
		request.setAttribute("listComment", commentDAO.getItemsForPublic(nid));
		request.setAttribute("listNewsAdd", listNewsAdd);
		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
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
