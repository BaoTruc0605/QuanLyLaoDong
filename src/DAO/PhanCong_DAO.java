package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CongTrinh;
import entity.CongViec;
import entity.NhanVien;
import entity.PhanCong;

public class PhanCong_DAO {
	private NhanVien_DAO nhanVien_DAO;
	private CongViec_DAO congViec_DAO;
	private CongTrinh_DAO congTrinh_DAO;

	public PhanCong_DAO() {
		nhanVien_DAO = new NhanVien_DAO();
		congViec_DAO = new CongViec_DAO();
		congTrinh_DAO = new CongTrinh_DAO();
	}

	private PhanCong getPhanCong(ResultSet resultSet) throws SQLException {
		String maPhanCong = resultSet.getString("MaPC");
		String maCongNhan = resultSet.getString("MaNV");
		String maCongViec = resultSet.getString("MaCongViec");
		String maCongTrinh = resultSet.getString("MaCT");
		LocalDate ngayBatDau = LocalDate.parse(resultSet.getDate("NgayBatDau").toString());
		LocalDate ngayKetThuc = LocalDate.parse(resultSet.getDate("NgayKetThuc").toString());
		String ghiChu = resultSet.getString("ghiChu");
		int soNC = Integer.parseInt(resultSet.getString("TongNgayCong"));
		NhanVien nv = nhanVien_DAO.getNhanVienTheoMa(maCongNhan);
		CongViec congViec = congViec_DAO.getCongViec(maCongViec);
		CongTrinh congTrinh = congTrinh_DAO.getCongTrinh(maCongTrinh);

		return new PhanCong(maPhanCong, nv, congViec, congTrinh, ngayBatDau, ngayKetThuc, ghiChu, soNC);
	}

	public List<PhanCong> getAllPhanCong() {
		List<PhanCong> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM PhanCong");
			while (resultSet.next())
				list.add(getPhanCong(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public boolean them(PhanCong phanCong) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT PhanCong VALUES (?, ?, ?, ?, ?, ?, ?,0)");
			preparedStatement.setString(1, phanCong.getMaPhanCong());
			preparedStatement.setString(2, phanCong.getNhanVien().getMaNhanVien());
			preparedStatement.setString(3, phanCong.getCongViec().getMaCongViec());
			preparedStatement.setString(4, phanCong.getCongTrinh().getMaCongTrinh());
			preparedStatement.setDate(5, Date.valueOf(phanCong.getNgayBatDau()));
			preparedStatement.setDate(6, Date.valueOf(phanCong.getNgayKetThuc()));
			preparedStatement.setString(7, phanCong.getGhiChu());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean sua(PhanCong phanCong) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE PhanCong SET MaNV = ?, MaCongViec = ?, MaCT = ?, NgayBatDau = ?, NgayKetThuc = ?, GhiChu = ? WHERE MaPC = ?");
			preparedStatement.setString(1, phanCong.getNhanVien().getMaNhanVien());
			preparedStatement.setString(2, phanCong.getCongViec().getMaCongViec());
			preparedStatement.setString(3, phanCong.getCongTrinh().getMaCongTrinh());
			preparedStatement.setDate(4, Date.valueOf(phanCong.getNgayBatDau()));
			preparedStatement.setDate(5, Date.valueOf(phanCong.getNgayKetThuc()));
			preparedStatement.setString(6, phanCong.getGhiChu());
			preparedStatement.setString(7, phanCong.getMaPhanCong());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean xoa(String maPhanCong) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("DELETE PhanCong WHERE MaPC = ?");
			preparedStatement.setString(1, maPhanCong);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}






}
