package com.example.schoolassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DayActivity extends AppCompatActivity {
    Day day;
    String lessonS[] = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day );

        final Bundle arguments = getIntent().getExtras();
        day = (Day) arguments.getSerializable(Day.class.getSimpleName());
        TextView nameDay = (TextView) findViewById(R.id.tw_day);
        nameDay.setTextSize(30);
        nameDay.setText(day.nameDay);

        int honorNow = 9, minNow = 15;
        int honorLast = 8, minLast = 30;
        int temp;
        for(int i = 0; i<lessonS.length; i++){
            if(day.getLesson(i)!="") {
                lessonS[i] =  (i+1) + ". " + day.getLesson(i) + " " + honorLast + ":" + minLast + " - " + honorNow + ":" + minNow;

                temp = honorNow * 60 + minNow + 15;
                honorLast = temp / 60;
                minLast = temp % 60;

                temp += 45;
                honorNow = temp / 60;
                minNow = temp % 60;
            } else {
                lessonS[i] = "not lesson";
            }
        }

        ListView lessonList = (ListView) findViewById(R.id.lw_lessons);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,lessonS );
        lessonList.setAdapter(adapter);

        lessonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                String s = Character.toString(strText.charAt(0)) + Character.toString(strText.charAt(1)) + " " ;
                boolean b = true;

                for(int i = 3; i<strText.length() && b; i++){
                    if(Character.toString(strText.charAt(i)).equals(" ") ){
                        b = false;
                    } else {
                        s = s.concat(Character.toString(strText.charAt(i)));
                    }
                }

                Intent i = new Intent(DayActivity.this, LessonActivity.class);
                i.putExtra(Day.class.getSimpleName(), day);
                i.putExtra("dayNow",arguments.getString("dayNow"));
                //i.putExtra("dayClick", position );
                i.putExtra("lesson", s);
                startActivity(i);

            }
        });
    }


}
