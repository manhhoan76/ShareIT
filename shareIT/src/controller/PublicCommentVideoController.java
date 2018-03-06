package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
public class PublicCommentVideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicCommentVideoController() {
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
		String name= request.getParameter("cnam");
		String email= request.getParameter("cemail");
		String website= request.getParameter("cwebsite");
		String content= request.getParameter("ccontent");
		CommentVideo objComment = new CommentVideo(0, content, null, 0, idNew, null, name, email, website, 0);
		PrintWriter out = response.getWriter();
		CommentVideoDAO commentDAO = new CommentVideoDAO();
		if (commentDAO.addcommentvideoParent(objComment) >0) {
			out.println("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Comment của bạn đã được ghi nhận -- Chúng tôi sẽ xem xét</div>");
			out.println("<div class='contact-content'>"
					+"<div class='marked-title'>"
					+"<h3>Leave a comment</h3></div>"
            		  +"<div class='contact-form'>"
               	   +"<form>"
               	  +"<div class='top-form'>"
               	 +"<span class='parent name'>"
               	+"<input class='field' type='text' name='' id='name_comment' placeholder='Your Name' >"
               			+"<span class='icon'></span></span>"
                          +"<span class='parent email'>"
                          +"<input class='field' type='text' name='' id='email_comment' placeholder='Your Email'>"
                          		+"<span class='icon'></span></span>"
                          +"<span class='parent web last'>"
							+"<input class='field' type='text' name='' id='website_comment' placeholder='Your Web'>"
									+"<span class='icon'></span></span>"
                          +"<div class='clear'></div></div>"
                      +"<div class='bottom-form'>"
                      +"<textarea id='content_comment' placeholder='Your Comment'></textarea></div>"
                      +"<button  class='btn btn-icon submit' type='submit'><span class='icon'></span><a href='javascript:void(0)' onclick='comment("+idNew+")' style='color: white;'>Comment</a></button>"
                      +"</form>"
                  +"<div class='clear'></div></div></div>");

			} else {
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}

	}

}
