package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import database.ConnectDB;
import entity.CaLamViec;

public class CaLamViec_DAO {
	
	public ArrayList<CaLamViec> dsca(){
		ArrayList<CaLamViec> dsca=new ArrayList<CaLamViec>();
		ConnectDB c = ConnectDB.getInstance();
		Statement statemen = null;
		try {
			c.connect();
	        Connection con = c.getConnection();
			String sql = "Select * from CaLamViec";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			while (rs.next()) {
				int maCa=rs.getInt(1);
				String tenCa=rs.getString(2);
				Time gioVaoLam = rs.getTime(3);
                Time gioKetThuc = rs.getTime(4);
				CaLamViec ca=new CaLamViec(maCa, tenCa, gioVaoLam, gioKetThuc);
				dsca.add(ca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsca;
	}
	
	public boolean themCa(CaLamViec ca) {
		ConnectDB c = ConnectDB.getInstance();
		PreparedStatement stmt = null;
		int n=0;
		try {
			c.connect();
	        Connection con = c.getConnection();
			stmt = con.prepareStatement("insert into"
					+ " CaLamViec(MaCa, TenCa, GioVaoLam, GioKetThuc)"
					+ " values(?, ?, ?, ?)");
			stmt.setInt(1, ca.getMaCa());
			stmt.setString(2, ca.getTenCa());
			stmt.setTime(3, ca.getgioVaoLam());
			stmt.setTime(4, ca.getGioKetThuc());
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
	public boolean xoaCa(int maCa) {
		ConnectDB c = ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			c.connect();
	        con = c.getConnection();
			stmt = con.prepareStatement("delete from CaLamViec"
					+ " Where MaCa = ?");
			stmt.setInt(1, maCa);
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
	
	public boolean capNhapCa(CaLamViec ca) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("Update CaLamViec"
										+ " Set TenCa=?,"
										+ " GioVaoLam=?,"
										+ " GioKetThuc=? "
										+"where MaCa=?");
			stmt.setString(1, ca.getTenCa());
			stmt.setTime(2, ca.getgioVaoLam());
			stmt.setTime(3, ca.getGioKetThuc());
			stmt.setInt(4, ca.getMaCa());
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
