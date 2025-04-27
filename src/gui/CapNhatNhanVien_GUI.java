package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import dao.ChucVu_DAO;
import dao.NhanVien_DAO;
import database.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;


public class CapNhatNhanVien_GUI extends JPanel implements ActionListener, MouseListener{
	private JLabel lbTitle, lbMaNV, lbTenNV, lbTrangThaiLamViec, lbNgayVaoLam, lbSdt, lbEmail, lbChucVu, lbTim;
	private JTextField txtMaNV, txtTenNV, txtNgayVaoLam, txtSdt, txtEmail, txtTim;
	private JComboBox<String> comboTrangThaiLamViec, comboChucVu;
	private static NhanVien_DAO nv_dao = new NhanVien_DAO();
	private static ChucVu_DAO cv_dao = new ChucVu_DAO();
	private DefaultTableModel modelNhanVien, modelLuong;
	private JScrollPane scroll, scroll2;
	private Box boxWhole, boxNorth, boxNhapLieu, boxNhapLieuLeft, boxNhapLieuRight, boxScroll, boxSouth, boxTop, boxBot;
	private JTable tableNhanVien, tableLuong;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang, btnTim, btnAll, btnLuong;
	private String regexMaNV = "^NV[0-9]+$";
	private String regexTenNV = "^([A-Z][a-z]*\\s)+([A-Z][a-z]*){1}$";
	private String regexSDT = "^[0-9]{10}$";
	private String regexEmail = "^[^@]+@[^.]+[.]com$";

	public CapNhatNhanVien_GUI() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//North
		
        boxNorth = Box.createVerticalBox();
        boxNhapLieu = Box.createHorizontalBox();
        boxNhapLieuLeft = Box.createVerticalBox();
        boxNhapLieuRight = Box.createVerticalBox();
        Box bMa, bTen, bTrangThai, bNgayVaoLam, bSdt, bEmail, bChucVu;
        
        lbTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.blue);
		JPanel pTitle = new JPanel();
		pTitle.add(lbTitle);
        
		lbMaNV = new JLabel("Mã nhân viên: ");
		lbTenNV = new JLabel("Tên nhân viên: ");
		lbTrangThaiLamViec = new JLabel("Trạng thái làm việc: ");
		lbNgayVaoLam = new JLabel("Ngày vào làm: ");
		lbSdt = new JLabel("Số điện thoại: ");
		lbEmail = new JLabel("Email: ");
		lbChucVu = new JLabel("Chức vụ");
		
		txtMaNV = new JTextField();
		txtTenNV = new JTextField(20);
		comboTrangThaiLamViec = new JComboBox<String>();
		comboTrangThaiLamViec.addItem("Đang làm việc");
		comboTrangThaiLamViec.addItem("Nghỉ việc");
		txtNgayVaoLam = new JTextField();
		txtSdt = new JTextField();
		txtEmail = new JTextField();
		comboChucVu = new JComboBox<String>();
		comboChucVu.setEditable(true);
		ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
		for(ChucVu cv : dscv) {
			comboChucVu.addItem(cv.getTenChucVu());
		}
		
		boxNorth.add(pTitle);
		boxNorth.add(Box.createVerticalStrut(20));
		boxNorth.add(boxNhapLieu);
		boxNorth.add(Box.createVerticalStrut(20));
		
		boxNhapLieu.add(Box.createHorizontalStrut(40));
		boxNhapLieu.add(boxNhapLieuLeft);
		boxNhapLieu.add(Box.createHorizontalStrut(30));
		boxNhapLieu.add(boxNhapLieuRight);
		boxNhapLieu.add(Box.createHorizontalStrut(40));
		
		boxNhapLieuLeft.add(bMa = Box.createHorizontalBox());
		bMa.add(lbMaNV);
		bMa.add(txtMaNV);
		boxNhapLieuLeft.add(Box.createVerticalStrut(5));
		boxNhapLieuLeft.add(bTen = Box.createHorizontalBox());
		bTen.add(lbTenNV);
		bTen.add(txtTenNV);
		boxNhapLieuLeft.add(Box.createVerticalStrut(5));
		boxNhapLieuLeft.add(bSdt = Box.createHorizontalBox());
		bSdt.add(lbSdt);
		bSdt.add(txtSdt);
		boxNhapLieuLeft.add(Box.createVerticalStrut(5));
		boxNhapLieuLeft.add(bEmail = Box.createHorizontalBox());
		bEmail.add(lbEmail);
		bEmail.add(txtEmail);
		
		boxNhapLieuRight.add(bNgayVaoLam = Box.createHorizontalBox());
		bNgayVaoLam.add(lbNgayVaoLam);
		bNgayVaoLam.add(txtNgayVaoLam);
		boxNhapLieuRight.add(Box.createVerticalStrut(10));
		boxNhapLieuRight.add(bTrangThai = Box.createHorizontalBox());
		bTrangThai.add(lbTrangThaiLamViec);
		bTrangThai.add(comboTrangThaiLamViec);
		boxNhapLieuRight.add(Box.createVerticalStrut(10));
		boxNhapLieuRight.add(bChucVu = Box.createHorizontalBox());
		bChucVu.add(lbChucVu);
		bChucVu.add(comboChucVu);
		
		lbMaNV.setPreferredSize(lbTrangThaiLamViec.getPreferredSize());
		lbTenNV.setPreferredSize(lbTrangThaiLamViec.getPreferredSize());
		lbNgayVaoLam.setPreferredSize(lbTrangThaiLamViec.getPreferredSize());
		lbSdt.setPreferredSize(lbTrangThaiLamViec.getPreferredSize());
		lbEmail.setPreferredSize(lbTrangThaiLamViec.getPreferredSize());
		lbChucVu.setPreferredSize(lbTrangThaiLamViec.getPreferredSize());
		
		
		//Center
		boxScroll = Box.createVerticalBox();
		String[] colHeader = { "Mã NV", "Tên", "Trạng thái", "Ngày vào làm", "Số điện thoại", "Email", "Chức vụ" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		scroll = new JScrollPane(tableNhanVien);
		boxScroll.add(scroll);
		
		String[] colHeader2 = { "Mã NV", "Tên", "Tổng số ca", "Lương theo giờ", "Tổng lương"};
		modelLuong = new DefaultTableModel(colHeader2, 0);
		tableLuong = new JTable(modelLuong);
		scroll2 = new JScrollPane(tableLuong);
		
		DocDuLieuDatabaseVaoTable();
		
		//South
		boxSouth = Box.createVerticalBox();
		boxTop = Box.createHorizontalBox();
		boxBot = Box.createHorizontalBox();
		boxSouth.add(boxTop);
		boxSouth.add(Box.createVerticalStrut(10));
		boxSouth.add(boxBot);
		boxSouth.add(Box.createVerticalStrut(10));
		
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnCapNhat = new JButton("Cập nhật");
		btnXoaTrang = new JButton("Xóa trắng");
		btnLuong = new JButton("Lương tháng này");
		btnTim = new JButton("Tìm");
		lbTim = new JLabel("Nhập mã hoặc tên nhân viên: ");
		txtTim = new JTextField();
		btnAll = new JButton("Hiển thị toàn bộ danh sách");

		boxTop.add(Box.createHorizontalStrut(60));
		boxTop.add(btnThem);
		boxTop.add(Box.createHorizontalStrut(10));
		boxTop.add(btnXoa);
		boxTop.add(Box.createHorizontalStrut(10));
		boxTop.add(btnCapNhat);
		boxTop.add(Box.createHorizontalStrut(10));
		boxTop.add(btnXoaTrang);
		boxTop.add(Box.createHorizontalStrut(10));
		boxTop.add(btnAll);
		boxTop.add(Box.createHorizontalStrut(10));
		boxTop.add(btnLuong);
		boxTop.add(Box.createHorizontalStrut(60));
		
		boxBot.add(Box.createHorizontalStrut(60));
		boxBot.add(lbTim);
		boxBot.add(Box.createHorizontalStrut(15));
		boxBot.add(txtTim);
		boxBot.add(Box.createHorizontalStrut(15));
		boxBot.add(btnTim);
		boxBot.add(Box.createHorizontalStrut(60));
		
		
		boxWhole = Box.createVerticalBox();
		boxWhole.add(Box.createVerticalStrut(30));
		boxWhole.add(boxNorth);
		boxWhole.add(boxScroll);
		boxWhole.add(Box.createVerticalStrut(10));
		boxWhole.add(boxSouth);
		add(boxWhole);

//		setTitle("Quản lý nhân viên");
		setSize(800, 500);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setVisible(true);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuong.addActionListener(this);
		btnTim.addActionListener(this);
		btnAll.addActionListener(this);
		tableNhanVien.addMouseListener(this);
	}
	
	private void DocDuLieuDatabaseVaoTable() {
		ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
		for(NhanVien nv : dsnv) {
			modelNhanVien.addRow(new Object[] {
					nv.getMaNV(),
					nv.getTenNV(),
					nv.getTrangThaiLamViec(),
					nv.getNgayVaoLam(),
					nv.getSdt(),
					nv.getEmail(),
					nv.getChucVu().getTenChucVu()
			});
		}
	}
	
	private void DocDuLieuDatabaseVaoTable(ArrayList<NhanVien> dsnv) {
		int rowCount = tableNhanVien.getRowCount();
		for (int i=1; i<=rowCount; i++) {
			modelNhanVien.removeRow(0);
		}
		for(NhanVien nv : dsnv) {
			modelNhanVien.addRow(new Object[] {
					nv.getMaNV(),
					nv.getTenNV(),
					nv.getTrangThaiLamViec(),
					nv.getNgayVaoLam(),
					nv.getSdt(),
					nv.getEmail(),
					nv.getChucVu().getTenChucVu()
			});
		}
	}
	
	private String chuyenSangKhongDau(String str) {
		String[] dsa = {"à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ậ", "ẩ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ"};
		for(String kyTu : dsa) {
			str = str.replace(kyTu, "a");
		}
	    String[] dsA = {"À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ", "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ"};
	    for(String kyTu : dsA) {
	        str = str.replace(kyTu, "A");
	    }
	    String[] dse = {"è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ"};
	    for(String kyTu : dse) {
	        str = str.replace(kyTu, "e");
	    }
	    String[] dsE = {"È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ"};
	    for(String kyTu : dsE) {
	        str = str.replace(kyTu, "E");
	    }
	    String[] dsi = {"ì", "í", "ị", "ỉ", "ĩ"};
	    for(String kyTu : dsi) {
	        str = str.replace(kyTu, "i");
	    }
	    String[] dsI = {"Ì", "Í", "Ị", "Ỉ", "Ĩ"};
	    for(String kyTu : dsI) {
	        str = str.replace(kyTu, "I");
	    }
	    String[] dso = {"ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ơ", "ờ", "ớ", "ợ", "ở", "ỡ"};
	    for(String kyTu : dso) {
	        str = str.replace(kyTu, "o");
	    }
	    String[] dsO = {"Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ơ", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ"};
	    for(String kyTu : dsO) {
	        str = str.replace(kyTu, "O");
	    }
	    String[] dsu = {"ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ"};
	    for(String kyTu : dsu) {
	        str = str.replace(kyTu, "u");
	    }
	    String[] dsU = {"Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ"};
	    for(String kyTu : dsU) {
	        str = str.replace(kyTu, "U");
	    }
	    String[] dsy = {"ỳ", "ý", "ỵ", "ỷ", "ỹ"};
	    for(String kyTu : dsy) {
	        str = str.replace(kyTu, "y");
	    }
	    String[] dsY = {"Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ"};
	    for(String kyTu : dsY) {
	        str = str.replace(kyTu, "Y");
	    }
	    str = str.replace("đ", "d");
	    str = str.replace("Đ", "D");
		return str;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if(o.equals(btnThem)){
			String maNV = txtMaNV.getText();
			String tenNV = txtTenNV.getText();
			String trangThaiLamViec = comboTrangThaiLamViec.getSelectedItem().toString();
			String ngayString = txtNgayVaoLam.getText();
			String sdt = txtSdt.getText();
			String email = txtEmail.getText();
			String tenChucVu = comboChucVu.getSelectedItem().toString();
			if (!maNV.matches(regexMaNV)) 
				JOptionPane.showMessageDialog(this, "Mã nhân viên bắt đầu bằng NV và theo sau là số");
			else if (!chuyenSangKhongDau(tenNV).matches(regexTenNV))
				JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			else if(!sdt.matches(regexSDT))
				JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 chữ số");
			else if(!email.matches(regexEmail)) 
				JOptionPane.showMessageDialog(this, "Email sai định dạng");
			else {
				try {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate ngayVaoLam = LocalDate.parse(ngayString, dtf);
					ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
					ChucVu chucVu = dscv.stream().filter(cv -> cv.getTenChucVu().equalsIgnoreCase(tenChucVu)).findFirst().orElse(null);
					NhanVien nv = new NhanVien(maNV, tenNV, trangThaiLamViec, ngayVaoLam, sdt, email, chucVu);
					if(nv_dao.themNV(nv)) {
						modelNhanVien.addRow(new Object[] {
								nv.getMaNV(),
								nv.getTenNV(),
								nv.getTrangThaiLamViec(),
								nv.getNgayVaoLam(),
								nv.getSdt(),
								nv.getEmail(),
								nv.getChucVu().getTenChucVu()
						});
					}
					else
						JOptionPane.showMessageDialog(this, "Trùng mã");
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Ngày có dạng dd/MM/yyyy");
				}
			}
			
		}
		else if(o.equals(btnXoaTrang)) {
			txtMaNV.setText("");
			txtTenNV.setText("");
			comboTrangThaiLamViec.setSelectedItem("Đang làm việc");
			txtNgayVaoLam.setText("");
			txtSdt.setText("");
			txtEmail.setText("");
			txtTim.setText("");
			ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
			comboChucVu.setSelectedItem(dscv.get(0).getTenChucVu());
		}
		else if(o.equals(btnXoa)) {
			if (tableNhanVien.getSelectedRow() == -1)
				JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá.Hãy thử lại");
			else{
				if(JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa nhân viên này và tất cả lịch làm việc, hóa đơn của nhân viên này hay không??", "Xác nhận xóa",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					int row = tableNhanVien.getSelectedRow();
					String maNV = modelNhanVien.getValueAt(row, 0).toString();
					try {
						nv_dao.xoaNV(maNV);
						modelNhanVien.removeRow(tableNhanVien.getSelectedRow());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, "Trùng");
					}
				}
			}
		}
		else if(o.equals(btnCapNhat)) {
			String maNV = txtMaNV.getText();
			String tenNV = txtTenNV.getText();
			String trangThaiLamViec = comboTrangThaiLamViec.getSelectedItem().toString();
			String ngayString = txtNgayVaoLam.getText();
			String sdt = txtSdt.getText();
			String email = txtEmail.getText();
			String tenChucVu = comboChucVu.getSelectedItem().toString();
			if (!maNV.matches(regexMaNV)) 
				JOptionPane.showMessageDialog(this, "Mã nhân viên bắt đầu bằng NV và theo sau là số");
			else if (!chuyenSangKhongDau(tenNV).matches(regexTenNV))
				JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			else if(!sdt.matches(regexSDT))
				JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 chữ số");
			else if(!email.matches(regexEmail)) 
				JOptionPane.showMessageDialog(this, "Email sai định dạng");
			else {
				try {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate ngayVaoLam = LocalDate.parse(ngayString, dtf);
					ArrayList<ChucVu> dscv = cv_dao.getDSChucVu();
					ChucVu chucVu = dscv.stream().filter(cv -> cv.getTenChucVu().equalsIgnoreCase(tenChucVu)).findFirst().orElse(null);
					NhanVien nv = new NhanVien(maNV, tenNV, trangThaiLamViec, ngayVaoLam, sdt, email, chucVu);
					if(nv_dao.capNhatNV(nv)) {
						ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
						DocDuLieuDatabaseVaoTable(dsnv);
					}
					else
						JOptionPane.showMessageDialog(this, "Không có nhân viên mã "+maNV);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Ngày có dạng dd/MM/yyyy");
				}
			}
			
		}
		else if(o.equals(btnTim)) {
			String thongTin = txtTim.getText();
			NhanVien nv = null;
			if(thongTin.matches(regexMaNV)) {
				nv = nv_dao.timNVTheoMa(thongTin);
				if(nv!=null) {
					ArrayList<NhanVien> dsnvKetQuaTim = new ArrayList<NhanVien>();
					dsnvKetQuaTim.add(nv);
					DocDuLieuDatabaseVaoTable(dsnvKetQuaTim);
				}
				else
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
			}	
			else {
				ArrayList<NhanVien> dsnvKetQuaTim = nv_dao.timNVTheoTen(thongTin);
				if(dsnvKetQuaTim.size()>0) {
					DocDuLieuDatabaseVaoTable(dsnvKetQuaTim);
				}
				else
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
			}
		}
		else if(o.equals(btnAll)) {
			boxScroll.remove(scroll2);
			boxScroll.add(scroll);
			ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
			DocDuLieuDatabaseVaoTable(dsnv);
		}
		else if(o.equals(btnLuong)) {
			Statement statemen = null;
			boxScroll.remove(scroll);
			boxScroll.add(scroll2);
			int rowCount = tableLuong.getRowCount();
			for (int i=1; i<=rowCount; i++) {
				modelLuong.removeRow(0);
			}
			try {
				Connection con = ConnectDB.getConnection();
				String sql = "SELECT nv.MaNV, nv.Ten, TongSoCa = COUNT(llv.MaLLV), cv.LuongTheoGio, Luong = cv.LuongTheoGio*4*COUNT(llv.MaLLV)"
						+ " FROM NhanVien nv JOIN ChucVu cv ON nv.MaChucVu=cv.MaChucVu JOIN LichLamViec llv ON nv.MaNV=llv.MaNV"
						+ " WHERE MONTH(llv.NgayLamViec) = MONTH(GETDATE()) AND YEAR(llv.NgayLamViec) = YEAR(GETDATE())"
						+ " GROUP BY nv.MaNV, nv.Ten, cv.LuongTheoGio";
				statemen= con.createStatement();
				ResultSet rs = statemen.executeQuery(sql);
		
				while (rs.next()) {
					String maNV = rs.getString(1);
					String tenNV = rs.getString(2);
					int tongSoCa = rs.getInt(3);
					DecimalFormat df = new DecimalFormat("#,000");
					Double luongTheoGio = rs.getDouble(4);
					String luongTheoGioFormat = df.format(luongTheoGio);
					Double tongLuong = rs.getDouble(5);
					String tongLuongFormat = df.format(tongLuong);
					modelLuong.addRow(new Object[] {
							maNV,
							tenNV,
							tongSoCa,
							luongTheoGioFormat,
							tongLuongFormat
					});
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}finally {
				try {
					statemen.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNhanVien.getSelectedRow();
		txtMaNV.setText(modelNhanVien.getValueAt(row, 0).toString());
		txtTenNV.setText(modelNhanVien.getValueAt(row, 1).toString());
		comboTrangThaiLamViec.setSelectedItem(modelNhanVien.getValueAt(row, 2).toString());
		LocalDate ngayVaoLam = Date.valueOf(modelNhanVien.getValueAt(row, 3).toString()).toLocalDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		txtNgayVaoLam.setText(dtf.format(ngayVaoLam));
		txtSdt.setText(modelNhanVien.getValueAt(row, 4).toString());
		txtEmail.setText(modelNhanVien.getValueAt(row, 5).toString());
		comboChucVu.setSelectedItem(modelNhanVien.getValueAt(row, 6).toString());
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
