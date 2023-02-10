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
import com.javaDao.MyLibraryDAO;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;
import com.javaDao.SongDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/MyLibrary"})
public class MyLibrary extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public MyLibrary() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = MyUtils.getStoredConnection(request);
        //String errorString = null;
        String idUser =(String) request.getSession(false).getAttribute("idUser");
        List<String>listIdSong=null;
        try {
            listIdSong=MyLibraryDAO.findSong(conn, idUser);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        List<Song> listSong =new ArrayList<>();
        for(int i=0; i<listIdSong.size(); i++) {
            Song song=null;
            try {
                song=SongDAO.findSong(conn, listIdSong.get(i));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(song!=null) {
                listSong.add(song);
            }
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
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/user/myLibrary.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
