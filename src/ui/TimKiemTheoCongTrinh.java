package ui;

import java.awt.BorderLayout;
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
import java.util.ArrayList;

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

public class TimKiemTheoCongTrinh extends JFrame implements ActionListener, MouseListener, WindowListener {

	private JTextField txtTimKiem;
	private JButton  btTim;
	private DefaultTableModel model;
	private JTable table;
	private JPanel pnCenter,pnSouth;
	private ListNhanVien dsNV = new ListNhanVien();
	private static NhanVien_DAO NhanVien_DAO;
	private String tenDangNhap;

	public TimKiemTheoCongTrinh() {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		UI_JMenubar ui_JMenubar = new UI_JMenubar(this);

		// TODO Auto-generated constructor stub
		setTitle("Thông tin nhân viên");
		setSize(1030, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		NhanVien_DAO = new NhanVien_DAO();

		JLabel lblTieuDe = new JLabel("TÌM NHÂN VIÊN THEO MÃ CÔNG TRÌNH");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(lblTieuDe, BorderLayout.NORTH);


		

		pnCenter = new JPanel();
		TaoBang();
		this.add(pnCenter, BorderLayout.CENTER);
		pnCenter = new JPanel();
		pnSouth = new JPanel();
		txtTimKiem = new JTextField(20);
		txtTimKiem.requestFocus();
		btTim = new JButton("Tìm theo mã công trình");
		pnSouth.add(txtTimKiem);
		pnSouth.add(btTim);
		this.add(pnSouth, BorderLayout.SOUTH);
		btTim.addActionListener(this);
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
		cbbcm.addItem("CMDM-Đào móng");
		cbbcm.addItem("CMLN-Lót nền");
		cbbcm.addItem("CMST-Sơn tường");
		cbbcm.addItem("CMLG-Lát gạch");
		cbbcm.addItem("CMLK-Lắp kính");
		cbbcm.addItem("CMTK-Thiết kế");
		cbbcm.addItem("CMLD-Lắp điện");
		cbbcm.addItem("CMDV-Đi vôi");

		TableColumn phongban = table.getColumnModel().getColumn(9);
		JComboBox cbphb = new JComboBox();
		cbphb.addItem("PBKT-Phòng kế toán");
		cbphb.addItem("PBKD-Phòng kinh doanh");
		cbphb.addItem("PBKTh-Phòng kĩ thuật");
		cbphb.addItem("PBTC-Phòng tổ chức");
		cbphb.addItem("PBDA-Phòng dự án");
		cbphb.addItem("PBCM-Phòng chuyên môn");
		cbphb.addItem("PBPV-Phòng phục vụ");
		cbphb.addItem("Khác");

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
		sp.setPreferredSize(new Dimension(1000, 450));
		pnCenter.add(sp);

	}

	public static void main(String[] args) {
		new NhanVien_GUI().setVisible(true);
	}

	public TimKiemTheoCongTrinh(String tenDangNhap) throws HeadlessException {
		super();
		this.tenDangNhap = tenDangNhap;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btTim)) {
			if(txtTimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công trình cần tìm");
			}
			else {
				String maCT = txtTimKiem.getText().trim();
				ArrayList<NhanVien> list =	NhanVien_DAO.getNhanVienTheoMaCT(maCT);
				if(list.size() == 0) {
					JOptionPane.showMessageDialog(this, "Không có nhân viên từ mã công trình này");
				}
				else {
					model.setRowCount(0);
					for (NhanVien nv : list) {
						model.addRow(new Object[] { nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getNgaySinh(),
								nv.getGioiTinh() ? "Nam" : "Nữ", nv.getDiaChiThuongTru(), nv.getDiaChiTamTru(), nv.getSoDienThoai(),
								nv.getChuyenMon().getMaChuyenMon() + "-" + nv.getChuyenMon().getTenChuyenMon(),
								nv.getPhongBan().getMaPB() + "-" + nv.getPhongBan().getTenPB(), nv.getChucVu()});
					}
				}
			}
		}
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
		// TODO Auto-generated method stub
		
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
