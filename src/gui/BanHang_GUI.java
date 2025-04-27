package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.MonNuoc_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.MonNuoc;
import entity.NhanVien;

public class BanHang_GUI extends JPanel implements ActionListener, MouseListener {

    private DefaultTableModel tableModelMenu;
    private JTable tableMenu;
    private JButton btnLapHoaDon, btnCong, btnTru;
    private JLabel lbMaMon, lbTenMon, lbDonGia, lbSoLuong, lbThanhTien;
    private JTextField txtMa, txtTen, txtDonGia, txtSoLuong, txtThanhTien;
    private JPanel pCen;
    private DefaultTableModel tableModelCTHD;
    private JButton btnSua;
    private JTable tableCTHD;
    private JButton btnXoa;
    private JTextField txtBan;
    private JCheckBox checkBox;
    private boolean isCheckBoxSelected = false;
    private JTextField txtNV;
    private JTextField txtSDT;
    private JButton btnTimKH;
    private JPanel pTongTien;
    private JLabel lbTongTien;
    private JTextField txtTongTien;
    private JTextField txtVAT;
    private JTextField txtTongThu;
    private JPanel pKhachHang;
    private JTextField txtTenKH;
    private JButton btCapNhatTen;
    private JTextField txtDiemTL;
    private JTextField txtDiemTK;
    private JCheckBox khongTaoTK;
    private JButton btnThemHD;
    private JComboBox<String> cbPTTT;
    private ArrayList<NhanVien> dsnvTN;
    private JComboBox<String> comboNVTN;
    private JTextField txtTim;
    private JButton btnTim;

    private static MonNuoc_DAO monNuoc_dao = new MonNuoc_DAO();
    private static NhanVien_DAO nv_dao = new NhanVien_DAO();
    private static KhachHang_DAO kh_dao = new KhachHang_DAO();
    private static HoaDon_DAO hd_dao = new HoaDon_DAO();

    // constants
    private final Color brown = new Color(139, 69, 19);
    private final Color lightBrown = new Color(255, 204, 153);
    private final Color activeColor = new Color(255, 229, 204);

