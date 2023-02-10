package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.javaDTO.Song;
import com.javaDao.MyUtils;
import com.javaDao.SongDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = { "/DeleteSongAlbum" })
public class DeleteSongAlbum extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteSongAlbum() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String id = (String) request.getParameter("id");
//
//        String errorString = null;
        Song song=null;
        try {
            song=SongDAO.findSong(conn, id);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            SongDAO.deleteSong(conn, id);
            response.sendRedirect(request.getContextPath() + "/GetSongAlbum?id="+song.getIdAlbum());
        } catch (SQLException e) {
            e.printStackTrace();
//            errorString = e.getMessage();
        } 
        
//        // Nếu có lỗi, forward (chuyển tiếp) sang trang thông báo lỗi.
//        if (errorString != null) {
//            // Lưu thông tin vào request attribute trước khi forward sang views.
////            request.setAttribute("errorString", errorString);
//            // 
//            RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
//            dispatcher.forward(request, response);
//        }
//        // Nếu mọi thứ tốt đẹp.
//        // Redirect (chuyển hướng) sang trang danh sách sản phẩm.
//        else {
//            response.sendRedirect(request.getContextPath() + "/productList");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
