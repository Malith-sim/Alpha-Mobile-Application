package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;

public class Login extends AppCompatActivity {

    private EditText puname, pupw;
    private String username, userpassword;
    Button log;
    DatabaseHelper myDataReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        puname = (EditText) findViewById(R.id.username);
        pupw = (EditText) findViewById(R.id.userpassword);
        log = (Button) findViewById(R.id.logbtn);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logIn(); //call when the button is clicked to validate input
            }



        });

    }

    public void regbtn_registration(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


    public void logIn(){
        initialize(); //initialize the input to string variables
        if(!validate()) {
            Toast.makeText(this, "Login Failed!!", Toast.LENGTH_SHORT).show();
        }
        else{
            onLoginSuccess();
        }
    }

    public void onLoginSuccess(){
        myDataReg = new DatabaseHelper(Login.this);
        Cursor cc = myDataReg.ViewLoginData(username,userpassword);
        if(!(cc.moveToNext())){
            Toast.makeText(this, "Invalid Login Details...!", Toast.LENGTH_SHORT).show();
        }else{

            DatabaseHelper.loginuserID = cc.getString(0);
            DatabaseHelper.loginuserName = cc.getString(1);
            DatabaseHelper.loginuserGender = cc.getString(2);
            DatabaseHelper.loginuserEmail = cc.getString(3);
            DatabaseHelper.loginuserPhone = cc.getString(4);
            DatabaseHelper.loginuserPassword = cc.getString(5);

            Toast.makeText(this, "Login Successful!!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),HomePage.class));
        }

    }


    public boolean validate(){
        boolean val = true;


        if(username.isEmpty()){
            puname.setError("Email can't be blank!");
            puname.requestFocus();
            val = false;
        }


        if(userpassword.isEmpty()){
            pupw.setError("Password can't be blank!");
            pupw.requestFocus();
            val = false;
        }





        return val;

    }


    public void initialize(){
        username = puname.getText().toString().trim();
        userpassword = pupw.getText().toString().trim();

    }

}

