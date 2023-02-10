package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import oracle.security.o3logon.a;
@MultipartConfig(maxFileSize = 16177216)//1.5mb
@WebServlet(urlPatterns={"/UpdateSong"})
public class UpdateSong extends HttpServlet{
    private static String id =null;
    private static final long serialVersionUID = 1L;
    public UpdateSong() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        id  = (String) request.getParameter("id");

        Song song= null;

//        String errorString = nSull;

        try {
            song = SongDAO.findSong(conn, id);
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
        Album album=null;
        Genres genres=null;
        try {
            album=AlbumDAO.findAlbum(conn, song.getIdAlbum());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            genres=GenresDAO.findgenres(conn,song.getIdGenre());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        song.setIdAlbum(album.getName());
        song.setIdGenre(genres.getName());
        //request.setAttribute("errorString", errorString);
        request.setAttribute("albumList", listAlbum);
        request.setAttribute("genreList", listGenre);
        request.setAttribute("singerList", listSinger);
        request.setAttribute("song", song);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/updateSong.jsp");
        dispatcher.forward(request, response);

    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
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
 
        Song song = new Song(id,name,idSinger,idAlbum,genreSong);
        if (part != null ) {
            try {
                InputStream is = part.getInputStream();
                InputStream is1 = part1.getInputStream();
                SongDAO.updateSong(conn, song,is,is1);
                id=null;
                response.sendRedirect(request.getContextPath() + "/GetSong");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        }     
    }
}
