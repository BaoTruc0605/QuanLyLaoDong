package DAO;

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
import entity.CongTrinh;


public class CongTrinh_DAO {
	

	private CongTrinh getCongTrinh(ResultSet resultSet) throws SQLException {
		String maCongTrinh = resultSet.getString("MaCT");
		String tenCongTrinh = resultSet.getString("TenCT");
		String diaDiem = resultSet.getString("DiaDiem");
		LocalDate ngayCapGP = LocalDate.parse(resultSet.getString("NgayCapGP"), DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate ngayKhoiCong = LocalDate.parse(resultSet.getString("NgayKhoiCong"), DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate ngayHT = LocalDate.parse(resultSet.getString("NgayHT"), DateTimeFormatter.ISO_LOCAL_DATE);
		return new CongTrinh(maCongTrinh, tenCongTrinh, diaDiem,  ngayCapGP, ngayKhoiCong, ngayHT);
	}

	public List<CongTrinh> getAllCongTrinh() {
		List<CongTrinh> list = new ArrayList<>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CongTrinh");
			while (resultSet.next())
				list.add(getCongTrinh(resultSet));

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean themCongTrinh(CongTrinh congTrinh) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT CongTrinh VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, congTrinh.getMaCongTrinh());
			preparedStatement.setString(2, congTrinh.getTenCongTrinh());
			preparedStatement.setString(3, congTrinh.getDiaDiem());
			preparedStatement.setDate(4, Date.valueOf(congTrinh.getNgayCapGP()));
			preparedStatement.setDate(5, Date.valueOf(congTrinh.getNgayKhoiCong()));
			preparedStatement.setDate(6, Date.valueOf(congTrinh.getNgayHT()));
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean suaCongTrinh(CongTrinh congTrinh) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE CongTrinh SET TenCT = ?, DiaDiem = ?, NgayCapGP = ?, NgayKhoiCong = ?, NgayHT = ? WHERE MaCT = ?");
			preparedStatement.setString(1, congTrinh.getTenCongTrinh());
			preparedStatement.setString(2, congTrinh.getDiaDiem());
			preparedStatement.setDate(3, Date.valueOf(congTrinh.getNgayCapGP()));
			preparedStatement.setDate(4, Date.valueOf(congTrinh.getNgayKhoiCong()));
			preparedStatement.setDate(5,Date.valueOf(congTrinh.getNgayHT()));
			preparedStatement.setString(6, congTrinh.getMaCongTrinh());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean xoaCongTrinh(String maCongTrinh) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("DELETE CongTrinh WHERE MaCT = ?");
			preparedStatement.setString(1, maCongTrinh);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public CongTrinh getCongTrinh(String maCongTrinh) {
		CongTrinh congTrinh = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM CongTrinh WHERE MaCT = ?");
			preparedStatement.setString(1, maCongTrinh);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				congTrinh = getCongTrinh(resultSet);
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return congTrinh;
	}


}
