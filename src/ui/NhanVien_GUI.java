package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAO.ChuyenMon_DAO;
import DAO.NhanVien_DAO;
import DAO.PhongBan_DAO;
import connectDB.ConnectDB;
import entity.ChuyenMon;
import entity.ListNhanVien;
import entity.NhanVien;
import entity.PhongBan;
import ui.Login;

public class NhanVien_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {
	private JTextField txtMa, txtHo, txtTen, txtNgaySinh, txtDCThuongTru, txtDCTamTru, txtSDT;
	private JRadioButton rdnam, rdnu;
	private JComboBox cbpb, cbcm, cbcv;
	private JButton btSua, btLuu, btXoaTrang;
	private DefaultTableModel model;
	private JTable table;
	private JPanel pnCenter;
	private ChuyenMon_DAO ChuyenMon_DAO;
	private PhongBan_DAO PhongBan_DAO;
	private static NhanVien_DAO NhanVien_DAO;
	private String tenDangNhap;

	public NhanVien_GUI() {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		UI_JMenubar ui_JMenubar = new UI_JMenubar(this);

		// TODO Auto-generated constructor stub
		setTitle("Thông tin nhân viên");
		setSize(1030, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		ChuyenMon_DAO = new ChuyenMon_DAO();
		PhongBan_DAO = new PhongBan_DAO();
		NhanVien_DAO = new NhanVien_DAO();

		JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		JPanel pnNORTH = new JPanel();
		pnNORTH.add(lblTieuDe);
		this.add(pnNORTH, BorderLayout.NORTH);

		Box b, b1, b2, b3, b4, b5, b6, b7;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();
		b7 = Box.createHorizontalBox();

		b.add(Box.createVerticalStrut(20));
		JLabel lbMa = new JLabel("Mã nhân viên: ");
		txtMa = new JTextField(20);
		b1.add(lbMa);
		b1.add(txtMa);

		b.add(b1);
		b.add(Box.createVerticalStrut(10));

		JLabel lbHo = new JLabel("Họ nhân viên: ");
		txtHo = new JTextField(20);
		JLabel lbTen = new JLabel("Tên nhân viên: ");
		txtTen = new JTextField(20);
		b2.add(lbHo);
		b2.add(txtHo);
		b2.add(lbTen);
		b2.add(txtTen);
		b.add(b2);
		b.add(Box.createVerticalStrut(10));

		JLabel lbNgaySinh = new JLabel("Ngày sinh: ");
		txtNgaySinh = new JTextField(20);
		JLabel lbgt = new JLabel("Giới tính: ");
		rdnam = new JRadioButton("Nam", true);
		rdnam.setEnabled(true);
		rdnu = new JRadioButton("Nu");
		ButtonGroup rdgroup = new ButtonGroup();
		rdgroup.add(rdnam);
		rdgroup.add(rdnu);
		b3.add(lbNgaySinh);
		b3.add(txtNgaySinh);
		b3.add(lbgt);
		b3.add(rdnam);
		b3.add(rdnu);
		b.add(b3);
		b.add(Box.createVerticalStrut(10));

		JLabel lbDCThuongTru = new JLabel("Địa chỉ thường trú: ");
		txtDCThuongTru = new JTextField(20);
		b4.add(lbDCThuongTru);
		b4.add(txtDCThuongTru);
		b.add(b4);
		b.add(Box.createVerticalStrut(10));

		JLabel lbDCTamTru = new JLabel("Địa chỉ tạm trú: ");
		txtDCTamTru = new JTextField(20);
		b5.add(lbDCTamTru);
		b5.add(txtDCTamTru);
		b.add(b5);
		b.add(Box.createVerticalStrut(10));

		JLabel lbSDT = new JLabel("Số điện thoại: ");
		txtSDT = new JTextField(20);
		JLabel lbpb = new JLabel("Phòng ban: ");
		cbpb = new JComboBox<String>();
		ArrayList<PhongBan> listPB = (ArrayList<PhongBan>) PhongBan_DAO.getAllPhongBan();
		for (PhongBan phongBan : listPB) {
			cbpb.addItem(phongBan.getMaPB() + "-" + phongBan.getTenPB());
		}
		b6.add(lbSDT);
		b6.add(txtSDT);
		b6.add(lbpb);
		b6.add(cbpb);
		b.add(b6);
		b.add(Box.createVerticalStrut(10));

		JLabel lbChuyenMon = new JLabel("Chuyên môn: ");
		cbcm = new JComboBox<String>();
		ArrayList<ChuyenMon> listCM = (ArrayList<ChuyenMon>) ChuyenMon_DAO.getAllChuyenMon();
		for (ChuyenMon chuyenMon : listCM) {
			cbcm.addItem(chuyenMon.getMaChuyenMon() + "-" + chuyenMon.getTenChuyenMon());
		}
		JLabel lbChucVu = new JLabel("Chức vụ: ");
		cbcv = new JComboBox();
		cbcv.addItem("Trưởng phòng ban");
		cbcv.addItem("Nhân viên phòng dự án");
		cbcv.addItem("Nhân Viên");
		b7.add(lbChuyenMon);
		b7.add(cbcm);
		b7.add(lbChucVu);
		b7.add(cbcv);
		b.add(b7);
		b.add(Box.createVerticalStrut(10));

		pnCenter = new JPanel();
		pnCenter.add(b);

		TaoBang();
		this.add(pnCenter, BorderLayout.CENTER);
		JPanel pnbutton = new JPanel();
		btSua = new JButton("Sửa");
		btLuu = new JButton("Lưu");
		btXoaTrang = new JButton("Xóa trắng");
		pnbutton.add(btSua);
		pnbutton.add(btLuu);
		pnbutton.add(btXoaTrang);
		this.add(pnbutton, BorderLayout.SOUTH);
		pnNORTH.setBackground(new Color(241, 187, 247, 255));
		pnCenter.setBackground(new Color(230, 220, 229, 255));
		pnbutton.setBackground(new Color(236, 195, 193, 255));
		khoaTextfields();
		docDuLieuTuDatabaseVaoTable();
		duaNhanVienLenForm(getNhanVienTuTable(0));
		btLuu.setEnabled(false);
		btSua.addActionListener(this);
		btLuu.addActionListener(this);
		btXoaTrang.addActionListener(this);
		table.addMouseListener(this);
	}

	public void TaoBang() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã NV");
		model.addColumn("Họ");
		model.addColumn("Tên");
		model.addColumn("Ngày sinh");
		model.addColumn("Giới tính");
		model.addColumn("DC thường trú");
		model.addColumn("DC tạm trú");
		model.addColumn("Số điện thoại");
		model.addColumn("Chuyên môn");
		model.addColumn("Phòng ban");
		model.addColumn("Chức vụ");
		TableColumn gioitinh = table.getColumnModel().getColumn(4);
		TableColumn chuyenmon = table.getColumnModel().getColumn(8);
		JComboBox cbbcm = new JComboBox();
		ArrayList<ChuyenMon> listCM = (ArrayList<ChuyenMon>) ChuyenMon_DAO.getAllChuyenMon();
		for (ChuyenMon chuyenMon : listCM) {
			cbbcm.addItem(chuyenMon.getMaChuyenMon() + "-" + chuyenMon.getTenChuyenMon());
		}

		TableColumn phongban = table.getColumnModel().getColumn(9);
		JComboBox cbphb = new JComboBox();

		ArrayList<PhongBan> listPB = (ArrayList<PhongBan>) PhongBan_DAO.getAllPhongBan();
		for (PhongBan phongBan : listPB) {
			cbphb.addItem(phongBan.getMaPB() + "-" + phongBan.getTenPB());
		}

		TableColumn chucvu = table.getColumnModel().getColumn(10);
		JComboBox cbbcv = new JComboBox();
		cbbcv.addItem("Trưởng phòng ban");
		cbbcv.addItem("Nhân viên phòng dự án");
		cbbcv.addItem("Nhân viên");

		chuyenmon.setCellEditor(new DefaultCellEditor(cbbcm));
		phongban.setCellEditor(new DefaultCellEditor(cbphb));
		chucvu.setCellEditor(new DefaultCellEditor(cbbcv));
		TableColumn colum = new TableColumn();
		colum.setPreferredWidth(100);
		chuyenmon.setPreferredWidth(110);
		phongban.setPreferredWidth(130);
		gioitinh.setPreferredWidth(40);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setPreferredSize(new Dimension(1000, 250));
		pnCenter.add(sp);

	}

