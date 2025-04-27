package dao;
//dã test thành công
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	public static KhachHang_DAO getInstance() {
		return new KhachHang_DAO();
	}
	public ArrayList<KhachHang> getdskh(){
		ArrayList<KhachHang> dskh=new ArrayList<KhachHang>();
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectDB.getInstance().connect();
			if (con == null) {
	            // Xử lý trường hợp không có kết nối được thiết lập
	            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
	            return dskh;
	        }
			String sql = "Select * from KhachHang";
			stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			while (rs.next()) {
				int maKH=rs.getInt(1);
				String tenKH=rs.getString(2);
				String sdt=rs.getString(3);
				int diemTichLuy=rs.getInt(4);
				KhachHang kh=new KhachHang(maKH,tenKH, sdt, diemTichLuy);
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dskh;
	}
	
	public boolean themKH(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into"
					+ " KhachHang(TenKH, SoDienThoai)"
					+ " values(?, ?)");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getSdt());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean themKHKhongTK(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into"
					+ " KhachHang(TenKH)"
					+ " values(?)");
			stmt.setString(1, kh.getTenKH());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean xoaKH(int maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete from KhachHang"
					+ " Where MaKH = ?");
			stmt.setInt(1, maKH);
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
	
	public boolean capNhapKH(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("Update KhachHang"
										+ " Set TenKH=?,"
										+ " DiemTichLuy=? "
										+"WHERE SoDienThoai=?");
			stmt.setString(1, kh.getTenKH());
			stmt.setInt(2, kh.getDiemTichLuy());
			stmt.setString(3, kh.getSdt());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n>0;	
	}
	
	public KhachHang timKHTheoSDT(String sdtF){
		KhachHang kh = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = " SELECT * "
					+ "  FROM KhachHang "
					+ "  WHERE SoDienThoai = '"+ sdtF +"'";
			Statement statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			
			while (rs.next()) {
				int maKH = rs.getInt(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(3);
				int diemtichLuy = rs.getInt(4);

				kh=new KhachHang(maKH,tenKH, sdt, diemtichLuy);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
}
