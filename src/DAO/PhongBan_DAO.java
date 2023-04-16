package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.PhongBan;


public class PhongBan_DAO {
	
	private PhongBan getPhongBan(ResultSet resultSet) throws SQLException {
		String ma = resultSet.getString("MaPhongBan");
		String ten = resultSet.getString("TenPhongBan");
		return new PhongBan(ma, ten);
	}
	
	public List<PhongBan> getAllPhongBan() {
		List<PhongBan> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM PhongBan");
			while (resultSet.next())
				list.add(getPhongBan(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean xoaPhongBan(String maPhongBan) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("DELETE PhongBan WHERE MaPhongBan = ?");
			preparedStatement.setString(1, maPhongBan);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean themPhongBan(PhongBan phongBan) {
		int res = 0;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT PhongBan VALUES (?, ?)");
			preparedStatement.setString(1, phongBan.getMaPB());
			preparedStatement.setString(2, phongBan.getTenPB());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res > 0;
	}

	public boolean suaPhongBan(PhongBan phongBan) {
		int res = 0;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE PhongBan SET TenPhongBan = ? WHERE MaPhongBan = ?");
			preparedStatement.setString(1, phongBan.getMaPB());
			preparedStatement.setString(2, phongBan.getTenPB());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res > 0;
	}
	
	public String getTenPhongBan(String maPhongBan) {
		String res = null;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT TenPhongBan FROM PhongBan WHERE MaPhongBan = ?");
			preparedStatement.setString(1, maPhongBan);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				res = resultSet.getString(1);

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
}
