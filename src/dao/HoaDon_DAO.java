package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChucVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.MonNuoc;
import entity.NhanVien;

public class HoaDon_DAO {

    NhanVien_DAO nv_dao = new NhanVien_DAO();
    KhachHang_DAO kh_dao = new KhachHang_DAO();

    public boolean addHD(HoaDon hd) {
        Connection con = null;
        PreparedStatement ps = null;
        int n = 0;
        try {
            con = ConnectDB.getInstance().connect();
            String query = "INSERT INTO HoaDon (MaNV, MaKH, MaBan, TrangThai, TongTien, VAT, ChiPhiKhac, TongThu)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(query);
            ps.setString(1, hd.getNv().getMaNV());
            ps.setInt(2, hd.getKh() == null ? 0 : hd.getKh().getMaKH());
            ps.setInt(3, hd.getBan());
            ps.setString(4, hd.getTrangThai());
            ps.setDouble(5, hd.getTongTien());
            ps.setDouble(6, hd.getThueVAT());
            ps.setDouble(7, hd.getChiPhiKhac());
            ps.setDouble(8, hd.getTongThu());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return n > 0;
    }

    public ArrayList<HoaDon> getAllHoaDon() {

        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getInstance().connect();
            String query = "SELECT * FROM HoaDon";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                hd.setNv(nv);
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKH"));
                hd.setKh(kh);
                hd.setBan(rs.getInt("MaBan"));
                hd.setNgayLap(rs.getTimestamp("NgayLapHoaDon"));
                hd.setTrangThai(rs.getString("TrangThai"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setThueVAT(rs.getDouble("VAT"));
                hd.setChiPhiKhac(rs.getDouble("ChiPhiKhac"));
                hd.setTongThu(rs.getDouble("TongThu"));

                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return list;
    }

    public double tinhTongDoanhThuTheoNgay(LocalDate ngay) {
        double tongDoanhThuNgay = 0.0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getInstance().connect();
            String query = "SELECT SUM(TongThu) AS tongDoanhThu "
                    + "FROM HoaDon "
                    + "WHERE CAST(NgayLapHoaDon AS DATE) = ?";
            ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(ngay));
            rs = ps.executeQuery();

            if (rs.next()) {
                tongDoanhThuNgay = rs.getDouble("tongDoanhThu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng connection, PreparedStatement và ResultSet sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tongDoanhThuNgay;
    }

    public double tinhTongDoanhThuTheoThang(LocalDate thang) {
        double tongDoanhThuThang = 0.0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getInstance().connect();
            String query = "SELECT SUM(TongThu) AS tongDoanhThu "
                    + "FROM HoaDon "
                    + "WHERE MONTH(NgayLapHoaDon) = MONTH(?) AND YEAR(NgayLapHoaDon) = YEAR(?)";
            ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(thang));
            ps.setDate(2, Date.valueOf(thang));
            rs = ps.executeQuery();

            if (rs.next()) {
                tongDoanhThuThang = rs.getDouble("tongDoanhThu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return tongDoanhThuThang;
    }
}
