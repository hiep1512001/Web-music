package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.javaDTO.Singer;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig(maxFileSize = 16177216)//1.5mb
@WebServlet(urlPatterns={"/AddSinger"})
public class AddSinger extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public AddSinger() {
	super();
}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/admin/addSinger.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		String name = (String) request.getParameter("SingerName");
		Part part = request.getPart("ImageSinger");
		Singer singer = new Singer(name);
        if (part != null ) {
            try {
                InputStream is = part.getInputStream();
    			SingerDAO.insertProduct(conn, singer,is);
            } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            }
        }
	}
}
