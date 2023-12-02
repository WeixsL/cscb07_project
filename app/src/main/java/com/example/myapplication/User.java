package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable  {

    public String email;
    public String name;

    public String id;


    public User(){

    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nEmail: %s\n", name, email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
}
