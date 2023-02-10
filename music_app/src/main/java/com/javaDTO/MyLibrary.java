package com.javaDTO;

public class MyLibrary {
public MyLibrary(String idUser, String idSong) {
        super();
        this.idUser = idUser;
        this.idSong = idSong;
    }
public MyLibrary() {
    super();

}
private String idUser;
public String getIdUser() {
    return idUser;
}
public void setIdUser(String idUser) {
    this.idUser = idUser;
}
public String getIdSong() {
    return idSong;
}
public void setIdSong(String idSong) {
    this.idSong = idSong;
}
private String idSong;

}