	private void themNhanVienVaoTable(NhanVien nv) {
		model.addRow(new Object[] { nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getNgaySinh(),
				nv.getGioiTinh() ? "Nam" : "Nữ", nv.getDiaChiThuongTru(), nv.getDiaChiTamTru(), nv.getSoDienThoai(),
				nv.getChuyenMon().getMaChuyenMon() + "-" + nv.getChuyenMon().getTenChuyenMon(),
				nv.getPhongBan().getMaPB() + "-" + nv.getPhongBan().getTenPB(), nv.getChucVu() });
	}

	private void docDuLieuTuDatabaseVaoTable() {
		NhanVien nv = NhanVien_DAO.getNhanVienTheoMa(Login.getTenDangNhap());
		themNhanVienVaoTable(nv);
	}

	public static void main(String[] args) {
		new NhanVien_GUI().setVisible(true);
	}

	public NhanVien_GUI(String tenDangNhap) throws HeadlessException {
		super();
		this.tenDangNhap = tenDangNhap;
	}

	public void khoaTextfields() {
		txtMa.setEditable(false);
		txtHo.setEditable(false);
		txtTen.setEditable(false);
		txtNgaySinh.setEditable(false);
		rdnu.setSelected(false);
		txtDCThuongTru.setEditable(false);
		txtDCTamTru.setEditable(false);
		txtSDT.setEditable(false);
		cbpb.setEditable(false);
		cbcm.setEditable(false);
		cbcv.setEditable(false);
	}

