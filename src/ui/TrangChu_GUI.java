package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import DAO.NhanVien_DAO;
import connectDB.ConnectDB;
import entity.NhanVien;
import utils.XuLyChuyenTrang;


public class TrangChu_GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrangChu_GUI() {
		
		this.setTitle("Quản lý lao động");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setIconImage(new ImageIcon("HinhAnh\\iconTrangChu4.gif").getImage());
		this.setResizable(false);

		UI_JMenubar ui_JMenubar = new UI_JMenubar(this);
		ui_JMenubar.getJmnitTrangChu().setEnabled(false);

		UI_Navigation ui_Navigation = new UI_Navigation(this);

		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		jSplitPane.setEnabled(false);
		jSplitPane.add(ui_Navigation.getUINavigation());
		this.add(jSplitPane);

		int widthNavigation = ui_Navigation.getWidthNavigation();
		jSplitPane.setDividerLocation(widthNavigation);
		jSplitPane.add(new JLabel(XuLyChuyenTrang.convertImageToIcon("imgs\\TrangChuXayDung.jpg",
				this.getWidth() - widthNavigation - jSplitPane.getDividerSize(), this.getHeight())));

		//this.addWindowListener(new WindowListener());

	}
	public static void main(String[] args) {
		new TrangChu_GUI().setVisible(true);
	}
}
