package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.crypto.CipherInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.CommentVideo;
import model.bean.Users;
import model.bean.Video;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.CommentDAO;
import model.dao.CommentVideoDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class PublicCommentVideoReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicCommentVideoReplyController() {
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
		int idNew = 0;
		try {
			idNew = Integer.parseInt(request.getParameter("cidNew"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}

		int idParentComment = 0;
		try {
			idParentComment = Integer.parseInt(request.getParameter("cidParent"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		String name= request.getParameter("cnam");
		String email= request.getParameter("cemail");
		String website= request.getParameter("cwebsite");
		String content= request.getParameter("ccontent");
		CommentVideo objComment = new CommentVideo(0, content, null, idParentComment, idNew, null, name, email, website, 0);
		PrintWriter out = response.getWriter();
		CommentVideoDAO commentDAO = new CommentVideoDAO();
		System.out.println(name+"--"+email+"--"+website+"--"+content+"--"+idNew+"--"+idParentComment);
		if (commentDAO.addcommentvideo(objComment) >0) {
			out.println("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Comment của bạn đã được ghi nhận -- Chúng tôi sẽ xem xét</div>");
		} else {
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}

	}

}
