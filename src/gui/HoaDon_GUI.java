package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import database.ConnectDB;
import entity.HoaDon;

public class HoaDon_GUI extends JPanel implements MouseListener, ActionListener {

    private JLabel lbTitle;
    private Box boxWhole, boxTop, boxBottom;
//	private JPanel p1, p2, p3;
    private DefaultTableModel modelHoaDon;
    private JTable tableHoaDon;
    private JScrollPane scrollHoaDon;
    private JTextField textKetQua;
    private JButton btnBestSeller, btnDTHomNay, btnDTThangNay, btnDTNgay, btnDTThang;
    private static HoaDon_DAO hd_dao = new HoaDon_DAO();
    private static ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();

    public HoaDon_GUI() {
        //kết nối
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Tạo box và panel
        boxWhole = Box.createVerticalBox();
        boxTop = Box.createHorizontalBox();
        boxBottom = Box.createHorizontalBox();

        //Tiêu đề
        lbTitle = new JLabel("HÓA ĐƠN");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lbTitle.setForeground(Color.blue);
        JPanel pTitle = new JPanel();
        pTitle.add(lbTitle);

        //Tạo bảng
        String[] colHeader = {"Mã HD", "Ngày lập", "Mã NV", "Mã KH", "Bàn", "Tổng tiền", "Tổng thu"};
        modelHoaDon = new DefaultTableModel(colHeader, 0);
        tableHoaDon = new JTable(modelHoaDon);
        scrollHoaDon = new JScrollPane(tableHoaDon);
        scrollHoaDon.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        // load data
        loadData();

        //Tạo textfield button
        textKetQua = new JTextField(20);
        textKetQua.setBorder(BorderFactory.createTitledBorder("Kết quả"));
        textKetQua.setEditable(false);
        btnBestSeller = new JButton("Món bán chạy nhất");
        btnDTHomNay = new JButton("Doanh thu hôm nay");
        btnDTThangNay = new JButton("Doanh thu tháng này");
        btnDTNgay = new JButton("Bảng doanh thu theo ngày");
        btnDTThang = new JButton("Bảng doanh thu theo tháng");

        //Panel
        boxBottom.add(textKetQua);
        boxBottom.add(Box.createHorizontalStrut(10));
        boxBottom.add(btnBestSeller);
        boxBottom.add(Box.createHorizontalStrut(10));
        boxBottom.add(btnDTHomNay);
        boxBottom.add(Box.createHorizontalStrut(10));
        boxBottom.add(btnDTThangNay);
        boxBottom.add(Box.createHorizontalStrut(10));
        boxBottom.add(btnDTNgay);
        boxBottom.add(Box.createHorizontalStrut(10));
        boxBottom.add(btnDTThang);

        boxWhole.add(Box.createVerticalStrut(20));
        boxWhole.add(pTitle);
        boxWhole.add(Box.createVerticalStrut(20));
        boxWhole.add(scrollHoaDon);
        boxWhole.add(Box.createVerticalStrut(20));
        boxWhole.add(boxBottom);
        boxWhole.add(Box.createVerticalStrut(20));

        add(boxWhole);

//		setTitle("Bảng doanh thu");
        setSize(1000, 800);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
        setVisible(true);

        //Thêm sự kiện
        tableHoaDon.addMouseListener(this);
        btnDTNgay.addActionListener(this);
        btnDTThang.addActionListener(this);
        btnDTHomNay.addActionListener(this);
        btnDTThangNay.addActionListener(this);
        btnBestSeller.addActionListener(this);
    }