    public BanHang_GUI() {
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel panelTable = new JPanel();
        setBorder(new LineBorder(brown, 2));
        setBackground(lightBrown);

        // Search menu
        txtTim = new JTextField(30);
        btnTim = new JButton("Tìm món");
        Box timBox = Box.createHorizontalBox();
        timBox.add(txtTim);
        timBox.add(btnTim);
        panelTable.add(timBox);
        decorateButton(btnTim);
        txtTim.setBorder(BorderFactory.createLineBorder(brown, 3));

        // Menu
        tableModelMenu = new DefaultTableModel();
        tableModelMenu.addColumn("Mã món");
        tableModelMenu.addColumn("Tên món");
        tableModelMenu.addColumn("Đơn giá");
        tableMenu = new JTable(tableModelMenu);
        TableColumnModel columnModel = tableMenu.getColumnModel();
        TableColumn column = columnModel.getColumn(0);
        columnModel.removeColumn(column);
        JScrollPane scrollPanel = new JScrollPane(tableMenu);
        scrollPanel.setBorder(BorderFactory.createLineBorder(brown, 3));
        panelTable.add(scrollPanel);

        loadMonNuocToTable();

        JPanel panelButton = new JPanel();
        btnLapHoaDon = new JButton("Thêm món");
        panelButton.add(btnLapHoaDon);
        decorateButton(btnLapHoaDon);

        leftPanel.add(panelTable, BorderLayout.NORTH);
        leftPanel.add(panelButton, BorderLayout.SOUTH);

        pCen = new JPanel();
        lbMaMon = new JLabel("Mã món ");
        lbTenMon = new JLabel("Tên món ");
        lbDonGia = new JLabel("Đơn Giá ");
        lbSoLuong = new JLabel("Số lượng ");
        lbThanhTien = new JLabel("Thành tiền ");
        txtMa = new JTextField();
        txtTen = new JTextField();
        txtDonGia = new JTextField();
        txtSoLuong = new JTextField();
        txtThanhTien = new JTextField();
        txtMa.setBackground(lightBrown);
        txtTen.setBackground(lightBrown);
        txtDonGia.setBackground(lightBrown);
        txtSoLuong.setBackground(lightBrown);
        txtThanhTien.setBackground(lightBrown);
        txtMa.setFont(new Font("Arial", Font.BOLD, 20));
        txtTen.setFont(new Font("Arial", Font.BOLD, 20));
        txtDonGia.setFont(new Font("Arial", Font.BOLD, 20));
        txtSoLuong.setFont(new Font("Arial", Font.BOLD, 20));
        txtThanhTien.setFont(new Font("Arial", Font.BOLD, 20));
        txtMa.setForeground(Color.black);
        txtTen.setForeground(Color.black);
        txtDonGia.setForeground(Color.black);
        txtSoLuong.setForeground(Color.black);
        txtThanhTien.setForeground(Color.black);
        txtMa.setEditable(false);
        txtTen.setEditable(false);
        txtDonGia.setEditable(false);
        txtSoLuong.setEditable(false);
        txtThanhTien.setEditable(false);
        txtMa.setBorder(BorderFactory.createLineBorder(brown, 3));
        txtTen.setBorder(BorderFactory.createLineBorder(brown, 3));
        txtDonGia.setBorder(BorderFactory.createLineBorder(brown, 3));
        txtSoLuong.setBorder(BorderFactory.createLineBorder(brown, 3));
        txtThanhTien.setBorder(BorderFactory.createLineBorder(brown, 3));

        txtMa.setPreferredSize(new Dimension(100, txtMa.getPreferredSize().height));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Box boxMaMon = Box.createHorizontalBox();
        boxMaMon.add(lbMaMon);
        boxMaMon.add(Box.createHorizontalStrut(10));
        boxMaMon.add(txtMa);

        Box boxTenMon = Box.createHorizontalBox();
        boxTenMon.add(lbTenMon);
        boxTenMon.add(Box.createHorizontalStrut(10));
        boxTenMon.add(txtTen);

        Box boxDonGia = Box.createHorizontalBox();
        boxDonGia.add(lbDonGia);
        boxDonGia.add(Box.createHorizontalStrut(10));
        boxDonGia.add(txtDonGia);

        Box boxSoLuong = Box.createHorizontalBox();
        boxSoLuong.add(lbSoLuong);
        boxSoLuong.add(Box.createHorizontalStrut(10));
        boxSoLuong.add(btnTru = new JButton("-"));
        btnTru.setBackground(Color.GRAY);
        btnTru.setForeground(Color.WHITE);
        boxSoLuong.add(Box.createHorizontalStrut(20));
        boxSoLuong.add(txtSoLuong);
        boxSoLuong.add(Box.createHorizontalStrut(20));
        boxSoLuong.add(btnCong = new JButton("+"));
        btnCong.setBackground(Color.GRAY);
        btnCong.setForeground(Color.WHITE);

        Box boxThanhTien = Box.createHorizontalBox();
        boxThanhTien.add(lbThanhTien);
        boxThanhTien.add(Box.createHorizontalStrut(10));
        boxThanhTien.add(txtThanhTien);

        lbMaMon.setPreferredSize(lbThanhTien.getPreferredSize());
        lbTenMon.setPreferredSize(lbThanhTien.getPreferredSize());
        lbDonGia.setPreferredSize(lbThanhTien.getPreferredSize());
        lbSoLuong.setPreferredSize(lbThanhTien.getPreferredSize());
        Box boxCenterMon = Box.createVerticalBox();
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxMaMon);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxTenMon);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxDonGia);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxSoLuong);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxThanhTien);
        boxCenterMon.add(Box.createVerticalStrut(10));
        //panel.add(boxCenterMon);
        leftPanel.add(boxCenterMon, BorderLayout.CENTER);
        leftPanel.add(panel, BorderLayout.WEST);
        leftPanel.add(panel, BorderLayout.EAST);

        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel khungCTHD = new JPanel(new BorderLayout());
        JLabel tdCTHD = new JLabel("Chi tiết đơn hàng");
        tdCTHD.setFont(new Font("Arial", Font.BOLD, 20));
        tdCTHD.setForeground(brown);
        tdCTHD.setHorizontalAlignment(SwingConstants.CENTER);
        khungCTHD.add(tdCTHD, BorderLayout.NORTH);
        khungCTHD.setPreferredSize(new Dimension(WIDTH, 300));

        tableModelCTHD = new DefaultTableModel();
        tableModelCTHD.addColumn("Mã món");
        tableModelCTHD.addColumn("Tên món");
        tableModelCTHD.addColumn("Đơn Giá");
        tableModelCTHD.addColumn("Số lượng");
        tableModelCTHD.addColumn("Thành Tiền");
        tableCTHD = new JTable(tableModelCTHD);
        JScrollPane scrollPaneCTHD = new JScrollPane(tableCTHD);
        scrollPaneCTHD.setBorder(BorderFactory.createLineBorder(brown, 3));
        khungCTHD.add(scrollPaneCTHD, BorderLayout.CENTER);
        rightPanel.add(khungCTHD, BorderLayout.NORTH);

        JPanel lapHD = new JPanel(new GridLayout(2, 1));
        JPanel cthd = new JPanel(new GridLayout(0, 2));
        JLabel lbMaBan = new JLabel("Số Bàn");
        txtBan = new JTextField();
        txtBan.setPreferredSize(new Dimension(150, 25));
        checkBox = new JCheckBox("Mang đi");
        txtBan.setBorder(BorderFactory.createLineBorder(brown, 3));
        JPanel pMaBan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pMaBan.add(lbMaBan);
        pMaBan.add(txtBan);
        cthd.add(pMaBan);
        cthd.add(checkBox);

        JPanel pNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbMaNV = new JLabel("Nhân viên thu ngân");
        comboNVTN = new JComboBox<String>();
        comboNVTN.setEditable(true);
        comboNVTN.setBorder(BorderFactory.createLineBorder(brown, 3));
        dsnvTN = nv_dao.getDSTN();
        for (NhanVien nv : dsnvTN) {
            comboNVTN.addItem(nv.getTenNV());
        }
        txtNV = new JTextField();
        txtNV.setPreferredSize(new Dimension(150, 25));
        pNV.add(lbMaNV);
        pNV.add(comboNVTN);
        txtNV.setBorder(BorderFactory.createLineBorder(brown, 3));

        cthd.add(pNV);

        JPanel pTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbPhuongThucThanhToan = new JLabel("Phương thức thanh toán:");
        String[] ptttList = {"Tiền mặt", "Thẻ ngân hàng", "Chuyển khoản"};
        cbPTTT = new JComboBox<>(ptttList);
        cbPTTT.setBorder(BorderFactory.createLineBorder(brown, 3));
        pTrangThai.add(lbPhuongThucThanhToan);
        pTrangThai.add(cbPTTT);
        cthd.add(pTrangThai);

        pTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbTongTien = new JLabel("Tổng tiền");
        txtTongTien = new JTextField();
        txtTongTien.setPreferredSize(new Dimension(150, 25));
        pTongTien.add(lbTongTien);
        pTongTien.add(txtTongTien);
        txtTongTien.setBorder(BorderFactory.createLineBorder(brown, 3));

        cthd.add(pTongTien);

        JPanel pVAT = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbVAT = new JLabel("Thuế VAT");
        txtVAT = new JTextField();
        txtVAT.setPreferredSize(new Dimension(150, 25));
        txtVAT.setText("0.1"); // Đặt giá trị mặc định là 0.1
        pVAT.add(lbVAT);
        pVAT.add(txtVAT);
        txtVAT.setBorder(BorderFactory.createLineBorder(brown, 3));

        cthd.add(pVAT);
        JPanel pTongThu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbTongThu = new JLabel("Tổng thu");
        txtTongThu = new JTextField();
        txtTongThu.setPreferredSize(new Dimension(150, 25));
        pTongThu.add(lbTongThu);
        pTongThu.add(txtTongThu);
        txtTongThu.setBorder(BorderFactory.createLineBorder(brown, 3));

        cthd.add(pTongThu);

        lapHD.add(cthd);
        Box pKhachHang = Box.createVerticalBox();

        TitledBorder tieudeKH = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(brown, 3), "Khách hàng");
        pKhachHang.setBorder(tieudeKH);

        // Khách hàng
        khongTaoTK = new JCheckBox("Không có tài khoản");
        pKhachHang.add(khongTaoTK);
        pKhachHang.add(Box.createVerticalStrut(20));
        Box pKH = Box.createHorizontalBox();
        JLabel lbSDT = new JLabel("Số điện thoại khách: ");
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(new Dimension(150, 25));
        pKH.add(lbSDT);
        pKH.add(txtSDT);
        pKH.add(btnTimKH = new JButton("Tìm"));
        pKhachHang.add(pKH);
        pKhachHang.add(Box.createVerticalStrut(10));
        txtSDT.setBorder(BorderFactory.createLineBorder(brown, 3));
        // Tên khách hàng
        Box pTen = Box.createHorizontalBox();
        JLabel lbTenKH = new JLabel("Tên khách hàng ");
        txtTenKH = new JTextField();
        txtTenKH.setPreferredSize(new Dimension(150, 25));
        pTen.add(lbTenKH);
        pTen.add(txtTenKH);
        pKhachHang.add(pTen);
        pKhachHang.add(Box.createVerticalStrut(10));
        // Điểm tích lũy trên hóa đơn (nếu cần)
        Box pDiem = Box.createHorizontalBox();
        JLabel lbDiemTL = new JLabel("Điểm tích lũy trên hóa đơn ");
        txtDiemTL = new JTextField();
        txtDiemTL.setPreferredSize(new Dimension(150, 25));
        txtDiemTL.setBorder(BorderFactory.createLineBorder(brown, 3));
        JLabel lbDiemTK = new JLabel("Điểm tích lũy trong tài khoản  ");
        txtDiemTK = new JTextField();
        txtDiemTK.setPreferredSize(new Dimension(150, 25));
        txtDiemTK.setBorder(BorderFactory.createLineBorder(brown, 3));
        pDiem.add(lbDiemTL);
        pDiem.add(txtDiemTL);
        pDiem.add(Box.createHorizontalStrut(10));
        pDiem.add(lbDiemTK);
        pDiem.add(txtDiemTK);
        pKhachHang.add(pDiem);
        txtTenKH.setBorder(BorderFactory.createLineBorder(brown, 3));

        lbSDT.setPreferredSize(lbDiemTK.getPreferredSize());
        lbTenKH.setPreferredSize(lbDiemTK.getPreferredSize());
        lbDiemTL.setPreferredSize(lbDiemTK.getPreferredSize());
        // Thêm pKhachHang vào lapHD hoặc panel cần chứa pKhachHang
        lapHD.add(pKhachHang);

        rightPanel.add(lapHD, BorderLayout.CENTER);

        JPanel pCapNhat = new JPanel();
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnThemHD = new JButton("Thêm hóa đơn");
        pCapNhat.add(btnSua);
        pCapNhat.add(btnXoa);
        pCapNhat.add(btnThemHD);

        rightPanel.add(pCapNhat, BorderLayout.SOUTH);
        // Tạo JSplitPane để chia layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(500);

        //dk sk
        tableMenu.addMouseListener(this);
        tableCTHD.addMouseListener(this);
        btnCong.addActionListener(this);
        btnTru.addActionListener(this);
        btnLapHoaDon.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        checkBox.addActionListener(this);
        khongTaoTK.addActionListener(this);
        btnTimKH.addActionListener(this);
        btnThemHD.addActionListener(this);
        btnTim.addActionListener(this);

        decorateButton(btnCong);
        decorateButton(btnTru);
        decorateButton(btnSua);
        decorateButton(btnXoa);
        decorateButton(btnTimKH);
        decorateButton(btnThemHD);
        btnThemHD.setPreferredSize(new Dimension(150, 20));

        add(splitPane);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tableMenu) {
            // Xử lý sự kiện click trên bảng menu
            int row = tableMenu.getSelectedRow();
            if (row != -1) { // Đảm bảo rằng có một dòng được chọn
                // Lấy giá trị từ bảng
                String maMon = tableModelMenu.getValueAt(row, 0).toString();
                String tenMon = tableModelMenu.getValueAt(row, 1).toString();
                String donGiaStr = tableModelMenu.getValueAt(row, 2).toString();

                // Hiển thị thông tin lên các JTextField tương ứng
                txtMa.setText(maMon);
                txtTen.setText(tenMon);
                txtDonGia.setText(donGiaStr);

                // Set giá trị mặc định cho số lượng là 1
                txtSoLuong.setText("1");

                // Tính thành tiền và hiển thị lên JTextField tương ứng
                try {
                    double donGia = Double.parseDouble(donGiaStr);
                    int soLuong = Integer.parseInt(txtSoLuong.getText());
                    double thanhTien = donGia * soLuong;
                    txtThanhTien.setText(String.valueOf(thanhTien));
                } catch (NumberFormatException ex) {
                    // Xử lý ngoại lệ nếu có lỗi khi chuyển đổi chuỗi sang số
                    txtThanhTien.setText("0.0");
                }
            }
        } else if (e.getSource() == tableCTHD) {
            // Xử lý sự kiện click trên bảng chi tiết hóa đơn
            int selectedRow = tableCTHD.getSelectedRow();
            if (selectedRow != -1) { // Đảm bảo có một dòng được chọn
                // Lấy thông tin từ dòng đã chọn và điền vào các text field
                txtMa.setText(tableModelCTHD.getValueAt(selectedRow, 0).toString());
                txtTen.setText(tableModelCTHD.getValueAt(selectedRow, 1).toString());
                txtDonGia.setText(tableModelCTHD.getValueAt(selectedRow, 2).toString());
                txtSoLuong.setText(tableModelCTHD.getValueAt(selectedRow, 3).toString());
                txtThanhTien.setText(tableModelCTHD.getValueAt(selectedRow, 4).toString());
            }
        }
    }

    private void decorateButton(JButton btn) {
        btn.setBackground(brown);
        btn.setForeground(lightBrown);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(100, 20));
        btn.setBorder(new LineBorder(lightBrown, 2, true));
    }

    private void clearTextFields() {
        txtMa.setText("");
        txtTen.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnCong)) {
            try {
                // Lấy giá trị hiện tại của số lượng từ JTextField "txtSoLuong"
                int soLuong = Integer.parseInt(txtSoLuong.getText());
                soLuong++;
                txtSoLuong.setText(String.valueOf(soLuong));

                // Tính toán lại thành tiền và cập nhật vào JTextField "txtThanhTien"
                double donGia = Double.parseDouble(txtDonGia.getText());
                double thanhTien = donGia * soLuong;
                txtThanhTien.setText(String.valueOf(thanhTien));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: Vui lòng nhập số lượng là số nguyên.");
            }
        } else if (o.equals(btnTru)) {
            try {
                // Lấy giá trị hiện tại của số lượng từ JTextField "txtSoLuong"
                int soLuong = Integer.parseInt(txtSoLuong.getText());

                // Kiểm tra nếu số lượng đã là 0 hoặc nhỏ hơn, không thực hiện thêm bất kỳ thay đổi nào
                if (soLuong > 0) {
                    soLuong--;
                    txtSoLuong.setText(String.valueOf(soLuong));

                    // Tính toán lại thành tiền và cập nhật vào JTextField "txtThanhTien"
                    double donGia = Double.parseDouble(txtDonGia.getText());
                    double thanhTien = donGia * soLuong;
                    txtThanhTien.setText(String.valueOf(thanhTien));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: Vui lòng nhập số lượng là số nguyên.");
            }
        } else if (o.equals(btnLapHoaDon)) {
            // Thêm món vào chi tiết hóa đơn
            // Lấy thông tin từ các JTextField
            String maMon = txtMa.getText();
            String tenMon = txtTen.getText();
            String donGiaStr = txtDonGia.getText();
            String soLuongStr = txtSoLuong.getText();
            String thanhTienStr = txtThanhTien.getText();

            // Kiểm tra các trường thông tin có rỗng không
            if (maMon.isEmpty() || tenMon.isEmpty() || donGiaStr.isEmpty() || soLuongStr.isEmpty() || thanhTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin.");
                return;
            }

            // Parse các giá trị số từ các chuỗi
            double donGia = Double.parseDouble(donGiaStr);
            int soLuong = Integer.parseInt(soLuongStr);
            double thanhTien = Double.parseDouble(thanhTienStr);

            // Thêm vào table model của chi tiết hóa đơn
            Object[] rowData = {maMon, tenMon, donGia, soLuong, thanhTien};
            tableModelCTHD.addRow(rowData);
            tableMenu.clearSelection();
            clearTextFields();
        } else if (o.equals(btnSua)) {
            int row = tableCTHD.getSelectedRow();
            if (row != -1) {
                tableModelCTHD.setValueAt(txtSoLuong.getText(), row, 3);
                double thanhTien = Double.parseDouble(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText());
                tableModelCTHD.setValueAt(thanhTien, row, 4);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng.");
            }
        } else if (o.equals(btnThemHD)) {
            String tenTN = comboNVTN.getSelectedItem().toString();
            NhanVien nvTN = dsnvTN.stream().filter(x -> x.getTenNV().equalsIgnoreCase(tenTN)).findFirst().orElse(null);
            KhachHang khachHang = kh_dao.timKHTheoSDT(txtSDT.getText());
            String txtBanStr = txtBan.getText();
            if (!txtBanStr.isEmpty()) {
                int soBan = Integer.parseInt(txtBan.getText());
                String trangThai = cbPTTT.getSelectedItem().toString();
                Double tongTien = Double.parseDouble(txtTongTien.getText());
                Double thueVAT = Double.parseDouble(txtVAT.getText());
                Double tongThu = Double.parseDouble(txtTongThu.getText());

                if (txtTongTien.getText().isEmpty() || txtVAT.getText().isEmpty() || txtTongThu.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
                } else {
                    HoaDon hdNew = new HoaDon(nvTN, khachHang, soBan, trangThai, tongTien, thueVAT, 0, tongThu);
                    if (hd_dao.addHD(hdNew)) {
                        // Thêm hóa đơn thành công
                        JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
                    } else {
                        // Thêm hóa đơn không thành công
                        JOptionPane.showMessageDialog(this, "Thêm hóa đơn không thành công!");
                    }
                }
            } else {
                // Xử lý khi chuỗi rỗng
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số bàn.");
            }

        }

        if (checkBox.isSelected()) {
            txtBan.setText(null);
            txtBan.setEnabled(false);
        } else {
            txtBan.setEnabled(true);
        }
        if (khongTaoTK.isSelected()) {
            txtSDT.setEnabled(false);
            txtTenKH.setEnabled(false);
            txtDiemTL.setEnabled(false);
            txtDiemTK.setEnabled(false);
            txtTenKH.setText(null);
        } else {
            txtSDT.setEnabled(true);
            txtTenKH.setEnabled(true);
            txtDiemTL.setEnabled(true);
            txtDiemTK.setEnabled(true);
        }
        if (e.getSource() == btnTimKH) {
            String soDienThoai = txtSDT.getText();
            if (!soDienThoai.isEmpty()) {
                KhachHang_DAO khachHangDAO = new KhachHang_DAO();
                KhachHang khachHang = khachHangDAO.timKHTheoSDT(soDienThoai);
                if (khachHang != null) {
                    // Hiển thị tên khách hàng lên txtTenKH nếu tìm thấy
                    txtTenKH.setText(khachHang.getTenKH());

                    // Lấy điểm tích lũy từ đối tượng KhachHang
                    int diemTichLuy = khachHang.getDiemTichLuy();

                    // Cập nhật điểm tích lũy trong tài khoản
                    txtDiemTK.setText(String.valueOf(diemTichLuy));
                } else {
                    // Nếu không tìm thấy khách hàng, hiển thị hộp thoại xác nhận
                    int choice = JOptionPane.showConfirmDialog(null, "Không tìm thấy khách hàng, bạn có muốn tạo mới không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Hiển thị hộp thoại nhập thông tin khách hàng
                        String tenKhachHangMoi = JOptionPane.showInputDialog(null, "Nhập tên khách hàng mới:");

                        // Kiểm tra nếu người dùng không hủy bỏ việc nhập
                        if (tenKhachHangMoi != null && !tenKhachHangMoi.isEmpty()) {
                            // Tạo một đối tượng KhachHang mới và lưu vào cơ sở dữ liệu
                            KhachHang khachHangMoi = new KhachHang();
                            khachHangMoi.setSdt(soDienThoai);
                            khachHangMoi.setTenKH(tenKhachHangMoi);
                            khachHangDAO.themKH(khachHangMoi);

                            // Hiển thị tên khách hàng mới lên txtTenKH
                            txtTenKH.setText(tenKhachHangMoi);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng.");
            }
        }

        capNhatTongTien();
        capNhatTongThu();

    }

    public void loadMonNuocToTable() {
        tableModelMenu.setRowCount(0);
        MonNuoc_DAO monNuocDao;
        ArrayList<MonNuoc> dsMonNuoc = monNuoc_dao.getDSMonNuoc();
        for (MonNuoc monNuoc : dsMonNuoc) {
            Object[] rowData = {monNuoc.getMaMon(), monNuoc.getTenMon(), monNuoc.getDonGia()};
            tableModelMenu.addRow(rowData);
        }
    }

    private void loadNhanVienThuNgan() {
        tableModelMenu.setRowCount(0);
        MonNuoc_DAO monNuocDao;
        ArrayList<MonNuoc> dsMonNuoc = monNuoc_dao.getDSMonNuoc();
        for (MonNuoc monNuoc : dsMonNuoc) {
            Object[] rowData = {monNuoc.getMaMon(), monNuoc.getTenMon(), monNuoc.getDonGia()};
            tableModelMenu.addRow(rowData);
        }
    }
    // Phương thức tính tổng tiền từ dữ liệu trong bảng chi tiết hóa đơn

    private double tinhTongTien() {
        double tongTien = 0.0;
        for (int i = 0; i < tableModelCTHD.getRowCount(); i++) {
            double thanhTien = Double.parseDouble(tableModelCTHD.getValueAt(i, 4).toString());
            tongTien += thanhTien;
        }
        return tongTien;
    }

    // Phương thức cập nhật giá trị của ô text field "Tổng tiền"
    private void capNhatTongTien() {
        double tongTien = tinhTongTien();
        txtTongTien.setText(String.valueOf(tongTien));
    }

    public double tinhTongThu() {
        // Kiểm tra xem các trường văn bản có dữ liệu hợp lệ không
        if (txtTongTien.getText().isEmpty() || txtVAT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lỗi: Vui lòng nhập đầy đủ thông tin.");
            return -1.0; // Trả về -1.0 để chỉ ra rằng có lỗi xảy ra trong quá trình tính toán
        }

        // Lấy giá trị từ các trường văn bản
        double tongTien = Double.parseDouble(txtTongTien.getText());
        double thueVAT = Double.parseDouble(txtVAT.getText());

        // Tính tổng thu
        double tongThu = tongTien + tongTien * (thueVAT / 100);

        return tongThu;
    }

    private void capNhatTongThu() {
        double tongThu = tinhTongThu();
        txtTongThu.setText(String.valueOf(tongThu));
        int diemTichLuy = (int) (tongThu / 10000);
        txtDiemTL.setText(String.valueOf(diemTichLuy));
    }

}
