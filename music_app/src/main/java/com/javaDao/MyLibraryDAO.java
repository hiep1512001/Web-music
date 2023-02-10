package com.javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.MyLibrary;

public class MyLibraryDAO {
    public static List<MyLibrary> queryMylibrary(Connection conn) throws SQLException {
        String sql = "Select a.iduser, a.idsong from mylibrary a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<MyLibrary> list = new ArrayList<MyLibrary>();
        while (rs.next()) {
            String iduser = rs.getString("iduser");
            String idsong = rs.getString("idsong");
            MyLibrary mylibrary = new MyLibrary();
            mylibrary.setIdUser(iduser);
            mylibrary.setIdSong(idsong);
            list.add(mylibrary);
        }
        return list;
    }
    public static void addMyLibrary(Connection conn, MyLibrary mylibrary) throws SQLException {
        String sql = "Insert into mylibrary(iduser,idsong) values (?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, mylibrary.getIdUser());
        pstm.setString(2, mylibrary.getIdSong());
        pstm.executeUpdate();
    }
//
//    public static void updatemylibrary(Connection conn, MyLibrary mylibrary) throws SQLException {
//        String sql = "Update mylibrary useridsong=? where iduser=? and idsong=? ";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//
//        pstm.setString(1, mylibrary.getIdSong());
//        pstm.setString(2,  mylibrary.getIdUser());
//        pstm.setString(3,  mylibrary.getId());
//        pstm.executeUpdate();
//    }
    public static MyLibrary findMylibrary(Connection conn, String iduser,String idsong) throws SQLException {
        String sql = "Select a.iduser, a.idsong from mylibrary a where a.iduser=? and a.idsong=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, iduser);
        pstm.setString(2, idsong);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String  idUser= rs.getString("iduser");
            String  idSong= rs.getString("idsong");
            MyLibrary mylibrary = new MyLibrary(idUser,idSong);
            return mylibrary;
        }
        return null;
    }
    public static List<String> findSong(Connection conn, String iduser) throws SQLException {
        String sql = "Select a.iduser, a.idsong from mylibrary a where a.iduser=?";
        List<String> list= new ArrayList<>();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, iduser);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String  idSong= rs.getString("idsong");
            list.add(idSong);       
            }
        return list;
    }
//    public static MyLibrary findmylibraryId(Connection conn, String name) throws SQLException {
//        String sql = "Select a.id_mylibrary, a.name_mylibrary, a.picture_mylibrary from mylibrary a where a.name_mylibrary=?";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, name);
//        ResultSet rs = pstm.executeQuery();
//
//        while (rs.next()) {
//            String  id= rs.getString("id_mylibrary");
//            MyLibrary mylibrary = new MyLibrary(id,name);
//            return mylibrary;
//        }
//        return null;
//    }
    public static void deleteMylibrary(Connection conn, String idUser,String idSong) throws SQLException {
        String sql = "Delete From mylibrary where iduser= ? and idsong=?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, idUser);
        pstm.setString(2, idSong);
        pstm.executeUpdate();
    }

}
