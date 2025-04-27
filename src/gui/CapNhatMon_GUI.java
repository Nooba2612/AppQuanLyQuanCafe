package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import dao.MonNuoc_DAO;
import database.ConnectDB;
import entity.MonNuoc;

public class CapNhatMon_GUI extends JPanel implements ActionListener, MouseListener{

	private JLabel lblTitle, lblMaMon, lblTenMon, lblGia, lbldvt, lblTim;
	private JTextField txtMaMon, txtTenMon, txtGia, txtTim ;
	private JComboBox<String> combodvt;
	private static MonNuoc_DAO mn_dao =new MonNuoc_DAO();
	private DefaultTableModel modelMonNuoc;
	private JTable tableMonNuoc;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang, btnTim;
	private String regexMaMon = "^(CF|TR|ST|TP)[0-9]+$";
	private String regexGia = "^[0-9]*$";
	private String regexTenMon = "^([A-Z][a-z]*)+(\\s[a-z]*)*$";
	private String regexCF="^CF[0-9]+$", regexTR="^TR[0-9]+$",regexST="^ST[0-9]+$", regexTP="^TP[0-9]+$";
	
	public CapNhatMon_GUI() {
		setLayout(new BorderLayout());
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        Box boxNorth = Box.createVerticalBox();
        Box boxNhapLieu = Box.createHorizontalBox();
        Box boxNhapLeft = Box.createVerticalBox();
        Box boxNhapRight = Box.createVerticalBox();
        Box bMaMon, bTenMon, bGia, bdvt;
        
        lblTitle = new JLabel("MENU");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.blue);
		JPanel pTitle = new JPanel();
		pTitle.add(lblTitle);
        
		lblMaMon = new JLabel("Mã món: ");
		lblTenMon = new JLabel("Tên món: ");
		lblGia = new JLabel("Giá: ");
		lbldvt = new JLabel("Đơn vị tính: ");
		
		txtMaMon = new JTextField();
		txtTenMon = new JTextField(20);
		txtGia = new JTextField();
		combodvt = new JComboBox<String>();
		combodvt.addItem("Ly");
		combodvt.addItem("Phần");
		
		boxNorth.add(pTitle);
		boxNorth.add(boxNhapLieu);
		boxNorth.add(Box.createVerticalStrut(20));
		
		boxNhapLieu.add(Box.createHorizontalStrut(30));
		boxNhapLieu.add(boxNhapLeft);
		boxNhapLieu.add(Box.createHorizontalStrut(30));
		boxNhapLieu.add(boxNhapRight);
		boxNhapLieu.add(Box.createHorizontalStrut(30));
		
		boxNhapLeft.add(bMaMon = Box.createHorizontalBox());
		bMaMon.add(lblMaMon);
		bMaMon.add(txtMaMon);
		boxNhapLeft.add(Box.createVerticalStrut(5));
		boxNhapLeft.add(bTenMon = Box.createHorizontalBox());
		bTenMon.add(lblTenMon);
		bTenMon.add(txtTenMon);
		boxNhapLeft.add(Box.createVerticalStrut(5));
		boxNhapLeft.add(bGia = Box.createHorizontalBox());
		bGia.add(lblGia);
		bGia.add(txtGia);
		
		boxNhapRight.add(Box.createVerticalStrut(10));
		boxNhapRight.add(bdvt = Box.createHorizontalBox());
		bdvt.add(lbldvt);
		bdvt.add(combodvt);
		
		lblMaMon.setPreferredSize(lbldvt.getPreferredSize());
		lblTenMon .setPreferredSize(lbldvt.getPreferredSize());
		lblGia.setPreferredSize(lbldvt.getPreferredSize());
		
		//Center
		String[] colHeader = { "Mã món", "Tên món", "Giá", "Đơn vị tính", "Danh mục"};
		modelMonNuoc = new DefaultTableModel(colHeader, 0);
		tableMonNuoc = new JTable(modelMonNuoc);
		add(new JScrollPane(tableMonNuoc), BorderLayout.CENTER);
		add(boxNorth, BorderLayout.NORTH);
				
