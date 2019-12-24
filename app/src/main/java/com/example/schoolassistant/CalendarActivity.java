package com.example.schoolassistant;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    String nameDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int m = month;
                if(month > 2){
                    m = ((m+10)%13) + 1;
                } else {
                    m = ((m+10)%13);
                }

                int w = (dayOfMonth + (13 * m - 1 )/5 + year % 100 + (year % 100 )/4  + (year/100)/4 - 2*(year/100)) % 7 ;
                switch (w){
                    case 1:
                        nameDay = "Monday";
                        break;

                    case 2:
                        nameDay = "Tuesday";
                        break;

                    case 3:
                        nameDay = "Wednesday";
                        break;

                    case 4:
                        nameDay = "Thursday";
                        break;

                    case 5:
                        nameDay = "Friday";
                        break;

                    case 6:
                        nameDay = "Saturday";
                        break;

                    case 7:
                        nameDay = "Sunday";
                        break;
                }
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(nameDay);

            }
        });

    }



}
