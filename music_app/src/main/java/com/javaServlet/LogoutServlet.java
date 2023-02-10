/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaServlet;

import jakarta.servlet.http.HttpSession;
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

/**
 *
 * @author hoang
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession(false); //Fetch session object
 
        if(session!=null) //If session is not null
        {
            session.invalidate(); 
            Connection conn = MyUtils.getStoredConnection(request);
            //String errorString = null;
//            List<Singer> listSinger = null;
//            try {
//                listSinger = SingerDAO.querySinger(conn);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                //errorString = e.getMessage();
//            }
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
//            List<SingerAlbum> list = new ArrayList<SingerAlbum>();
//            for(int i=0; i<listSinger.size(); i++) {
//                Album album= null;
//                try {
//                    album=AlbumDAO.findAlbum(conn,listSinger.get(i).getId());
//                } catch (SQLException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                if(album!=null) {
//                    SingerAlbum singerAlbum = new SingerAlbum(album.getIdSinger(),listSinger.get(i).getName(),album.getId(),album.getName());     
//                    list.add(singerAlbum);
//                }
//            }
            //removes all session attributes bound to the session
            request.setAttribute("errMessage", "You have logged out successfully");
            request.setAttribute("listAlbum", listAlbum);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
            System.out.println("Logged out");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
