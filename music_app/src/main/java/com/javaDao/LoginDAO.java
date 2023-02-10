/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaDao;

import com.javaConnection.OracleConnection;
import com.oracle.music_app.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang
 */
public class LoginDAO {
    public User authenticateUser(String email, String password) throws ClassNotFoundException
{
    User loginBean = new User();
    loginBean.setEmail(email);
    loginBean.setPassword(password);
 
    Connection con = null;
    Statement statement = null;
    ResultSet resultSet = null;
 
    String emailDB = "";
    String passwordDB = "";
    String fullnameDB = "";
    int roleDB = 0;
        try {
            con = OracleConnection.getOracleConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select EMAIL, PASSWORD, NAME_USER, DECENTRALIZATION from users");
            while(resultSet.next())
            {
                emailDB = resultSet.getString("EMAIL");
                passwordDB = resultSet.getString("PASSWORD");
                roleDB = resultSet.getInt("DECENTRALIZATION");
                fullnameDB = resultSet.getString("NAME_USER");

                if(email.equals(emailDB) && password.equals(passwordDB) && roleDB == 1){
                    loginBean.setFull_name(fullnameDB);
                    loginBean.setDecentralization(1);
                }
                else if(email.equals(emailDB) && password.equals(passwordDB) && roleDB == 0){
                    loginBean.setFull_name(fullnameDB);
                    loginBean.setDecentralization(0);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return loginBean;
    }   
}
