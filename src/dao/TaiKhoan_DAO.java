package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import database.ConnectDB;
import entity.CaLamViec;
import entity.ChucVu;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	CaLamViec_DAO ca_dao = new CaLamViec_DAO();
	
	public ArrayList<TaiKhoan> dstk(){
		ArrayList<TaiKhoan> dstk= new ArrayList<TaiKhoan>();
		ConnectDB c = ConnectDB.getInstance();
		Statement stmt = null;
		try {
			c.connect();
	        Connection con = c.getConnection();
			con = ConnectDB.getConnection();
			String sql = "Select * from TaiKhoan";
			stmt= con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			while (rs.next()) {				
				int maTK=rs.getInt(1);
				int maCa=rs.getInt(2);
				String tenDN=rs.getString(3);
				String MatKhau=rs.getString(4);
				String MoTa=rs.getString(5);
				ArrayList<CaLamViec> dsCa = ca_dao.dsca();
				CaLamViec ca = dsCa.stream().filter(calv -> calv.getMaCa()== maCa).findFirst().orElse(null);
				TaiKhoan tk=new TaiKhoan(maTK, ca, tenDN, MatKhau, MoTa);
				dstk.add(tk);
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
		return dstk;		
	}
	public boolean themTK(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into"
					+ " TaiKhoan(MaTK, MaCa, tenDN, MatKhau, MoTa)"
					+ " values(?, ?, ?, ?, ?)");
			stmt.setInt(1, tk.getMaTK());
			stmt.setInt(2, tk.getCa().getMaCa());
			stmt.setString(3, tk.getUsername());
			stmt.setString(4, tk.getPassword());
			stmt.setString(5, tk.getMoTa());
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
	
	public boolean xoaTK(int maTK) {
		ConnectDB c = ConnectDB.getInstance();
		PreparedStatement stmt = null;
		int n=0;
		try {
			c.connect();
	        Connection con = c.getConnection();
			con = ConnectDB.getConnection();
			stmt = con.prepareStatement("delete from TaiKhoan "
					+ " Where MaTK = ?");
			stmt.setInt(1, maTK);
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
	
	public boolean capNhapTK(TaiKhoan tk) {
		ConnectDB c = ConnectDB.getInstance();
		PreparedStatement stmt = null;
		int n=0;
		try {
			c.connect();
	        Connection con = c.getConnection();
			con = ConnectDB.getConnection();
			stmt = con.prepareStatement("Update TaiKhoan"
										+ " Set TenCa=?,"
										+ " TaiKhoan=?,"
										+ " MatKhau=?,"
										+ " MoTa=? "
										+"where maTK=?");			
			stmt.setInt(1, tk.getCa().getMaCa());
			stmt.setString(2, tk.getUsername());
			stmt.setString(3, tk.getPassword());
			stmt.setString(4, tk.getMoTa());
			stmt.setInt(5, tk.getMaTK());
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
}
