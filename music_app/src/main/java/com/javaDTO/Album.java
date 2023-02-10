package com.javaDTO;

public class Album {
public Album(String id, String name, String idSinger, String genre) {
        super();
        this.id = id;
        this.name = name;
        this.idSinger = idSinger;
        this.genre = genre;

    }
public Album( String name, String idSinger, String genre) {
    super();
    this.name = name;
    this.idSinger = idSinger;
    this.genre = genre;

}
public Album() {
    super();

}
private String id;
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
public String getIdSinger() {
    return idSinger;
}
public void setIdSinger(String idSinger) {
    this.idSinger = idSinger;
}
public String getGenre() {
    return genre;
}
public void setGenre(String genre) {
    this.genre = genre;
}
public byte[] getImage() {
    return image;
}
public void setImage(byte[] image) {
    this.image = image;
}
private String name;
private String idSinger;
private String genre;
private byte[] image;
}
