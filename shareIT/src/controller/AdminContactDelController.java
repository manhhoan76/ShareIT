package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.dao.ContactDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminContactDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminContactDelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id=0;
		try {
			id = Integer.parseInt(request.getParameter("contactid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=4");
			return;
		}
		ContactDAO contactDAO = new ContactDAO();
		if (contactDAO.delContact(id) >0){
			//ThÃªm thÃ nh cÃ´ng
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=3");
			return;
		} else 
		{
			//thÃªm tháº¥t báº¡i
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=0");
			return;
		}
	}

}
