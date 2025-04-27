package test;

import dao.Ban_DAO;
import dao.TaiKhoan_DAO;
import entity.Ban;
import entity.CaLamViec;
import entity.KhuVuc;
import entity.TaiKhoan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main_testTK {
    public static void main(String[] args) {
        // Kiểm tra phương thức lấy danh sách tài khoản
//        testLayDanhSachTaiKhoan();

        // Kiểm tra phương thức thêm tài khoản
//        testThemTaiKhoan();
        
        // Kiểm tra phương thức xóa tài khoản
//        testXoaTaiKhoan();
        testLayDanhSachTaiKhoan();
        // Kiểm tra phương thức cập nhật tài khoản
        testCapNhatTaiKhoan();
    }

    public static void testLayDanhSachTaiKhoan() {
        TaiKhoan_DAO taiKhoanDAO = new TaiKhoan_DAO();
        ArrayList<TaiKhoan> danhSachTaiKhoan = taiKhoanDAO.dstk();
        System.out.println("Danh sách tài khoản:");
        for (TaiKhoan taiKhoan : danhSachTaiKhoan) {
            System.out.println("Tên đăng nhập: " + taiKhoan.getUsername() + ", Mật khẩu: " + taiKhoan.getPassword());
        }
    }

    public static void testThemTaiKhoan() {

        TaiKhoan_DAO taiKhoanDAO = new TaiKhoan_DAO();
        CaLamViec ca = new CaLamViec(1, null, null, null);
        TaiKhoan taiKhoan = new TaiKhoan("aaaaaaaaaaaaaaaaaa", "aaaaaaaaaaa");
        taiKhoan.setMaTK(888); // Thiết lập mã tài khoản
        taiKhoan.setCa(ca); // Thiết lập mã ca
        taiKhoan.setMoTa("Mô tả tài khoản mới");
  
        boolean result = taiKhoanDAO.themTK(taiKhoan);
        if (result) {
            System.out.println("Thêm tài khoản mới thành công!");
        } else {
            System.out.println("Thêm tài khoản mới thất bại!");
        }
    }

    public static void testXoaTaiKhoan() {
        TaiKhoan_DAO taiKhoanDAO = new TaiKhoan_DAO();
        int maTaiKhoan = 888; // Thay bằng mã tài khoản cần xóa
        boolean result = taiKhoanDAO.xoaTK(maTaiKhoan);
        if (result) {
            System.out.println("Xóa tài khoản thành công!");
        } else {
            System.out.println("Xóa tài khoản thất bại!");
        }
    }

    public static void testCapNhatTaiKhoan() {
        TaiKhoan_DAO taiKhoanDAO = new TaiKhoan_DAO();
        CaLamViec ca = new CaLamViec(1, null, null, null);
        TaiKhoan taiKhoan = new TaiKhoan("updated_username", "updated_password");
        taiKhoan.setMaTK(7); // Mã tài khoản cần cập nhật
        taiKhoan.setCa(ca); // Mã ca mới
        taiKhoan.setMoTa("Mô tả tài khoản đã cập nhật");
 //       taiKhoan.setNgayTao(new Date()); // Ngày cập nhật
        boolean result = taiKhoanDAO.capNhapTK(taiKhoan);
        if (result) {
            System.out.println("Cập nhật tài khoản thành công!");
        } else {
            System.out.println("Cập nhật tài khoản thất bại!");
        }
    }
}
