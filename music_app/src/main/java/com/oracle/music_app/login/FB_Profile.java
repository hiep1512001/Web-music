package com.oracle.music_app.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.oracle.music_app.model.User;

public class FB_Profile {
    public static boolean isFB = false;
	public User call_me(String access_token) throws Exception {
	     String url = "https://graph.facebook.com/v2.12/me?fields=id,name,picture,email&access_token="+access_token;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     System.out.println(response);
	     User user=new User();
	     JSONObject myResponse = new JSONObject(response.toString());
	     user.setFull_name(myResponse.getString("name"));
	     user.setId(myResponse.getString("id"));
	     user.setEmail(myResponse.getString("email"));
             isFB = true;
		return user;
	   }
}
