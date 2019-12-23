package com.example.schoolassistant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationActivity extends AppCompatActivity {

    boolean firsTheards = true;
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Intent i = this.getIntent();
        //Bundle bundle = i.getExtras();

        //dbhelper = (DBHelper) bundle.getSerializable(DBHelper.class.getSimpleName());;
        dbhelper = new DBHelper(this);

        Button checkInf = (Button) findViewById(R.id.b_check2);

        checkInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText eLog = (EditText) findViewById(R.id.ed_loginAu2);
                Editable logEd = eLog.getText();
                String login = logEd.toString();

                EditText ePass = (EditText) findViewById(R.id.ed_passwordAu2);
                Editable passEd = ePass.getText();
                String password = passEd.toString();

                if(firsTheards && verification(login) && verification(password)) {
                    new RegistrationActivity.addUses().execute(login, password);

                    Intent i = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
                    //i.putExtra(DBHelper.class.getSimpleName(), dbhelper);
                    startActivity(i);
                } else {
                    TextView twInf = (TextView) findViewById(R.id.tw_sost2);
                    twInf.setText("Состояние введенных данных: данные введены не корректно");
                    twInf.setBackgroundColor(Color.RED);
                }
            }
        });

    }

    private boolean verification(String s){
        boolean b = false;

        if(s.length() <= 10 && !s.equals("")){
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


    private class addUses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            firsTheards = false;
        }

        protected Void doInBackground(String... args) {
            String login = args[0];
            String password = args[1];

            SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(DBHelper.KEY_LOGIN, login);
            try {
                contentValues.put(DBHelper.KEY_PASSWORD, hashFun(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            sqLiteDatabase.insert(DBHelper.TABLE_USERS, null, contentValues);

            return null;
        }

        protected void onPostExecute(Void image) {
            firsTheards = true;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //super.onProgressUpdate(values);

        }
    }

    private String hashFun( String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance( "SHA-256" );
        //String text = "Text to hash, cryptographically.";

        // Change this to UTF-16 if needed
        md.update(s.getBytes());
        byte[] digest = md.digest();
        String hex = String.format( "%064x", new BigInteger( 1, digest ) );

        return hex;
    }
}
