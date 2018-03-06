package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Message;
import model.bean.News;
import util.ConnectDBUtil;

public class MessageDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public MessageDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}

	public int countView() {
		int sumView = 0;
		conn = connectDBUtil.getConnection();
		String sql = "select sum(view) as sumView from news";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				sumView = rs.getInt("sumView");
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
		return sumView;
	}

	public ArrayList<Message> getItemsMess(int user1, int user2) {
		ArrayList<Message> listMess = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "select * from mess where (id_sent=" + user1 + " && id_receive = " + user2 + ") or (id_sent="
				+ user2 + " && id_receive = " + user1 + ")  order by mess.date_create";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Message objMess = new Message(rs.getInt("id"), rs.getInt("id_sent"), rs.getInt("id_receive"),
						rs.getString("content"), rs.getTimestamp("date_create"));
				listMess.add(objMess);
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

		return listMess;

	}

	public Message getItem(int user1, int user2) {
		Message objMess = null;
		conn = connectDBUtil.getConnection();
		String sql = "select * from mess where (id_sent=" + user1 + "  && id_receive = " + user2 + ") or (id_sent="
				+ user2 + "  && id_receive = " + user1 + ")  order by mess.date_create desc limit 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				objMess = new Message();
				objMess = new Message(rs.getInt("id"), rs.getInt("id_sent"), rs.getInt("id_receive"),
						rs.getString("content"), rs.getTimestamp("date_create"));
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

		return objMess;

	}


	public int countNewsByUserId(int idUser) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where user_id= " + idUser;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("sumNews");
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
		return result;
	}

	public int addMess(Message objMess) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "INSERT INTO mess (id_sent , id_receive , content)  VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objMess.getId_sent());
			pst.setInt(2, objMess.getId_receive());
			pst.setString(3, objMess.getContent());
			result = pst.executeUpdate();
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
		return result;
	}
}
