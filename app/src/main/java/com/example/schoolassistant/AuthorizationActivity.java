package com.example.schoolassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

public class AuthorizationActivity extends AppCompatActivity {

    boolean firstThreads = true;
    DBHelper dbhelper;
    String login;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        dbhelper = new DBHelper(this);

        Button checkInf = (Button) findViewById(R.id.b_check);

        checkInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText eLog = (EditText) findViewById(R.id.ed_loginAu);
                Editable logEd = eLog.getText();
                login = logEd.toString();

                EditText ePass = (EditText) findViewById(R.id.ed_passwordAu);
                Editable passEd = ePass.getText();
                password = passEd.toString();

                if(verification(login) && verification(password)) {
                    if(firstThreads){
                        CheckUsers checkUsers = new CheckUsers();
                        checkUsers.execute(login, password);
                        try {
                            if(checkUsers.get()){
                                Intent i = new Intent(AuthorizationActivity.this, CalendarActivity.class);
                                startActivity(i);
                            } else {
                                TextView twInf = (TextView) findViewById(R.id.tw_sost);
                                twInf.setText("Состояние введенных данных: данные не найдены");
                                twInf.setBackgroundColor(Color.WHITE);
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    TextView twInf = (TextView) findViewById(R.id.tw_sost);
                    twInf.setText("Состояние введенных данных: данные введены не корректно");
                    twInf.setBackgroundColor(Color.RED);
                }
            }
        });

        Button registration = (Button) findViewById(R.id.b_new);

        registration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        Button read = (Button) findViewById(R.id.read);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();

                Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_USERS,null,null,
                        null,null, null, null );
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                    int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);

                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", login = " + cursor.getString(loginIndex) +
                                ", password = " + cursor.getString(passwordIndex));
                    } while (cursor.moveToNext());
                } else{
                    Log.d("mLog","0 rows");
                }

                Log.d("mLog",  "now - " + "login = " + login + ", password = " + password + ", firsTheards = " + firstThreads);

                cursor.close();
            }
        });


    }

    private boolean verification(String s){
        boolean b = false;

        if(s.length() <= 10 && s!=""){
            boolean b1 = true;
            for(int i = 0; (i<s.length()) && b1; i++){
                if(!Character.isLetterOrDigit(s.charAt(i)) || (s.charAt(i)>='а' &&  s.charAt(i)<='я')){
                    b1 = false;
                }
            }
            b =  b1;
        }
        return b;
    }


    private class CheckUsers extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            firstThreads = false;
        }

        protected Boolean doInBackground(String... args) {
            String login = args[0];
            String password = null;
            try {
                password = hashFun(args[1]);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            boolean isUsers = false;

            SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();

            Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_USERS,null,null,
                    null,null, null, null );
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);

                do {
                    /*Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                            ", login = " + cursor.getString(loginIndex) +
                            ", password = " + cursor.getString(passwordIndex));*/
                    if(login.equals(cursor.getString(loginIndex)) && password.equals(cursor.getString(passwordIndex))){
                        isUsers = true;
                    }
                } while (cursor.moveToNext());
            }


            cursor.close();
           // ContentValues contentValues = new ContentValues();

            firstThreads = true;
            return isUsers;
        }

        protected void onPostExecute(boolean b) {
            //firsTheards = true;
            super.onPostExecute(b);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //super.onProgressUpdate(values);

        }
    }


    private String hashFun(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance( "SHA-256" );
        //String text = "Text to hash, cryptographically.";

        // Change this to UTF-16 if needed
        md.update(s.getBytes());
        byte[] digest = md.digest();
        String hex = String.format( "%064x", new BigInteger( 1, digest ) );

        return hex;
    }


}
