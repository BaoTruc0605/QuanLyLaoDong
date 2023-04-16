package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
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
import DAO.CongTrinh_DAO;
import DAO.CongViec_DAO;
import DAO.NhanVien_DAO;
import DAO.PhanCong_DAO;
import DAO.PhongBan_DAO;
import connectDB.ConnectDB;
import entity.ChuyenMon;
import entity.CongTrinh;
import entity.CongViec;
import entity.ListNhanVien;
import entity.NhanVien;
import entity.PhanCong;
import entity.PhongBan;

public class PhanCong_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {
	private CongTrinh_DAO CongTrinh_DAO;
	private NhanVien_DAO NhanVien_DAO;
	private CongViec_DAO CongViec_DAO;
	private PhanCong_DAO PhanCong_DAO;
	private JTextField txtMa, txtNgayBD, txtNgayKT, txtGhiChu;
	JComboBox txtMaNV, txtMaCV, txtMaCT;
	private JButton btThem, btXoa, btSua, btLuu, btFirst, btLast, btNext, btPrevious, btXT;
	private DefaultTableModel model;
	private JTable table;
	private JPanel pnCenter;
	private int edit = 0;
	private int viTri = 0, TongSo;

	public PhanCong_GUI() {
		// TODO Auto-generated constructor stub
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		UI_JMenubar ui_JMenubar = new UI_JMenubar(this);
		setTitle("Thông tin phân công");
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		PhanCong_DAO = new PhanCong_DAO();
		CongTrinh_DAO = new CongTrinh_DAO();
		NhanVien_DAO = new NhanVien_DAO();
		CongViec_DAO = new CongViec_DAO();
		JLabel lblTieuDe = new JLabel("THÔNG TIN PHÂN CÔNG");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblTieuDe, BorderLayout.NORTH);

		Box b, b1, b2, b3, b4, b5;

		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();

		b.add(Box.createVerticalStrut(20));

		JLabel lbMa = new JLabel("Mã phân công: ");
		txtMa = new JTextField(20);
		JLabel lbMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JComboBox<String>();
		ArrayList<NhanVien> listNV = (ArrayList<NhanVien>) NhanVien_DAO.getAllNhanVien();
		for (NhanVien nhanVien : listNV) {
			txtMaNV.addItem(nhanVien.getMaNhanVien());
		}
		b1.add(lbMa);
		b1.add(txtMa);
		b1.add(lbMaNV);
		b1.add(txtMaNV);
		b.add(b1);
		b.add(Box.createVerticalStrut(10));

		JLabel lbMaCV = new JLabel("Mã công việc: ");
		txtMaCV = new JComboBox<String>();
		ArrayList<CongViec> listCV = (ArrayList<CongViec>) CongViec_DAO.getAllCongViec();
		for (CongViec congViec : listCV) {
			txtMaCV.addItem(congViec.getMaCongViec());
		}
		JLabel lbMaCT = new JLabel("Mã công trình: ");
		txtMaCT = new JComboBox<String>();
		ArrayList<CongTrinh> listCT = (ArrayList<CongTrinh>) CongTrinh_DAO.getAllCongTrinh();
		for (CongTrinh congTrinh : listCT) {
			txtMaCT.addItem(congTrinh.getMaCongTrinh());
		}
		
		b2.add(lbMaCV);
		b2.add(txtMaCV);
		b2.add(lbMaCT);
		b2.add(txtMaCT);
		b.add(b2);
		b.add(Box.createVerticalStrut(10));

		JLabel lbNgayBD = new JLabel("Ngày bắt đầu: ");
		txtNgayBD = new JTextField(20);
		b3.add(lbNgayBD);
		b3.add(txtNgayBD);
		b.add(b3);
		b.add(Box.createVerticalStrut(10));

		JLabel lbNgayKT = new JLabel("Ngày kết thúc: ");
		txtNgayKT = new JTextField(20);
		b4.add(lbNgayKT);
		b4.add(txtNgayKT);
		b.add(b4);
		b.add(Box.createVerticalStrut(10));

		JLabel lbGhiChu = new JLabel("Ghi chú: ");
		txtGhiChu = new JTextField(20);
		b5.add(lbGhiChu);
		b5.add(txtGhiChu);
		b.add(b5);
		b.add(Box.createVerticalStrut(10));

