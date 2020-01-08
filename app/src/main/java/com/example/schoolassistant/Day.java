package com.example.schoolassistant;

import java.io.Serializable;

public class Day implements Serializable {
    public String nameDay = "";
    private Lesson timeTable[] = new Lesson[7];

    public Day(){
        for (int i = 0; i<timeTable.length;i++){
            timeTable[i] = new Lesson();
            timeTable[i].nameLesson = "Math";
            timeTable[i].homework = "Не заданно";
        }
    }

    public void setLesson(int x, String s){
        timeTable[x].nameLesson = s;
    }

    public void setHomework(int x, String s){
        timeTable[x].homework = s;
    }

    public String getLesson(int x){
        return timeTable[x].nameLesson;
    }

    public String getHomework(int x){
        return timeTable[x].homework;
    }

    class Lesson implements Serializable{
        String nameLesson = "";
        String homework = "";
    }
}
