package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.TaiKhoan_DAO;
import database.ConnectDB;
import entity.TaiKhoan;

public class DangNhap_GUI extends JFrame implements ActionListener{
	private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
	private JButton loginButton;
	private static TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();

    public DangNhap_GUI() {
    	
    	try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordField = new JPasswordField();//sử dụng JPasswordField để nhập mật khẩu
        
        loginButton = new JButton("Đăng nhập");
        
        loginButton.addActionListener(this);

        panel.add(usernameLabel);panel.add(usernameField);
        panel.add(passwordLabel);panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        
        setVisible(true);
        setTitle("Đăng nhập");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }


	@Override
	public void actionPerformed(ActionEvent e) {
	Object o = e.getSource();
			if(o.equals(loginButton)) {
				String username = usernameField.getText();
				//phương thức getPassword() trả về một mảng các ký tự (char[]) thay vì một chuỗi (String)
				//new string để chuyển thành chuỗi
                String password = new String(passwordField.getPassword());
                
                ArrayList<TaiKhoan> dstk = tk_dao.dstk();
                TaiKhoan tk = dstk.stream()
                					.filter(x -> x.getUsername().equals(username) && x.getPassword().equals(password))
                					.findFirst()
                					.orElse(null);
                if (tk!=null) {
                	new TrangChu_GUI(username);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(DangNhap_GUI.this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
			}
		
	}
//	public static void main(String[] args) {
//		new CuaSoDangNhap().setVisible(true);
//	}
}
