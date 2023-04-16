package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.NhanVien_DAO;
import connectDB.ConnectDB;
import entity.NhanVien;
import utils.XuLyChuyenTrang;
import ui.Login;

public class UI_Navigation implements ActionListener {
	private int widthIconBtn = 20;
	private int heightIconBtn = 20;
	private JButton btnQuanLyThongTin;
	private JButton btnQuanLyNhanVien;
	private JButton btnQuanLyCongTrinh;
	private JButton btnPhanCongLaoDong;
	private JButton btnQuanLyCongViec;
	private JButton btnQuanLyChuyenMon;
	NhanVien_DAO dao_nv = new NhanVien_DAO();

	private JFrame frame;

	public UI_Navigation(JFrame frame) {
		this.frame = frame;
	}

	public int getHeightIconBtn() {
		return heightIconBtn;
	}

	public void setHeightIconBtn(int heightIconBtn) {
		this.heightIconBtn = heightIconBtn;
	}

	private int getMaxWidthBtn() {
		int width1 = Math.max((int) btnPhanCongLaoDong.getPreferredSize().getWidth(),
				(int) btnPhanCongLaoDong.getPreferredSize().getWidth());
		width1 = Math.max(width1, (int) btnPhanCongLaoDong.getPreferredSize().getWidth());
		width1 = Math.max(width1, (int) btnQuanLyNhanVien.getPreferredSize().getWidth());
		width1 = Math.max(width1, (int) btnQuanLyThongTin.getPreferredSize().getWidth());
		return width1;
	}

	public Component getUINavigation() {
		JPanel box = new JPanel(null);
		box.setBackground(new Color(230, 220, 229, 255));
		btnQuanLyThongTin = new JButton("Quản lý thông tin");
		btnQuanLyNhanVien = new JButton("Quản lý nhân viên");
		btnQuanLyCongTrinh = new JButton("Quản lý công trình");
		btnPhanCongLaoDong = new JButton("Phân công lao động");
		btnQuanLyCongViec = new JButton("Quản lý công việc");
		btnQuanLyChuyenMon = new JButton("Quản lý chuyên môn");

		int x = 15, y = 90;
		int width = getMaxWidthBtn() + 50;

		btnQuanLyThongTin.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyNhanVien.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyCongTrinh.setBounds(x, y, width, 30);
		y += 45;
		btnPhanCongLaoDong.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyCongViec.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyChuyenMon.setBounds(x, y, width, 30);
		y += 45;
		String maNV = Login.getTenDangNhap();
		JLabel nv = new JLabel("Mã nhân viên: " + maNV);
		JLabel lblChucVu = new JLabel("Chức vụ: " + dao_nv.getChucVu(maNV));

		nv.setBounds(x, y, width, 30);
		y += 45;
		lblChucVu.setBounds(x, y, width, 30);

		Color color = new Color(236, 195, 193, 255);

		btnQuanLyThongTin.setBackground(color);
		btnQuanLyNhanVien.setBackground(color);
		btnQuanLyCongTrinh.setBackground(color);
		btnPhanCongLaoDong.setBackground(color);
		btnQuanLyCongViec.setBackground(color);
		btnQuanLyChuyenMon.setBackground(color);
		btnQuanLyThongTin.setToolTipText("Quản lý thông tin");
		btnQuanLyNhanVien.setToolTipText("Quản lý nhân viên");
		btnQuanLyCongTrinh.setToolTipText("Quản lý công trình");
		btnPhanCongLaoDong.setToolTipText("Phân công lao động");
		btnQuanLyCongViec.setToolTipText("Quản lý công việc");
		btnQuanLyChuyenMon.setToolTipText("Quản lý chuyên môn");

		box.add(btnQuanLyThongTin);
		box.add(btnQuanLyNhanVien);
		box.add(btnQuanLyCongTrinh);
		box.add(btnPhanCongLaoDong);
		box.add(btnQuanLyCongViec);
		box.add(btnQuanLyChuyenMon);
		box.add(nv);
		box.add(lblChucVu);
		btnQuanLyThongTin.addActionListener(this);
		btnQuanLyNhanVien.addActionListener(this);
		btnQuanLyCongTrinh.addActionListener(this);
		btnPhanCongLaoDong.addActionListener(this);
		btnQuanLyCongViec.addActionListener(this);
		btnQuanLyChuyenMon.addActionListener(this);
		if (dao_nv.getChucVu(Login.getTenDangNhap()).equalsIgnoreCase("Nhân Viên")) {
			btnQuanLyNhanVien.setEnabled(false);
			btnQuanLyCongTrinh.setEnabled(false);
			btnPhanCongLaoDong.setEnabled(false);
			btnQuanLyCongViec.setEnabled(false);
			btnQuanLyChuyenMon.setEnabled(false);
		} else if (dao_nv.getChucVu(Login.getTenDangNhap()).equalsIgnoreCase("Nhân viên phòng dự án")) {
			btnQuanLyNhanVien.setEnabled(false);
			btnQuanLyCongTrinh.setEnabled(true);
			btnPhanCongLaoDong.setEnabled(false);
			btnQuanLyCongViec.setEnabled(true);
			btnQuanLyChuyenMon.setEnabled(true);
		} else if (dao_nv.getChucVu(Login.getTenDangNhap()).equalsIgnoreCase("Trưởng phòng ban")) {
			btnQuanLyNhanVien.setEnabled(true);
			btnQuanLyCongTrinh.setEnabled(false);
			btnPhanCongLaoDong.setEnabled(true);
			btnQuanLyCongViec.setEnabled(true);
			btnQuanLyChuyenMon.setEnabled(true);
		}

		return box;
	}

	public int getWidthNavigation() {
		return getMaxWidthBtn() + 90;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnQuanLyThongTin)) {
			XuLyChuyenTrang.openJFrame(frame, new NhanVien_GUI());
		} else if (o.equals(btnQuanLyNhanVien)) {
			XuLyChuyenTrang.openJFrame(frame, new QuanLy_GUI());
		} else if (o.equals(btnQuanLyCongTrinh))
			XuLyChuyenTrang.openJFrame(frame, new CongTrinh_GUI());
		else if (o.equals(btnPhanCongLaoDong))
			XuLyChuyenTrang.openJFrame(frame, new PhanCong_GUI());
		else if (o.equals(btnQuanLyChuyenMon))
			XuLyChuyenTrang.openJFrame(frame, new ChuyenMon_GUI());
		else if (o.equals(btnQuanLyCongViec))
			XuLyChuyenTrang.openJFrame(frame, new CongViec_GUI());

	}
}