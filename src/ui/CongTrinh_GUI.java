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
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAO.CongTrinh_DAO;
import connectDB.ConnectDB;
import entity.CongTrinh;

public class CongTrinh_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {
	private CongTrinh_DAO CongTrinh_DAO;
	private JTextField txtMa, txtTen, txtDiaDiem, txtNgayCapGP, txtNgayKhoiCong, txtNgayHT;
	private JButton btThem, btXoa, btSua, btLuu, btFirst, btLast, btNext, btPrevious, btXoaTrang;
	private DefaultTableModel model;
	private JTable table;
	private JPanel pnCenter;
	private int edit = 0;
	private int viTri = 0, TongSo;

	public CongTrinh_GUI() {
		// TODO Auto-generated constructor stub
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		UI_JMenubar ui_JMenubar = new UI_JMenubar(this);
		setTitle("Thông tin công trình");
		setSize(850, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		CongTrinh_DAO = new CongTrinh_DAO();

		JLabel lblTieuDe = new JLabel("THÔNG TIN CÔNG TRÌNH");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblTieuDe, BorderLayout.NORTH);

		Box b, b1, b2, b3, b4, b5, b6;

		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b6 = Box.createHorizontalBox();

		b.add(Box.createVerticalStrut(20));
		JLabel lbMa = new JLabel("Mã công trình: ");
		txtMa = new JTextField(20);
		b1.add(lbMa);
		b1.add(txtMa);

		b.add(b1);
		b.add(Box.createVerticalStrut(10));

		JLabel lbTen = new JLabel("Tên công trình: ");
		txtTen = new JTextField(20);
		b2.add(lbTen);
		b2.add(txtTen);
		b.add(b2);
		b.add(Box.createVerticalStrut(10));

		JLabel lbDD = new JLabel("Địa điểm: ");
		txtDiaDiem = new JTextField(20);
		b3.add(lbDD);
		b3.add(txtDiaDiem);
		b.add(b3);
		b.add(Box.createVerticalStrut(10));

		JLabel lbNgayCapGP = new JLabel("Ngày cấp giấy phép: ");
		txtNgayCapGP = new JTextField(20);
		b4.add(lbNgayCapGP);
		b4.add(txtNgayCapGP);
		b.add(b4);
		b.add(Box.createVerticalStrut(10));

		JLabel lbNgayKC = new JLabel("Ngày khởi công: ");
		txtNgayKhoiCong = new JTextField(20);
		b5.add(lbNgayKC);
		b5.add(txtNgayKhoiCong);
		b.add(b5);
		b.add(Box.createVerticalStrut(10));

		JLabel lbNgayHT = new JLabel("Ngày hoàn thành: ");
		txtNgayHT = new JTextField(20);

		// cbpb = new JComboBox(s1);
		b6.add(lbNgayHT);
		b6.add(txtNgayHT);
		b.add(b6);
		b.add(Box.createVerticalStrut(10));
		pnCenter = new JPanel();
		pnCenter.add(b);
		JPanel pnbt = new JPanel();
		btFirst = new JButton("First");
		btLast = new JButton("Last ");
		btNext = new JButton("Next");
		btPrevious = new JButton("Previous");
		pnbt.add(btFirst);
		pnbt.add(btLast);
		pnbt.add(btPrevious);
		pnbt.add(btNext);

		b.add(pnbt);
		TaoBang();
		pnCenter.setBackground(new Color(230, 220, 229, 255));
		this.add(pnCenter, BorderLayout.CENTER);
		JPanel pnbutton = new JPanel();
		btThem = new JButton("Thêm");
		btXoa = new JButton("Xóa");
		btSua = new JButton("Sửa");
		btLuu = new JButton("Lưu");
		btXoaTrang = new JButton("Xóa trắng");
		pnCenter = new JPanel();
		pnbutton.add(btThem);
		pnbutton.add(btXoa);
		pnbutton.add(btSua);
		pnbutton.add(btLuu);
		pnbutton.add(btXoaTrang);

		pnbutton.setBackground(new Color(236, 195, 193, 255));
		// pnCenter.add(pnbutton);
		this.add(pnbutton, BorderLayout.SOUTH);
		docDuLieuTuDatabaseVaoTable();
		khoaTextfields();
		duaCongTrinhLenForm(getCongTrinhTuTable(0));
		capNhatViTri(viTri);
		btLuu.setEnabled(false);
		btThem.addActionListener(this);
		btXoa.addActionListener(this);
		btSua.addActionListener(this);
		btLuu.addActionListener(this);
		btXoaTrang.addActionListener(this);
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
		model.addColumn("Mã CT");
		model.addColumn("Tên CT");
		model.addColumn("Địa điểm");
		model.addColumn("Ngày cấp GP");
		model.addColumn("Ngày khởi công");
		model.addColumn("Ngày hoàn thành");

		TableColumn cldd = new TableColumn(2);
		cldd.setPreferredWidth(140);
		TableColumn colum = new TableColumn();
		colum.setPreferredWidth(100);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setPreferredSize(new Dimension(800, 250));
		pnCenter.add(sp);

	}

