package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.News;
import util.ConnectDBUtil;

public class NewsDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public NewsDAO() {
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
	public int countViewByID(int idUser) {
		int sumView = 0;
		conn = connectDBUtil.getConnection();
		String sql = "select sum(view) as sumView from news where user_id="+idUser;
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
	public ArrayList<News> getItemsPaginationByIdCatChil(int IDCat,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username, news.cat_parent from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.cat_id="+IDCat+" ORDER by news.id desc limit  "+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByIdCatParent(int IDCat,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username, news.cat_parent from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.cat_parent="+IDCat+" ORDER by news.id desc limit  "+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsByIDCat(int catId,int offset, int number) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username, news.cat_parent from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.cat_id="+catId+" ORDER by news.date_create desc limit  "+ offset + "," + number;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsByIDCatParent(int catId,int offset, int number) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username, news.cat_parent from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.cat_parent="+catId+" ORDER by news.date_create desc limit  "+ offset + "," + number;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPagination(int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username, news.cat_parent from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id ORDER by news.id desc limit  "+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public News getItemCatParent(int catID) {
		News objNews = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username, news.cat_parent from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id  where news.cat_parent= "+catID+" ORDER by news.date_create desc limit 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objNews = new News();
				objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
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

		return objNews;

	}
	public ArrayList<News> getItemsPaginationById(int id,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, news.cat_parent,detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.user_id="+id+"  ORDER by news.id desc limit  "+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"),rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByIdCat(int idCat,String key,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, news.cat_parent,detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where (news.cat_id="+idCat+" && news.name LIKE '%"+key+"%')  ORDER by news.id desc limit  "
				+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"),rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByIdCatParent(int idCat,String key,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, news.cat_parent,detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where (news.cat_parent="+idCat+" && news.name LIKE '%"+key+"%')  ORDER by news.id desc limit  "
				+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"),rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, news.cat_parent,detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.name LIKE '%"+key+"%'  ORDER by news.id desc limit "+offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByKeyAndUserID(String key,int userID,int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, news.cat_parent, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where (news.name LIKE '%"+key+"%' && news.user_id="+userID+") ORDER by news.id desc limit "+offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByIdCatAndIdUser(int idCat, int idUser, String key, int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where (news.cat_id="+idCat+" && news.user_id="+idUser+" && news.name  LIKE '%"+key+"%')  ORDER by news.id desc limit  "
				+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"),rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsPaginationByIdCatParentAndIdUser(int idCat, int idUser, String key, int offset, int row_count) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where (news.cat_parent="+idCat+" && news.user_id="+idUser+" && news.name  LIKE '%"+key+"%')  ORDER by news.id desc limit  "
				+ offset + "," + row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"),rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public int countNews() {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news ";
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
	public int countNewsByCatId(int idCat,String key) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where (news.cat_id="+idCat+" && news.name LIKE '%"+key+"%'";
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
	public int countNewsByCatIdParent(int idCat,String key) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where (news.cat_parent="+idCat+" && news.name LIKE '%"+key+"%'";
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
	public int countNewsByCatIdParent(int idCat) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where news.cat_parent="+idCat;
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
	public int countNewsByCatIdChil(int idCat) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where news.cat_id="+idCat;
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
	public int countNewsSearchByKey(String key) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where news.name LIKE '%"+key+"%'";
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
	public int countNewsSearchByKeyAndUserID(String key, int userId) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where (news.name LIKE '%"+key+"%' && news.user_id="+userId+")";
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
	public ArrayList<News> getItemsADD(int idCat, int idNew) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id  where news.cat_id=  "+idCat+" && news.id != "+idNew+" ORDER by news.date_create DESC LIMIT 4";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"),rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public int editView(News objNew) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update news set view=?,news.date_create=?   where news.id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objNew.getView());
			pst.setTimestamp(2, objNew.getDate_create());
			pst.setInt(3, objNew.getId());
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
	public ArrayList<News> getItemsMostViewByIdCat(int idCat) {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id where news.cat_id="+idCat+"  ORDER by view DESC LIMIT 2";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsMostView() {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id   ORDER by view DESC LIMIT 3";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsLast() {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, news.cat_parent, detail, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id   ORDER by news.date_create DESC LIMIT 3";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public ArrayList<News> getItemsSlide() {
		ArrayList<News> listNews = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id  where news.is_slide=1 ORDER by news.date_create DESC ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News objNews = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"), rs.getString("cat_name"), rs.getInt("is_slide"), rs.getInt("view"),
						rs.getInt("user_id"), rs.getString("username"), rs.getInt("cat_parent"));
				listNews.add(objNews);
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

		return listNews;

	}
	public int countNewsByCatIdAndUserID(int idCat, int UserID, String key) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where (news.cat_id="+idCat+"  && news.user_id="+UserID+" && news.name LIKE '%"+key+"%'";
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
	public int countNewsByCatIdParentAndUserID(int idCat, int UserID, String key) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where (news.cat_parent="+idCat+"  && news.user_id="+UserID+" && news.name LIKE '%"+key+"%'";
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
	public int countNewsByUserId(int idUser) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM news where user_id= "+idUser;
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

	public int addNew(News objNew) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "INSERT INTO news (name,preview,detail,picture,cat_id,is_slide,view,user_id,news.cat_parent)  VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNew.getName());
			pst.setString(2, objNew.getPreview());
			pst.setString(3, objNew.getDetail());
			pst.setString(4, objNew.getPicture());
			pst.setInt(5, objNew.getCat_id());
			pst.setInt(6, objNew.getIs_slide());
			pst.setInt(7, 0);
			pst.setInt(8, objNew.getUser_id());
			pst.setInt(9, objNew.getCat_parent());
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

	public int editNew(News objNew) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "update news set name=?,preview=?,detail=?,picture=?,cat_id=?,is_slide=?, cat_parent=?, date_create=?  where news.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNew.getName());
			pst.setString(2, objNew.getPreview());
			pst.setString(3, objNew.getDetail());
			pst.setString(4, objNew.getPicture());
			pst.setInt(5, objNew.getCat_id());
			pst.setInt(6, objNew.getIs_slide());
			pst.setInt(9, objNew.getId());
			pst.setTimestamp(8, objNew.getDate_create());
			pst.setInt(7, objNew.getCat_parent());
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

	public int delNews(int idNews) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from news where news.id=" + idNews;
		try {
			pst = conn.prepareStatement(sql);
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
	public int delNewsByCatIdParet(int idCatParent) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from news where news.cat_parent=" + idCatParent;
		try {
			pst = conn.prepareStatement(sql);
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
	public int delNewsByCatID(int idCat) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from news where news.cat_id=" + idCat;
		try {
			pst = conn.prepareStatement(sql);
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
	public String getItemFileName(int id) {
		String picture = null;
		conn = connectDBUtil.getConnection();
		String sql = "select news.picture from news where news.id= " + id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				picture = rs.getString("picture");
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

		return picture;
	}

	public News getItem(int id) {
		News objNew = null;
		conn = connectDBUtil.getConnection();
		String sql = "select news.id, news.name, preview, detail, news.cat_parent, date_create, news.picture, cat_id, is_slide, view, news.user_id, cat_list.name AS cat_name , user.username from news INNER JOIN cat_list ON news.cat_id = cat_list.id INNER JOIN user ON news.user_id = user.id  where news.id= " + id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("cat_id"),rs.getString("cat_name") , rs.getInt("is_slide"), rs.getInt("view"), rs.getInt("user_id"),
						rs.getString("username"), rs.getInt("cat_parent"));
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

		return objNew;
	}

	public int blockNews(int id) {
		int resut = 0;
		conn = connectDBUtil.getConnection();
		String sql = "update news set is_slide=0 where news.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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

	public int activeNews(int id) {
		int resut = 0;
		conn = connectDBUtil.getConnection();
		String sql = "update news set is_slide=1 where news.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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

	public News checkNews(String nameNew) {
		conn = connectDBUtil.getConnection();
		News objNew = null;
		String sql = "select * from news where news.name like %"+nameNew+"%";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				objNew = new News(rs.getInt("id"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("cat_id"), null, rs.getInt("is_slide"), rs.getInt("view"), rs.getInt("user_id"), null,rs.getInt("cat_parent"));

			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return objNew;

	}
}
