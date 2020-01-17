package com.example.schoolassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LessonActivity extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";

    Day day;
    String dayNow;
    int numLesson;
    AlarmManager am;
    int time = 0;
    EditText ed_message;
    EditText ed_date, ed_time;
    TextView tw_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Bundle arguments = getIntent().getExtras();
        day = (Day) arguments.getSerializable(Day.class.getSimpleName());
        dayNow = arguments.getString("dayNow");
        ed_message = findViewById(R.id.ed_message);
        ed_date = findViewById(R.id.ed_date);
        ed_time = findViewById(R.id.ed_time);

        TextView nameDay = (TextView) findViewById(R.id.tw_les);
        nameDay.setTextSize(30);
        nameDay.setText(arguments.get("lesson").toString());
        numLesson = Integer.parseInt(Character.toString(arguments.getString("lesson").charAt(0)));

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);


        Button message = (Button) findViewById(R.id.b_message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!day.nameDay.equals(dayNow)){
                //}

                String s1 = ed_date.getText().toString(), s2 = ed_time.getText().toString();
                if(s1!="" && s2!="" ){//&& checkTime(s2) && checkDate(s1) ){
                    int honor, min;
                    honor = Integer.parseInt(Character.toString(s2.charAt(0)))*10 + Integer.parseInt(Character.toString(s2.charAt(1)));
                    min = Integer.parseInt(Character.toString(s2.charAt(3)))*10 + Integer.parseInt(Character.toString(s2.charAt(4)));
                    min+=honor*60;

                    int d, m, y ;
                    d  =  Integer.parseInt(Character.toString(s1.charAt(0)) + Character.toString(s1.charAt(1)));
                    m = Integer.parseInt(Character.toString(s1.charAt(3)) + Character.toString(s1.charAt(4)));
                    y = Integer.parseInt(Character.toString(s1.charAt(6)) + Character.toString(s1.charAt(7)) +
                            Character.toString(s1.charAt(8))+
                            Character.toString(s1.charAt(9)));

                    //int month[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

                    Calendar calendar = new GregorianCalendar();
                    long time = calendar.getTimeInMillis() ;
                    calendar.set(Calendar.HOUR,0);
                    calendar.set(Calendar.MINUTE,0);
                    calendar.set(Calendar.SECOND,0);
                    time -= calendar.getTimeInMillis();
                    //time += System.currentTimeMillis();
                    time -= min * 60000;

                    TextView tw = findViewById(R.id.textView8);
                    tw.setText(Long.toString(time));

                    /*for(int i = 0; i<(m-1) ; i++){
                        time += month[i];
                    }*/

                    TextView tw_event = (TextView) findViewById(R.id.tw_mes);
                    restartNotify(tw_event.getText().toString(), time );
                }


            }
        });

        Button addMes = (Button) findViewById(R.id.b_add);

        addMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ed_message.getText().toString();
                if(s!=""){
                    //saveText(s);
                    tw_mes = findViewById(R.id.tw_mes);
                    tw_mes.setText(s);

                }

            }
        });

    }


    private void restartNotify(String s, long time) {
        TimeNotification.text = s;
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TimeNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT );
        // На случай, если мы ранее запускали активити, а потом поменяли время,
        // откажемся от уведомления
        am.cancel(pendingIntent);
        // Устанавливаем разовое напоминание
        am.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }


    private boolean checkTime(String s){
        boolean b = true;
        if(s.length()==5){
            boolean t = true;
            for(int i = 0; i<s.length() && t; i++){
                if(!Character.isDigit(s.charAt(i)) || s.charAt(i)!=':'){
                    t = false;
                }
            }
            b  = t;
        } else {
            b = false;
        }

       return b;
    }

    private boolean checkDate(String s){
        boolean b = true;
        if(s.length()==10){
            boolean t = true;
            for(int i = 0; i<s.length() && t; i++){
                if(!Character.isDigit(s.charAt(i)) || s.charAt(i)!=':'){
                    t = false;
                }
            }
            b  = t;
        } else {
            b = false;
        }

        return b;
    }

    public void saveText(String s) {

        FileOutputStream fos = null;
        //try {
        //EditText textBox = (EditText) findViewById(R.id.save_text);
        //String text = textBox.getText().toString();

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        //}
       /* catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        */
    }
    // открытие файла
    public void openText(){

        FileInputStream fin = null;
        //TextView textView = (TextView) findViewById(R.id.open_text);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            //textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }




}
