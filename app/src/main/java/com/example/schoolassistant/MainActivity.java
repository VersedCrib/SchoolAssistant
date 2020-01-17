package com.example.schoolassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int numDay = 1;
    Day week[] = new Day[5];
    String weekS[] = new String[5];
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bundle arguments = getIntent().getExtras();
       // name = arguments.getString("name");

        TextView tw_date = (TextView)findViewById(R.id.tw_date) ;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        int d,M,y;
        String s = format.format(new Date());
        d  =  Integer.parseInt(Character.toString(s.charAt(0)) + Character.toString(s.charAt(1)));
        M = Integer.parseInt(Character.toString(s.charAt(3)) + Character.toString(s.charAt(4)));
        y = Integer.parseInt(Character.toString(s.charAt(6)) + Character.toString(s.charAt(7)) +
                Character.toString(s.charAt(8))+
                Character.toString(s.charAt(9)));

        numDay = weekday(y, M, d);
        tw_date.setTextSize(30);
        tw_date.setText( dayInWeek(numDay) + " " +  format.format(new Date()) );

        for(int i =0; i<week.length; i++){
            week[i] = new Day();
            week[i].nameDay = dayInWeek(i+1);
        }

        for(int i =0; i<weekS.length; i++){
            weekS[i] = week[i].nameDay;
        }

        ListView dayList = (ListView) findViewById(R.id.lw_week);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,weekS );
        dayList.setAdapter(adapter);

        dayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                Intent i = new Intent(MainActivity.this, DayActivity.class);
                i.putExtra("dayNow", dayInWeek(numDay));
                //i.putExtra("dayClick", dayInWeek(position));
                i.putExtra(Day.class.getSimpleName(), week[dayInWeek(strText)]);
                //i.putExtra("name",name);

                startActivity(i);

            }
        });



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
                numDay = 1;
                nameDay = "Monday";
                break;

            case 2:
                numDay = 2;
                nameDay = "Tuesday";
                break;

            case 3:
                numDay = 3;
                nameDay = "Wednesday";
                break;

            case 4:
                numDay = 4;
                nameDay = "Thursday";
                break;

            case 5:
                numDay = 5;
                nameDay = "Friday";
                break;

            case 6:
                numDay = 6;
                nameDay = "Saturday";
                break;

            case 7:
                numDay = 7;
                nameDay = "Sunday";
                break;
        }

        return nameDay;
    }

    public String dayInWeek(){
        String nameDay = "";
        switch (this.numDay){
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
        return nameDay;
    }

    public String dayInWeek(int numDay){
        String nameDay = "";
        switch (numDay){
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
        return nameDay;
    }


    public int dayInWeek(String s){
        int nameDay = 0;
        switch (s){
            case "Monday":
                nameDay = 0;
                break;

            case "Tuesday":
                nameDay = 1;
                break;

            case "Wednesday":
                nameDay = 2;
                break;

            case "Thursday":
                nameDay = 3;
                break;

            case "Friday":
                nameDay = 4;
                break;

            case "Saturday":
                nameDay = 5;
                break;

            case "Sunday":
                nameDay = 6;
                break;
        }
        return nameDay;
    }

    static int weekday(int year, int month, int day) {
        if (month < 3) {
            --year;
            month += 10;
        } else
            month -= 2;
        return ((day + 31 * month / 12 + year + year / 4 - year / 100 + year / 400) % 7);
    }
}