		DocDuLieuVaoTable();
				
		//South
		Box boxSouth = Box.createHorizontalBox();
				
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnCapNhat = new JButton("Cập nhật");
		btnXoaTrang = new JButton("Xóa trắng");
		btnTim = new JButton("Tìm");
		lblTim = new JLabel("Nhập mã Món ");
		txtTim = new JTextField();
				
		boxSouth.add(Box.createHorizontalStrut(20));
		boxSouth.add(lblTim);
		boxSouth.add(Box.createHorizontalStrut(5));
		boxSouth.add(txtTim);
		boxSouth.add(Box.createHorizontalStrut(5));
		boxSouth.add(btnTim);
		boxSouth.add(Box.createHorizontalStrut(40));
		boxSouth.add(btnThem);
		boxSouth.add(Box.createHorizontalStrut(5));
		boxSouth.add(btnXoa);
		boxSouth.add(Box.createHorizontalStrut(5));
		boxSouth.add(btnCapNhat);
		boxSouth.add(Box.createHorizontalStrut(5));
		boxSouth.add(btnXoaTrang);
		boxSouth.add(Box.createHorizontalStrut(20));
				
		add(boxSouth, BorderLayout.SOUTH);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		tableMonNuoc.addMouseListener(this);
	        
//		setTitle("Quản lý món nước");
//		setSize(800, 500);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
	}
	
	private void DocDuLieuVaoTable() {
		ArrayList<MonNuoc> dsmn = mn_dao.getDSMonNuoc();
		for(MonNuoc mn : dsmn) {
			modelMonNuoc.addRow(new Object[] {
					mn.getMaMon(),
					mn.getTenMon(),
					mn.getDonGia(),
					mn.getDonViTinh(),
					mn.getDanhMuc()
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
		Object o = e.getSource();
		
		if(o.equals(btnThem)){
			String maMon = txtMaMon.getText();
			String tenMon = txtTenMon.getText();
			String giaString = txtGia.getText();
			String dvt = combodvt.getSelectedItem().toString();
			if (!maMon.matches(regexMaMon)) {
				JOptionPane.showMessageDialog(this, "Mã món bắt đầu bằng CF(cafe),TR(Trà),ST(sinh tố),TP(topping) và theo sau là số");
			}else if(!chuyenSangKhongDau(tenMon).matches(regexTenMon)) {
				JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			}else if(!giaString.matches(regexGia)) {
				JOptionPane.showMessageDialog(this, "Giá phải lớn hơn hoặc bằng 0");
			}else {
				try {
					String danhMuc = " ";
					Double gia = Double.parseDouble(giaString);
					if (maMon.matches(regexCF))
						danhMuc = "Cà phê";
					else if (maMon.matches(regexTR))
						danhMuc = "Trà";
					else if (maMon.matches(regexST))
						danhMuc = "Sinh tố";
					else if (maMon.matches(regexTP))
						danhMuc = "Topping";
					MonNuoc mn = new MonNuoc(maMon, tenMon, dvt, gia, danhMuc);
					if(mn_dao.themMonNuoc(mn)) {
						modelMonNuoc.addRow(new Object[] {
								mn.getMaMon(),
								mn.getTenMon(),
								mn.getDonGia(),
								mn.getDonViTinh(),
								mn.getDanhMuc()
								});
					}else {
						JOptionPane.showMessageDialog(this, "Trùng mã");
					}							
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
		else if(o.equals(btnXoaTrang)) {
			txtMaMon.setText("");
			txtTenMon.setText("");
			txtGia.setText("");
			combodvt.setSelectedItem("Ly");			
		}
		else if(o.equals(btnXoa)) {
			if (tableMonNuoc.getSelectedRow() == -1)
				JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá.Hãy thử lại");
			else{
				if(JOptionPane.showConfirmDialog(this,"Bạn có muốn xoá dòng này hay không?", "Xác nhận xóa",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					int row = tableMonNuoc.getSelectedRow();
					String maMon = modelMonNuoc.getValueAt(row, 0).toString();
					try {
						mn_dao.xoaMonNuoc(maMon);
						modelMonNuoc.removeRow(tableMonNuoc.getSelectedRow());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, "Trùng");
					}
				}
			}
		}
		else if(o.equals(btnCapNhat)) {
			String maMon = txtMaMon.getText();
			String tenMon = txtTenMon.getText();
			String giaString = txtGia.getText();
			String dvt = combodvt.getSelectedItem().toString();
			if (!maMon.matches(regexMaMon)) {
				JOptionPane.showMessageDialog(this, "Mã món bắt đầu bằng CF(cafe),TR(Trà),ST(sinh tố),TP(topping) và theo sau là số");
			}else if(!chuyenSangKhongDau(tenMon).matches(regexTenMon)) {
				JOptionPane.showMessageDialog(this, "Tên không hợp lệ");
			}else if(!giaString.matches(regexGia)) {
				JOptionPane.showMessageDialog(this, "Giá phải lớn hơn hoặc bằng 0");
			}else {
				try {
					String danhMuc = "";
					if (maMon.matches(regexCF))
						danhMuc = "Cà phê";
					else if (maMon.matches(regexTR))
						danhMuc = "Trà";
					else if (maMon.matches(regexST))
						danhMuc = "Sinh tố";
					else if (maMon.matches(regexTP))
						danhMuc = "Topping";
					Double gia = Double.parseDouble(giaString);
					MonNuoc mn = new MonNuoc(maMon, tenMon, dvt, gia, danhMuc);
					if(mn_dao.capNhatMonNuoc(mn)) {
						ArrayList<MonNuoc> dsmn = mn_dao.getDSMonNuoc();
						for(int i=1; i<=dsmn.size(); i++)
							modelMonNuoc.removeRow(0);
						DocDuLieuVaoTable();
					}else {
						JOptionPane.showMessageDialog(this, "Không có món nước mã "+maMon);
					}						
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Đã cập nhập");
				}
			}		
		}
		else if(o.equals(btnTim)) {
			String thongTin = txtTim.getText();
			ArrayList<MonNuoc> dsmn = mn_dao.getDSMonNuoc();
			MonNuoc mn = null;
			if(thongTin.matches(regexMaMon)) {
				mn = mn_dao.timmonNuocTheoMa(thongTin);
				if(mn!=null) {
					String maMon = mn.getMaMon();
					int index = dsmn.indexOf(dsmn.stream().filter(x -> x.getMaMon().equalsIgnoreCase(maMon)).findFirst().orElse(null));
					tableMonNuoc.setRowSelectionInterval(index, index);
				}
			}	
			else {
				mn = mn_dao.timmonNuocTheoMa(thongTin);
				if(mn!=null) {
					String tenMon = mn.getTenMon();
					int index = dsmn.indexOf(dsmn.stream().filter(x -> x.getTenMon().equalsIgnoreCase(tenMon)).findFirst().orElse(null));
					tableMonNuoc.setRowSelectionInterval(index, index);
				}
				else
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
			}
		}
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableMonNuoc.getSelectedRow();
		txtMaMon.setText(modelMonNuoc.getValueAt(row, 0).toString());
		txtTenMon.setText(modelMonNuoc.getValueAt(row, 1).toString());
		txtGia.setText(modelMonNuoc.getValueAt(row, 2).toString());
		combodvt.setSelectedItem(modelMonNuoc.getValueAt(row, 3).toString());		
	}
	
//	public static void main(String[] args) {
//		new GD_CapNhatMon().setVisible(true);
//	}

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
