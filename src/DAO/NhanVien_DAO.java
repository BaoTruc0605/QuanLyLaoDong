package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;
import entity.ChuyenMon;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_DAO {
	private ChuyenMon_DAO chuyenMon_DAO;
	private PhongBan_DAO phongBan_DAO;

	public NhanVien_DAO() {
		chuyenMon_DAO = new ChuyenMon_DAO();
		phongBan_DAO = new PhongBan_DAO();
	}

	private NhanVien getNhanVien(ResultSet rs) throws SQLException {
		NhanVien nv = new NhanVien();
		nv.setMaNhanVien(rs.getString("MaNV"));
		nv.setHo(rs.getString("HoNV"));
		nv.setTen(rs.getString("TenNV"));
		nv.setNgaySinh(LocalDate.parse(rs.getString("NgaySinh"), DateTimeFormatter.ISO_LOCAL_DATE));
		nv.setGioiTinh(rs.getBoolean("GioiTinh"));
		nv.setDiaChiThuongTru(rs.getString("DiaChiThuongTru"));
		nv.setDiaChiTamTru(rs.getString("DiaChiTamTru"));
		nv.setSoDienThoai(rs.getString("SoDienThoai"));
		String maChuyenMon = rs.getString("MaChuyenMon");
		nv.setChuyenMon(new ChuyenMon(maChuyenMon, chuyenMon_DAO.getTenChuyenMon(maChuyenMon)));
		String maPhongBan = rs.getString("MaPhongBan");
		nv.setPhongBan(new PhongBan(maPhongBan, phongBan_DAO.getTenPhongBan(maPhongBan)));
		nv.setChucVu(rs.getString("ChucVu"));
		return nv;
	}

	/* Lấy tất cả nhân viên */
	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM NhanVien";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next())
				listNV.add(getNhanVien(rs));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listNV;
	}

	public NhanVien getNhanVienTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM NhanVien where MaNV = ?");
			preparedStatement.setString(1, ma);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return getNhanVien(rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/* Thêm Nhân viên */
	public boolean themNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement psta = null;
		int n = 0;
		try {
			psta = con.prepareStatement("INSERT INTO NhanVien VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			psta.setString(1, nv.getMaNhanVien());
			psta.setString(2, nv.getHo());
			psta.setString(3, nv.getTen());
			psta.setDate(4, Date.valueOf(nv.getNgaySinh()));
			psta.setBoolean(5, nv.getGioiTinh());
			psta.setString(6, nv.getDiaChiThuongTru());
			psta.setString(7, nv.getDiaChiTamTru());
			psta.setString(8, nv.getSoDienThoai());
			psta.setString(9, nv.getChuyenMon().getMaChuyenMon());
			psta.setString(10, nv.getPhongBan().getMaPB());
			psta.setString(11, nv.getChucVu());
			n = psta.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				psta.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean xoaNV(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE NhanVien WHERE MaNV = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean suaNV(NhanVien nv) {
		int res = 0;
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(
					"UPDATE NhanVien SET HoNV = ?,TenNV = ?, NgaySinh =?, GioiTinh = ?, DiaChiThuongTru = ?, DiaChiTamTru = ?, SoDienThoai = ?,  MaChuyenMon = ?, MaPhongBan = ?, ChucVu = ? WHERE MaNV = ?");
			pre.setString(1, nv.getHo());
			pre.setString(2, nv.getTen());
			pre.setDate(3, Date.valueOf(nv.getNgaySinh()));
			pre.setBoolean(4, nv.getGioiTinh());
			pre.setString(5, nv.getDiaChiThuongTru());
			pre.setString(6, nv.getDiaChiTamTru());
			pre.setString(7, nv.getSoDienThoai());
			pre.setString(8, nv.getChuyenMon().getMaChuyenMon());
			pre.setString(9, nv.getPhongBan().getMaPB());
			pre.setString(10, nv.getChucVu());
			pre.setString(11, nv.getMaNhanVien());
			res = pre.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pre.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return res > 0;
	}

	public String getChucVu(String maNV) {
		String chucVu = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM NhanVien where MaNV = ?");
			preparedStatement.setString(1, maNV);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				chucVu = rs.getNString("ChucVu");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return chucVu;
	}

	// lấy danh sách nhân viên theo mã công trình
	public ArrayList<NhanVien> getNhanVienTheoMaCT(String maCT) {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try (CallableStatement cstmt = con.prepareCall("SELECT NhanVien.MaNV FROM   (CongTrinh INNER JOIN"
				+ "             PhanCong ON (CongTrinh.MaCT = PhanCong.MaCT)) INNER JOIN"
				+ "             NhanVien ON (PhanCong.MaNV = NhanVien.MaNV)"
				+ "			 where ? = CongTrinh.MaCT");) {
			cstmt.setString(1, maCT);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = getNhanVienTheoMa(rs.getString(1));
				list.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
