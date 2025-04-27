package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CaLamViec_DAO;
import dao.LichLamViec_DAO;
import dao.NhanVien_DAO;
import database.ConnectDB;
import entity.CaLamViec;
import entity.LichLamViec;
import entity.NhanVien;

public class DiemDanh_GUI extends JPanel implements ActionListener, MouseListener{
	private Box boxLeft, boxRight;
	private JSplitPane splitPane;
	private DefaultTableModel modelNhanVien, modelCaLamViec, modelDiemDanh;
	private JTable tableNhanVien, tableCaLamViec, tableDiemDanh;
	private JScrollPane scrollNhanVien, scrollCaLamViec, scrollDiemDanh;
	private JButton btnDiemDanh;
	private JPanel panelDiemDanh;
	private static NhanVien_DAO nv_dao = new NhanVien_DAO();
	private static CaLamViec_DAO clv_dao = new CaLamViec_DAO();
	private static LichLamViec_DAO llv_dao = new LichLamViec_DAO();
	private ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
	private ArrayList<CaLamViec> dsclv = new ArrayList<CaLamViec>();
	private ArrayList<LichLamViec> dsllv = new ArrayList<LichLamViec>();
	private String maNVDiemDanh, tenNVDiemDanh, tenCaDiemDanh;
	private int maCaDiemDanh;
	private JLabel lbTitle;

	public DiemDanh_GUI() {
		setLayout(new BorderLayout());
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Layout chính
		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boxLeft, boxRight);
		
		//------BoxLeft-------
		//Tiêu đề
		lbTitle = new JLabel("ĐIỂM DANH");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.blue);
		JPanel pTitle = new JPanel();
		pTitle.add(lbTitle);
		add(pTitle, BorderLayout.NORTH);
		
		//Bảng nhân viên
		String[] colHeaderNV = { "Mã nhân viên", "Họ tên" };
		modelNhanVien = new DefaultTableModel(colHeaderNV, 0);
		tableNhanVien = new JTable(modelNhanVien);
		scrollNhanVien = new JScrollPane(tableNhanVien);
		scrollNhanVien.setBorder(BorderFactory.createTitledBorder("Chọn nhân viên"));
		boxLeft.add(scrollNhanVien);
		boxLeft.add(Box.createVerticalStrut(15));
		
		dsnv = nv_dao.getDSNhanVien();
		for(NhanVien nv : dsnv) {
			modelNhanVien.addRow(new Object[] {nv.getMaNV(), nv.getTenNV()});
		}
		
		//Bảng ca làm việc
		String[] colHeaderCLV = { "Ca làm việc", "Thời gian" };
		modelCaLamViec = new DefaultTableModel(colHeaderCLV, 0);
		tableCaLamViec = new JTable(modelCaLamViec);
		scrollCaLamViec = new JScrollPane(tableCaLamViec);
		scrollCaLamViec.setBorder(BorderFactory.createTitledBorder("Chọn ca làm việc"));
		tableCaLamViec.setPreferredScrollableViewportSize(new Dimension(300, 130));
		boxLeft.add(scrollCaLamViec);
		boxLeft.add(Box.createVerticalStrut(15));
		
		dsclv = clv_dao.dsca();
		for(CaLamViec clv : dsclv) {
			modelCaLamViec.addRow(new Object[] {clv.getTenCa(), clv.getgioVaoLam() + "-" + clv.getGioKetThuc()});
		}
		
		//Nút điểm danh
		btnDiemDanh = new JButton("Điểm danh");
		panelDiemDanh = new JPanel();
		panelDiemDanh.add(btnDiemDanh);
		boxLeft.add(panelDiemDanh);
		boxLeft.add(Box.createVerticalStrut(15));
		
		
		//-------BoxRight-------
		//Bảng điểm danh
		String[] colHeaderDD = { "Mã nhân viên", "Họ tên", "Ca làm việc", "Ngày" };
		modelDiemDanh = new DefaultTableModel(colHeaderDD, 0);
		tableDiemDanh = new JTable(modelDiemDanh);
		scrollDiemDanh = new JScrollPane(tableDiemDanh);
		scrollDiemDanh.setBorder(BorderFactory.createTitledBorder("Bảng điểm danh"));
		boxRight.add(scrollDiemDanh);
		boxRight.add(Box.createVerticalStrut(15));
		
		dsllv = llv_dao.getDSLichLamViec();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for(LichLamViec llv : dsllv) {
			modelDiemDanh.addRow(new Object[]{llv.getNv().getMaNV(), llv.getNv().getTenNV(), llv.getCa().getTenCa(), dtf.format(llv.getNgayLamViec())});
		}
		
		splitPane.setDividerLocation(500);
		add(splitPane);
		
		//Set thuộc tính
		setSize(800, 500);
		setVisible(true);
		
		//Thêm sự kiện
		tableNhanVien.addMouseListener(this);
		tableCaLamViec.addMouseListener(this);
		btnDiemDanh.addActionListener(this);
	}
	public static void main(String[] args) {
		new DiemDanh_GUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDiemDanh)) {
			if(tableNhanVien.getSelectedRow()==-1)
				JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
			
			else if(tableCaLamViec.getSelectedRow()==-1)
				JOptionPane.showMessageDialog(this, "Chưa chọn ca làm việc");
			
			else {
				LocalDate ngayLamViec = LocalDate.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String ngayFormat = dtf.format(ngayLamViec);
				
				if(JOptionPane.showConfirmDialog(this,"Mã NV: "+maNVDiemDanh
													+"\nTên: " +tenNVDiemDanh
													+"\nCa: "+tenCaDiemDanh,
													"Xác nhận thông tin",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(llv_dao.themLichLamViec(maNVDiemDanh, maCaDiemDanh, ngayLamViec)) {	
					modelDiemDanh.addRow(new Object[] {maNVDiemDanh, tenNVDiemDanh, tenCaDiemDanh, ngayFormat});
					}
					else
						JOptionPane.showMessageDialog(this, "Bạn đã điểm danh rồi!");
				}
			}
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if(o.equals(tableNhanVien)) {
			int row = tableNhanVien.getSelectedRow();
			maNVDiemDanh = modelNhanVien.getValueAt(row, 0).toString();
			tenNVDiemDanh = modelNhanVien.getValueAt(row, 1).toString();
		}
		if(o.equals(tableCaLamViec)) {
			int row = tableCaLamViec.getSelectedRow();
			tenCaDiemDanh = modelCaLamViec.getValueAt(row, 0).toString();
			maCaDiemDanh = row + 1;
		}
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
