package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import com.javaDTO.Genres;
import com.javaDTO.Singer;
import com.javaDTO.Song;

import com.javaDao.GenresDAO;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;
import com.javaDao.SongDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/GetSong"})
@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class GetSong extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GetSong() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = MyUtils.getStoredConnection(request);
        //String errorString = null;
        List<Song> listSong = null;
        try {
            listSong = SongDAO.querySong(conn);
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
        //request.setAttribute("errorString", errorString);
        request.setAttribute("songList", listSong);
//       Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/admin/songManager.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
