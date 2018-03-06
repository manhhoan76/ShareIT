package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Ads;
import model.bean.Comment;
import model.bean.CommentVideo;
import util.ConnectDBUtil;

public class CommentVideoDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public CommentVideoDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}
	public int addComment(CommentVideo objComment){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into commentvideo (content,parent_id,video_id,name,email,website,active) values(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objComment.getContent());
			pst.setInt(2, objComment.getParent_id());
			pst.setInt(3, objComment.getVideo_id());
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
	public int addCommentParent(CommentVideo objComment){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into commentvideo (content,parent_id,video_id,name,email,website,active) values(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objComment.getContent());
			pst.setInt(2, 0);
			pst.setInt(3, objComment.getVideo_id());
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
	public ArrayList<CommentVideo> getItemsPagination(int offset, int row_count) {
		ArrayList<CommentVideo> listcommentvideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT commentvideo.id, content, commentvideo.date_create, commentvideo.parent_id, commentvideo.video_id, video.name as name_video, commentvideo.name, email, website, commentvideo.active  FROM commentvideo INNER join video on commentvideo.video_id = video.id limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CommentVideo objcommentvideo = new CommentVideo(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("video_id"), rs.getString("name_video"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listcommentvideo.add(objcommentvideo);
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
		return listcommentvideo;
	}
	public ArrayList<CommentVideo> getItemsForPublic(int idVideo) {
		ArrayList<CommentVideo> listcommentvideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT commentvideo.id, content, commentvideo.date_create, commentvideo.parent_id, commentvideo.video_id, video.name as name_video, commentvideo.name, email, website, commentvideo.active  FROM commentvideo INNER join video on commentvideo.video_id = video.id where commentvideo.video_id="+idVideo;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CommentVideo objcommentvideo = new CommentVideo(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("video_id"), rs.getString("name_video"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listcommentvideo.add(objcommentvideo);
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
		return listcommentvideo;
	}
	public ArrayList<CommentVideo> getItemsForPublicByParent(int idVideo, int parent) {
		ArrayList<CommentVideo> listcommentvideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT commentvideo.id, content, commentvideo.date_create, commentvideo.parent_id, commentvideo.video_id, video.name as name_video, commentvideo.name, email, website, commentvideo.active  FROM commentvideo INNER join video on commentvideo.video_id = video.id where commentvideo.video_id="+idVideo+" && commentvideo.parent_id="+parent+" order by commentvideo.date_create ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CommentVideo objcommentvideo = new CommentVideo(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("video_id"), rs.getString("name_video"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listcommentvideo.add(objcommentvideo);
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
		return listcommentvideo;
	}
	public ArrayList<CommentVideo> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<CommentVideo> listcommentvideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT commentvideo.id, content, commentvideo.date_create, commentvideo.parent_id, commentvideo.video_id, video.name as name_video, commentvideo.name, email, website, commentvideo.active  FROM commentvideo INNER join video on commentvideo.video_id = video.id where commentvideo.content like '%"+key+"%' or commentvideo.name like '%"+key+"%' or commentvideo.email like '%"+key+"%' or commentvideo.website like '%"+key+"%' limit "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CommentVideo objcommentvideo = new CommentVideo(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("date_create"), rs.getInt("parent_id"), rs.getInt("video_id"), rs.getString("name_video"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getInt("active"));
				listcommentvideo.add(objcommentvideo);
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
		return listcommentvideo;
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
	public int addcommentvideo(CommentVideo objcommentvideo){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into commentvideo (content,parent_id,video_id,name,email,website,active) values(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objcommentvideo.getContent());
			pst.setInt(2, objcommentvideo.getParent_id());
			pst.setInt(3, objcommentvideo.getVideo_id());
			pst.setString(4, objcommentvideo.getName());
			pst.setString(5, objcommentvideo.getEmail());
			pst.setString(6, objcommentvideo.getWebsite());
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
	public int addcommentvideoParent(CommentVideo objcommentvideo){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql="insert into commentvideo (content,parent_id,video_id,name,email,website,active) values(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objcommentvideo.getContent());
			pst.setInt(2, 0);
			pst.setInt(3, objcommentvideo.getVideo_id());
			pst.setString(4, objcommentvideo.getName());
			pst.setString(5, objcommentvideo.getEmail());
			pst.setString(6, objcommentvideo.getWebsite());
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
	public int countCommentByIdNews(int idVideo){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumComment FROM commentvideo where commentvideo.video_id="+idVideo;
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
	
	public int countcommentvideoByIdvideo(int idvideo){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumcommentvideo FROM commentvideo where commentvideo.video_id="+idvideo;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumcommentvideo");
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
	public int countcommentvideo(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumcommentvideo FROM commentvideo ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumcommentvideo");
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
	public int countcommentvideoByKey(String key){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumcommentvideo FROM commentvideo where commentvideo.content like '%"+key+"%' or commentvideo.name like '%"+key+"%' or commentvideo.email like '%"+key+"%' or commentvideo.website like '%"+key+"%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumcommentvideo");
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
	public int delcommentvideo(int idCom) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from commentvideo where commentvideo.id=?";
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
	public int blockcommentvideo(int idads) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update commentvideo set active=0 where commentvideo.id=?";
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
	public int activecommentvideo(int idcom) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update commentvideo set active=1 where commentvideo.id=?";
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
