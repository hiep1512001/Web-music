package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Album;
import com.javaDTO.Singer;
import com.javaDao.AlbumDAO;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/HomeMember"})
public class HomeMember extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public HomeMember() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = MyUtils.getStoredConnection(request);
        //String errorString = null;
//        List<Singer> listSinger = null;
//        try {
//            listSinger = SingerDAO.querySinger(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            //errorString = e.getMessage();
//        }
        List<Album> listAlbum = null;
        try {
            listAlbum=AlbumDAO.queryAlbum(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0; i<listAlbum.size();i++) {
            Singer singer=null;
            try {
                singer=SingerDAO.findSinger(conn, listAlbum.get(i).getIdSinger());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(singer!=null) {
                listAlbum.get(i).setIdSinger(singer.getName());
            }
        }
//        List<SingerAlbum> list = new ArrayList<SingerAlbum>();
//        for(int i=0; i<listSinger.size(); i++) {
//            Album album= null;
//            try {
//                album=AlbumDAO.findAlbum(conn,listSinger.get(i).getId());
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            if(album!=null) {
//                SingerAlbum singerAlbum = new SingerAlbum(album.getIdSinger(),listSinger.get(i).getName(),album.getId(),album.getName());     
//                list.add(singerAlbum);
//            }
//        }
        //request.setAttribute("errorString", errorString);
        String userName =(String) request.getSession(false).getAttribute("Name");
        request.setAttribute("listAlbum", listAlbum);
        request.setAttribute("userName", userName);
        //request.setAttribute("errorString", errorString);
//       Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/user/homeMember.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
