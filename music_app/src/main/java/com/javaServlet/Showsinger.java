package com.javaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Singer;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/ShowSinger"})
public class ShowSinger extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ShowSinger() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Connection conn = MyUtils.getStoredConnection(request);
		//String errorString = null;
		List<Singer> list = null;
		try {
			list = SingerDAO.querySinger(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			//errorString = e.getMessage();
		}
		//request.setAttribute("errorString", errorString);
		request.setAttribute("singerList", list);
//		 Forward sang /WEB-INF/views/productListView.jsp
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/pages/test.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
