package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {
    public String name;
    public String content;
    public int occupancy;

    int participants;//number of rsvped student, <=Occupancy

    int count;//number of students who evaluated this event
    double avgRate = 5.0;//scaled from 1-10, 1 for the worst, 5 for normal, 10 for the best
    List<String> comments;//List of comments by students

    public Event(){
    }

    public Event(String title, String content, int occupancy){
        this.name = title;
        this.content = content;
        this.occupancy = occupancy;

        this.participants = 0;//no Rsvp on created

        this.count = 0;//no evaluation on created
        this.avgRate = 5.0;//denote Value
        this.comments = new ArrayList<String>();
    }
}
