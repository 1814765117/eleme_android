package com.elemexyhServer.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	/*使用静态代码块完成驱动的加载*/
	static {
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*提供连接的方法*/
	public static Connection getConnection() {
		System.out.println("进入jdbc");
		Connection con = null;
		try {
			//连接指定的MMySQL数据库，三个参数分别是：数据库地址、账号、密码
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elemexyhserver?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//关闭连接的方法
	public static void close(ResultSet rs, PreparedStatement pst, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (pst != null)
				pst.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 
}
