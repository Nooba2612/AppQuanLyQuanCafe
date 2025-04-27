package test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.Ban_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import database.ConnectDB;
import database.ConnectDBP;
import entity.HoaDon;
import entity.NhanVien;
import entity.KhachHang;
import entity.MonNuoc;
import entity.Ban;
import entity.ChiTietHoaDon;

public class Main_testHoaDon {
    public static void main(String[] args) {
    	ConnectDB c = ConnectDBP.getInstance();
		try {
			c.connect();
			System.out.println("kết nối thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HoaDon_DAO hd_dao = new HoaDon_DAO();
    	HoaDon hoaDon;
    	NhanVien_DAO nv_dao = new NhanVien_DAO();
    	KhachHang_DAO kh_dao = new KhachHang_DAO();
    	Ban_DAO ban_dao = new Ban_DAO();
    	//them hoa don
    	NhanVien nv = new NhanVien();
    	KhachHang kh = new KhachHang();
    	Ban ban = new Ban();
    	
    	nv.setMaNV("NV1");
    	kh.setMaKH(3);
    	ban.setMaBan(202);
    	hoaDon = new HoaDon(nv, kh, ban, null, 0, 0.1, 0, 0);
    	//hd_dao.addHD(hoaDon);
    	
    	//cập nhật tong tiền hóa đơn
    	HoaDon hd = new HoaDon();
        hd.setMaHD(4); // Set mã hóa đơn cần cập nhật tổng tiền
        // Khởi tạo một đối tượng HoaDonDAO và gọi phương thức updateTongTien
        
    	ArrayList<HoaDon> dshd = hd_dao.getAllHoaDon();
 		System.out.println("Danh sách hd:");
 		  for (HoaDon hD: dshd) {
 		      System.out.println(hD);
 		  }
 	        LocalDate ngayHienTai = LocalDate.now();

 	        // Gọi hàm tính tổng doanh thu theo ngày từ DAO với ngày hiện tại
 	        double tongDoanhThu = hd_dao.tinhTongDoanhThuTheoNgay(ngayHienTai);
 	        System.out.println(tongDoanhThu);
 	    }

	}

