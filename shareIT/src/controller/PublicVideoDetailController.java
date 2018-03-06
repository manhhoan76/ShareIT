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
import model.bean.Video;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.CommentVideoDAO;
import model.dao.NewsDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicVideoDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicVideoDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		VideoDAO videoDAO = new VideoDAO();
		CommentVideoDAO commentVideoDAO = new CommentVideoDAO();
		int id = Integer.parseInt(request.getParameter("vid"));
		Video objVideo = (Video) videoDAO.getItemByID(id);
		request.setAttribute("numberComment", commentVideoDAO.countCommentByIdNews(id));
		ArrayList<Video> listVideoAdd =(ArrayList<Video>) videoDAO.getItemsPaginationPublicAdd(objVideo.getCat_id(), id);
		request.setAttribute("objVideo", objVideo);
		request.setAttribute("listComment", commentVideoDAO.getItemsForPublic(id));
		request.setAttribute("listVideoAdd", listVideoAdd);
		RequestDispatcher rd = request.getRequestDispatcher("/videoDetail.jsp");
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
