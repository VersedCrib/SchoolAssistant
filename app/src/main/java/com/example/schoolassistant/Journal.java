package com.example.schoolassistant;

import java.io.Serializable;

public class Journal implements Serializable {
    Day[] day;


    public Journal(int numDay){
        day = new Day[numDay];


        for(int i = 0; i<numDay; i++){
            day[i] = new Day();

            for(int k = 0 ; k<7; k++) {
                day[i].setLesson(k,"Math");
                day[i].setHomework(k,"");
            }
        }
    }


    class Day implements Serializable{
        String lesson[];
        String homework[];

        public Day(){
            lesson = new String[7];
            homework = new String[7];

            for (String s: lesson){
                s = "";
            }

            for (String s: homework){
                s = "";
            }

        }

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