	public static void main(String[] args) {
		new CongTrinh_GUI().setVisible(true);
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
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình cần xóa", "Xóa công trình",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa công trình này??",
						"Xóa công trình", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maCT = model.getValueAt(row, 0).toString();
					CongTrinh_DAO.xoaCongTrinh(maCT);
					updateTable();
					// model.removeRow(row);
					if (table.getRowCount() > 0) {
						duaCongTrinhLenForm(getCongTrinhTuTable(0));
					}
					JOptionPane.showMessageDialog(null, String.format("Xóa công trình %s  thành công!", maCT),
							"Xóa công trình", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, String.format("Xóa công trình không thành công"),
							"Xóa công trình", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (o.equals(btXoaTrang)) {
			txtMa.setText("");
			txtTen.setText("");
			txtDiaDiem.setText("");
			txtNgayCapGP.setText("");
			txtNgayKhoiCong.setText("");
			txtNgayHT.setText("");
			txtMa.requestFocus();
		} else if (o.equals(btFirst)) {
			viTri = 0;
			capNhatViTri(viTri);
			duaCongTrinhLenForm(getCongTrinhTuTable(viTri));
		} else if (o.equals(btLast)) {
			viTri = table.getRowCount() - 1;
			capNhatViTri(viTri);
			duaCongTrinhLenForm(getCongTrinhTuTable(viTri));
		} else if (o.equals(btNext)) {
			if (viTri != table.getRowCount() - 1)
				viTri++;
			capNhatViTri(viTri);
			duaCongTrinhLenForm(getCongTrinhTuTable(viTri));
		} else if (o.equals(btPrevious)) {
			if (viTri != 0)
				viTri--;
			capNhatViTri(viTri);
			duaCongTrinhLenForm(getCongTrinhTuTable(viTri));
		}
	}

	private void capNhatViTri(int n) {
		table.setRowSelectionInterval(n, n);
		viTri = n;
		TongSo = table.getRowCount();
	}

	private void themCongTrinhVaoTable(CongTrinh ct) {
		model.addRow(new Object[] { ct.getMaCongTrinh(), ct.getTenCongTrinh(), ct.getDiaDiem(), ct.getNgayCapGP(),
				ct.getNgayKhoiCong(), ct.getNgayHT() });
	}

	private void themCongTrinhVaoTable(List<CongTrinh> list) {
		list.forEach(congTrinh -> themCongTrinhVaoTable(congTrinh));
	}

	private void docDuLieuTuDatabaseVaoTable() {
		List<CongTrinh> listCT = CongTrinh_DAO.getAllCongTrinh();
		for (CongTrinh congTrinh : listCT) {
			themCongTrinhVaoTable(congTrinh);
		}
	}

	private void duaCongTrinhLenForm(CongTrinh ct) {
		txtMa.setText(ct.getMaCongTrinh());
		txtTen.setText(ct.getTenCongTrinh());
		txtDiaDiem.setText(ct.getDiaDiem());
		txtNgayCapGP.setText(ct.getNgayCapGP().toString());
		txtNgayKhoiCong.setText(ct.getNgayKhoiCong().toString());
		txtNgayHT.setText(ct.getNgayHT().toString());
	}

	private CongTrinh getCongTrinhTuTable(int row) {
		String maCT = model.getValueAt(row, 0).toString();
		String tenCT = model.getValueAt(row, 1).toString();
		String diaDiem = model.getValueAt(row, 2).toString();
		LocalDate ngayCapGP = LocalDate.parse(model.getValueAt(row, 3).toString());
		LocalDate ngayKC = LocalDate.parse(model.getValueAt(row, 4).toString());
		LocalDate ngayHT = LocalDate.parse(model.getValueAt(row, 5).toString());

		return new CongTrinh(maCT, tenCT, diaDiem, ngayCapGP, ngayKC, ngayHT);
	}

	private boolean kiemTraCTTonTaiTrongTable(String ma) {
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
		txtTen.setEditable(false);
		txtDiaDiem.setEditable(false);
		txtNgayCapGP.setEditable(false);
		txtNgayKhoiCong.setEditable(false);
		txtNgayHT.setEditable(false);
	}

	public void moKhoaTextfields() {
		txtMa.setEditable(true);
		txtTen.setEditable(true);
		txtDiaDiem.setEditable(true);
		txtNgayCapGP.setEditable(true);
		txtNgayKhoiCong.setEditable(true);
		txtNgayHT.setEditable(true);
	}

	public void updateTable() {
		model.setRowCount(0);
		docDuLieuTuDatabaseVaoTable();
	}

	public void them() {
		String sma = txtMa.getText();
		String sten = txtTen.getText();
		String sdd = txtDiaDiem.getText();
		String sngayCapGP = txtNgayCapGP.getText();
		String sngayKC = txtNgayKhoiCong.getText();
		String sngayHT = txtNgayHT.getText();

		if (!kiemTraRegex())
			return;
		else {
			if (!kiemTraCTTonTaiTrongTable(sma)) {
				CongTrinh_DAO.themCongTrinh(new CongTrinh(sma, sten, sdd, LocalDate.parse(sngayCapGP),
						LocalDate.parse(sngayKC), LocalDate.parse(sngayHT)));
				updateTable();
				JOptionPane.showMessageDialog(this, "Đã thêm công trình");
			} else
				JOptionPane.showMessageDialog(this, "Mã công trình đã tồn tại");
		}
	}

	public void capNhat() {
		String sma = txtMa.getText();
		String sten = txtTen.getText();
		String sdd = txtDiaDiem.getText();
		String sngayCapGP = txtNgayCapGP.getText();
		String sngayKC = txtNgayKhoiCong.getText();
		String sngayHT = txtNgayHT.getText();

		if (!kiemTraRegex())
			return;
		else {
			CongTrinh_DAO.suaCongTrinh(new CongTrinh(sma, sten, sdd, LocalDate.parse(sngayCapGP),
					LocalDate.parse(sngayKC), LocalDate.parse(sngayHT)));
			updateTable();
			JOptionPane.showMessageDialog(this, "Đã sửa công trình");
		}

	}

	public boolean kiemTraRegex() {
		if ((txtMa.getText()).trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công trình");
			txtMa.requestFocus();
			return false;
		} else if (!(txtMa.getText()).matches("^CT[a-zA-Z0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã CT là chuỗi tối thiểu 3 kí tự bắt đầu là 'CT'");
			txtMa.requestFocus();
			return false;
		} else if (txtTen.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên công trình");
			txtTen.requestFocus();
			return false;
		} else if (txtDiaDiem.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập điạ điểm");
			txtDiaDiem.requestFocus();
			return false;
		} else if (txtNgayCapGP.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày cấp giấy phép");
			txtNgayCapGP.requestFocus();
			return false;
		} else if (!txtNgayCapGP.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày cấp giấy phép theo định dạng yyyy-MM-dd");
			txtNgayCapGP.requestFocus();
			return false;
		} else if (txtNgayKhoiCong.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày khởi công");
			txtNgayKhoiCong.requestFocus();
			return false;
		} else if (!txtNgayKhoiCong.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày khởi công theo định dạng yyyy-MM-dd");
			txtNgayKhoiCong.requestFocus();
			return false;
		} else if (txtNgayHT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hoàn thành");
			txtNgayHT.requestFocus();
			return false;
		} else if (!txtNgayHT.getText().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hoàn thành theo định dạng yyyy-MM-dd");
			txtNgayHT.requestFocus();
			return false;
		} else if ((LocalDate.parse(txtNgayHT.getText().toString()))
				.isBefore(LocalDate.parse(txtNgayKhoiCong.getText().toString()))) {
			JOptionPane.showMessageDialog(this, "Ngày hoàn thành phải sau ngày khởi công");
			txtNgayHT.requestFocus();
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
			duaCongTrinhLenForm(getCongTrinhTuTable(row));

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
