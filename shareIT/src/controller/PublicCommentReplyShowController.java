package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Users;
import model.bean.Video;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class PublicCommentReplyShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicCommentReplyShowController() {
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
		int idComment = 0;
		try {
			idComment = Integer.parseInt(request.getParameter("cidComment"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}

		PrintWriter out = response.getWriter();
			out.println("<div class='contact-content'>"+
                              		  "<div class='contact-form'>"
                                 	   +"<form action='#' method='post'>"
                                 	  +"<div class='top-form'>"
                                 	 +"<span class='parent name'>"
                                 	+"<input class='field' type='text' name='' id='name_reply' placeholder='Your Name' >"
                                 			+"<span class='icon'></span></span>"
                                            +"<span class='parent email'>"
                                            +"<input class='field' type='text' name='' id='email_reply' placeholder='Your Email'>"
                                            		+"<span class='icon'></span></span>"
                                            +"<span class='parent web last'>"
											+"<input class='field' type='text' name='' id='website_reply' placeholder='Your Web'>"
													+"<span class='icon'></span></span>"
                                            +"<div class='clear'></div></div>"
                                        +"<div class='bottom-form'>"
                                        +"<textarea id='content_reply' placeholder='Your Comment'></textarea></div>"
                                        +"<button  class='btn btn-icon submit' type='submit'><span class='icon'></span><a href='javascript:void(0)' onclick='reply("+idComment+","+idNew+","+idParentComment+")' style='color: white;'>Reply</a></button>"
                                        +"</form>"
                                    +"<div class='clear'></div></div></div>");

	}

}
