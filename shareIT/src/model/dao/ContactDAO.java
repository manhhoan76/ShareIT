package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Comment;
import model.bean.Contact;
import util.ConnectDBUtil;


public class ContactDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public ContactDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}
	public ArrayList<Contact> getItemsPagination(int offset, int row_count) {
		ArrayList<Contact> listContatc = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from contact order by contact.id desc limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contact objContatc = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("web"), rs.getString("content")); 
				listContatc.add(objContatc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 	listContatc;
	}
	public int countContact(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumContact FROM contact ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumContact");
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return  result;
	}
	public int delContact(int idCon) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from contact where contact.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCon);
			resut = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resut;
	}
	public int addContact(Contact objContact) {
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into contact (name,email,web,content) values(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objContact.getName() );
			pst.setString(2, objContact.getEmail());
			pst.setString(3, objContact.getWeb());
			pst.setString(4, objContact.getContent());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
		
	}
}
