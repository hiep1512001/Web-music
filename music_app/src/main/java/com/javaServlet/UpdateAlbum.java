package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Album;
import com.javaDTO.Genres;
import com.javaDTO.Singer;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;
import com.javaDao.AlbumDAO;
import com.javaDao.GenresDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig(maxFileSize = 16177216)//1.5mb
@WebServlet(urlPatterns={"/UpdateAlbum"})
public class UpdateAlbum extends HttpServlet {
    private static String id =null;
    private static final long serialVersionUID = 1L;
    public UpdateAlbum() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        id  = (String) request.getParameter("id");

        Album album= null;

//        String errorString = nSull;

        try {
            album = AlbumDAO.findAlbum(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
//            errorString = e.getMessage();
        }
        List<Genres> listGenre = null;
        try {
            listGenre = GenresDAO.querygenres(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            //errorString = e.getMessage();
        }
        List<Singer> listSinger = null;
        try {
            listSinger = SingerDAO.querySinger(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            //errorString = e.getMessage();
        }
        //request.setAttribute("errorString", errorString);
        request.setAttribute("genreList", listGenre);
        request.setAttribute("singerList", listSinger);
        // Không có lỗi.
        // Sản phẩm không tồn tại để edit.
        // Redirect sang trang danh sách sản phẩm.
//        if (errorString != null && product == null) {
//            response.sendRedirect(request.getServletPath() + "/productList");
//            return;
//        }

        // Lưu thông tin vào request attribute trước khi forward sang views.
//        request.setAttribute("errorString", errorString);
        request.setAttribute("album", album);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/updateAlbum.jsp");
        dispatcher.forward(request, response);

    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String name = (String) request.getParameter("AlbumName");
        String idSinger = (String) request.getParameter("NameSinger");
        String genreAlbum = (String) request.getParameter("GenreAlbum");
        Part part = request.getPart("ImageAlbum");
        Album album = new Album(id,name,idSinger,genreAlbum);
        if (part != null || id!=null) {
            try {
                InputStream is = part.getInputStream();
                AlbumDAO.updateAlbum(conn, album,is);
                id=null;
                response.sendRedirect(request.getContextPath() + "/GetAlbum");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }     
    }
}
