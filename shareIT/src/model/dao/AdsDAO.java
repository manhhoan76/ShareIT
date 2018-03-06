package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Ads;
import util.ConnectDBUtil;

public class AdsDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public AdsDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}

	public ArrayList<Ads> getItemsPagination(int offset, int row_count) {
		ArrayList<Ads> listAds = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM ads limit "+offset+","+row_count;
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
	public ArrayList<Ads> getItemsShow() {
		ArrayList<Ads> listAds = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM ads where ads.active=1  ";
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
	public ArrayList<Ads> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<Ads> listAds = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM ads where ads.name like '%"+key+"%' limit "+offset+","+row_count;
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
	public int countAds(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sunAds FROM ads ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sunAds");
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
	public int countAdsByKey(String key){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sunAds FROM ads where ads.name like '%"+key+"%' ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sunAds");
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
	public int delAds(int idAds) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from ads where ads.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idAds);
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
	public int blockAds(int idads) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update ads set active=0 where ads.id=?";
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
	public int activeAds(int idAds) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update ads set active=1 where ads.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idAds);
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
	public int addAds(Ads objAds) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "insert into ads (name,picture, link, active) values(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAds.getName());
			pst.setString(2, objAds.getPicture());
			pst.setString(3, objAds.getLink());
			pst.setInt(4, 1);
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
	public Ads checkAdsName(String name){
		conn = connectDBUtil.getConnection();
		Ads objAds = null;
		String sql ="select * from ads where ads.name =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()){
				objAds = new Ads(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getInt("active"));
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
		return objAds;
		
	}

	public Ads getItem(int id) {
		Ads objAds = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM ads where ads.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				objAds = new  Ads(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getInt("active"));
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

		return objAds;

	}
	public int editAds(Ads objAds) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update ads set  name=?, picture =?, link=?   where ads.id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAds.getName());
			pst.setString(2, objAds.getPicture());
			pst.setString(3, objAds.getLink());
			pst.setInt(4, objAds.getId());
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
