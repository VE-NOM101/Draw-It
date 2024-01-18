package com.example.drawit.Account;

public class Users {

    public String email;
    public String pass;
    public String name;
    public String dob;
    public String country;


    public Users(){};

    public Users(String email, String pass, String name, String dob, String country) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.dob = dob;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
