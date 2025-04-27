package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.smartcardio.Card;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class TrangChu_GUI extends JFrame implements ActionListener {

    private JButton btnNhanVien;
    private JButton btnBanHang;
    private JButton btnSanPham;
    private JButton btnDangXuat;
    private JButton btnHoaDon;
    private JButton btnDiemDanh;
    private JButton btnTrangChu;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // constants
    private final Color brown = new Color(139, 69, 19);
    private final Color lightBrown = new Color(255, 204, 153);
    private final Color activeColor = new Color(255, 229, 204);

    public TrangChu_GUI(String username) {

        // North
        JPanel pNorth = new JPanel();
        pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.X_AXIS));

        try {
            InputStream logoPath = getClass().getResourceAsStream("/images/logo.png");
            BufferedImage logoImg = ImageIO.read(logoPath);
            Image scaledImage = logoImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));
            pNorth.add(imgLabel);

            JLabel logoNameLabel = new JLabel("Trung Nguyên");
            logoNameLabel.setFont(new Font("Arial", Font.BOLD, 30));
            logoNameLabel.setForeground(brown);
            pNorth.add(logoNameLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pNorth.add(Box.createHorizontalGlue());

        btnDangXuat = new JButton("Đăng xuất");
        decorateButton(btnDangXuat);
        pNorth.add(btnDangXuat);
        pNorth.add(Box.createHorizontalStrut(20));

        pNorth.setBorder(BorderFactory.createLineBorder(brown, 4));
        pNorth.setBackground(lightBrown);

        add(pNorth, BorderLayout.NORTH);

        // West
        JPanel pWest = new JPanel(new GridLayout(6, 1));
        btnTrangChu = new JButton("Trang chủ");
        btnNhanVien = new JButton("Nhân viên");
        btnBanHang = new JButton("Bán hàng");
        btnSanPham = new JButton("Sản phẩm");
        btnHoaDon = new JButton("Hóa đơn");
        btnDiemDanh = new JButton("Điểm danh");

        pWest.setBackground(brown);
        pWest.setPreferredSize(new Dimension(200, HEIGHT));

        btnTrangChu.addActionListener(this);
        btnNhanVien.addActionListener(this);
        btnBanHang.addActionListener(this);
        btnSanPham.addActionListener(this);
        btnHoaDon.addActionListener(this);
        btnDiemDanh.addActionListener(this);
        btnDangXuat.addActionListener(this);

        decorateButton(btnTrangChu);
        decorateButton(btnBanHang);
        decorateButton(btnNhanVien);
        decorateButton(btnSanPham);
        decorateButton(btnHoaDon);
        decorateButton(btnDiemDanh);

        pWest.add(btnTrangChu);
        pWest.add(btnBanHang);
        pWest.add(btnNhanVien);
        pWest.add(btnSanPham);
        pWest.add(btnHoaDon);
        pWest.add(btnDiemDanh);

        setActiveButton(btnTrangChu);

        add(pWest, BorderLayout.WEST);

        // Center
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        try {
            InputStream is = getClass().getResourceAsStream("/images/coffee_shop_logo.png");
            BufferedImage image = ImageIO.read(is);
            Image scaledImage = image.getScaledInstance(900, 700, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));
            contentPanel.add(imgLabel, "TrangChu");
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPanel.setBackground(brown);

        contentPanel.add(new CapNhatNhanVien_GUI(), "CapNhatNhanVien");
        contentPanel.add(new BanHang_GUI(), "BanHang");
        contentPanel.add(new CapNhatMon_GUI(), "CapNhatMon");
        contentPanel.add(new HoaDon_GUI(), "HoaDon");
        contentPanel.add(new DiemDanh_GUI(), "DiemDanh");

        add(contentPanel, BorderLayout.CENTER);

        if (username.equals("NV01")) {
            setTitle("Ca Sáng");
        } else if (username.equals("NV02")) {
            setTitle("Ca Trưa");
        } else if (username.equals("NV03")) {
            setTitle("Ca Chiều");
        } else if (username.equals("NV04")) {
            setTitle("Ca Tối");
        } else {
            setTitle("Quản lý quán cafe");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void setActiveButton(JButton activeBtn) {

        ArrayList<JButton> btnList = new ArrayList<>(Arrays.asList(
                btnTrangChu,
                btnNhanVien,
                btnBanHang,
                btnSanPham,
                btnHoaDon,
                btnDiemDanh
        ));

        for (JButton btn : btnList) {
            if (btn.equals(activeBtn)) {
                btn.setBackground(activeColor);
                btn.setForeground(brown);
            } else {
                decorateButton(btn);
            }
        }

    }

    private void decorateButton(JButton btn) {
        btn.setBackground(brown);
        btn.setForeground(lightBrown);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(150, 70));
        btn.setBorder(new LineBorder(lightBrown, 2, true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnTrangChu)) {
            cardLayout.show(contentPanel, "TrangChu");
            setActiveButton(btnTrangChu);
        } else if (o.equals(btnNhanVien)) {
            cardLayout.show(contentPanel, "CapNhatNhanVien");
            setActiveButton(btnNhanVien);
        } else if (o.equals(btnBanHang)) {
            cardLayout.show(contentPanel, "BanHang");
            setActiveButton(btnBanHang);
        } else if (o.equals(btnSanPham)) {
            cardLayout.show(contentPanel, "CapNhatMon");
            setActiveButton(btnSanPham);
        } else if (o.equals(btnHoaDon)) {
            new HoaDon_GUI().loadData();
            cardLayout.show(contentPanel, "HoaDon");
            setActiveButton(btnHoaDon);
        } else if (o.equals(btnDiemDanh)) {
            cardLayout.show(contentPanel, "DiemDanh");
            setActiveButton(btnDiemDanh);
        } else if (o.equals(btnDangXuat)) {
            new DangNhap_GUI();
            setVisible(false);
        }

    }
}
