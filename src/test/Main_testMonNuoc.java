package test;

import java.util.ArrayList;

import dao.MonNuoc_DAO;
import database.ConnectDB;
import entity.MonNuoc;

public class Main_testMonNuoc {
    public static void main(String[] args) {
        // Kiểm tra phương thức getDSMonNuoc
//        testGetDSMonNuoc();
    	try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	MonNuoc_DAO monNuocDao = new MonNuoc_DAO();
    	ArrayList<String> danhMucList = monNuocDao.getDSDanhMuc();
        	
        // Hiển thị danh sách danh mục
        System.out.println("Danh sách các danh mục trong cơ sở dữ liệu:");
        for (String danhMuc : danhMucList) {
            System.out.println(danhMuc);
        }
        // Kiểm tra phương thức themMonNuoc
//        testThemMonNuoc();
 //       testGetDSMonNuoc();
//
//        // Kiểm tra phương thức xoaMonNuoc
//        testXoaMonNuoc();
//
//        // Kiểm tra phương thức capNhatMonNuoc
//        testCapNhatMonNuoc();
//
//        // Kiểm tra phương thức timmonNuocTheoMa
 //       testTimMonNuocTheoMa();
    }

    public static void testGetDSMonNuoc() {
        MonNuoc_DAO monNuocDao = MonNuoc_DAO.getInstance();
        System.out.println("Danh sách món nước:");
        System.out.println(monNuocDao.getDSMonNuoc());
    }

    public static void testThemMonNuoc() {
        MonNuoc_DAO monNuocDao = MonNuoc_DAO.getInstance();
        MonNuoc monNuoc = new MonNuoc("MN001", "Món mới th", "Ly", 25000.0, "Nước uống");
        boolean result = monNuocDao.themMonNuoc(monNuoc);
        if (result) {
            System.out.println("Thêm món nước mới thành công!");
        } else {
            System.out.println("Thêm món nước mới thất bại!");
        }
    }

    public static void testXoaMonNuoc() {
        MonNuoc_DAO monNuocDao = MonNuoc_DAO.getInstance();
        String maMonNuoc = "MN001"; // Thay bằng mã món nước cần xóa
        boolean result = monNuocDao.xoaMonNuoc(maMonNuoc);
        if (result) {
            System.out.println("Xóa món nước thành công!");
        } else {
            System.out.println("Không tìm thấy món nước có mã " + maMonNuoc);
        }
    }

    public static void testCapNhatMonNuoc() {
        MonNuoc_DAO monNuocDao = MonNuoc_DAO.getInstance();
        MonNuoc monNuoc = new MonNuoc("MN001", "Trà sữa trân châu", "Ly", 35000.0, "Nước uống");
        boolean result = monNuocDao.capNhatMonNuoc(monNuoc);
        if (result) {
            System.out.println("Cập nhật món nước thành công!");
        } else {
            System.out.println("Cập nhật món nước thất bại!");
        }
    }

    public static void testTimMonNuocTheoMa() {
        MonNuoc_DAO monNuocDao = MonNuoc_DAO.getInstance();
        String maMonNuoc = "MN001"; // Thay bằng mã món nước cần tìm
        MonNuoc monNuoc = monNuocDao.timmonNuocTheoMa(maMonNuoc);
        if (monNuoc != null) {
            System.out.println("Món nước có mã " + maMonNuoc + ": " + monNuoc);
        } else {
            System.out.println("Không tìm thấy món nước có mã " + maMonNuoc);
        }
    }
}
