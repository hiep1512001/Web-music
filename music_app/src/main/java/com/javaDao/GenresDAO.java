package com.javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.Genres;

public class GenresDAO {
    public static List<Genres> querygenres(Connection conn) throws SQLException {
        String sql = "Select a.id_genre, a.name_genre from genres a";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Genres> list = new ArrayList<Genres>();
        while (rs.next()) {
            String id = rs.getString("id_genre");
            String name = rs.getString("name_genre");
            Genres genres = new Genres(id, name);
            genres.setId(id);
            genres.setName(name);
            list.add(genres);
        }
        return list;
    }
    public static void addGenres(Connection conn, Genres genres) throws SQLException {
        String sql = "Insert into genres(name_genre) values (?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, genres.getName());
        pstm.executeUpdate();
    }

    public static void updategenres(Connection conn, Genres genres) throws SQLException {
        String sql = "Update genres set name_genre=? where id_genre=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, genres.getName());
        pstm.setString(2, genres.getId());
        pstm.executeUpdate();
    }
    public static Genres findgenres(Connection conn, String id) throws SQLException {
        String sql = "Select a.name_genre, a.name_genre from genres a where a.id_genre=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String  name= rs.getString("name_genre");
            Genres genres = new Genres(id, name);
            return genres;
        }
        return null;
    }
    public static void deletegenres(Connection conn, String id) throws SQLException {
        String sql = "Delete From genres where id_genre= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, id);

        pstm.executeUpdate();
    }
}
