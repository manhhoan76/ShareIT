package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBUtil {
	private Connection conn;
	private String url;
	private String user;
	/*private String host="node14356-shareit-h2gunner.kilatiron.com";*/
	private String password;
	public ConnectDBUtil(){
		/*this.url="jdbc:mysql://"+host+"/shareit?useUnicode=true&characterEncoding=UTF-8";*/
		this.url="jdbc:mysql://localhost:3306/shareit?useUnicode=true&characterEncoding=UTF-8";
		this.user="root";
		/*this.password="XHIyhx12195";*/
		this.password="";
	}
	public Connection getConnection(){
		//Nạp Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		ConnectDBUtil connectDB = new ConnectDBUtil();
		System.out.println(connectDB.getConnection());
	}
}
