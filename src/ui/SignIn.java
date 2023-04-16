package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SignIn extends JFrame {

	private JPanel SignIn;
	private JTextField txtTenDangNhap;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
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
	public SignIn() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 650, 431);
		SignIn = new JPanel();
		SignIn.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(SignIn);
		SignIn.setLayout(null);
		getContentPane().setBackground(new Color(170, 220, 200));

		
		JLabel lblTitle = new JLabel("Đăng ký");
		lblTitle.setLabelFor(SignIn);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 128, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTitle.setBackground(new Color(240, 128, 128));
		lblTitle.setBounds(0, 0, 632, 32);
		SignIn.add(lblTitle);
		
		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
		lblTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTenDangNhap.setBounds(0, 81, 210, 35);
		SignIn.add(lblTenDangNhap);
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setToolTipText("Tên đăng nhập là mã nhân viên của bạn\r\n");
		lblTenDangNhap.setLabelFor(txtTenDangNhap);
		txtTenDangNhap.setText("\r\n");
		txtTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenDangNhap.setForeground(Color.BLACK);
		txtTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenDangNhap.setColumns(10);
		txtTenDangNhap.setBounds(210, 81, 403, 35);
		SignIn.add(txtTenDangNhap);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMatKhau.setBounds(0, 127, 210, 35);
		SignIn.add(lblMatKhau);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setLabelFor(passwordField);
		passwordField.setToolTipText("\r\nNhập mật khẩu");
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField.setEchoChar('*');
		passwordField.setBounds(210, 127, 403, 35);
		SignIn.add(passwordField);
		
		JLabel lblNhapLaiMK = new JLabel("Nhập mật khẩu:");
		lblNhapLaiMK.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhapLaiMK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNhapLaiMK.setBounds(0, 173, 210, 35);
		SignIn.add(lblNhapLaiMK);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhapLaiMK.setLabelFor(passwordField_1);
		passwordField_1.setToolTipText("Nhập lại mật khẩu:");
		passwordField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField_1.setEchoChar('*');
		passwordField_1.setBounds(210, 173, 403, 35);
		SignIn.add(passwordField_1);
		
		JCheckBox ckbxHienMK = new JCheckBox("Hiện mật khẩu");
		ckbxHienMK.setToolTipText("");
		ckbxHienMK.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ckbxHienMK.setBounds(210, 226, 147, 36);
		SignIn.add(ckbxHienMK);
		
		JButton btnDangKy = new JButton("Đăng ký");
		btnDangKy.setForeground(new Color(0, 128, 0));
		btnDangKy.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		btnDangKy.setBounds(241, 329, 137, 40);
		SignIn.add(btnDangKy);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Quản lý");
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnNewRadioButton.setBounds(210, 279, 150, 35);
		SignIn.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Nhân viên");
		rdbtnNewRadioButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setBounds(412, 279, 140, 35);
		SignIn.add(rdbtnNewRadioButton_1);
		ButtonGroup btnGroupChucVu = new ButtonGroup();
		btnGroupChucVu.add(rdbtnNewRadioButton_1);
		btnGroupChucVu.add(rdbtnNewRadioButton);
		
		JLabel lbChucVu = new JLabel("Chức vụ");
		lbChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbChucVu.setBounds(10, 277, 121, 35);
		SignIn.add(lbChucVu);
	}
}