		pnCenter = new JPanel();
		pnCenter.add(b);
		JPanel pnbt = new JPanel();
		btFirst = new JButton("First");
		btLast = new JButton("Last ");
		btPrevious = new JButton("Previous");
		btNext = new JButton("Next");
		pnbt.add(btFirst);
		pnbt.add(btLast);
		pnbt.add(btNext);
		pnbt.add(btPrevious);
		b.add(pnbt);
		TaoBang();
		pnCenter.setBackground(new Color(230, 220, 229, 255));
		this.add(pnCenter, BorderLayout.CENTER);
		JPanel pnbutton = new JPanel();
		btThem = new JButton("Thêm");
		btXoa = new JButton("Xóa");
		btSua = new JButton("Sửa");
		btLuu = new JButton("Lưu");
		btXT = new JButton("Xóa trắng");

		pnbutton.add(btThem);
		pnbutton.add(btXoa);
		pnbutton.add(btSua);
		pnbutton.add(btLuu);
		pnbutton.add(btXT);
		pnbutton.setBackground(new Color(236, 195, 193, 255));
		this.add(pnbutton, BorderLayout.SOUTH);
		docDuLieuTuDatabaseVaoTable();
		khoaTextfields();
		capNhatViTri(viTri);
		duaPhanCongLenForm(getPhanCongTuTable(0));
		btLuu.setEnabled(false);
		btThem.addActionListener(this);
		btXoa.addActionListener(this);
		btSua.addActionListener(this);
		btLuu.addActionListener(this);
		btXT.addActionListener(this);
		btNext.addActionListener(this);
		btFirst.addActionListener(this);
		btPrevious.addActionListener(this);
		btLast.addActionListener(this);
		table.addMouseListener(this);
	}

	public void TaoBang() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã phân công");
		model.addColumn("Mã nhân viên");
		model.addColumn("Mã công việc");
		model.addColumn("Mã công trình");
		model.addColumn("Ngày bắt đầu");
		model.addColumn("Ngày hoàn thành");
		model.addColumn("Ghi chú");
		model.addColumn("Tổng ngày công");

		TableColumn colum = new TableColumn();
		colum.setPreferredWidth(100);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setPreferredSize(new Dimension(800, 250));
		pnCenter.add(sp);

	}

	public static void main(String[] args) {
		new PhanCong_GUI().setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btThem)) {
			edit = 1;
			if (btThem.getText().equalsIgnoreCase("Thêm")) {
				moKhoaTextfields();
				btLuu.setEnabled(true);
				btSua.setEnabled(false);
				btThem.setEnabled(true);
				btThem.setText("Hủy");
				btXoa.setEnabled(false);
			} else if (btThem.getText().equalsIgnoreCase("Hủy")) {
				khoaTextfields();
				btLuu.setEnabled(false);
				btThem.setText("Thêm");
				btSua.setEnabled(true);
				btXoa.setEnabled(true);
			}

		} else if (o.equals(btSua)) {
			edit = 2;
			if (btSua.getText().equalsIgnoreCase("Sửa")) {
				moKhoaTextfields();
				txtMa.setEditable(false);
				btLuu.setEnabled(true);
				btSua.setEnabled(true);
				btSua.setText("Hủy");
				btThem.setEnabled(false);
				btXoa.setEnabled(false);

			} else if (btSua.getText().equalsIgnoreCase("Hủy")) {
				khoaTextfields();
				btLuu.setEnabled(false);
				btThem.setEnabled(true);
				btSua.setText("Sửa");
				btXoa.setEnabled(true);
			}
		} else if (o.equals(btLuu)) {
			if (edit == 1) {
				them();
				khoaTextfields();
				btLuu.setEnabled(false);
				btThem.setText("Thêm");
				btSua.setEnabled(true);
				btThem.setEnabled(true);
				btXoa.setEnabled(true);
			} else if (edit == 2) {
				capNhat();
				khoaTextfields();
				btLuu.setEnabled(false);
				btSua.setText("Sửa");
				btSua.setEnabled(true);
				btThem.setEnabled(true);
				btXoa.setEnabled(true);
			}

		} else if (o.equals(btXoa)) {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phân công cần xóa", "Xóa nhân viên",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phân công này??",
						"Xóa phân công", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maPC = model.getValueAt(row, 0).toString();
					PhanCong_DAO.xoa(maPC);
					updateTable();
					if (table.getRowCount() > 0) {
						duaPhanCongLenForm(getPhanCongTuTable(0));
					}
					JOptionPane.showMessageDialog(null, String.format("Xóa phân công %s  thành công!", maPC),
							"Xóa phân công", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, String.format("Xóa phân công không thành công"),
							"Xóa phân công", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (o.equals(btXT)) {
			txtMa.setText("");
			txtNgayBD.setText("");
			txtNgayKT.setText("");
			txtGhiChu.setText("");
			txtMa.requestFocus();
		} else if (o.equals(btFirst)) {
			viTri = 0;
			capNhatViTri(viTri);
			duaPhanCongLenForm(getPhanCongTuTable(viTri));
		} else if (o.equals(btLast)) {
			viTri = table.getRowCount() - 1;
			capNhatViTri(viTri);
			duaPhanCongLenForm(getPhanCongTuTable(viTri));
		} else if (o.equals(btNext)) {
			if (viTri != table.getRowCount() - 1)
				viTri++;
			capNhatViTri(viTri);
			duaPhanCongLenForm(getPhanCongTuTable(viTri));
		} else if (o.equals(btPrevious)) {
			if (viTri != 0)
				viTri--;
			capNhatViTri(viTri);
			duaPhanCongLenForm(getPhanCongTuTable(viTri));
		}
	}

	private void capNhatViTri(int n) {
		table.setRowSelectionInterval(n, n);
		viTri = n;
		TongSo = table.getRowCount();
	}

	private void themPhanCongVaoTable(PhanCong pc) {
		model.addRow(new Object[] { pc.getMaPhanCong(), pc.getNhanVien().getMaNhanVien(),
				pc.getCongViec().getMaCongViec(), pc.getCongTrinh().getMaCongTrinh(), pc.getNgayBatDau(),
				pc.getNgayKetThuc(), pc.getGhiChu(), pc.getTongNgayCong() });
	}

	private void themPhanCongVaoTable(List<PhanCong> list) {
		list.forEach(phanCong -> themPhanCongVaoTable(phanCong));
	}

	private void docDuLieuTuDatabaseVaoTable() {
		List<PhanCong> listPC = PhanCong_DAO.getAllPhanCong();
		for (PhanCong phanCong : listPC) {
			themPhanCongVaoTable(phanCong);
		}

	}

	private void duaPhanCongLenForm(PhanCong pc) {
		txtMa.setText(pc.getMaPhanCong());
		txtMaNV.setSelectedItem(pc.getNhanVien().getMaNhanVien());
		txtMaCV.setSelectedItem(pc.getCongViec().getMaCongViec());
		txtMaCT.setSelectedItem(pc.getCongTrinh().getMaCongTrinh());
		txtNgayBD.setText(pc.getNgayBatDau().toString());
		txtNgayKT.setText(pc.getNgayKetThuc().toString());
		txtGhiChu.setText(pc.getGhiChu());
	}

	private PhanCong getPhanCongTuTable(int row) {
		String maPC = model.getValueAt(row, 0).toString();
		String maNV = model.getValueAt(row, 1).toString();
		String maCV = model.getValueAt(row, 2).toString();
		String maCT = model.getValueAt(row, 3).toString();
		LocalDate ngayBD = LocalDate.parse(model.getValueAt(row, 4).toString());
		LocalDate ngayKT = LocalDate.parse(model.getValueAt(row, 5).toString());
		String gc = model.getValueAt(row, 6).toString();
		int tnc = (int) model.getValueAt(row, 7);
		NhanVien nv = NhanVien_DAO.getNhanVienTheoMa(maNV);
		CongViec cv = CongViec_DAO.getCongViec(maCV);
		CongTrinh ct = CongTrinh_DAO.getCongTrinh(maCT);
		return new PhanCong(maPC, nv, cv, ct, ngayBD, ngayKT, gc, tnc);
	}

	private boolean kiemTraPCTonTaiTrongTable(String ma) {
		int i = table.getRowCount();
		String s = "";
		while (i > 0) {
			s = (String) model.getValueAt(i - 1, 0);
			if (ma.equals(s)) {
				return true;
			}
			i--;
		}
		return false;
	}

	public void khoaTextfields() {
		txtMa.setEditable(false);
		txtMaNV.setEditable(false);
		txtMaCV.setEditable(false);
		txtMaCT.setEditable(false);
		txtNgayBD.setEditable(false);
		txtNgayKT.setEditable(false);
		txtGhiChu.setEditable(false);
	}

	public void moKhoaTextfields() {
		txtMa.setEditable(true);
		txtMaNV.setEditable(true);
		txtMaCV.setEditable(true);
		txtMaCT.setEditable(true);
		txtNgayBD.setEditable(true);
		txtNgayKT.setEditable(true);
		txtGhiChu.setEditable(true);
	}

	public void updateTable() {
		model.setRowCount(0);
		docDuLieuTuDatabaseVaoTable();
	}

	public void them() {
		String sma = txtMa.getText();
		String maNV = (String) txtMaNV.getSelectedItem();
		String maCV = (String) txtMaCV.getSelectedItem();
		String maCT = (String) txtMaCT.getSelectedItem();
		String sngayBD = txtNgayBD.getText();
		String sngayKT = txtNgayKT.getText();
		String sGC = txtGhiChu.getText();
		NhanVien nv = NhanVien_DAO.getNhanVienTheoMa(maNV);
		CongViec cv = CongViec_DAO.getCongViec(maCV);
		CongTrinh ct = CongTrinh_DAO.getCongTrinh(maCT);
		if (!kiemTraRegex())
			return;
		else {
			if (!kiemTraPCTonTaiTrongTable(sma)) {
				PhanCong_DAO.them(
						new PhanCong(sma, nv, cv, ct, LocalDate.parse(sngayBD), LocalDate.parse(sngayKT), sGC, 0));
				updateTable();
				JOptionPane.showMessageDialog(this, "Đã thêm phân công");
			} else
				JOptionPane.showMessageDialog(this, "Mã phân công đã tồn tại");
		}
	}

	public void capNhat() {
		String sma = txtMa.getText();
		String maNV = (String) txtMaNV.getSelectedItem();
		String maCV = (String) txtMaCV.getSelectedItem();
		String maCT = (String) txtMaCT.getSelectedItem();
		String sngayBD = txtNgayBD.getText();
		String sngayKT = txtNgayKT.getText();
		String sGC = txtGhiChu.getText();
		NhanVien nv = NhanVien_DAO.getNhanVienTheoMa(maNV);
		CongViec cv = CongViec_DAO.getCongViec(maCV);
		CongTrinh ct = CongTrinh_DAO.getCongTrinh(maCT);
		if (!kiemTraRegex())
			return;
		else {
			PhanCong_DAO.sua(new PhanCong(sma, nv, cv, ct, LocalDate.parse(sngayBD), LocalDate.parse(sngayKT), sGC, 0));
			updateTable();
			JOptionPane.showMessageDialog(this, "Đã sửa phân công");
		}
	}

	public boolean kiemTraRegex() {
		if ((txtMa.getText()).trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phân công");
			txtMa.requestFocus();
			return false;
		} else if (!(txtMa.getText()).matches("^PC[a-zA-Z0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã PC là chuỗi tối thiểu 3 kí tự bắt đầu là 'PC'");
			txtMa.requestFocus();
			return false;
//		} else if ((txtMaNV.getText()).trim().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã NV");
//			txtMaNV.requestFocus();
//			return false;
//		} else if (!(txtMaNV.getText()).matches("^NV[a-zA-Z0-9]+$")) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã NV là chuỗi tối thiểu 3 kí tự bắt đầu là 'NV'");
//			txtMaNV.requestFocus();
//			return false;
//		} else if ((txtMaCV.getText().trim().isEmpty())) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công việc");
//			txtMaCV.requestFocus();
//			return false;
//		} else if (!(txtMaCV.getText()).matches("^CV\\d{3}")) {
//			JOptionPane.showMessageDialog(this,
//					"Vui lòng nhập mã CV theo đúng định dạng là 'CVXXX' trong đó X là số bất kì");
//			txtMaCV.requestFocus();
//			return false;
//		} else if ((txtMaCT.getText()).trim().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công trình");
//			txtMaCT.requestFocus();
//			return false;
//		} else if (!(txtMaCT.getText()).matches("^CT[a-zA-Z0-9]+$")) {
//			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã CT là chuỗi tối thiểu 3 kí tự bắt đầu là 'CT'");
//			txtMaCT.requestFocus();
//			return false;
		} else if (txtNgayBD.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày bắt đầu");
			txtNgayBD.requestFocus();
			return false;
		} else if (!txtNgayBD.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày bắt đầu theo định dạng yyyy-MM-dd");
			txtNgayBD.requestFocus();
			return false;
		} else if (txtNgayKT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày kết thúc");
			txtNgayKT.requestFocus();
			return false;
		} else if (!txtNgayKT.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày kết thúc theo định dạng yyyy-MM-dd");
			txtNgayKT.requestFocus();
			return false;
		} else if ((LocalDate.parse(txtNgayKT.getText().toString()))
				.isBefore(LocalDate.parse(txtNgayBD.getText().toString()))) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
			txtNgayKT.requestFocus();
			return false;
		}
		return true;

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
		// TODO Auto-generated method stub
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaPhanCongLenForm(getPhanCongTuTable(row));
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
