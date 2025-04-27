package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChucVu;
import entity.HoaDon;
import entity.MonNuoc;
import entity.NhanVien;

public class ChiTietHoaDon_DAO {
	HoaDon_DAO hd_dao = new HoaDon_DAO();
	MonNuoc_DAO mon_dao = new MonNuoc_DAO();
    // Phương thức để thêm một mục chi tiết hoá đơn vào cơ sở dữ liệu(ok)
    public boolean addChiTietHoaDon(ChiTietHoaDon cthd) {
    	
        Connection con = null;
        PreparedStatement ps = null;
        int n=0;
        try {
        	//con = database.ConnectDBP.getInstance().connect();
            con = ConnectDB.getInstance().connect();
//            String query = "INSERT INTO ChiTietHoaDon (maCTHD, maHoaDon, maMon, soLuong, donGia, thanhTien) "
//            		+ "VALUES (?, ?, ?, ?, ?, ?)";
            String query = "INSERT INTO ChiTietHoaDon (MaHD, MaMon, DonGia, SoLuong, ThanhTien) " +
                    "VALUES (?, ?, (SELECT DonGia FROM MonNuoc WHERE MaMon = ?), ?, " +
                    "(SELECT DonGia FROM MonNuoc WHERE MaMon = ?) * ?)";

		     ps = con.prepareStatement(query);
		     //ps.setInt(1, cthd.getMaCTHD());
		     ps.setInt(1, cthd.getHd().getMaHD());
		     ps.setString(2, cthd.getMon().getMaMon());
		     ps.setString(3, cthd.getMon().getMaMon()); // Sử dụng mã món để lấy đơn giá từ bảng MonNuoc
		     ps.setInt(4, cthd.getSoLuong());
		     ps.setString(5, cthd.getMon().getMaMon()); // Sử dụng mã món để lấy đơn giá từ bảng MonNuoc
		     ps.setInt(6, cthd.getSoLuong());
		     
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng connection và PreparedStatement sau khi sử dụng
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n>0;
    }
    
    // Phương thức để lấy danh sách các mục chi tiết hoá đơn từ cơ sở dữ liệu
    public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon() {
    	
    	ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        Connection con = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = ConnectDB.getInstance().connect();
            String query = "SELECT * FROM ChiTietHoaDon";
            
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<HoaDon> dsHD = hd_dao.getAllHoaDon();
            ArrayList<MonNuoc> dsMon = mon_dao.getDSMonNuoc();
            while (rs.next()) {
                int maCTHD = rs.getInt("MaCTHD");
                int maHD = rs.getInt("MaHD");
                String maMon = rs.getString("MaMon");
                Double donGia = rs.getDouble("DonGia");
                int sl = rs.getInt("SoLuong");
                Double thanhTien = rs.getDouble("ThanhTien");
                HoaDon hd = dsHD.stream().filter(x -> x.getMaHD()==maHD).findFirst().orElse(null);
                MonNuoc mon = dsMon.stream().filter(y -> y.getMaMon().equals(maMon)).findFirst().orElse(null);
                ChiTietHoaDon cthd = new ChiTietHoaDon(maCTHD, hd, mon, sl, donGia, thanhTien);
                
                list.add(cthd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng connection, PreparedStatement và ResultSet sau khi sử dụng
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    
    // Các phương thức khác như update, delete có thể triển khai tương tự
}
