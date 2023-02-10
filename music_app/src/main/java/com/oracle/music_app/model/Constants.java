/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.music_app.model;

/**
 *
 * @author hoang
 */
public class Constants {
    public static String GOOGLE_CLIENT_ID = "400727485680-va07ln04qr2fbg5ng3db8gsdveh4v806.apps.googleusercontent.com";

	public static String GOOGLE_CLIENT_SECRET = "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";

	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/music_app/LoginWithApiServlet";

	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=";

	public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
