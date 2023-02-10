/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.music_app.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oracle.music_app.model.Constants;
import com.oracle.music_app.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

/**
 *
 * @author hoang
 */
public class GG_Profile {
    public User call_me(String code) throws ClientProtocolException, IOException{
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		      JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
                String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		URL obj = new URL(link);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + link);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer responseStr = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	responseStr.append(inputLine);
	     }
	     in.close();
	     System.out.println(responseStr);
	     User user=new User();
	     JSONObject myResponse = new JSONObject(responseStr.toString());
	     user.setFull_name(myResponse.getString("name"));
	     user.setId(myResponse.getString("id"));
	     user.setEmail(myResponse.getString("email"));
		return user;
    }
}
