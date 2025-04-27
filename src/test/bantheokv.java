package test;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import dao.Ban_DAO;
import dao.KhuVuc_DAO;
import database.ConnectDB;
import entity.Ban;
import entity.KhuVuc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class bantheokv extends JFrame implements ActionListener {
    private KhuVuc_DAO khuVucDao;
    private Ban_DAO banDao;
    private JPanel danhMucPanel;
    private JPanel monPanel;
    private JPanel menu;
    private JPanel khuVucPanel;

    public bantheokv() {
        // Tạo đối tượng KhuVuc_DAO
        khuVucDao = new KhuVuc_DAO();
        banDao = new Ban_DAO();
        // Kết nối đến cơ sở dữ liệu
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Tạo panel chứa các button khu vực theo chiều dọc
        khuVucPanel = new JPanel();
        khuVucPanel.setLayout(new BoxLayout(khuVucPanel, BoxLayout.Y_AXIS));

        // Lấy danh sách các khu vực từ cơ sở dữ liệu
        ArrayList<KhuVuc> khuVucList = khuVucDao.getDSKhuVuc();

        // Tạo button cho mỗi khu vực và thêm vào panel khu vực
        for (KhuVuc khuVuc : khuVucList) {
            JButton button = new JButton(khuVuc.getTenKV());
            button.setPreferredSize(new Dimension(150, 50));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Xử lý sự kiện khi button được nhấn
                    hienThiBanTheoKhuVuc(khuVuc.getMaKV());
                }
            });
            khuVucPanel.add(button);
        }

        // Tạo panel chứa các button món
        monPanel = new JPanel();
        monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));

        // Tạo border với tiêu đề "Khu vực" cho panel khu vực
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Khu vực");
        khuVucPanel.setBorder(titledBorder);

        // Tạo panel chứa cả danh mục và khu vực
        menu = new JPanel(new GridLayout(2, 1));
        menu.add(khuVucPanel);

        // Thêm các panel vào frame
        add(menu, BorderLayout.WEST);
        add(monPanel, BorderLayout.CENTER);
    }

    private void hienThiBanTheoKhuVuc(int maKhuVuc) {
        // Xóa các button bàn cũ trước khi hiển thị danh sách mới
        monPanel.removeAll();

        // Lấy danh sách bàn theo khu vực từ cơ sở dữ liệu
        // (Giả sử bạn có một hàm trong DAO để lấy danh sách bàn theo mã khu vực)
        ArrayList<Ban> banTheoKhuVuc = banDao.getDSBanTheoKV(maKhuVuc);

        // Tạo button cho mỗi bàn và thêm vào panel
        for (Ban ban : banTheoKhuVuc) {
        	JButton button = new JButton(String.valueOf(ban.getMaBan())); // Chuyển đổi int thành String
            monPanel.add(button);
        }

        // Yêu cầu frame cập nhật lại giao diện
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            bantheokv frame = new bantheokv();
            frame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện
    }
}
