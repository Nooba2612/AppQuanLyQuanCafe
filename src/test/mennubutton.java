package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import dao.MonNuoc_DAO;
import entity.MonNuoc;

public class mennubutton extends JPanel implements ActionListener, MouseListener{
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnLapHoaDon, btnCong, btnTru;
    private JLabel lbMaMon,lbTenMon, lbDonGia, lbSoLuong, lbThanhTien;
    private JTextField txtMa, txtTen, txtDonGia, txtSoLuong, txtThanhTien;
	private JPanel pTrai;

    public mennubutton() {
        setLayout(new BorderLayout());
        pTrai = new JPanel();
        // Panel chứa bảng danh sách các món nước
        JPanel panelMonNuoc = new JPanel(new BorderLayout());
        JLabel lblMonNuoc = new JLabel("Danh sách món nước");
        panelMonNuoc.add(lblMonNuoc, BorderLayout.NORTH);

        // Tạo table model và table để hiển thị danh sách món nước
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã món");
        tableModel.addColumn("Tên món");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Đơn vị tính");
        tableModel.addColumn("Danh mục");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panelMonNuoc.add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ cơ sở dữ liệu vào JTable
        loadMonNuocToTable();

        // Panel chứa nút lập hóa đơn
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnLapHoaDon = new JButton("Lập Hóa Đơn");
        panelButton.add(btnLapHoaDon);

        // Thêm panel danh sách món nước và panel nút lập hóa đơn vào frame
        pTrai.add(panelMonNuoc, BorderLayout.NORTH);
        
        // Tạo một Box chứa các JTextField và JLabel của món
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
        txtMa.setBackground(Color.GRAY); // Example color
        txtTen.setBackground(Color.GRAY); // Example color
        txtDonGia.setBackground(Color.GRAY); // Example color
        txtSoLuong.setBackground(Color.GRAY); // Example color
        txtThanhTien.setBackground(Color.GRAY); // Example color
        // Thiết lập thuộc tính cho các JTextField
        txtMa.setEnabled(false);
        txtTen.setEnabled(false);
        txtDonGia.setEnabled(false);
        txtSoLuong.setEnabled(false);
        txtThanhTien.setEnabled(false);
        // Tạo một Box chứa các JLabel và JTextField ở cùng một hàng
        Box boxMaMon = Box.createHorizontalBox();
        boxMaMon.add(lbMaMon);
        boxMaMon.add(Box.createHorizontalStrut(10)); // Khoảng cách giữa label và text field
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
        boxSoLuong.add(btnTru = new JButton("  -  "));
        btnTru.setBackground(Color.GRAY);
        btnTru.setForeground(Color.WHITE);
        boxSoLuong.add(Box.createHorizontalStrut(20));
        boxSoLuong.add(txtSoLuong);
        boxSoLuong.add(Box.createHorizontalStrut(20));
        boxSoLuong.add(btnCong = new JButton("  +  "));
        btnCong.setBackground(Color.GRAY);
        btnCong.setForeground(Color.WHITE);

        Box boxThanhTien = Box.createHorizontalBox();
        boxThanhTien.add(lbThanhTien);
        boxThanhTien.add(Box.createHorizontalStrut(10));
        boxThanhTien.add(txtThanhTien);

        // Tạo một Box chứa các Box hàng
        Box boxCenterMon = Box.createVerticalBox();
        boxCenterMon.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các thành phần
        boxCenterMon.add(boxMaMon);
        boxCenterMon.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các hàng
        boxCenterMon.add(boxTenMon);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxDonGia);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxSoLuong);
        boxCenterMon.add(Box.createVerticalStrut(10));
        boxCenterMon.add(boxThanhTien);
        boxCenterMon.add(Box.createVerticalStrut(10));

        // Thêm Box chứa các hàng vào vùng trung tâm của giao diện
        pTrai.add(boxCenterMon);

        pTrai.add(panelButton);
        add(pTrai);
        //sk
        table.addMouseListener(this);
        btnCong.addActionListener(this);
        btnTru.addActionListener(this);
        
//        setTitle("Lập Hóa Đơn");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 800);
//        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
    }

    private void loadMonNuocToTable() {
        // Xóa dữ liệu cũ trong table model
        tableModel.setRowCount(0);

        // Lấy danh sách món nước từ cơ sở dữ liệu
        MonNuoc_DAO monNuocDao = MonNuoc_DAO.getInstance();
        ArrayList<MonNuoc> dsMonNuoc = monNuocDao.getDSMonNuoc();

        // Thêm dữ liệu vào table model
        for (MonNuoc monNuoc : dsMonNuoc) {
            Object[] rowData = {monNuoc.getMaMon(), monNuoc.getTenMon(), monNuoc.getDonGia(), monNuoc.getDonViTinh(), monNuoc.getDanhMuc()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mennubutton gui = new mennubutton();
                gui.setVisible(true);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row != -1) { // Đảm bảo rằng có một dòng được chọn
            // Lấy giá trị từ bảng
            String maMon = tableModel.getValueAt(row, 0).toString();
            String tenMon = tableModel.getValueAt(row, 1).toString();
            String donGiaStr = tableModel.getValueAt(row, 2).toString();

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
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

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
        }
    }
}
