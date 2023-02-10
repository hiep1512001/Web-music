package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Song;
import com.javaDTO.Album;
import com.javaDTO.Genres;
import com.javaDTO.Singer;
import com.javaDao.SongDAO;
import com.javaDao.AlbumDAO;
import com.javaDao.GenresDAO;
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
@WebServlet(urlPatterns={"/AddSong"})
public class AddSong extends HttpServlet{

    private static final long serialVersionUID = 1L;
    public AddSong () {
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
        List<Album> listAlbum = null;
        try {
            listAlbum = AlbumDAO.queryAlbum(conn);
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
        request.setAttribute("albumList", listAlbum);
        request.setAttribute("genreList", listGenre);
        request.setAttribute("singerList", listSinger);
//       Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/admin/addSong.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String name = (String) request.getParameter("SongName");
        String idSinger = (String) request.getParameter("NameSinger");
        Part part = request.getPart("ImageSong");
        Part part1 = request.getPart("DataSong");
        String genreSong = (String) request.getParameter("NameGenre");
        String idAlbum = (String) request.getParameter("NameAlbum");
 
        Song song = new Song(name,idSinger,idAlbum,genreSong);

        
        if (!name.equals("") ) {
            try {
                InputStream is = part.getInputStream();
                InputStream is1 = part1.getInputStream();
                SongDAO.addSong(conn, song,is,is1);
                response.sendRedirect(request.getContextPath() + "/GetSong");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }
        else {
            String errorString  = "Emty song name!"; 
            request.setAttribute("errorString", errorString);
            doGet(request, response);
        }
    }
}
