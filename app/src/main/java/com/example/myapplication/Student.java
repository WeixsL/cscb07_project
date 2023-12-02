package com.example.myapplication;

public class Student extends User {

    public Student() {

    }
    public Student(String email, String name) {
        super(email, name);
    }

    @Override
    public String toString() {
        String res = super.toString();
        return res;
    }

}
