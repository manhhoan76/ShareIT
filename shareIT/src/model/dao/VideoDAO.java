package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.News;
import model.bean.Video;
import util.ConnectDBUtil;

public class VideoDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public VideoDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}
	
	public ArrayList<Video> getItemsPagination(int offset, int row_count) {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video ORDER by  video.date_create desc limit  "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public ArrayList<Video> getItemsPaginationPublicAdd(int idCat, int idVieo) {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video where video.cat_id=  "+idCat+" && video.id != "+idVieo+" ORDER by video.date_create DESC LIMIT 4";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public ArrayList<Video> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video where video.name like '%"+key+"%'  ORDER by  video.date_create desc limit  "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public ArrayList<Video> getItemsPaginationByIdUser(int idUser,int offset, int row_count) {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video where user_id="+idUser+" ORDER by  video.date_create desc limit  "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public ArrayList<Video> getItemsPaginationByIdUserAndKey(String key,int idUser,int offset, int row_count) {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video where user_id="+idUser+" && video.name like '%"+key+"%' ORDER by  video.date_create desc limit  "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public int delVideo(int idVideo) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from video where video.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idVideo);
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
	public Video checkVideoName(String nameVideo) {
		conn = connectDBUtil.getConnection();
		Video objVideo = null;
		String sql = "select * from video where video.name like '%"+nameVideo+"%'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
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
		return objVideo;

	}

	public ArrayList<Video> getItems() {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public ArrayList<Video> getItemsByNumber() {
		ArrayList<Video> listVideo = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video where show_link=1 order by date_create desc limit 4";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Video objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
				listVideo.add(objVideo);
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
		return listVideo;
	}
	public Video getItemByID(int id) {
		Video objVideo = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from video where video.id="+id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				objVideo = new Video(rs.getInt("id"), rs.getString("link"), rs.getInt("cat_id"), rs.getInt("show_link"), rs.getString("name"), rs.getInt("view"), rs.getTimestamp("date_create"), rs.getInt("user_id"));
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
		return objVideo;
	}
	public int countVideo(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumVideo FROM video ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumVideo");
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
	public int countVideoByKey(String key){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumVideo FROM video where video.name like '%"+key+"%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumVideo");
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
	public int countVideoByIdUserAndKey(String key,int idUser){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumVideo FROM video where user_id= "+idUser+"  &&  video.name like '%"+key+"%'" ;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumVideo");
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
	public int countVideoByIdUser(int idUser){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumVideo FROM video where user_id= "+idUser;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumVideo");
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
	public int blockVideo(int idVideo, Timestamp date) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update video set show_link=0,video.date_create="+date+" where video.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idVideo);
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
	public int activeVideo(int idVideo, Timestamp date) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update video set show_link=1, video.date_create="+date+" where video.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idVideo);
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
	public int addVideo(Video objVideo) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "INSERT INTO video (link,cat_id,show_link,name,view,user_id)  VALUES (?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objVideo.getLink());
			pst.setInt(2, objVideo.getCat_id());
			pst.setInt(3, objVideo.getShow_link());
			pst.setString(4, objVideo.getName());
			pst.setInt(5, 0);
			pst.setInt(6, objVideo.getUser_id());
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
	public int editVideo(Video objVideo) {
		int result = 0;
		conn = connectDBUtil.getConnection();
		String sql = "update video set link=?,cat_id=?,show_link=?, name=?, date_create=?  where video.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objVideo.getLink());
			pst.setInt(2, objVideo.getCat_id());
			pst.setInt(3, objVideo.getShow_link());
			pst.setString(4, objVideo.getName());
			pst.setTimestamp(5, objVideo.getDate_create());
			pst.setInt(6, objVideo.getId());
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
