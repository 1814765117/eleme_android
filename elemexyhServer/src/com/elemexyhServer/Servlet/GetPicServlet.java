package com.elemexyhServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetPicServlet
 */
@WebServlet("/GetPicServlet")
public class GetPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		// 获取客服端请求参数
		String reqMessage = request.getParameter("request");
		String restaurantname=request.getParameter("restaurantname");
		try {
			PrintWriter out = response.getWriter();
			if(reqMessage.equals("GET_PIC")){
				System.out.println("返回报文: " + ConnectDB.getPicPath(restaurantname) );
				out.write(ConnectDB.getPicPath(restaurantname));
				out.close();
			}else{
				System.out.println("获取图片失败");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		
	}

}
