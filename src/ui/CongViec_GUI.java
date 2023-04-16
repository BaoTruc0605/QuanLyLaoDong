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

import DAO.CongViec_DAO;
import connectDB.ConnectDB;
import entity.CongViec;

public class CongViec_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {
	private CongViec_DAO CongViec_DAO;
	private JTextField txtMa, txtTen;
	private JButton btThem, btXoa, btSua, btLuu, btFirst, btLast, btNext, btPrevious, btXT;
	private DefaultTableModel model;
	private JTable table;
	private JPanel pnCenter;
	private int edit = 0;
	private int viTri = 0, TongSo;

	public CongViec_GUI() {
		// TODO Auto-generated constructor stub
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		UI_JMenubar ui_JMenubar = new UI_JMenubar(this);
		setTitle("Thông tin công việc");
		setSize(850, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		CongViec_DAO = new CongViec_DAO();

		JLabel lblTieuDe = new JLabel("THÔNG TIN CÔNG VIỆC");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblTieuDe, BorderLayout.NORTH);

		Box b, b1, b2;

		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b.add(Box.createVerticalStrut(20));

		JLabel lbMa = new JLabel("Mã công việc: ");
		txtMa = new JTextField(20);
		b1.add(lbMa);
		b1.add(txtMa);

		b.add(b1);
		b.add(Box.createVerticalStrut(10));

		JLabel lbTen = new JLabel("Tên công việc: ");
		txtTen = new JTextField(20);
		b2.add(lbTen);
		b2.add(txtTen);
		b.add(b2);
		b.add(Box.createVerticalStrut(20));

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
		// pnCenter.add(pnbt,BorderLayout.CENTER);
		TaoBang();
		pnCenter.setBackground(new Color(230, 220, 229, 255));
		this.add(pnCenter, BorderLayout.CENTER);
		JPanel pnbutton = new JPanel();
		btThem = new JButton("Thêm");
		btXoa = new JButton("Xóa");
		btSua = new JButton("Sửa");
		btLuu = new JButton("Lưu");
		btXT = new JButton("Xóa trắng");
		pnCenter = new JPanel();
		pnbutton.add(btThem);
		pnbutton.add(btXoa);
		pnbutton.add(btSua);
		pnbutton.add(btLuu);
		pnbutton.add(btXT);
		pnbutton.setBackground(new Color(236, 195, 193, 255));
		this.add(pnbutton, BorderLayout.SOUTH);
		docDuLieuTuDatabaseVaoTable();
		khoaTextfields();
		duaCongViecLenForm(getCongViecTuTable(0));
		capNhatViTri(viTri);
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
		model.addColumn("Mã công việc");
		model.addColumn("Tên công việc");

		TableColumn cldd = new TableColumn(1);
		cldd.setPreferredWidth(150);
		TableColumn colum = new TableColumn();
		colum.setPreferredWidth(100);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setPreferredSize(new Dimension(800, 250));
		pnCenter.add(sp);

	}

	public static void main(String[] args) {
		new CongViec_GUI().setVisible(true);
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
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc cần xóa", "Xóa công việc",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa công việc này??",
						"Xóa công việc", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maCV = model.getValueAt(row, 0).toString();
					CongViec_DAO.xoaCongViec(maCV);
					updateTable();
					// model.removeRow(row);
					if (table.getRowCount() > 0) {
						duaCongViecLenForm(getCongViecTuTable(0));
					}
					JOptionPane.showMessageDialog(null, String.format("Xóa công việc %s  thành công!", maCV),
							"Xóa công việc", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, String.format("Xóa công việc không thành công"),
							"Xóa công việc", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (o.equals(btXT)) {
			txtMa.setText("");
			txtTen.setText("");
			txtMa.requestFocus();
		} else if (o.equals(btFirst)) {

			viTri = 0;
			capNhatViTri(viTri);
			duaCongViecLenForm(getCongViecTuTable(viTri));
		} else if (o.equals(btLast)) {
			viTri = table.getRowCount() - 1;
			capNhatViTri(viTri);
			duaCongViecLenForm(getCongViecTuTable(viTri));
		} else if (o.equals(btNext)) {
			if (viTri != table.getRowCount() - 1)
				viTri++;
			capNhatViTri(viTri);
			duaCongViecLenForm(getCongViecTuTable(viTri));
		} else if (o.equals(btPrevious)) {
			if (viTri != 0)
				viTri--;
			capNhatViTri(viTri);
			duaCongViecLenForm(getCongViecTuTable(viTri));
		}
	}

	private void capNhatViTri(int n) {
		table.setRowSelectionInterval(n, n);
		viTri = n;
		TongSo = table.getRowCount();
	}

	private void themCongViecVaoTable(CongViec cv) {
		model.addRow(new Object[] { cv.getMaCongViec(), cv.getTenCongViec() });
	}

	private void themCongViecVaoTable(List<CongViec> list) {
		list.forEach(congViec -> themCongViecVaoTable(congViec));
	}

	private void docDuLieuTuDatabaseVaoTable() {
		List<CongViec> listCV = CongViec_DAO.getAllCongViec();
		for (CongViec congViec : listCV) {
			themCongViecVaoTable(congViec);
		}
	}

	private void duaCongViecLenForm(CongViec cv) {
		txtMa.setText(cv.getMaCongViec());
		txtTen.setText(cv.getTenCongViec());
	}

	private CongViec getCongViecTuTable(int row) {
		String maCV = model.getValueAt(row, 0).toString();
		String tenCV = model.getValueAt(row, 1).toString();

		return new CongViec(maCV, tenCV);
	}

	private boolean kiemTraCVTonTaiTrongTable(String ma) {
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
	}

	public void moKhoaTextfields() {
		txtMa.setEditable(true);
		txtTen.setEditable(true);
	}

	public void updateTable() {
		model.setRowCount(0);
		docDuLieuTuDatabaseVaoTable();
	}

	public void them() {
		String sma = txtMa.getText();
		String sten = txtTen.getText();

		if (!kiemTraCuPhap())
			return;
		else {
			if (!kiemTraCVTonTaiTrongTable(sma)) {
				CongViec_DAO.themCongViec(new CongViec(sma, sten));
				updateTable();
				JOptionPane.showMessageDialog(this, "Đã thêm công việc");
			} else
				JOptionPane.showMessageDialog(this, "Mã công việc đã tồn tại");
		}
	}

	public void capNhat() {
		String sma = txtMa.getText();
		String sten = txtTen.getText();
 
		if (!kiemTraCuPhap())
			return;
		else {
			CongViec_DAO.suaCongViec(new CongViec(sma, sten));
			updateTable();
			JOptionPane.showMessageDialog(this, "Đã sửa công việc");
		}

	}

	public boolean kiemTraCuPhap() {
		if ((txtMa.getText().trim().isEmpty())) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công việc");
			txtMa.requestFocus();
			return false;
		} else if (!(txtMa.getText()).matches("^CV\\d{3}")) {
			JOptionPane.showMessageDialog(this,
					"Vui lòng nhập mã CV theo đúng định dạng là 'CVXXX' trong đó X là số bất kì");
			txtMa.requestFocus();
			return false;
		} else if ((txtTen.getText().trim().isEmpty())) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên công việc");
			txtTen.requestFocus();
			return false;
		} else if (!(txtTen.getText()).matches("[^!@#$%^&*]+")) {
			JOptionPane.showMessageDialog(this,
					"Vui lòng nhập mã CV theo đúng định dạng làì");
			txtMa.requestFocus();
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
			duaCongViecLenForm(getCongViecTuTable(row));
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
