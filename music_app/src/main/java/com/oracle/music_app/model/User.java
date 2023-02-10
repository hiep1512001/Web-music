package com.oracle.music_app.model;

import java.sql.Date;

public class User {
	private String id;
	private String name_user;
	private String name_account;
	private String password;
	private String email;
	private int decentralization;
	private Date registration_date;
	private byte[] avatar;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount_name() {
		return name_account;
	}
	public void setAccount_name(String name_account) {
		this.name_account = name_account;
	}
	public String getFull_name() {
		return name_user;
	}
	public void setFull_name(String full_name) {
		this.name_user = full_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDecentralization() {
		return decentralization;
	}
	public void setDecentralization(int decentralization) {
		this.decentralization = decentralization;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	
}