    public void loadData() {
        ArrayList<HoaDon> dshd = hd_dao.getAllHoaDon();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat fmTien = new DecimalFormat("#,000 VNĐ");
        for (HoaDon hd : dshd) {
            this.modelHoaDon.addRow(new Object[]{
                hd.getMaHD(),
                dtf.format(hd.getNgayLap().toLocalDateTime()),
                hd.getNv().getMaNV(),
                hd.getKh().getMaKH(),
                hd.getBan() == 0 ? "Mang về" : hd.getBan(),
                fmTien.format(hd.getTongTien()),
                fmTien.format(hd.getTongThu())
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnDTNgay)) {
            new BangDoanhThuNgay_GUI();
        }
        if (o.equals(btnDTThang)) {
            new BangDoanhThuThang_GUI();
        }
        if (o.equals(btnBestSeller)) {
            String[] colHeader = {"Tên món", "Số lượng đã bán"};
            DefaultTableModel modelBestSeller = new DefaultTableModel(colHeader, 0);
            JTable tableBestSeller = new JTable(modelBestSeller);
            JScrollPane scrollBestSeller = new JScrollPane(tableBestSeller);

            String tenMon = "";
            int tongSoLuong = 0;

            Statement statemen = null;
            try {
                try {
                    ConnectDB.getInstance().connect();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                Connection con = ConnectDB.getConnection();
                String sql = "SELECT TOP 1 WITH TIES mn.TenMon, TongSoLuong=SUM(SoLuong)"
                        + " FROM ChiTietHoaDon cthd JOIN MonNuoc mn ON cthd.MaMon=mn.MaMon"
                        + " GROUP BY mn.MaMon, mn.TenMon"
                        + " ORDER BY TongSoLuong DESC";
                statemen = con.createStatement();
                ResultSet rs = statemen.executeQuery(sql);
                while (rs.next()) {
                    tenMon = rs.getString(1);
                    tongSoLuong = rs.getInt(2);
                    modelBestSeller.addRow(new Object[]{tenMon, tongSoLuong});
                }
                if (tableBestSeller.getRowCount() > 1) {
                    tableBestSeller.setPreferredScrollableViewportSize(new Dimension(300, 80));
                    JOptionPane.showMessageDialog(this, scrollBestSeller);
                } else {
                    String bestSeller = tenMon + "\tSL đã bán: " + tongSoLuong;
                    textKetQua.setEditable(true);
                    textKetQua.setText(bestSeller);
                    textKetQua.setEditable(false);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {

            }
        }
        if (o.equals(btnDTHomNay)) {
            DecimalFormat fmTien = new DecimalFormat("#,000 VNĐ");
            double doanhThu = hd_dao.tinhTongDoanhThuTheoNgay(LocalDate.now());
            String doanhThuStr = fmTien.format(doanhThu);
            textKetQua.setEditable(true);
            textKetQua.setText(doanhThuStr);
            textKetQua.setEditable(false);
        }
        if (o.equals(btnDTThangNay)) {
            DecimalFormat fmTien = new DecimalFormat("#,000 VNĐ");
            double doanhThu = hd_dao.tinhTongDoanhThuTheoThang(LocalDate.now());
            String doanhThuStr = fmTien.format(doanhThu);
            textKetQua.setEditable(true);
            textKetQua.setText(doanhThuStr);
            textKetQua.setEditable(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableHoaDon.getSelectedRow();
        int maHD = Integer.parseInt(modelHoaDon.getValueAt(row, 0).toString());
        String[] colHeader = {"Tên món", "Số lượng", "Thành tiền"};
        DefaultTableModel modelChiTiet = new DefaultTableModel(colHeader, 0);
        JTable tableChiTiet = new JTable(modelChiTiet);
        JScrollPane scrollChiTiet = new JScrollPane(tableChiTiet);
        DecimalFormat fmTien = new DecimalFormat("#,000 VNĐ");

        Statement statemen = null;
        try {
            try {
                ConnectDB.getInstance().connect();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TenMon, SoLuong, ThanhTien"
                    + " FROM ChiTietHoaDon cthd JOIN MonNuoc mn ON cthd.MaMon=mn.MaMon"
                    + " WHERE MaHD = " + maHD;
            statemen = con.createStatement();
            ResultSet rs = statemen.executeQuery(sql);
            double tongTien = 0;
            while (rs.next()) {
                String tenMon = rs.getString(1);
                int soLuong = rs.getInt(2);
                double thanhTien = rs.getDouble(3);
                modelChiTiet.addRow(new Object[]{tenMon, soLuong, fmTien.format(thanhTien)});
                tongTien += thanhTien;
            }
            modelChiTiet.addRow(new Object[]{"Tổng tiền:", "", fmTien.format(tongTien)});

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                statemen.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        tableChiTiet.setPreferredScrollableViewportSize(new Dimension(300, 80));
        JOptionPane.showMessageDialog(this, scrollChiTiet);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
