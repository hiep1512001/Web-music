package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Album;
import com.javaDao.MyUtils;
import com.javaDao.AlbumDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/GetAlbum"})
@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class GetAlbum extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public GetAlbum() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = MyUtils.getStoredConnection(request);
        //String errorString = null;
        List<Album> list = null;
        try {
            list = AlbumDAO.queryAlbum(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            //errorString = e.getMessage();
        }
        //request.setAttribute("errorString", errorString);
        request.setAttribute("albumList", list);
//       Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/admin/albumManager.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
