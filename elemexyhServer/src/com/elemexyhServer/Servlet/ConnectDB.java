package com.elemexyhServer.Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	public final static String driver = "com.mysql.jdbc.Driver";
	public final static String url = "jdbc:mysql://localhost:3306/elemexyhserver";
	public final static String dbName = "root";
	public final static String dbPassword = "123456";
 
	/**
	 * 根据编号获取图片路径
	 * @param ID 编号
	 * @return 返回图片路径
	 */
	public static String getPicPath(String restaurantname){
		
		String result = "";
		String sql = "select restauranticonpath from restaurant where restaurantname = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 加载驱动
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbName, dbPassword);
			ps = conn.prepareStatement(sql);
			ps.setString(1, restaurantname);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				result = resultSet.getString("restauranticonpath");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();	
			}
		}
		
		return result;
	}
}
