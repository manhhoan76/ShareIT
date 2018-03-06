package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.Contact;
import model.bean.Users;
import model.bean.Video;
import model.dao.AdsDAO;
import model.dao.CatDAO;
import model.dao.CommentDAO;
import model.dao.ContactDAO;
import model.dao.UserDAO;
import model.dao.VideoDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicContactController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/contact.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name= request.getParameter("cnam");
		String email= request.getParameter("cemail");
		String website= request.getParameter("cwebsite");
		String content= request.getParameter("ccontent");
		PrintWriter out = response.getWriter();
		Contact objContact = new Contact(0, name, email, website, content);
		System.out.println(name+"--"+email+"--"+website+"--"+content);
		ContactDAO contactDAO = new ContactDAO();
		if (contactDAO.addContact(objContact) >0) {
			out.println("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Thank You So Much!</strong>Cảm ơn những ý kiến đóng góp chân thành của bạn</div>");
			out.println("<form>"
               	  +"<div class='top-form'>"
               	 +"<span class='parent name'>"
               	+"<input class='field' type='text' name='' id='name_contact' placeholder='Your Name' >"
               			+"<span class='icon'></span></span>"
                          +"<span class='parent email'>"
                          +"<input class='field' type='text' name='' id='email_contact' placeholder='Your Email'>"
                          		+"<span class='icon'></span></span>"
                          +"<span class='parent web last'>"
							+"<input class='field' type='text' name='' id='website_contact' placeholder='Your Web'>"
									+"<span class='icon'></span></span>"
                          +"<div class='clear'></div></div>"
                      +"<div class='bottom-form'>"
                      +"<textarea id='content_contact' placeholder='Your Message'></textarea></div>"
                      +"<button  class='btn btn-icon submit' type='submit'><span class='icon'></span><a href='javascript:void(0)' onclick='contact()' style='color: white;'>Sent</a></button>"
                      +"</form>"
                  +"<div class='clear'></div>");

			} else {
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}

	}

}
