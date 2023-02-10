package com.javaServlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.javaDTO.User;
import com.javaDao.MyUtils;
import com.javaDao.UserDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns={"/UpdateUser"})
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UpdateUser() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String idUser  = (String) request.getParameter("id");
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
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/user/updateUser.jsp");
        dispatcher.forward(request, response);

    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String id =(String) request.getSession(false).getAttribute("idUser");
        String name = (String) request.getParameter("name");
        String password = (String) request.getParameter("password");
        User user= new User(id, name, password);
        if (!name.equals("") && !password.equals("")) {
            try {
                UserDAO.updateUSer(conn, user);
                response.sendRedirect(request.getContextPath() + "/GetInfoUser");
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
        } 
        else {
            String errorString  = "Emty name or password!"; 
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/user/getInfoUser.jsp");
            dispatcher.forward(request, response);
        }
    }
}