	public void moKhoaTextfields() {
		txtMa.setEditable(true);
		txtHo.setEditable(true);
		txtTen.setEditable(true);
		txtNgaySinh.setEditable(true);
		rdnu.setSelected(true);
		txtDCThuongTru.setEditable(true);
		txtDCTamTru.setEditable(true);
		txtSDT.setEditable(true);
		cbpb.setEditable(true);
		cbcm.setEditable(true);
		cbcv.setEditable(true);
	}

	public void capNhat() {
		String sma = txtMa.getText();
		String sho = txtHo.getText();
		String sten = txtTen.getText();
		String sNgaySinh = txtNgaySinh.getText();
		Boolean gioiTinh = rdnam.isSelected();
		String sdcthuongTru = txtDCThuongTru.getText();
		String sdcTamTru = txtDCTamTru.getText();
		String ssdt = txtSDT.getText();
		String scm[] = cbcm.getSelectedItem().toString().split("-");
		String spb[] = cbpb.getSelectedItem().toString().split("-");
		String scv = (String) cbcv.getSelectedItem();
		/**/
		if (!kiemTraRegex()) {
			return;
		} else {
			NhanVien_DAO.suaNV(new NhanVien(sma, sho, sten, LocalDate.parse(txtNgaySinh.getText()), gioiTinh,
					sdcthuongTru, sdcTamTru, ssdt, new ChuyenMon(scm[0], scm[1]), new PhongBan(spb[0], spb[1]), scv));
			model.setRowCount(0);
			docDuLieuTuDatabaseVaoTable();
			JOptionPane.showMessageDialog(this, "Đã sửa");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btSua)) {
			if (btSua.getText().equalsIgnoreCase("Sửa")) {
				moKhoaTextfields();
				txtMa.setEditable(false);
				btLuu.setEnabled(true);
				btSua.setEnabled(true);
				btSua.setText("Hủy");

			} else if (btSua.getText().equalsIgnoreCase("Hủy")) {
				khoaTextfields();
				btLuu.setEnabled(false);
				btSua.setText("Sửa");
			}
		} else if (o.equals(btLuu)) {
			capNhat();
			khoaTextfields();
			btLuu.setEnabled(false);
			btSua.setText("Sửa");
		} else if (o.equals(btXoaTrang)) {

			txtMa.setText("");
			txtHo.setText("");
			txtTen.setText("");
			txtNgaySinh.setText("");
			txtDCThuongTru.setText("");
			txtDCTamTru.setText("");
			txtSDT.setText("");
		}
	}

	public boolean kiemTraRegex() {
		if ((txtMa.getText()).trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã NV");
			txtMa.requestFocus();
			return false;
		} else if (!(txtMa.getText()).matches("^NV[a-zA-Z0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã NV là chuỗi tối thiểu 3 kí tự bắt đầu là 'NV'");
			txtMa.requestFocus();
			return false;
		} else if (txtHo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập họ");
			txtHo.requestFocus();
			return false;
		} else if (txtTen.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
			txtTen.requestFocus();
			return false;
		} else if (txtNgaySinh.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh");
			txtNgaySinh.requestFocus();
			return false;
		} else if (!txtNgaySinh.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh theo định dạng yyyy-MM-dd");
			txtNgaySinh.requestFocus();
			return false;
		} else if ((LocalDateTime.now().getYear() - LocalDate.parse(txtNgaySinh.getText().toString()).getYear()) < 18) {
			JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi");
			txtNgaySinh.requestFocus();
			return false;
		} else if (txtDCThuongTru.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ thường trú");
			txtDCThuongTru.requestFocus();
			return false;
		} else if (txtDCTamTru.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ tạm trú");
			txtDCTamTru.requestFocus();
			return false;
		} else if (txtSDT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
			txtSDT.requestFocus();
			return false;
		} else if (!(txtSDT.getText().matches("^0\\d{9}$"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại có 10 số và bắt đầu là 0");
			txtSDT.requestFocus();
			return false;
		}

		return true;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaNhanVienLenForm(getNhanVienTuTable(row));

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

	private void duaNhanVienLenForm(NhanVien nv) {
		txtMa.setText(nv.getMaNhanVien());
		txtHo.setText(nv.getHo());
		txtTen.setText(nv.getTen());
		txtNgaySinh.setText(nv.getNgaySinh().toString());
		if (nv.getGioiTinh())
			rdnam.setSelected(true);
		else
			rdnu.setSelected(true);
		txtDCThuongTru.setText(nv.getDiaChiThuongTru());
		txtDCTamTru.setText(nv.getDiaChiTamTru());
		txtSDT.setText(nv.getSoDienThoai());

		cbcm.setSelectedItem(
				String.format("%s-%s", nv.getChuyenMon().getMaChuyenMon(), nv.getChuyenMon().getTenChuyenMon()));
		cbpb.setSelectedItem(String.format("%s-%s", nv.getPhongBan().getMaPB(), nv.getPhongBan().getTenPB()));
		cbcv.setSelectedItem(nv.getChucVu());
	}

	private NhanVien getNhanVienTuTable(int row) {
		String maCongNhan = model.getValueAt(row, 0).toString();
		String ho = model.getValueAt(row, 1).toString();
		String ten = model.getValueAt(row, 2).toString();
		LocalDate ngaySinh = LocalDate.parse(model.getValueAt(row, 3).toString());
		boolean gioiTinh = model.getValueAt(row, 4).toString().equals("Nam");
		String diaChiThuongTru = model.getValueAt(row, 5).toString();
		String diaChiTamTru = model.getValueAt(row, 6).toString();
		String sdt = model.getValueAt(row, 7).toString();
		String chuyenMon[] = model.getValueAt(row, 8).toString().split("-");
		String phongBan[] = model.getValueAt(row, 9).toString().split("-");
		String chucVu = model.getValueAt(row, 10).toString();

		return new NhanVien(maCongNhan, ho, ten, ngaySinh, gioiTinh, diaChiThuongTru, diaChiTamTru, sdt,
				new ChuyenMon(chuyenMon[0], chuyenMon[1]), new PhongBan(phongBan[0], phongBan[1]), chucVu);
	}

}
