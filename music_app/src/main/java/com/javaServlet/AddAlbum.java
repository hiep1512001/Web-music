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
@WebServlet(urlPatterns={"/AddAlbum"})
public class AddAlbum extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public AddAlbum () {
    super();
}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        //String errorString = null;
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
//       Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/admin/addAlbum.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String name = (String) request.getParameter("AlbumName");
        String idSinger = (String) request.getParameter("NameSinger");
        String genreAlbum = (String) request.getParameter("GenreAlbum");
        Part part = request.getPart("ImageAlbum");
        Album album = new Album(name,idSinger,genreAlbum);
        if (!name.equals("")  ) {
            try {
                InputStream is = part.getInputStream();
                AlbumDAO.addAlbum(conn, album,is);
                response.sendRedirect(request.getContextPath() + "/GetAlbum");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }
        else {
            String errorString  = "Emty album name!"; 
            request.setAttribute("errorString", errorString);
            doGet(request, response);
        }
    }
}
