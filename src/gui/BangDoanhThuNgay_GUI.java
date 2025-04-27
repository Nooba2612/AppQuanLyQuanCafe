package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDB;

public class BangDoanhThuNgay_GUI extends JFrame{
	private DefaultTableModel modelDoanhThu;
	private JTable tableDoanhThu;
	private JScrollPane scrollDoanhThu;

	public BangDoanhThuNgay_GUI() {
		//Kết nối
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Tạo bảng
		String[] colHeader = { "Ngày", "Tổng số hóa đơn", "Tổng doanh thu"};
		modelDoanhThu = new DefaultTableModel(colHeader, 0);
		tableDoanhThu = new JTable(modelDoanhThu);
		scrollDoanhThu = new JScrollPane(tableDoanhThu);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat fmTien = new DecimalFormat("#,000 VNĐ");
		Statement statemen = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT NgayLapHoaDon, TongSoHoaDon = COUNT(*), TongDoanhThu = SUM(TongThu)"
					+ " FROM HoaDon"
					+ " GROUP BY NgayLapHoaDon"
					+ "	ORDER BY NgayLapHoaDon DESC";
			statemen= con.createStatement();
			ResultSet rs = statemen.executeQuery(sql);
	
			while (rs.next()) {
				String ngayLapHD = dtf.format(rs.getDate(1).toLocalDate());
				int tongSoHD = rs.getInt(2);
				double tongDoanhThu = rs.getDouble(3);
				modelDoanhThu.addRow(new Object[] {ngayLapHD, tongSoHD, fmTien.format(tongDoanhThu)});
			}

		}catch(Exception e1) {
			e1.printStackTrace();
		}finally {
			try {
				statemen.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		add(scrollDoanhThu);
		
		setTitle("Bảng doanh thu theo ngày");
		setSize(400, 200);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new BangDoanhThuNgay_GUI();
	}
}
