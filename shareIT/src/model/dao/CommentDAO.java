package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Ads;
import model.bean.Comment;
import util.ConnectDBUtil;

public class CommentDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public CommentDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}

	public ArrayList<Comment> getItemsPagination(int offset, int row_count) {
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT comment.id, content, comment.date_create, comment.parent_id, comment.news_id, news.name as name_news, comment.name, email, website, comment.active  FROM comment INNER join news on comment.news_id = news.id limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("news_id"), rs.getString("name_news"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listComment.add(objComment);
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
		return listComment;
	}
	public ArrayList<Comment> getItemsForPublic(int idNew) {
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT comment.id, content, comment.date_create, comment.parent_id, comment.news_id, news.name as name_news, comment.name, email, website, comment.active  FROM comment INNER join news on comment.news_id = news.id where comment.news_id="+idNew;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("news_id"), rs.getString("name_news"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listComment.add(objComment);
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
		return listComment;
	}
	public ArrayList<Comment> getItemsForPublicByParent(int idNew, int parent) {
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT comment.id, content, comment.date_create, comment.parent_id, comment.news_id, news.name as name_news, comment.name, email, website, comment.active  FROM comment INNER join news on comment.news_id = news.id where comment.news_id="+idNew+" && comment.parent_id="+parent+" order by comment.date_create ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("news_id"), rs.getString("name_news"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listComment.add(objComment);
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
		return listComment;
	}
	public ArrayList<Comment> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<Comment> listComment = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT comment.id, content, comment.date_create, comment.parent_id, comment.news_id, news.name as name_news, comment.name, email, website, comment.active  FROM comment INNER join news on comment.news_id = news.id where comment.content like '%"+key+"%' or comment.name like '%"+key+"%' or comment.email like '%"+key+"%' or comment.website like '%"+key+"%' limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment objComment = new Comment(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("news_id"), rs.getString("name_news"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listComment.add(objComment);
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
		return listComment;
	}
	public ArrayList<Ads> getItemAds() {
		ArrayList<Ads> listAds = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM ads where ads.active=1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Ads objAds = new Ads(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getInt("active"));
				listAds.add(objAds);
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

		return listAds;

	}
	public int addComment(Comment objComment){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into comment (content,parent_id,news_id,name,email,website,active) values(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objComment.getContent());
			pst.setInt(2, objComment.getParent_id());
			pst.setInt(3, objComment.getNews_id());
			pst.setString(4, objComment.getName());
			pst.setString(5, objComment.getEmail());
			pst.setString(6, objComment.getWebsite());
			pst.setInt(7, 0);
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
	public int addCommentParent(Comment objComment){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into comment (content,parent_id,news_id,name,email,website,active) values(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objComment.getContent());
			pst.setInt(2, 0);
			pst.setInt(3, objComment.getNews_id());
			pst.setString(4, objComment.getName());
			pst.setString(5, objComment.getEmail());
			pst.setString(6, objComment.getWebsite());
			pst.setInt(7, 0);
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
	public int countCommentByIdNews(int idNews){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumComment FROM comment where comment.news_id="+idNews;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumComment");
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
	public int countComment(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumComment FROM comment ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumComment");
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
	public int countCommentByKey(String key){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumComment FROM comment where comment.content like '%"+key+"%' or comment.name like '%"+key+"%' or comment.email like '%"+key+"%' or comment.website like '%"+key+"%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumComment");
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
	public int delComment(int idCom) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from comment where comment.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCom);
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
	public int blockComment(int idads) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update comment set active=0 where comment.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idads);
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
	public int activeComment(int idcom) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update comment set active=1 where comment.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idcom);
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

	
}
