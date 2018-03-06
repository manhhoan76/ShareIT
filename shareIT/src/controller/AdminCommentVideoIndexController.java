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
import model.bean.Comment;
import model.bean.CommentVideo;
import model.dao.CommentDAO;
import model.dao.CommentVideoDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCatIndexController
 */
public class AdminCommentVideoIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommentVideoIndexController() {
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
		CommentVideoDAO commentDAO = new CommentVideoDAO();
		int sumComment = commentDAO.countcommentvideo();
		int sumPage = (int)Math.ceil((float)sumComment/Define.ROW_COUNT_ADMIN); // code trong do post sao chay dc e? // phai doGet chu, ko thi doGet phai goi doPost 
		request.setAttribute("sumPage", sumPage);
		int current_page=1;
		if (request.getParameter("page")!=null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("current_page", current_page);
		int offset = (current_page-1)* Define.ROW_COUNT_ADMIN;
		ArrayList<CommentVideo> listComment = commentDAO.getItemsPagination(offset, Define.ROW_COUNT_ADMIN);
		request.setAttribute("listComment", listComment);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/commentVideo/index.jsp");
		rd.forward(request, response);	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
