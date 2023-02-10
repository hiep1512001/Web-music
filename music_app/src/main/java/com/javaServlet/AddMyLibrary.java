package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Album;
import com.javaDTO.Genres;
import com.javaDTO.MyLibrary;
import com.javaDTO.Singer;
import com.javaDTO.Song;
import com.javaDao.AlbumDAO;
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
import jakarta.servlet.http.Part;
@WebServlet(urlPatterns={"/AddMyLibrary"})
public class AddMyLibrary extends HttpServlet{

    private static final long serialVersionUID = 1L;
    public AddMyLibrary () {
    super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String idSong = (String) request.getParameter("id");

        Song song=null;
        try {
            song = SongDAO.findSong(conn, idSong);
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
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String idSong = (String) request.getParameter("id");
        String idUser =(String) request.getSession(false).getAttribute("idUser");
        MyLibrary myLibrary= new MyLibrary(idUser,idSong);
        int kt=0;
        MyLibrary myLibrary2=null;
        try {
            myLibrary2= MyLibraryDAO.findMylibrary(conn, idUser, idSong);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if(myLibrary2==null) {
            kt=1;
        }
        if (kt==1) {
            try {
                MyLibraryDAO.addMyLibrary(conn,myLibrary);
                Song song=null;
                try {
                    song = SongDAO.findSong(conn, idSong);
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
                String errorString  = "Success!"; 
                request.setAttribute("errorString", errorString);
                request.setAttribute("song", song);
                request.setAttribute("singer", singer);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/user/playSongMember.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }           
        }
        else {
            Song song=null;
            try {
                song = SongDAO.findSong(conn, idSong);
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
            String errorString  = "The song has been added!"; 
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/user/playSongMember.jsp");
            dispatcher.forward(request, response);
        }
    }
}
