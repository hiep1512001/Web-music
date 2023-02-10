package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.Album;
import com.javaDTO.Genres;
import com.javaDTO.Singer;
import com.javaDTO.Song;
import com.javaDao.AlbumDAO;
import com.javaDao.GenresDAO;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;
import com.javaDao.SongDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/GetSongAlbum"})
public class GetSongAlbum extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public GetSongAlbum () {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = MyUtils.getStoredConnection(request);
        String id  = (String) request.getParameter("id");
        List<Song> listSong = null;
        try {
            listSong = SongDAO.findSongAlbum(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            //errorString = e.getMessage();
        }
        for (int i=0; i<listSong.size(); i++) {
            Singer singer= null;
          try {
              singer = SingerDAO.findSinger(conn,listSong.get(i).getIdSinger());
          } catch (SQLException e) {
              e.printStackTrace();
//              errorString = e.getMessage();
          }
          listSong.get(i).setIdSinger(singer.getName());
          Genres genres= null;
        try {
            genres = GenresDAO.findgenres(conn,listSong.get(i).getIdGenre());
        } catch (SQLException e) {
            e.printStackTrace();
//            errorString = e.getMessage();
        }
        listSong.get(i).setIdGenre(genres.getName());
        }
        Album album =null;
        try {
            album =AlbumDAO.findAlbum(conn, id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //request.setAttribute("errorString", errorString);
        request.setAttribute("songList", listSong);
        request.setAttribute("album", album);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/songAlbum.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
