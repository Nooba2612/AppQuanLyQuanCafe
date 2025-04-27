package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;
import entity.MonNuoc;
import entity.NhanVien;
import entity.MonNuoc;

public class MonNuoc_DAO {
	public static MonNuoc_DAO getInstance() {
		return new MonNuoc_DAO();
	}
	public ArrayList<MonNuoc> getDSMonNuoc(){
		ArrayList<MonNuoc> dsMonNuoc = new ArrayList<MonNuoc>();
		ConnectDB c = ConnectDB.getInstance();
		Statement stmt = null;
		try {
			c.connect();
	        Connection con = c.getConnection();
			con = ConnectDB.getConnection();
			if (con == null) {
	            // Xử lý trường hợp không có kết nối được thiết lập
	            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
	            return dsMonNuoc;
	        }
			String sql = "Select * from MonNuoc";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String maMonNuoc = rs.getString(1);
				String tenMonNuoc = rs.getString(2);
				Double donGia = rs.getDouble(3);
				String dvt = rs.getString(4);
				String danhMuc = rs.getString(5);
				MonNuoc monNuoc = new MonNuoc(maMonNuoc, tenMonNuoc, dvt, donGia, danhMuc);
				dsMonNuoc.add(monNuoc);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsMonNuoc;
	}
	// Lấy danh sách các danh mục từ cơ sở dữ liệu
    public ArrayList<String> getDSDanhMuc() {
        ArrayList<String> danhMucList = new ArrayList<String>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getConnection();
            if (con == null) {
                // Xử lý trường hợp không có kết nối được thiết lập
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return danhMucList;
            }
            String sql = "SELECT DanhMuc "
            		+ "FROM MonNuoc "
            		+ "GROUP BY DanhMuc";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String danhMuc = rs.getString("DanhMuc");
                danhMucList.add(danhMuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return danhMucList;
    }
	//lấy danh sách món nước theo danh mục
    public ArrayList<MonNuoc> getDSMonNuocTheoDanhMuc(String danhMuc) {
        ArrayList<MonNuoc> dsMonNuoc = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getConnection();
            if (con == null) {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return dsMonNuoc;
            }
            String sql = "SELECT * FROM MonNuoc WHERE DanhMuc = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, danhMuc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String maMonNuoc = rs.getString(1);
                String tenMonNuoc = rs.getString(2);
                Double donGia = rs.getDouble(3);
                String dvt = rs.getString(4);
                String danhMucMon = rs.getString(5);
                MonNuoc monNuoc = new MonNuoc(maMonNuoc, tenMonNuoc, dvt, donGia, danhMucMon);
                dsMonNuoc.add(monNuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dsMonNuoc;
    }
	public boolean themMonNuoc(MonNuoc mon) {
		ConnectDB c = ConnectDB.getInstance();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<MonNuoc> dsmonNuoc = getDSMonNuoc();
		if(dsmonNuoc.contains(mon))
			return false;
		try {
			c.connect();
			 Connection con = c.getConnection();
			stmt = con.prepareStatement("insert into"
					+ " MonNuoc(MaMon, TenMon, DonGia, DonViTinh, DanhMuc)"
					+ " values(?, ?, ?, ?, ?)");
			stmt.setString(1, mon.getMaMon());
			stmt.setString(2, mon.getTenMon());
			stmt.setDouble(3, mon.getDonGia());
			stmt.setString(4, mon.getDonViTinh());
			stmt.setString(5, mon.getDanhMuc());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}

	public boolean xoaMonNuoc(String mamonNuoc) {
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        ArrayList<MonNuoc> dsMonNuoc = getDSMonNuoc();
        MonNuoc monNuocToRemove = null;
        //tìm món
        for (MonNuoc monNuoc : dsMonNuoc) {
            if (monNuoc.getMaMon().equals(mamonNuoc)) {
                monNuocToRemove = monNuoc;
                break;
            }
        }
        if (monNuocToRemove == null)
            return false;
        try {
            stmt = con.prepareStatement("DELETE FROM MonNuoc WHERE MaMon = ?");
            stmt.setString(1, mamonNuoc);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return n>0;
	}

	public boolean capNhatMonNuoc(MonNuoc monNuoc) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;

        ArrayList<MonNuoc> dsMonNuoc = getDSMonNuoc();
        MonNuoc monNuocNew = null;
        for (MonNuoc MonNuoc : dsMonNuoc) {
        	if (monNuoc.getMaMon().equals(monNuoc.getMaMon())) {
                monNuocNew = monNuoc;
                break;
            }
        }
        if (monNuocNew == null)
            return false;
		try {
			stmt = con.prepareStatement("Update MonNuoc"
										+ " Set TenMon=?,"
										+ "DonGia = ?,"
										+ "DonViTinh = ?,"
										+ "DanhMuc = ?"
										+ " Where MaMon = ?");
			
			stmt.setString(1, monNuoc.getTenMon());
			stmt.setDouble(2, monNuoc.getDonGia());
			stmt.setString(3, monNuoc.getDonViTinh());
			stmt.setString(4, monNuoc.getDanhMuc());
			stmt.setString(5, monNuoc.getMaMon());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public MonNuoc timmonNuocTheoMa(String ma) {
		MonNuoc monNuoc = null;
		Statement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from MonNuoc"
					+ " Where MaMon = '" + ma+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maMonNuoc = rs.getString(1);
				String tenMonNuoc = rs.getString(2);
				Double donGia = rs.getDouble(3);
				String dvt = rs.getString(4);
				String danhMuc = rs.getString(5);
			
				monNuoc = new MonNuoc(maMonNuoc, tenMonNuoc, dvt, donGia, danhMuc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return monNuoc;
	}

}

