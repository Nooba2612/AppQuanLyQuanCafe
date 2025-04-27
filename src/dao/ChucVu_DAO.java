package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.ConnectDB;
import entity.ChucVu;

public class ChucVu_DAO {
	
	public ArrayList<ChucVu> getDSChucVu(){
		ArrayList<ChucVu> dsChucVu = new ArrayList<ChucVu>();
		Statement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChucVu";
			stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int maChucVu = rs.getInt(1);
				String tenChucVu = rs.getString(2);
				Double luongTheoGio = rs.getDouble(3);
				ChucVu cv = new ChucVu(maChucVu, tenChucVu, luongTheoGio);
				dsChucVu.add(cv);
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
		return dsChucVu;
	}
	
	public boolean themChucVu(ChucVu cv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<ChucVu> dscv = getDSChucVu();
		if(dscv.contains(cv))
			return false;
		try {
			stmt = con.prepareStatement("insert into"
					+ " ChucVu(MaChucVu, TenChucVu, LuongTheoGio)"
					+ " values(?, ?, ?)");
			stmt.setInt(1, cv.getMaChucVu());
			stmt.setString(2, cv.getTenChucVu());
			stmt.setDouble(3, cv.getLuongTheoGio());
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
	
	public boolean xoaChucVu(int maChucVu) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<ChucVu> dscv = getDSChucVu();
		ChucVu cv = dscv.stream().filter(x -> x.getMaChucVu()==maChucVu).findFirst().orElse(null);
		if(cv==null)
			return false;
		try {
			stmt = con.prepareStatement("delete from ChucVu"
					+ " Where MaChucVu = ?");
			stmt.setInt(1, maChucVu);
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
	
	public boolean capNhatChucVu(ChucVu cv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<ChucVu> dscv = getDSChucVu();
		ChucVu cvTam = dscv.stream().filter(x -> x.getMaChucVu()==cv.getMaChucVu()).findFirst().orElse(null);
		if(cvTam==null)
			return false;
		try {
			stmt = con.prepareStatement("Update ChucVu"
										+ " Set TenChucVu=?,"
										+ " LuongTheoGio=?"
										+ " Where MaChucVu = ?");
			stmt.setString(1, cv.getTenChucVu());
			stmt.setDouble(2, cv.getLuongTheoGio());
			stmt.setInt(3, cv.getMaChucVu());
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
	
	public ChucVu timCVTheoMa(int ma){
		ChucVu cv = null;
		Statement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChucVu"
					+ " Where MaChucVu=" + ma;
			stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int maChucVu = rs.getInt(1);
				String TenChucVu = rs.getString(2);
				Double luongTheoGio = rs.getDouble(3);
				cv = new ChucVu(maChucVu, TenChucVu, luongTheoGio);
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
		return cv;
	}
	
	public ChucVu timCVTheoTen(String ten){
		ChucVu cv = null;
		Statement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChucVu"
					+ " Where TenChucVu Like '%" + ten + "%' ";
			stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int maChucVu = rs.getInt(1);
				String TenChucVu = rs.getString(2);
				Double luongTheoGio = rs.getDouble(3);
				cv = new ChucVu(maChucVu, TenChucVu, luongTheoGio);
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
		return cv;
	}
	
}
