package com.example.schoolassistant;

import android.app.AlarmManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LessonActivity extends AppCompatActivity {
    Day day;
    String dayNow;
    int numLesson;
    AlarmManager am;
    int time = 0;
    EditText ed_message;
    TextView tw_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Bundle arguments = getIntent().getExtras();
        day = (Day) arguments.getSerializable(Day.class.getSimpleName());
        dayNow = arguments.getString("dayNow");
        ed_message = findViewById(R.id.ed_message);


        TextView nameDay = (TextView) findViewById(R.id.tw_les);
        nameDay.setTextSize(30);
        nameDay.setText(arguments.get("lesson").toString());
        numLesson = Integer.parseInt(Character.toString(arguments.getString("lesson").charAt(0)));

        Button message = (Button) findViewById(R.id.b_message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!day.nameDay.equals(dayNow)){
                //}

                String s = ed_message.getText().toString();
                if(s!=""){
                    tw_mes = findViewById(R.id.tw_mes);
                    tw_mes.setText(s);
                }
            }
        });

    }



}
