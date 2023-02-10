package com.javaDTO;

public class Singer {
	public Singer() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String id;
	private String name;
	private byte[] image;
	public Singer(String id, String name, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}
	public Singer(String id, String name) {
		super();
		this.id = id;
		this.name = name;

	}
	public Singer( String name) {
		super();
		this.name = name;

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
