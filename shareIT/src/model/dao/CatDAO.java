package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.ConnectDBUtil;

public class CatDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private ConnectDBUtil connectDBUtil;

	public CatDAO() {
		this.connectDBUtil = new ConnectDBUtil();
	}
	public ArrayList<Category> getItemsPagination(int offset, int row_count) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from cat_list ORDER by id limit  "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				listCat.add(objCat);
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
		return listCat;
	}
	public ArrayList<Category> getItemsPaginationByKey(String key,int offset, int row_count) {
		ArrayList<Category> listCat = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * from cat_list where cat_list.name like '%"+key+"%' ORDER by id limit  "+offset+","+row_count;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				listCat.add(objCat);
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
		return listCat;
	}
	public int countCat(){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM cat_list ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumNews");
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
	public int countCatByKey(String key){
		int result =0;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT count(*) as sumNews FROM cat_list where cat_list.name like '%"+key+"%' ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				result = rs.getInt("sumNews");
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
	public ArrayList<Category> getItemsParent() {
		ArrayList<Category> listCats = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM cat_list where parent_id=0";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				listCats.add(objCat);
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

		return listCats;

	}
	
	public ArrayList<Category> getItemsChil(int id) {
		ArrayList<Category> listCats = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM cat_list where parent_id="+id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				listCats.add(objCat);
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

		return listCats;

	}

	public Category getCatParent(int idCat) {
		Category objCat = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM cat_list where id="+idCat;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
					objCat = new Category();
				 objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
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

		return objCat;

	}

	public ArrayList<Category> getItems() {
		ArrayList<Category> listCats = new ArrayList<>();
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM cat_list";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				listCats.add(objCat);
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

		return listCats;

	}
	public String getCatParentName(int id) {
		String name = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT name FROM cat_list where id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
				
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

		return name;

	}
	public Category getItem(int cid) {
		Category objCat = null;
		conn = connectDBUtil.getConnection();
		String sql = "SELECT * FROM cat_list where id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			if (rs.next()) {
				objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index")); 
				
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

		return objCat;

	}
	public int addCat(String name, int prId) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "insert into cat_list (name,parent_id,show_index) values(?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setInt(2, prId);
			pst.setInt(3, 0);
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
	public Category checkCat(String catname, int id){
		conn = connectDBUtil.getConnection();
		Category objCat = null;
		String sql ="select * from cat_list where name =? && parent_id= ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, catname);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if (rs.next()){
				objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				
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
		return objCat;
		
	}
	public Category checkCatName(String catname){
		conn = connectDBUtil.getConnection();
		Category objCat = null;
		String sql ="select * from cat_list where name like '%"+catname+"%' ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				objCat = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("parent_id"),rs.getInt("show_index"));
				
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
		return objCat;
		
	}
	public int delCat(int idCat) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "delete from cat_list where id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
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
	public int BlockCat(int idCat) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update cat_list set show_index=0 where cat_list.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
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
	public int ActiveCat(int idCat) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update cat_list set show_index=1 where cat_list.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
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
	public int editCat(int id,String nameCat, int prID) {
		int resut=0;
		conn = connectDBUtil.getConnection();
		String sql = "update cat_list set  name = ?, parent_id=? where id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nameCat);
			pst.setInt(2, prID);
			pst.setInt(3, id);
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
