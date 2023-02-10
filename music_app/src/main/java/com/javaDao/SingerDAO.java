package com.javaDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaDTO.Singer;

public class SingerDAO {
	public static List<Singer> querySinger(Connection conn) throws SQLException {
		String sql = "Select a.id_singer, a.name_singer from singer a";

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
	public static void insertProduct(Connection conn, Singer singer,InputStream is) throws SQLException {
		String sql = "Insert into singer(name_singer,picture_singer) values (?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, singer.getName());
		pstm.setBlob(2, is);
		pstm.executeUpdate();
	}
	
}

