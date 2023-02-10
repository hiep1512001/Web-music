package com.javaDao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.Singer;


public class SingerDAO {
	public static List<Singer> querySinger(Connection conn) throws SQLException {
		String sql = "Select a.id_singer, a.name_singer from singer a order by to_number(a.id_singer)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Singer> list = new ArrayList<Singer>();
		while (rs.next()) {
			String id = rs.getString("id_singer");
			String name = rs.getString("name_singer");
			Singer singer = new Singer();
			singer.setId(id);
			singer.setName(name);
			list.add(singer);
		}
		return list;
	}
	public static void addSinger(Connection conn, Singer singer,InputStream is) throws SQLException {
		String sql = "Insert into singer(name_singer,picture_singer) values (?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, singer.getName());
		pstm.setBlob(2, is);
		pstm.executeUpdate();
	}

    public static void updateSinger(Connection conn, Singer singer,InputStream is) throws SQLException {
        String sql = "Update Singer set name_singer=?, picture_singer=? where id_singer=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, singer.getName());
        pstm.setBlob(2, is);
        pstm.setString(3, singer.getId());
        pstm.executeUpdate();
    }
    public static Singer findSinger(Connection conn, String id) throws SQLException {
        String sql = "Select a.id_singer, a.name_singer, a.picture_singer from singer a where a.id_singer=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String  name= rs.getString("name_singer");
            Blob blob = rs.getBlob("picture_singer");
            Singer singer = new Singer(id, name);
            return singer;
        }
        return null;
    }
    public static Singer findSingerId(Connection conn, String name) throws SQLException {
        String sql = "Select a.id_singer, a.name_singer, a.picture_singer from singer a where a.name_singer=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String  id= rs.getString("id_singer");
            Singer singer = new Singer(id,name);
            return singer;
        }
        return null;
    }
    public static void deleteSinger(Connection conn, String id) throws SQLException {
        String sql = "Delete From singer where id_singer= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, id);

        pstm.executeUpdate();
    }
}

