package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.javaDTO.Album;
import com.javaDTO.Singer;
import com.javaDTO.Song;
import com.javaDao.AlbumDAO;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;
import com.javaDao.SongDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/PlaySongMember"})
public class PlaySongMember extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PlaySongMember() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String id  = (String) request.getParameter("id");
        Song song=null;
        try {
            song = SongDAO.findSong(conn, id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Album album= null;
        try {
            album=AlbumDAO.findAlbum(conn,song.getIdAlbum());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        song.setIdAlbum(album.getName());
        
        Singer singer=null;
        try {
            singer=SingerDAO.findSinger(conn, song.getIdSinger());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("song", song);
        request.setAttribute("singer", singer);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/user/playSongMember.jsp");
        dispatcher.forward(request, response);
//      RequestDispatcher dispatcher = request.getServletContext()
//      .getRequestDispatcher("/pages/test.jsp");
//dispatcher.forward(request, response);
    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
  
    }
}
