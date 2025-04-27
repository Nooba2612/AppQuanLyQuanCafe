package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Ban_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import database.ConnectDB;
import database.ConnectDBP;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;

public class testnv {
public static void main(String[] args) {
	ConnectDB c = ConnectDBP.getInstance();
	try {
		c.connect();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	HoaDon_DAO hd_dao = new HoaDon_DAO();
	List<HoaDon> dshd = hd_dao.getAllHoaDon();
	System.out.println("Danh sách hd:");
	  for (HoaDon hd: dshd) {
	      System.out.println(hd);
	  }
	ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
	List<ChiTietHoaDon> dscthd = cthd_dao.getAllChiTietHoaDon();
	System.out.println("Danh sách nv:");
	  for (ChiTietHoaDon cthd: dscthd) {
	      System.out.println(cthd);
	  }
//	NhanVien_DAO nv_dao = new NhanVien_DAO();
//    ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
//    System.out.println("Danh sách nv:");
//    for (NhanVien nv : dsnv) {
//        System.out.println(nv);
//    }
}
}
