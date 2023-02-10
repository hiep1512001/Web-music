/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaServlet;

import com.javaDao.LoginDAO;
import com.oracle.music_app.login.FB_Profile;
import com.oracle.music_app.login.GG_Profile;
import com.oracle.music_app.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang
 */
@WebServlet(name = "LoginWithApiServlet", urlPatterns = {"/LoginWithApiServlet"})
public class LoginWithApiServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        User user = new User(); 
        String access_token=(String)request.getParameter("access_token");
        String code = request.getParameter("code");
            
        if(access_token != null){
            user = new FB_Profile().call_me(access_token);
        }else if(code != null){
            user = new GG_Profile().call_me(code);
        }
        LoginDAO loginDao = new LoginDAO();
        try
        {
            if(loginDao.isExistUser(user)== true){
                switch (user.getDecentralization()) {
                    case 1:
                        {
                            System.out.println("Admin's Home");
                            HttpSession session = (HttpSession) request.getSession(); //Creating a session
                            session.setAttribute("Admin", user.getEmail()); //setting session attribute
                            request.setAttribute("userName", user.getFull_name());
                            request.getRequestDispatcher("/admin/home_admin.jsp").forward(request, response);
                            break;
                        }
                    case 0:
                        {
                            System.out.println("User's Home");
                            HttpSession session = (HttpSession) request.getSession();
                            session.setMaxInactiveInterval(10*60);
                            session.setAttribute("User", user.getEmail());
                            request.setAttribute("userName", user.getFull_name());
                            request.getRequestDispatcher("/admin/home_member.jsp").forward(request, response);
                            break;
                        }
                    default:
                        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
                        break;
                }
            }else{
                System.out.println("Register Page");
                HttpSession session = (HttpSession) request.getSession(); //Creating a session
                session.setAttribute("RegisterUser", user); //setting session attribute

                request.getRequestDispatcher("/pages/ConfirmUser.jsp").forward(request, response);
            }
        }
        catch (Exception e2)
        {
            e2.printStackTrace();
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginWithApiServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginWithApiServlet.class.getName()).log(Level.SEVERE, null, ex);
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
