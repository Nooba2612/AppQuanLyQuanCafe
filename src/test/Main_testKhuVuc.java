package test;

import java.util.ArrayList;
import dao.KhuVuc_DAO;
import database.ConnectDB;
import entity.KhuVuc;

public class Main_testKhuVuc {
    public static void main(String[] args) {
        // Khởi tạo một đối tượng KhuVuc_DAO
        KhuVuc_DAO kvDao = KhuVuc_DAO.getInstance();
        ConnectDB con = ConnectDB.getInstance();
        con.getConnection();
        // Test phương thức getDSKhuVuc()
        testGetDSKhuVuc(kvDao);
        
        // Test phương thức themKhuVuc()
        testThemKhuVuc(kvDao);
        testGetDSKhuVuc(kvDao);
       // Test phương thức xoaKhuVuc()
        //testXoaKhuVuc(kvDao);
        //testGetDSKhuVuc(kvDao);
        // Test phương thức capNhatKhuVuc()
        testCapNhatKhuVuc(kvDao);
        testGetDSKhuVuc(kvDao);
//        
        // Test phương thức timCVTheoMa()
        testTimKVTheoMa(kvDao);
        con.disconnect();
    }
    
    public static void testGetDSKhuVuc(KhuVuc_DAO kvDao) {
        System.out.println("Danh sách các khu vực:");
        ArrayList<KhuVuc> dsKhuVuc = kvDao.getDSKhuVuc();
        for (KhuVuc kv : dsKhuVuc) {
            System.out.println(kv);
        }
        System.out.println();
    }
    
    public static void testThemKhuVuc(KhuVuc_DAO kvDao) {
        System.out.println("Thêm khu vực mới:");
        KhuVuc newKhuVuc = new KhuVuc(6, "Khu vực 6");
        boolean result = kvDao.themKhuVuc(newKhuVuc);
        if (result) {
            System.out.println("Thêm khu vực mới thành công!");
        } else {
            System.out.println("Thêm khu vực mới thất bại!");
        }
        System.out.println();
    }
    
    public static void testXoaKhuVuc(KhuVuc_DAO kvDao) {
        System.out.println("Xóa khu vực:");
        int maKhuVuc = 6; // Mã khu vực cần xóa
        boolean result = kvDao.xoaKhuVuc(maKhuVuc);
        if (result) {
            System.out.println("Xóa khu vực thành công!");
        } else {
            System.out.println("Xóa khu vực thất bại!");
        }
        System.out.println();
    }
    
    public static void testCapNhatKhuVuc(KhuVuc_DAO kvDao) {
        System.out.println("Cập nhật khu vực:");
        KhuVuc updatedKhuVuc = new KhuVuc(6, "Khu vực 6 updated");
        boolean result = kvDao.capNhatKhuVuc(updatedKhuVuc);
        if (result) {
            System.out.println("Cập nhật khu vực thành công!");
        } else {
            System.out.println("Cập nhật khu vực thất bại!");
        }
        System.out.println();
    }
    
    public static void testTimKVTheoMa(KhuVuc_DAO kvDao) {
        System.out.println("Tìm khu vực theo mã:");
        int maKhuVuc = 1; // Mã khu vực cần tìm
        KhuVuc foundKhuVuc = kvDao.timKVTheoMa(maKhuVuc);
        if (foundKhuVuc != null) {
            System.out.println("Khu vực được tìm thấy: " + foundKhuVuc);
        } else {
            System.out.println("Không tìm thấy khu vực có mã " + maKhuVuc);
        }
        System.out.println();
    }
}
