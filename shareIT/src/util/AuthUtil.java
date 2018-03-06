package util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {
	public static  boolean checkUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfor") == null) {
			return false;
		} 
		return true;
	}
}
