package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.bean.Users;
import model.dao.CatDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminUsersDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUsersDelController() {
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
			id = Integer.parseInt(request.getParameter("uid"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/users?msg=4");
			return;
		}
		
		System.out.println(id);
		UserDAO usersDAO = new UserDAO();
		Users oldUser = usersDAO.getItem(id);
		//Tên hình ảnh cũ
		String pictureOld = oldUser.getPicture();
		if(!pictureOld.isEmpty()) {
			//Xóa ảnh
			String filePath = request.getServletContext().getRealPath("/files")+File.separator+pictureOld;
			File file = new File(filePath);
			file.delete();
		}
		if (usersDAO.delUser(id) >0){
			//ThÃªm thÃ nh cÃ´ng
			response.sendRedirect(request.getContextPath()+"/admin/users?msg=3");
			return;
		} else 
		{
			//thÃªm tháº¥t báº¡i
			response.sendRedirect(request.getContextPath()+"/admin/users?msg=0");
			return;
		}
	}

}
