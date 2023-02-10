/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaServlet;

import com.javaDTO.Album;
import com.javaDTO.Singer;
import com.javaDao.AlbumDAO;
import com.javaDao.MyUtils;
import com.javaDao.RegisterDAO;
import com.javaDao.SingerDAO;
import com.javaDao.UserDAO;
import com.oracle.music_app.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        processRequest(request, response);
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
        Connection conn = MyUtils.getStoredConnection(request);
        String fullName = request.getParameter("fullname");
         String email = request.getParameter("email");
         String password = request.getParameter("password");
         
         User registerBean = new User();
        //Using Java Beans - An easiest way to play with group of related data
         registerBean.setFull_name(fullName);
         registerBean.setEmail(email);
         registerBean.setPassword(password); 
         
         RegisterDAO registerDao = new RegisterDAO();
         
        //The core Logic of the Registration application is present here. We are going to insert user data in to the database.
         com.javaDTO.User ktra= null;
         try {
            ktra=UserDAO.finduserUP(conn, email,password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         if(ktra==null) {
             String userRegistered = null;
             try {
                 userRegistered = registerDao.registerUser(registerBean);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
              
              if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
              {
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
                  com.javaDTO.User loginBean= null;
                  try {
                     loginBean=UserDAO.finduserUP(conn, email,password);
                 } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
                  System.out.println("User's Home");
                  HttpSession session = (HttpSession) request.getSession();
                  session.setMaxInactiveInterval(10*60);
                  session.setAttribute("idUser", loginBean.getId());
                  session.setAttribute("User", email);
                  session.setAttribute("Name", loginBean.getFull_name());
                  session.setAttribute("password", loginBean.getPassword());
                  session.setAttribute("account", loginBean.getAccount_name());
                  request.setAttribute("userName", loginBean.getFull_name());
                  request.setAttribute("listAlbum", listAlbum);
                  request.getRequestDispatcher("/admin/home_member.jsp").forward(request, response);
              }
              else   //On Failure, display a meaningful message to the User.
              {
                 request.getRequestDispatcher("/pages/ConfirmUser.jsp").forward(request, response);
              }
         }
         else {
             try ( PrintWriter out = response.getWriter()) {
                 /* TODO output your page here. You may use following sample code. */
                 out.println("<script type=\"text/javascript\">");
                     out.println("alert('Email already exist.');");
                         out.println("location='./pages/ConfirmUser.jsp';");
                    out.println("</script>");
             }
             request.getRequestDispatcher("/pages/ConfirmUser.jsp").forward(request, response);
         }

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
