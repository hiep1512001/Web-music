package com.javaDao;

import java.io.InputStream;
import java.lang.invoke.StringConcatFactory;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.User;

public class UserDAO {
    public static List<User> queryuser(Connection conn) throws SQLException {
        String sql = "select NAME_ACCOUNT, ID_USER, EMAIL, PASSWORD, NAME_USER, DECENTRALIZATION from users";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<User> list = new ArrayList<User>();
        while (rs.next()) {
            String id = rs.getString("id_user");
            String name = rs.getString("name_user");
            String account = rs.getString("name_account");
            String password = rs.getString("password");
            String email = rs.getString("email");
            User user = new User(id, name,account, password,email);
            list.add(user);
        }
        return list;
    }
//    public static void adduser(Connection conn, User user,InputStream is) throws SQLException {
//        String sql = "Insert into user(name_user,picture_user) values (?,?)";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//
//        pstm.setString(1, user.getFull_name());
//        pstm.setBlob(2, is);
//        pstm.executeUpdate();
//    }
////
    public static void updateUSer(Connection conn, User user) throws SQLException {
        String sql = "Update USERS set NAME_USER=?, PASSWORD=? where ID_USER=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, user.getFull_name());
        pstm.setString(2, user.getPassword());
        pstm.setString(3,user.getId() );
        pstm.executeUpdate();
    }
    public static User finduser(Connection conn, String id) throws SQLException {
        String sql = "select a.NAME_ACCOUNT, a.ID_USER, a.EMAIL, a.PASSWORD, a.NAME_USER, a.DECENTRALIZATION from users a where a.id_user=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String idUser = rs.getString("id_user");
            String name = rs.getString("name_user");
            String account = rs.getString("name_account");
            String password = rs.getString("password");
            String email = rs.getString("email");
            User user = new User(idUser, name,account, password,email);
            return user;
        }
        return null;
    }
    public static User finduserUP(Connection conn, String Email, String Password) throws SQLException {
        String sql = "select a.NAME_ACCOUNT, a.ID_USER, a.EMAIL, a.PASSWORD, a.NAME_USER, a.DECENTRALIZATION from users a where a.password=? and a.email=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(2, Email);
        pstm.setString(1, Password);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String idUser = rs.getString("id_user");
            String name = rs.getString("name_user");
            String account = rs.getString("name_account");
            String password = rs.getString("password");
            String email = rs.getString("email");
            User user = new User(idUser, name,account, password,email);
            return user;
        }
        return null;
    }
//    public static user finduserId(Connection conn, String name) throws SQLException {
//        String sql = "Select a.id_user, a.name_user, a.picture_user from user a where a.name_user=?";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, name);
//        ResultSet rs = pstm.executeQuery();
//
//        while (rs.next()) {
//            String  id= rs.getString("id_user");
//            user user = new user(id,name);
//            return user;
//        }
//        return null;
//    }
//    public static void deleteuser(Connection conn, String id) throws SQLException {
//        String sql = "Delete From user where id_user= ?";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//
//        pstm.setString(1, id);
//
//        pstm.executeUpdate();
//    }
}
