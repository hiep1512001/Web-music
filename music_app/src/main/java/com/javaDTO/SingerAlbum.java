package com.javaDTO;

public class SingerAlbum {
public SingerAlbum(String idSinger, String nameSinger, String idAlbum, String nameAlbum) {
        super();
        this.idSinger = idSinger;
        this.nameSinger = nameSinger;
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
    }
public SingerAlbum() {

}
private String idSinger;
public String getIdSinger() {
    return idSinger;
}
public void setIdSinger(String idSinger) {
    this.idSinger = idSinger;
}
public String getNameSinger() {
    return nameSinger;
}
public void setNameSinger(String nameSinger) {
    this.nameSinger = nameSinger;
}
public String getIdAlbum() {
    return idAlbum;
}
public void setIdAlbum(String idAlbum) {
    this.idAlbum = idAlbum;
}
public String getNameAlbum() {
    return nameAlbum;
}
public void setNameAlbum(String nameAlbum) {
    this.nameAlbum = nameAlbum;
}
private String nameSinger;
private String idAlbum;
private String nameAlbum;
}
