package com.example.schoolassistant;

import java.util.ArrayList;

public class Event {
    ArrayList<String> events = new ArrayList<String>();

    public void setEvents(String s){
        events.add(s);
    }

    public String getEvents(int num){
        return events.get(num);
    }
}
