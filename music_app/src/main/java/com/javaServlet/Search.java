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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/Search"})
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public  Search() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/search.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Song>listSong=null;
        String name=null;
        name  = (String) request.getParameter("NameSearch");
        Connection conn = MyUtils.getStoredConnection(request);
        try {
            listSong=SongDAO.findSongName(conn, name);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0; i<listSong.size(); i++) {
            Singer singer=null;
            try {
                singer=SingerDAO.findSinger(conn, listSong.get(i).getIdSinger());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(singer!=null) {
                listSong.get(i).setIdSinger(singer.getName());
            }
            
        }
        request.setAttribute("listSong",listSong );
        request.setAttribute("name",name );
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/admin/search.jsp");
        dispatcher.forward(request, response);
    }  
}
