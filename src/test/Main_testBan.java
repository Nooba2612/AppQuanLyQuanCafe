package test;

import dao.Ban_DAO;
import entity.Ban;
import entity.KhuVuc;
import java.util.ArrayList;

public class Main_testBan {
    public static void main(String[] args) {
        // Kiểm tra phương thức getDSBan()
        testGetDSBan();

        // Kiểm tra phương thức themBan()
        testThemBan();
//        testGetDSBan();
//        // Kiểm tra phương thức xoaBan()
//        testXoaBan();
//        testGetDSBan();
//        // Kiểm tra phương thức capNhatNV()
//        testCapNhatNV();
//
//        // Kiểm tra phương thức timBan()
        testTimBan();
    }

    // Phương thức test cho getDSBan()
    public static void testGetDSBan() {
        Ban_DAO banDAO = new Ban_DAO();
        ArrayList<Ban> dsBan = banDAO.getDSBan();
        System.out.println("Danh sách các bàn:");
        for (Ban ban : dsBan) {
            System.out.println(ban);
        }
    }

    // Phương thức test cho themBan()
    public static void testThemBan() {
        Ban_DAO banDAO = new Ban_DAO();
        KhuVuc kv = new KhuVuc(1, "Khu vực 1");
        Ban ban = new Ban(7000, 4, kv); // Ví dụ: thêm một bàn mới
        boolean result = banDAO.themBan(ban);
        if (result) {
            System.out.println("Thêm bàn thành công!");
        } else {
            System.out.println("Thêm bàn thất bại!");
        }
    }

    // Phương thức test cho xoaBan()
    public static void testXoaBan() {
        Ban_DAO banDAO = new Ban_DAO();
        int maBanCanXoa = 100; // Ví dụ: xóa bàn có mã 100
        boolean result = banDAO.xoaBan(maBanCanXoa);
        if (result) {
            System.out.println("Xóa bàn thành công!");
        } else {
            System.out.println("Xóa bàn thất bại!");
        }
    }

    // Phương thức test cho capNhatNV()
    public static void testCapNhatNV() {
        Ban_DAO banDAO = new Ban_DAO();
        KhuVuc kv = new KhuVuc(1, "Khu vực 1");
        Ban banCanCapNhat = new Ban(600, 10, kv); // Ví dụ: cập nhật số chỗ ngồi của bàn có mã 100 thành 6
        boolean result = banDAO.capNhatNV(banCanCapNhat);
        if (result) {
            System.out.println("Cập nhật bàn thành công!");
        } else {
            System.out.println("Cập nhật bàn thất bại!");
        }
    }

    // Phương thức test cho timBan()
    public static void testTimBan() {
        Ban_DAO banDAO = new Ban_DAO();
        int maBanCanTim = 101; // Ví dụ: tìm bàn có mã 600
        Ban ban = banDAO.timBan(maBanCanTim);
        if (ban != null) {
            System.out.println("Bàn được tìm thấy: " + ban);
        } else {
            System.out.println("Không tìm thấy bàn có mã " + maBanCanTim);
        }
    }
}
