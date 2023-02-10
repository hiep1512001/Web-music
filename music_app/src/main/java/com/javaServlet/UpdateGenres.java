package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.javaDTO.Genres;
import com.javaDao.GenresDAO;
import com.javaDao.MyUtils;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet(urlPatterns={"/UpdateGenres"})
public class UpdateGenres extends HttpServlet {
    private static String id =null;
    private static final long serialVersionUID = 1L;
    public UpdateGenres() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        id  = (String) request.getParameter("id");

        Genres genres= null;

//        String errorString = nSull;

        try {
            genres = GenresDAO.findgenres(conn, id);
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
        request.setAttribute("genres", genres);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/updateGenres.jsp");
        dispatcher.forward(request, response);

    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String name = (String) request.getParameter("GenresName");
        Genres genres = new Genres(id, name);
        if (id!=null) {
            try {
                GenresDAO.updategenres(conn, genres);
                id=null;
                response.sendRedirect(request.getContextPath() + "/GetGenres");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }     
    }
}
