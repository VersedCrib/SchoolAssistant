package com.example.schoolassistant;

import java.io.Serializable;

public class Journal implements Serializable {
    Day[] day;


    public Journal(int numDay){
        day = new Day[numDay];

        for(Day d: day){
            for(int i = 0 ; i<d.lesson.length; i++) {
                d.lesson[i] = "Math";
                d.homework[i] = "";
            }
        }
    }


    class Day implements Serializable{
        String lesson[] = new String[7];
        String homework[] = new String[7];

        public void setLesson(int num, String s) {
            lesson[num] = s;
        }

        public void setHomework(int num, String s) {
            homework[num] = s;
        }

        public String getLesson(int num) {
            return lesson[num];
        }

        public String getHomework(int num) {
            return homework[num];
        }

    }

}
