package com.elemexyhServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elemexyhServer.VO.restaurantmenu;

/**
 * Servlet implementation class RestaurantMenuServlet
 */
@WebServlet("/RestaurantMenuServlet")
public class RestaurantMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		}
		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		
		String restaurantName=request.getParameter("restaurantName");
		System.out.println("userName的值为："+restaurantName);
		restaurantmenu rsmenu=new restaurantmenu();
		rsmenu.setRestaurantname(restaurantName);
		
		//......
	}

}
