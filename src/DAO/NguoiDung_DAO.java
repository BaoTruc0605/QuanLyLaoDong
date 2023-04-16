package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import connectDB.ConnectDB;

public class NguoiDung_DAO {
	public int userLogin(String tenDangNhap, String matKhau) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int result = 0;
		 try(CallableStatement cstmt = con.prepareCall("{call dbo.login_app(?, ?, ?)}"); ) {  
		        cstmt.setString(1, tenDangNhap); 
		        cstmt.setString(2, matKhau);
		        cstmt.registerOutParameter(3, java.sql.Types.INTEGER);  
		        cstmt.execute();  
		        result = cstmt.getInt(3);
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	}
}	
