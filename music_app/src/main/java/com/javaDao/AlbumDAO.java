package com.javaDao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.Album;

public class AlbumDAO {
    public static List<Album> queryAlbum(Connection conn) throws SQLException {
        String sql = "Select a.id_album, a.name_albums,a.id_singer,a.genre_album from album a order by to_number(a.id_album)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Album> list = new ArrayList<Album>();
        while (rs.next()) {
            String id = rs.getString("id_album");
            String name = rs.getString("name_albums");
            String idSinger = rs.getString("id_singer");
            String genreAlbum = rs.getString("genre_album");
            Album album = new Album();
            album.setId(id);
            album.setName(name);
            album.setIdSinger(idSinger);
            album.setGenre(genreAlbum);
            list.add(album);
        }
        return list;
    }
    public static void addAlbum(Connection conn, Album album,InputStream is) throws SQLException {
        String sql = "Insert into album(name_albums,id_singer,genre_album,picture_album) values (?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, album.getName());
        pstm.setString(2, album.getIdSinger());
        pstm.setString(3, album.getGenre());
        pstm.setBlob(4, is);
        pstm.executeUpdate();
    }

    public static void updateAlbum(Connection conn, Album album,InputStream is) throws SQLException {
        String sql = "Update album set name_albums=?, id_singer=?, genre_album=?, picture_album=? where id_album=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, album.getName());
        pstm.setString(2, album.getIdSinger());
        pstm.setString(3, album.getGenre());
        pstm.setBlob(4, is);
        pstm.setString(5, album.getId());
        pstm.executeUpdate();
    }
    public static Album findAlbum(Connection conn, String id) throws SQLException {
        String sql = "Select a.id_album, a.name_albums,a.id_singer,a.genre_album from album a where a.id_album=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name_albums");
            String idSinger = rs.getString("id_singer");
            String genreAlbum = rs.getString("genre_album");

            Album album = new Album( name,idSinger,genreAlbum);
            return album;
        }
        return null;
    }
    public static Album findAlbumIdSinger(Connection conn, String id) throws SQLException {
        String sql = "Select a.id_album, a.name_albums,a.id_singer,a.genre_album from album a where a.id_singer=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name_albums");
            String idSinger = rs.getString("id_singer");
            String genreAlbum = rs.getString("genre_album");

            Album album = new Album( name,idSinger,genreAlbum);
            return album;
        }
        return null;
    }
    public static void deleteAlbum(Connection conn, String id) throws SQLException {
        String sql = "Delete From album where id_album= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, id);

        pstm.executeUpdate();
    }
}
