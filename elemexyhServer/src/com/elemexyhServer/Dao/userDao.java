package com.elemexyhServer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.elemexyhServer.Util.JDBCUtil;
import com.elemexyhServer.VO.user;

public class userDao {
	
	public user queryOneData(user u) {
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		user uq=new user();
		
		try {
			con=JDBCUtil.getConnection();
			pst=con.prepareStatement("select * from user where userName=? and userPwd=?");
			pst.setString(1,u.getUserName());
			pst.setString(2,u.getUserPwd());
			rs=pst.executeQuery();
			while(rs.next()) {
				uq.setUserName(rs.getString("userName"));
				uq.setUserPwd("userPwd");
			}					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pst,con);
		}
		
		return uq;
		
	}

	public int addUser(user u) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		int n=0;
		
		try {
			con=JDBCUtil.getConnection();
			pst=con.prepareStatement("insert into user values(?,?)");
			pst.setString(1,u.getUserName());
			pst.setString(2,u.getUserPwd());
			n=pst.executeUpdate();						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null, pst,con);
		}
		
		return n;
	}
}
