package com.example.drawit.Bookmark;

import java.io.Serializable;

public class Sqlite_StudentModel implements Serializable {

    int id ;
    String name ;
    int age ;
    String Address ;

    public Sqlite_StudentModel(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        Address = address;
    }

    public Sqlite_StudentModel(String name, int age, String address) {
        this.name = name;
        this.age = age;
        Address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
