package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;
import entity.KhuVuc;
import entity.NhanVien;
import entity.KhuVuc;

public class KhuVuc_DAO {
	public static KhuVuc_DAO getInstance() {
		return new KhuVuc_DAO();
	}
	public ArrayList<KhuVuc> getDSKhuVuc(){
		ArrayList<KhuVuc> dsKhuVuc = new ArrayList<KhuVuc>();
		ConnectDB c = ConnectDB.getInstance();
		Statement stmt = null;
		try {
			c.connect();
	        Connection con = c.getConnection();
			con = ConnectDB.getConnection();
			if (con == null) {
	            // Xử lý trường hợp không có kết nối được thiết lập
	            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
	            return dsKhuVuc;
	        }
			String sql = "Select * from KhuVuc";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int maKhuVuc = rs.getInt(1);
				String tenKhuVuc = rs.getString(2);
				KhuVuc kv = new KhuVuc(maKhuVuc, tenKhuVuc);
				dsKhuVuc.add(kv);
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
		return dsKhuVuc;
	}
	
	public boolean themKhuVuc(KhuVuc kv) {
		ConnectDB c = ConnectDB.getInstance();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<KhuVuc> dskv = getDSKhuVuc();
		if(dskv.contains(kv))
			return false;
		try {
			c.connect();
			 Connection con = c.getConnection();
			stmt = con.prepareStatement("insert into"
					+ " KhuVuc(MaKV, TenKV)"
					+ " values(?, ?)");
			stmt.setInt(1, kv.getMaKV());
			stmt.setString(2, kv.getTenKV());
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

	public boolean xoaKhuVuc(int maKV) {
		Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        ArrayList<KhuVuc> dsKhuVuc = getDSKhuVuc();
        KhuVuc kvToRemove = null;
        //tìm khu vực
        for (KhuVuc kv : dsKhuVuc) {
            if (kv.getMaKV() == maKV) {
                kvToRemove = kv;
                break;
            }
        }
        if (kvToRemove == null)
            return false;
        try {
            stmt = con.prepareStatement("DELETE FROM KhuVuc WHERE MaKV = ?");
            stmt.setInt(1, maKV);
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

	public boolean capNhatKhuVuc(KhuVuc kv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		
        ArrayList<KhuVuc> dsKhuVuc = getDSKhuVuc();
        KhuVuc kvNew = null;
        //tìm khu vực
        for (KhuVuc khuvuc : dsKhuVuc) {
        	if (khuvuc.getMaKV() == kv.getMaKV()) {
                kvNew = kv;
                break;
            }
        }
        if (kvNew == null)
            return false;
		try {
			stmt = con.prepareStatement("Update KhuVuc"
										+ " Set TenKV=?"
										+ " Where MaKV = ?");
			
			stmt.setString(1, kv.getTenKV());
			stmt.setInt(2, kv.getMaKV());
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
	public KhuVuc timKVTheoMa(int ma) {
		KhuVuc kv = null;
		Statement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KhuVuc"
					+ " Where MaKV = " + ma;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int maKV = rs.getInt(1);
				String tenKV = rs.getString(2);
				kv = new KhuVuc(maKV, tenKV);
			}

			//b5 đóng kết nối
			//ConnectDB.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return kv;
	}
	
	
}

