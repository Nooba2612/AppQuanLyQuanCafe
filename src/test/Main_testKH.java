package test;
//test thành công
import java.sql.SQLException;
import java.util.ArrayList;
import dao.KhachHang_DAO;
import database.ConnectDB;
import database.ConnectDBP;
import entity.KhachHang;

public class Main_testKH {
    public static void main(String[] args) {
    	ConnectDB c = ConnectDB.getInstance();
		try {
			c.connect();
			System.out.println("kết nối thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Khởi tạo một đối tượng KhachHang_DAO
        KhachHang_DAO khachHangDao = new KhachHang_DAO();
        // Test phương thức getdskh()
        
//        
//     testThemKhachHang(khachHangDao);
//        testGetDSKhachHang(khachHangDao);
        testGetDSKhachHang(khachHangDao);
 //       testGetDSKhachHang(khachHangDao);
//        // Test phương thức xoaKH()
 //       testXoaKhachHang(khachHangDao);

//        // Test phương thức capNhapKH()
        testCapNhapKhachHang(khachHangDao);
 //       testGetDSKhachHang(khachHangDao);
//        
       // Test phương thức timKHTheoTen()
        testTimKhachHangTheoSDT(khachHangDao);
        
        c.disconnect();
    }
    
    public static void testGetDSKhachHang(KhachHang_DAO khachHangDao) {
        System.out.println("Danh sách khách hàng:");
        ArrayList<KhachHang> dsKhachHang = khachHangDao.getdskh();
        for (KhachHang kh : dsKhachHang) {
            System.out.println(kh);
        }
        System.out.println();
    }
    
    public static void testThemKhachHang(KhachHang_DAO khachHangDao) {
        System.out.println("Thêm khách hàng mới:");
        KhachHang newKhachHang = new KhachHang("Nguyen Van A", "0123456789");
        boolean result = khachHangDao.themKH(newKhachHang);
        if (result) {
            System.out.println("Thêm khách hàng mới thành công!");
        } else {
            System.out.println("Thêm khách hàng mới thất bại!");
        }
        System.out.println();
    }
    
    public static void testXoaKhachHang(KhachHang_DAO khachHangDao) {
        System.out.println("Xóa khách hàng:");
        int maKhachHang = 7; // Mã khách hàng cần xóa
        boolean result = khachHangDao.xoaKH(maKhachHang);
        if (result) {
            System.out.println("Xóa khách hàng thành công!");
        } else {
            System.out.println("Xóa khách hàng thất bại!");
        }
        System.out.println();
    }
    
    public static void testCapNhapKhachHang(KhachHang_DAO khachHangDao) {
        System.out.println("Cập nhật thông tin khách hàng:");
        KhachHang updatedKhachHang = new KhachHang("Nguyễn tám", "0934861057", 100);
        boolean result = khachHangDao.capNhapKH(updatedKhachHang);
        if (result) {
            System.out.println("Cập nhật thông tin khách hàng thành công!");
        } else {
            System.out.println("Cập nhật thông tin khách hàng thất bại!");
        }
        System.out.println();
    }
    
    public static void testTimKhachHangTheoSDT(KhachHang_DAO khachHangDao) {
        System.out.println("Tìm khách hàng theo số điện thoại:");
        String sdt = "0934861057"; // Tên khách hàng cần tìm
        KhachHang foundKhachHang = khachHangDao.timKHTheoSDT(sdt);
        if (foundKhachHang != null) {
            System.out.println("Khách hàng được tìm thấy: " + foundKhachHang);
        } else {
            System.out.println("Khách hàng không tồn tại!");
        }
        System.out.println();
    }
}
