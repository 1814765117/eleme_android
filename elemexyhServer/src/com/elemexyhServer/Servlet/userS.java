package com.elemexyhServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elemexyhServer.Dao.userDao;
import com.elemexyhServer.VO.user;

/**
 * Servlet implementation class userS
 */
@WebServlet("/userS")
public class userS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response); 
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mark=request.getParameter("mark");
		System.out.println("mark的值为："+mark);
		switch(mark) {
		case "query":query(request,response);
					 break;
		case "add":add(request,response);
		 break;
		}
		 
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		
		String userName=request.getParameter("userName");
		System.out.println("userName的值为："+userName);
		String userPwd=request.getParameter("userPwd");
		System.out.println("userPwd的值为："+userPwd);
		user u=new user();
		u.setUserName(userName);
		u.setUserPwd(userPwd);
		
		userDao uDao=new userDao();
		int n=uDao.addUser(u);
		if(n>0) {
			user uq=uDao.queryOneData(u);
			String userNameq=uq.getUserName();
			System.out.println("userName的值："+userNameq);
			if(userNameq!=null) {
				writer.println("OK"+"&");								
				writer.println(userNameq);
			}
			else {
				writer.println("Wrong");
			}						 						 
		}
		else {
			writer.println("Wrong");
		}
				 										
		writer.flush();
		writer.close();
		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		
		//和javaweb的表单post请求类似，这里post请求体中的参数也是通过request.getParameter获得
		String userName=request.getParameter("userName");
		System.out.println("userName的值为："+userName);
		String userPwd=request.getParameter("userPwd");
		System.out.println("userPwd的值为："+userPwd);
		user u=new user();
		u.setUserName(userName);
		u.setUserPwd(userPwd);
		
		userDao uDao=new userDao();
		user uq=uDao.queryOneData(u);
		System.out.println("查询结束");
		System.out.println("userName的值："+uq.getUserName());
		String userNameq=uq.getUserName(); 						//userDao中uq.getUserName()要么有值要么为null
		/*if(uq.getUserName()!=null) {
			writer.println("OK");
		}
		else {
			writer.println("Wrong");
		}*/
		if(uq.getUserName()!=null) {
			writer.println("OK"+"&");
			writer.println(userNameq);
		}
		else {
			writer.println("Wrong");
		}
		
		writer.flush();
		writer.close();
		
	}
	
	
}
