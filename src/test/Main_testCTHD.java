package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChiTietHoaDon_DAO;
import database.ConnectDB;
import database.ConnectDBP;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MonNuoc;

public class Main_testCTHD {
	public static void main(String[] args) {
		//tạo kết nối
		ConnectDB c = ConnectDB.getInstance();
		try {
			c.connect();
			System.out.println("kết nối thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
		
//cách tạo 1 cthd thêm vào

         
//		 ChiTietHoaDon cthd1;
//		 HoaDon hd1 = new HoaDon();
//         hd1.setMaHD(2);
//         MonNuoc mon1 = new MonNuoc();
//         mon1.setMaMon("ST01");
//         cthdnew = new ChiTietHoaDon(hd1, mon1, 1);
//         cthd_dao.addChiTietHoaDon(cthdnew);
//         
//		 ChiTietHoaDon cth3;
//		 HoaDon hd3 = new HoaDon();
//         hd3.setMaHD(3);
//         MonNuoc mon3 = new MonNuoc();
//         mon3.setMaMon("CF01");
//         cthdnew = new ChiTietHoaDon(hd3, mon3, 1);
//         cthd_dao.addChiTietHoaDon(cthdnew);
//         
//         ChiTietHoaDon cth4;
//		 HoaDon hd4 = new HoaDon();
//         hd4.setMaHD(4);
//         MonNuoc mon4 = new MonNuoc();
//         mon4.setMaMon("CF01");
//         cthdnew = new ChiTietHoaDon(hd4, mon4, 1);
//         cthd_dao.addChiTietHoaDon(cthdnew);
         ArrayList<ChiTietHoaDon> dscthd = cthd_dao.getAllChiTietHoaDon();
 		System.out.println("Danh sách cthd:");
 		  for (ChiTietHoaDon cthd: dscthd) {
 		      System.out.println(cthd);
 		  }

	}
}
