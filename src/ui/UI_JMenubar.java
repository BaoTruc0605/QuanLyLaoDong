package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import utils.XuLyChuyenTrang;

public class UI_JMenubar implements ActionListener {

	private JMenuItem jmnitThoat;
	private JMenuItem jmnitDangXuat;
	private JMenuItem jmnitTrangChu;
	private int widthIconMenubar = 16;
	private int heightIconMenubar = 16;
	private JMenu jmnitTimKiem;

	private JFrame jFrame;
	private JMenuItem jmnTimKiemNhanVienTheoMaNhanVien;
	private JMenuItem jmnTimKiemNhanVienTheoMaCongTrinh;

	public void setWidthIconMenubar(int width) {
		this.widthIconMenubar = width;
	}

	public void setHeightIconMenubar(int height) {
		this.heightIconMenubar = height;
	}

	public JMenuItem getJmnitThoat() {
		return jmnitThoat;
	}

	public void setJmnitThoat(JMenuItem jmnitThoat) {
		this.jmnitThoat = jmnitThoat;
	}

	public JMenuItem getJmnitDangXuat() {
		return jmnitDangXuat;
	}

	public void setJmnitdDangXuat(JMenuItem jmnitTDangXuat) {
		this.jmnitDangXuat = jmnitDangXuat;
	}

	public JMenuItem getJmnitTrangChu() {
		return jmnitTrangChu;
	}

	public void setJmnitTrangChu(JMenuItem jmnitTrangChu) {
		this.jmnitTrangChu = jmnitTrangChu;
	}

	public UI_JMenubar(JFrame frame) {
		this.jFrame = frame;

		JMenuBar jMenuBar = new JMenuBar();
		frame.setBackground(Color.LIGHT_GRAY);

		jmnitTrangChu = new JMenuItem("Trang chủ",
				XuLyChuyenTrang.convertImageToIcon("imgs\\trangChu.png", widthIconMenubar, heightIconMenubar));
		jmnitTimKiem = new JMenu("Tìm kiếm");
		jmnitTimKiem
				.setIcon(XuLyChuyenTrang.convertImageToIcon("imgs\\search.png", widthIconMenubar, heightIconMenubar));
		jmnitDangXuat = new JMenuItem("Đăng xuất");
		jmnitThoat = new JMenuItem("Thoát");

		jmnitTrangChu.setBorder(BorderFactory.createLineBorder(new Color(149, 228, 209, 255)));
		jmnitTimKiem.setBorder(BorderFactory.createLineBorder(new Color(149, 228, 209, 255)));
		jmnitDangXuat.setBorder(BorderFactory.createLineBorder(new Color(149, 228, 209, 255)));
		jmnitThoat.setBorder(BorderFactory.createLineBorder(new Color(149, 228, 209, 255)));

		jMenuBar.setBackground(new Color(255, 216, 217, 255));
		jmnitTrangChu.setBackground(new Color(255, 216, 217, 255));
		jmnitTimKiem.setBackground(new Color(255, 216, 217, 255));
		jmnitDangXuat.setBackground(new Color(255, 216, 217, 255));
		jmnitThoat.setBackground(new Color(255, 216, 217, 255));

		jmnitTrangChu.setPreferredSize(new Dimension(200, 30));
		jmnitTimKiem.setPreferredSize(new Dimension(200, 30));
		jmnitDangXuat.setPreferredSize(new Dimension(200, 30));
		jmnitThoat.setPreferredSize(new Dimension(200, 30));

		jmnitTrangChu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
		jmnitTimKiem.setMnemonic(KeyEvent.VK_F2);
		jmnitDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK));
		jmnitThoat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

		jmnitTrangChu.setToolTipText("Trang chủ");
		jmnitTimKiem.setToolTipText("Tìm kiếm");
		jmnitDangXuat.setToolTipText("Trợ giúp");
		jmnitThoat.setToolTipText("Thoát");

		jmnTimKiemNhanVienTheoMaNhanVien = new JMenuItem("Tìm kiếm nhân viên theo mã nhân viên");
		jmnTimKiemNhanVienTheoMaCongTrinh = new JMenuItem("Tìm kiếm nhân viên theo mã công trình");

		jmnitTimKiem.add(jmnTimKiemNhanVienTheoMaNhanVien);
		jmnitTimKiem.add(jmnTimKiemNhanVienTheoMaCongTrinh);

		jmnTimKiemNhanVienTheoMaNhanVien.setBackground(new Color(242, 167, 172, 255));
		jmnTimKiemNhanVienTheoMaCongTrinh.setBackground(new Color(254, 157, 185, 255));

		jmnTimKiemNhanVienTheoMaNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		jmnTimKiemNhanVienTheoMaCongTrinh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));

		jmnTimKiemNhanVienTheoMaNhanVien.setToolTipText("Tìm kiếm nhân viên theo mã nhân viên");
		jmnTimKiemNhanVienTheoMaCongTrinh.setToolTipText("Tìm kiếm nhân viên theo mã công trình");

		jMenuBar.add(jmnitTrangChu);
		jMenuBar.add(jmnitTimKiem);
		jMenuBar.add(jmnitDangXuat);
		jMenuBar.add(jmnitThoat);

		frame.setJMenuBar(jMenuBar);

		jmnitThoat.addActionListener(this);
		jmnitTrangChu.addActionListener(this);
		jmnTimKiemNhanVienTheoMaNhanVien.addActionListener(this);
		jmnTimKiemNhanVienTheoMaCongTrinh.addActionListener(this);
		jmnitDangXuat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(jmnitThoat)) {
			XuLyChuyenTrang.thoat();
		} else if (o.equals(jmnitTrangChu)) {
			XuLyChuyenTrang.openJFrame(jFrame, new TrangChu_GUI());
		} else if (o.equals(jmnTimKiemNhanVienTheoMaNhanVien))
			XuLyChuyenTrang.openJFrame(jFrame, new TimKiemTheoMaNhanVien());
		else if (o.equals(jmnTimKiemNhanVienTheoMaCongTrinh))
			XuLyChuyenTrang.openJFrame(jFrame, new TimKiemTheoCongTrinh());
		else if (o.equals(jmnitDangXuat))
			XuLyChuyenTrang.openJFrame(jFrame, new Login());

	}
}
