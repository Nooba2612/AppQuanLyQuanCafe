package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import database.ConnectDB;
import entity.CaLamViec;
import entity.LichLamViec;
import entity.NhanVien;

public class LichLamViec_DAO {
//	private int maLLV;
//	private NhanVien nv;
//	private CaLamViec ca;
//	private LocalDate ngayLamViec;
	
//	MaLLV int IDENTITY(1,1) NOT NULL PRIMARY KEY,
//	MaNV nvarchar(10) NOT NULL,
//	MaCa int NOT NULL,
//    NgayLamViec date,
//	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
//	FOREIGN KEY (MaCa) REFERENCES CaLamViec(MaCa)

	
	NhanVien_DAO nv_dao = new NhanVien_DAO();
	CaLamViec_DAO clv_dao = new CaLamViec_DAO();
	
	public ArrayList<LichLamViec> getDSLichLamViec(){
		ArrayList<LichLamViec> dsLichLamViec = new ArrayList<LichLamViec>();
		Statement statemen = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LichLamViec";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			while(rs.next()){
				int maLLV = rs.getInt(1);
				String maNV = rs.getString(2);
				ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
				NhanVien nv = dsnv.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
				int maCa = rs.getInt(3);
				ArrayList<CaLamViec> dsCa = clv_dao.dsca();
				CaLamViec ca = dsCa.stream().filter(clv -> clv.getMaCa()==maCa).findFirst().orElse(null);
				LocalDate ngayLamViec = rs.getDate(4).toLocalDate();
				LichLamViec llv = new LichLamViec(maLLV, nv, ca, ngayLamViec);
				dsLichLamViec.add(llv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statemen.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsLichLamViec;
	}
	
	public boolean themLichLamViec(String maNV, int maCa, LocalDate ngayLamViec) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into"
					+ " LichLamViec(MaNV, MaCa, NgayLamViec)"
					+ " values(?, ?, ?)");
			stmt.setString(1, maNV);
			stmt.setInt(2, maCa);
			stmt.setDate(3, Date.valueOf(ngayLamViec));
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
	
	public boolean xoaLichLamViec(int maLLV) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete from LichLamViec"
					+ " Where MaLLV = ?");
			stmt.setInt(1, maLLV);
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
	
	public boolean capNhatLichLamViec(LichLamViec llv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		
		try {
			stmt = con.prepareStatement("Update LichLamViec"
										+ " Set MaNV=?,"
										+ " MaCa=?,"
										+ " NgayLamViec=?"
										+ " Where MaLLV = ?");
			stmt.setString(1, llv.getNv().getMaNV());
			stmt.setInt(2, llv.getCa().getMaCa());
			stmt.setDate(3, Date.valueOf(llv.getNgayLamViec()));
			stmt.setInt(4, llv.getMaLLV());
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
	
	public ArrayList<LichLamViec> timLichLamViecTheoNhanVien(String maNV){
		Statement statemen = null;
		ArrayList<LichLamViec> dsLLV = new ArrayList<LichLamViec>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LichLamViec"
					+ " Where MaNV = '" + maNV + "'";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
			ArrayList<CaLamViec> dsCa = clv_dao.dsca();
			
			while (rs.next()) {
				int maLLV = rs.getInt(1);
				NhanVien nv = dsnv.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
				int maCa = rs.getInt(3);
				CaLamViec ca = dsCa.stream().filter(x -> x.getMaCa()==maCa).findFirst().orElse(null);
				LocalDate ngayLamViec = rs.getDate(4).toLocalDate();
				LichLamViec llv = new LichLamViec(maLLV, nv, ca, ngayLamViec);
				dsLLV.add(llv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statemen.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsLLV;
	}
	
	public ArrayList<LichLamViec> timLichLamViecTheoNgay(LocalDate ngayLamViec){
		Statement statemen = null;
		ArrayList<LichLamViec> dsLLV = new ArrayList<LichLamViec>();
		Date ngayLamViecsql = Date.valueOf(ngayLamViec);
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LichLamViec"
					+ " Where NgayLamViec = '" + ngayLamViecsql + "'";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
			ArrayList<CaLamViec> dsCa = clv_dao.dsca();
			
			while (rs.next()) {
				int maLLV = rs.getInt(1);
				String maNV = rs.getString(2);
				NhanVien nv = dsnv.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
				int maCa = rs.getInt(3);
				CaLamViec ca = dsCa.stream().filter(x -> x.getMaCa()==maCa).findFirst().orElse(null);
				LichLamViec llv = new LichLamViec(maLLV, nv, ca, ngayLamViec);
				dsLLV.add(llv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statemen.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsLLV;
	}
	
	public ArrayList<LichLamViec> timLichLamViecTheoCa(int maCa){
		Statement statemen = null;
		ArrayList<LichLamViec> dsLLV = new ArrayList<LichLamViec>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LichLamViec"
					+ " Where MaCa = " + maCa ;
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
			ArrayList<CaLamViec> dsCa = clv_dao.dsca();
			
			while (rs.next()) {
				int maLLV = rs.getInt(1);
				String maNV = rs.getString(2);
				NhanVien nv = dsnv.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
				CaLamViec ca = dsCa.stream().filter(x -> x.getMaCa()==maCa).findFirst().orElse(null);
				LocalDate ngayLamViec = rs.getDate(4).toLocalDate();
				LichLamViec llv = new LichLamViec(maLLV, nv, ca, ngayLamViec);
				dsLLV.add(llv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statemen.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsLLV;
	}
}
