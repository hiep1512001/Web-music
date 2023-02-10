package com.javaConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	 public static Connection getOracleConnection()
	           throws ClassNotFoundException, SQLException {
		   
	       // Chú ý: Thay đổi các thông số kết nối cho phù hợp.
	       String hostName = "localhost";
	       String sid = "xe";
	       String userName = "c##spotify";
	       String password = "spotify";
	 
	       return getOracleConnection(hostName, sid, userName, password);
	   }
	 
	   public static Connection getOracleConnection(String hostName, String sid,
	           String userName, String password) throws ClassNotFoundException,
	           SQLException {
	  
	       Class.forName("oracle.jdbc.driver.OracleDriver");
	 
	       // Cấu trúc URL Connection đối với Oracle
	       // Ví dụ: 
	       // jdbc:oracle:thin:@localhost:1521:db11g
	       // jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
	       String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;
	 
	       Connection conn = DriverManager.getConnection(connectionURL, userName,
	               password);
	       return conn;
	   }
}
