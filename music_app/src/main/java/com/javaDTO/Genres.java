package com.javaDTO;

public class Genres {
public Genres(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
public Genres(String name) {
    super();
    this.name = name;
}
private String id;
private String name;
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
}
