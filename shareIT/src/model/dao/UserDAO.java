package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Users;
import util.ConnectDBUtil;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public UserDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}

	public ArrayList<Users> getItemsPagination(int offset, int row_count) {
		ArrayList<Users> listUser = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM user limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Users objUser = new Users( rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("active"), rs.getString("picture"), rs.getString("address"), rs.getString("phone"), rs.getString("caption"));
				listUser.add(objUser);
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

		return listUser;

	}
	public ArrayList<Users> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<Users> listUser = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM user where user.username like '%"+key+"%' or user.fullname like '%"+key+"%' limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Users objUser = new Users( rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("active"), rs.getString("picture"), rs.getString("address"), rs.getString("phone"), rs.getString("caption"));
				listUser.add(objUser);
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

		return listUser;

	}
	public ArrayList<Users> getItems() {
		ArrayList<Users> listUser = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM user ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Users objUser = new Users( rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("active"), rs.getString("picture"), rs.getString("address"), rs.getString("phone"), rs.getString("caption"));
				listUser.add(objUser);
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

		return listUser;

	}
	public int countUser(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumUser FROM user ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumUser");
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
	public int countUserByKey(String key){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumUser FROM user  where user.username like '%"+key+"%' or user.fullname like '%"+key+"%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumUser");
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
	public int delUser(int idUser) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from user where id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
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
	public int BlockUser(int idUser) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update user set active=0 where user.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
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
	public int ActiveUser(int idUser) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update user set active=1 where user.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
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
	public int addUser(Users objUser) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "insert into user (username, password, fullname,active,picture,address,phone,caption) values(?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUsername());
			pst.setString(2, objUser.getPassword());
			pst.setString(3, objUser.getFullname());
			pst.setInt(4, 1);
			pst.setString(5, objUser.getPicture());
			pst.setString(6, objUser.getAddress());
			pst.setString(7, objUser.getPhone());
			pst.setString(8, objUser.getCaption());
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
	public Users checkUser(String username){
		conn = connectDBUtil.getConnection();
		Users objUser = null;
		String sql ="select * from user where username =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()){
				objUser = new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"),rs.getInt("active"),rs.getString("picture"),rs.getString("address"),rs.getString("phone"),rs.getString("caption"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return objUser;
		
	}

	public Users getItem(int id) {
		Users objUser = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM user where id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("active"), rs.getString("picture"), rs.getString("address"),rs.getString("phone"), rs.getString("caption"));
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

		return objUser;

	}
	public int editUser(Users objUser) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update user set  username=? , password = ? , fullname=? ,  picture= ? , address= ?, caption= ? , phone= ?   where user.id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUsername());
			pst.setString(2, objUser.getPassword());
			pst.setString(3, objUser.getFullname());
			pst.setString(5, objUser.getAddress());
			pst.setString(6, objUser.getCaption());
			pst.setString(4, objUser.getPicture());
			pst.setString(7, objUser.getPhone());
			pst.setInt(8, objUser.getId());
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


	/*
	public int delUser(int idUser) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from users where id_user=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
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

	
	public Users checkUser(String username){
		conn = connectDBUtil.getConnection();
		Users objUser = null;
		String sql ="select * from users where username =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()){
				objUser = new Users(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return objUser;
		
	} 
	*/
	public Users getItem(String username, String password) {
		Users objUser = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM user where username=? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("active"), rs.getString("picture"), rs.getString("address"), rs.getString("phone"), rs.getString("caption"));
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

		return objUser;

	}
}
