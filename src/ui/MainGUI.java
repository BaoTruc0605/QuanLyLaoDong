//package ui;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import utils.XuLyChuyenTrang;
//
//import javax.swing.JLabel;
//import java.awt.Font;
//import java.awt.HeadlessException;
//import java.awt.Color;
//
//public class MainGUI extends JFrame {
//
//	private JPanel contentPane;
//	JLabel lblNewLabel;
//	static String tenDangNhap;
//	String matKhau;
//	NhanVien_GUI nv;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login login = new Login();
//					login.setVisible(true);
//
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
////	public void initcomponent() {
////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		setBounds(100, 100, 760, 550);
////		contentPane = new JPanel();
////		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
////		setContentPane(contentPane);
////		contentPane.setLayout(null);
////
////		lblNewLabel = new JLabel(this.getTenDangNhap());
////		lblNewLabel.setForeground(Color.PINK);
////		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
////		lblNewLabel.setBounds(5, 5, 736, 67);
////		contentPane.add(lblNewLabel);
////	}
////
////	public MainGUI(String tenDangNhap) throws HeadlessException {
////		super();
////		this.tenDangNhap = tenDangNhap;
////	}
////
////	public MainGUI(String tenDangNhap, String matKhau) throws HeadlessException {
////		super();
////		this.tenDangNhap = tenDangNhap;
////		this.matKhau = matKhau;
////	}
////
////	public String getTenDangNhap() {
////		return tenDangNhap;
////	}
////
////	public void setTenDangNhap(String tenDangNhap) {
////		this.tenDangNhap = tenDangNhap;
////	}
////
////	public String getMatKhau() {
////		return matKhau;
////	}
////
////	public void setMatKhau(String matKhau) {
////		this.matKhau = matKhau;
////	}
//
//}
