package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import utils.XuLyChuyenTrang;

import javax.swing.event.CaretListener;

import DAO.NguoiDung_DAO;

import javax.swing.event.CaretEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtTenDangNhap;
	private JPasswordField passwordField;
	static Login frame;
	NguoiDung_DAO dao_user = new NguoiDung_DAO();

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 727, 403);
		setLocationRelativeTo(null);
		setTitle("Đăng nhập");
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBackground(new Color(170, 220, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "kết nối Database thất bại");
		}

		JLabel labelTenCTy = new JLabel("Công Ty Xây Dựng SpaceX");
		labelTenCTy.setForeground(Color.RED);
		labelTenCTy.setHorizontalAlignment(SwingConstants.CENTER);
		labelTenCTy.setBackground(new Color(255, 255, 255));
		labelTenCTy.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTenCTy.setBounds(10, 11, 693, 38);
		contentPane.add(labelTenCTy);

		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
		lblTenDangNhap.setIcon(new ImageIcon("imgs\\icon-user.PNG"));
		lblTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenDangNhap.setBounds(10, 140, 180, 35);
		contentPane.add(lblTenDangNhap);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
			}
		});
		txtTenDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtTenDangNhap.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		txtTenDangNhap.setForeground(new Color(0, 0, 0));
		lblTenDangNhap.setLabelFor(txtTenDangNhap);
		txtTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenDangNhap.setBounds(188, 140, 436, 35);
		contentPane.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setIcon(new ImageIcon("imgs\\icon-password.PNG"));
		lblMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMatKhau.setBounds(10, 189, 180, 35);
		contentPane.add(lblMatKhau);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		passwordField.setEchoChar('*');
		passwordField.setToolTipText("\r\n");
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMatKhau.setLabelFor(passwordField);
		passwordField.setBounds(188, 189, 436, 35);
		contentPane.add(passwordField);

		JButton btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setForeground(new Color(0, 0, 255));
		btnDangNhap.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnDangNhap.addActionListener(new ActionListener() {
			String username = txtTenDangNhap.getText();
			String password = String.valueOf(passwordField.getPassword());

			public void actionPerformed(ActionEvent e) {
				if (dao_user.userLogin(getTenDangNhap(), getMatKhau()) != 0) {
					TrangChu_GUI trangChu_GUI = new TrangChu_GUI();
					XuLyChuyenTrang.openJFrame(frame, new TrangChu_GUI());
//					trangChu_GUI.setVisible(true);
//					frame.setVisible(false);
//					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng");
				}
			}
		});
		btnDangNhap.setBounds(188, 274, 140, 40);
		contentPane.add(btnDangNhap);

		JButton btnDangKy = new JButton("Đăng kí");
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn frameSignIn = new SignIn();
				frameSignIn.setVisible(true);
				frameSignIn.setTitle("Đăng ký");
				frame.getDefaultCloseOperation();
			}
		});
		btnDangKy.setForeground(new Color(0, 0, 255));
		btnDangKy.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnDangKy.setBounds(408, 274, 140, 40);
		contentPane.add(btnDangKy);

		JLabel lblSlogan = new JLabel("Xây, Xây nữa, Xây mãi");
		lblSlogan.setForeground(Color.BLUE);
		lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlogan.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 22));
		lblSlogan.setBounds(10, 325, 693, 30);
		contentPane.add(lblSlogan);

		JCheckBox ckbxHienMK = new JCheckBox("Hiện mật khẩu");
		ckbxHienMK.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});

		ckbxHienMK.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ckbxHienMK.setBounds(188, 231, 147, 36);
		contentPane.add(ckbxHienMK);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("imgs\\iconLogo.png"));
		lblLogo.setBounds(299, 49, 82, 90);
		contentPane.add(lblLogo);
	}

	public static String getTenDangNhap() {
		return txtTenDangNhap.getText();
	}

	private String getMatKhau() {
		return String.valueOf(passwordField.getPassword());
	}
}
