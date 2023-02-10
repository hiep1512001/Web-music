/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaDao;

import com.javaConnection.OracleConnection;
import com.oracle.music_app.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class RegisterDAO {
    public String registerUser(User registerBean) throws ClassNotFoundException
     {
         String fullName = registerBean.getFull_name();
         String email = registerBean.getEmail();
         String password = registerBean.getPassword();
         
         Connection con = null;
         PreparedStatement preparedStatement = null;         
         try
         {
             con = OracleConnection.getOracleConnection();
             String query = "insert into users(ID_USER, NAME_USER, EMAIL, DECENTRALIZATION, PASSWORD) values (NULL,?,?,?,?)"; //Insert user details into the table 'USERS'
             preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
             preparedStatement.setString(1, fullName);
             preparedStatement.setString(2, email);
             preparedStatement.setString(3, "0");
             preparedStatement.setString(4, password);
             
             int i= preparedStatement.executeUpdate();
             
             if (i!=0)  //Just to ensure data has been inserted into the database
             return "SUCCESS"; 
         }
         catch(SQLException e)
         {
            e.printStackTrace();
         }       
         return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
     }
}
