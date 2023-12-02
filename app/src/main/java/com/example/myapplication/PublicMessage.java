package com.example.myapplication;

import java.io.Serializable;

public class PublicMessage implements Serializable {
    public String title;
    public String content;
    public PublicMessage(){
    }

    public PublicMessage(String title, String content){
        this.title = title;
        this.content = content;
    }
}
