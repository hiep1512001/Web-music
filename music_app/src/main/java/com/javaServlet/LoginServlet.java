/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaServlet;

import com.javaDao.LoginDAO;
import com.oracle.music_app.model.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import java.io.PrintWriter;

/**
 *
 * @author hoang
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        LoginDAO loginDao = new LoginDAO();

        try
        {
            User loginBean = loginDao.authenticateUser(email,password);


            switch (loginBean.getDecentralization()) {
                case 1:
                    {
                        System.out.println("Admin's Home");
                        HttpSession session = (HttpSession) request.getSession(); //Creating a session
                        session.setAttribute("Admin", email); //setting session attribute
                        request.setAttribute("userName", loginBean.getFull_name());
                        request.getRequestDispatcher("/admin/home_admin.jsp").forward(request, response);
                        break;
                    }
                case 0:
                    {
                        System.out.println("User's Home");
                        HttpSession session = (HttpSession) request.getSession();
                        session.setMaxInactiveInterval(10*60);
                        session.setAttribute("User", email);
                        request.setAttribute("userName", loginBean.getFull_name());
                        request.getRequestDispatcher("/admin/home_member.jsp").forward(request, response);
                        break;
                    }
                default:
                    request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
                    break;

            }
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e2)
        {
            e2.printStackTrace();
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
