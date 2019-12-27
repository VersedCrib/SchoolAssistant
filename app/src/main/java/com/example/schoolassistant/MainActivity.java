package com.example.schoolassistant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Timetable timetable[] = new Timetable[5];
    Journal journal = new Journal(5);
    int day = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        for(Timetable t: timetable){
            t = new Timetable();
        }


        timetable[0].homework = (TextView) findViewById(R.id.tw_homework1);
        timetable[0].lesson = (TextView) findViewById(R.id.tw_lesson1);

        timetable[1].homework = (TextView) findViewById(R.id.tw_homework2);
        timetable[1].lesson = (TextView) findViewById(R.id.tw_lesson2);

        timetable[2].homework = (TextView) findViewById(R.id.tw_homework3);
        timetable[2].lesson = (TextView) findViewById(R.id.tw_lesson2);

        timetable[3].homework = (TextView) findViewById(R.id.tw_homework4);
        timetable[3].lesson = (TextView) findViewById(R.id.tw_lesson4);

        timetable[4].homework = (TextView) findViewById(R.id.tw_homework5);
        timetable[4].lesson = (TextView) findViewById(R.id.tw_lesson5);

        timetable[5].homework = (TextView) findViewById(R.id.tw_homework6);
        timetable[5].lesson = (TextView) findViewById(R.id.tw_lesson6);

        timetable[6].homework = (TextView) findViewById(R.id.tw_homework7);
        timetable[6].lesson = (TextView) findViewById(R.id.tw_lesson7);

        for(int i = 0; i<timetable.length;i++){
            timetable[i].homework.setText(journal.day[day].getHomework(i));
            timetable[i].lesson.setText(journal.day[day].getLesson(i));
        }

*/

    }


    public String dayInWeek(int year, int month, int dayOfMonth){
        String nameDay = "";
        int m = month;
        if(month > 2){
            m = ((m+10)%13) + 1;
        } else {
            m = ((m+10)%13);
        }

        int w = (dayOfMonth + (13 * m - 1 )/5 + year % 100 + (year % 100 )/4  + (year/100)/4 - 2*(year/100)) % 7 ;
        switch (w){
            case 1:
                day = 1;
                nameDay = "Monday";
                break;

            case 2:
                day = 2;
                nameDay = "Tuesday";
                break;

            case 3:
                day = 3;
                nameDay = "Wednesday";
                break;

            case 4:
                day = 4;
                nameDay = "Thursday";
                break;

            case 5:
                day = 5;
                nameDay = "Friday";
                break;

            case 6:
                day = 6;
                nameDay = "Saturday";
                break;

            case 7:
                day = 7;
                nameDay = "Sunday";
                break;
        }

        return nameDay;
    }

    class Timetable{
        TextView lesson;
        TextView homework;
    }

}
