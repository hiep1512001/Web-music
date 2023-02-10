package com.javaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaDTO.Singer;
import com.javaDTO.User;
import com.javaDao.MyUtils;
import com.javaDao.SingerDAO;
import com.javaDao.UserDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/GetInfoUser"})
public class GetInfoUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GetInfoUser() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        Connection conn = MyUtils.getStoredConnection(request);
        String idUser =(String) request.getSession(false).getAttribute("idUser");
        User user=null;
        try {
            user=UserDAO.finduser(conn, idUser);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //String errorString = null;

        //request.setAttribute("errorString", errorString);
        request.setAttribute("id", idUser);
        request.setAttribute("name", user.getFull_name());
        request.setAttribute("account", user.getAccount_name());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("email", user.getEmail());
//       Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/user/getInfoUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
