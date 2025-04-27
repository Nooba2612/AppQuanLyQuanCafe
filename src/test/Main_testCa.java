package test;
	import java.sql.Time;
import java.util.ArrayList;
	import dao.CaLamViec_DAO;
	import database.ConnectDB;
	import entity.CaLamViec;
public class Main_testCa {
	    public static void main(String[] args) {
	        // Khởi tạo một đối tượng CaLamViec_DAO
	        CaLamViec_DAO caLamViecDao = new CaLamViec_DAO();
	        ConnectDB con = ConnectDB.getInstance();
	        con.getConnection();
	        
	        // Test phương thức dsca()
//	        testGetDSCa(caLamViecDao);
	        
	        // Test phương thức themCa()
        testThemCa(caLamViecDao);
//	        testGetDSCa(caLamViecDao);
//	        
//	        // Test phương thức xoaCa()
	        testXoaCa(caLamViecDao);
	        testGetDSCa(caLamViecDao);
//	        
//	        // Test phương thức capNhapCa()
	        testCapNhapCa(caLamViecDao);
	        testGetDSCa(caLamViecDao);
//	        testGetDSCa(caLamViecDao);
//	        
	        con.disconnect();
	    }
	    
	    public static void testGetDSCa(CaLamViec_DAO caLamViecDao) {
	        System.out.println("Danh sách ca làm việc:");
	        ArrayList<CaLamViec> dsCa = caLamViecDao.dsca();
	        for (CaLamViec ca : dsCa) {
	            System.out.println(ca.getTenCa() + " - " + ca.getgioVaoLam() + " - " + ca.getGioKetThuc());
	        }
	        System.out.println();
	    }
	    
	    public static void testThemCa(CaLamViec_DAO caLamViecDao) {
	        // Tạo một thời gian bắt đầu và kết thúc cụ thể
	        Time gioVaoLam = Time.valueOf("06:00:00");
	        Time gioKetThuc = Time.valueOf("10:00:00");

	        // Tạo đối tượng CaLamViec mới
	        CaLamViec newCa = new CaLamViec(5, "Ca Sáng", gioVaoLam, gioKetThuc);
	        
	        boolean result = caLamViecDao.themCa(newCa);
	        if (result) {
	            System.out.println("Thêm ca làm việc thành công!");
	        } else {
	            System.out.println("Thêm ca làm việc thất bại!");
	        }
	        System.out.println();
	    }
	    
	    public static void testXoaCa(CaLamViec_DAO caLamViecDao) {
	        System.out.println("Xóa ca làm việc:");
	        int maCa = 5; // Mã ca làm việc cần xóa
	        boolean result = caLamViecDao.xoaCa(maCa);
	        if (result) {
	            System.out.println("Xóa ca làm việc thành công!");
	        } else {
	            System.out.println("Xóa ca làm việc thất bại!");
	        }
	        System.out.println();
	    }
	    
	    public static void testCapNhapCa(CaLamViec_DAO caLamViecDao) {
	        // Tạo một thời gian bắt đầu và kết thúc cụ thể
	        Time gioVaoLam = Time.valueOf("18:00:00");
	        Time gioKetThuc = Time.valueOf("22:00:00");

	        // Tạo đối tượng CaLamViec cần cập nhật
	        CaLamViec updatedCa = new CaLamViec(5, "Ca Buổi Tối", gioVaoLam, gioKetThuc);

	        boolean result = caLamViecDao.capNhapCa(updatedCa);
	        if (result) {
	            System.out.println("Cập nhật ca làm việc thành công!");
	        } else {
	            System.out.println("Cập nhật ca làm việc thất bại!");
	        }
	        System.out.println();
	    }
	}
