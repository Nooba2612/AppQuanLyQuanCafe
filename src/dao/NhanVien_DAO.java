package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import database.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;

public class NhanVien_DAO {
	ChucVu_DAO cv_dao = new ChucVu_DAO();
	public ArrayList<NhanVien> getDSNhanVien(){
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		Statement statemen = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			while(rs.next()){
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String trangThai = rs.getString(3);
				LocalDate ngayVaoLam = rs.getDate(4).toLocalDate();
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				int maChucVu = rs.getInt(7);
				ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
				ChucVu chucVu = dscv.stream().filter(cv -> cv.getMaChucVu()==maChucVu).findFirst().orElse(null);
				NhanVien nv = new NhanVien(maNV, tenNV, trangThai, ngayVaoLam, sdt, email, chucVu);				
				dsNhanVien.add(nv);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//statemen.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsNhanVien;
	}
	public ArrayList<NhanVien> getDSTN() {
	    ArrayList<NhanVien> danhSachNhanVienThuNgan = new ArrayList<>();
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet rs = null;
	    try {
	        connection = ConnectDB.getConnection();
	        String query = "SELECT * FROM NhanVien WHERE MaChucVu = 1";
	        statement = connection.createStatement();
	        rs = statement.executeQuery(query);
	        while (rs.next()) {
	        	String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String trangThai = rs.getString(3);
				LocalDate ngayVaoLam = rs.getDate(4).toLocalDate();
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				int maChucVu = rs.getInt(7);
				ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
				ChucVu chucVu = dscv.stream().filter(cv -> cv.getMaChucVu()==maChucVu).findFirst().orElse(null);
				NhanVien nv = new NhanVien(maNV, tenNV, trangThai, ngayVaoLam, sdt, email, chucVu);				
				danhSachNhanVienThuNgan.add(nv);
	        }
	    } catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//statemen.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return danhSachNhanVienThuNgan;
	}

	public boolean themNV(NhanVien nv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<NhanVien> dsNhanVien = getDSNhanVien();
		if(dsNhanVien.contains(nv))
			return false;
		try {
			stmt = con.prepareStatement("insert into"
					+ " NhanVien(MaNV, Ten, TrangThaiLamViec, NgayVaoLam, SoDienThoai, Email, MaChucVu)"
					+ " values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getTrangThaiLamViec());
			stmt.setDate(4, Date.valueOf(nv.getNgayVaoLam()));
			stmt.setString(5, nv.getSdt());
			stmt.setString(6, nv.getEmail());
			stmt.setInt(7, nv.getChucVu().getMaChucVu());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return n>0;
	}
	
	public boolean xoaNV(String maNV) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		ArrayList<NhanVien> dsNhanVien = getDSNhanVien();
		NhanVien nv = dsNhanVien.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
		if(nv==null)
			return false;
		try {
			stmt = con.prepareStatement("delete from NhanVien"
					+ " Where MaNV = ?");
			stmt.setString(1, maNV);
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
	
	public boolean capNhatNV(NhanVien nv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		if(nv!=null) {
			ArrayList<NhanVien> dsNhanVien = getDSNhanVien();
			NhanVien nvTam = dsNhanVien.stream().filter(x -> x.getMaNV().equalsIgnoreCase(nv.getMaNV())).findFirst().orElse(null);
			if(nvTam==null)
				return false;
		}
		try {
			stmt = con.prepareStatement("Update NhanVien"
										+ " Set Ten=?,"
										+ " TrangThaiLamViec=?,"
										+ " NgayVaoLam=?,"
										+ " SoDienThoai=?,"
										+ " Email=?,"
										+ " MaChucVu=?"
										+ " Where MaNV = ?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getTrangThaiLamViec());
			stmt.setDate(3, Date.valueOf(nv.getNgayVaoLam()));
			stmt.setString(4, nv.getSdt());
			stmt.setString(5, nv.getEmail());
			stmt.setInt(6, nv.getChucVu().getMaChucVu());
			stmt.setString(7, nv.getMaNV());
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
	
	public NhanVien timNVTheoMa(String ma){
		NhanVien nv = null;
		Statement statemen = null;
		//minh
		//ConnectDB c = ConnectDB.getInstance();
		try {
			//minh
			//
			//c.connect();
	        Connection con;// = c.getConnection();
			con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien"
					+ " Where MaNV = '" + ma + "'";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String trangThai = rs.getString(3);
				LocalDate ngayVaoLam = rs.getDate(4).toLocalDate();
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				int maChucVu = rs.getInt(7);
				ChucVu chucVu = dscv.stream().filter(cv -> cv.getMaChucVu()==maChucVu).findFirst().orElse(null);
				nv = new NhanVien(maNV, tenNV, trangThai, ngayVaoLam, sdt, email, chucVu);
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
		return nv;
	}
	
	public ArrayList<NhanVien> timNVTheoTen(String ten){
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		Statement statemen = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien"
					+ " Where Ten Like N'%" + ten + "%' ";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
			ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String trangThai = rs.getString(3);
				LocalDate ngayVaoLam = rs.getDate(4).toLocalDate();
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				int maChucVu = rs.getInt(7);
				ChucVu chucVu = dscv.stream().filter(cv -> cv.getMaChucVu()==maChucVu).findFirst().orElse(null);
				NhanVien nv = new NhanVien(maNV, tenNV, trangThai, ngayVaoLam, sdt, email, chucVu);
				dsnv.add(nv);
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
		return dsnv;
	}
	
}
