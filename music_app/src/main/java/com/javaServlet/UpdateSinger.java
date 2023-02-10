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
@WebServlet(urlPatterns={"/UpdateSinger"})
public class UpdateSinger extends HttpServlet{
    private static String id =null;
    private static final long serialVersionUID = 1L;
    public UpdateSinger() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        id  = (String) request.getParameter("id");

        Singer singer= null;

//        String errorString = nSull;

        try {
            singer = SingerDAO.findSinger(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
//            errorString = e.getMessage();
        }

        // Không có lỗi.
        // Sản phẩm không tồn tại để edit.
        // Redirect sang trang danh sách sản phẩm.
//        if (errorString != null && product == null) {
//            response.sendRedirect(request.getServletPath() + "/productList");
//            return;
//        }

        // Lưu thông tin vào request attribute trước khi forward sang views.
//        request.setAttribute("errorString", errorString);
        request.setAttribute("singer", singer);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/updateSinger.jsp");
        dispatcher.forward(request, response);

    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String name = (String) request.getParameter("SingerName");
        Part part = request.getPart("ImageSinger");
        Singer singer = new Singer(id, name);
        if (part != null || id!=null) {
            try {
                InputStream is = part.getInputStream();
                SingerDAO.updateSinger(conn, singer,is);
                id=null;
                response.sendRedirect(request.getContextPath() + "/GetSinger");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }     
    }
}
