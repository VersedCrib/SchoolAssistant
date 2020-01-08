package com.example.schoolassistant;

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
        Bundle arguments = getIntent().getExtras();
        day = (Day) arguments.getSerializable(Day.class.getSimpleName());
        TextView nameDay = (TextView) findViewById(R.id.tw_day);
        nameDay.setTextSize(30);
        nameDay.setText(day.nameDay);


        for(int i = 0; i<lessonS.length; i++){
            lessonS[i] = day.getLesson(i);
        }

        ListView lessonList = (ListView) findViewById(R.id.lw_lessons);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,lessonS );
        lessonList.setAdapter(adapter);

        lessonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                /*TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                Intent i = new Intent(DayActivity.this, DayActivity.class);
                i.putExtra(Day.class.getSimpleName(), day);
                startActivity(i);*/

            }
        });
    }


}
