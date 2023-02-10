package com.javaDTO;

public class Song {
public Song(String id, String name, String idSinger, String idAlbum, String idGenre) {
        super();
        this.id = id;
        this.name = name;
        this.idSinger = idSinger;
        this.idAlbum = idAlbum;
        this.idGenre = idGenre;
    }
public Song() {
    super();

}
public Song( String name, String idSinger, String idAlbum, String idGenre) {
    super();

    this.name = name;
    this.idSinger = idSinger;
    this.idAlbum = idAlbum;
    this.idGenre = idGenre;
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
public String getIdAlbum() {
    return idAlbum;
}
public void setIdAlbum(String idAlbum) {
    this.idAlbum = idAlbum;
}
public String getIdGenre() {
    return idGenre;
}
public void setIdGenre(String idGenre) {
    this.idGenre = idGenre;
}
public byte[] getImage() {
    return image;
}
public void setImage(byte[] image) {
    this.image = image;
}
public String getPath() {
    return path;
}
public void setPath(String path) {
    this.path = path;
}
private String name;
private String idSinger;
private String idAlbum;
private String idGenre;
private byte[] image;
private byte[] data_song;
private String path;
}
