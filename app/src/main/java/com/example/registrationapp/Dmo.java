package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Dmo extends AppCompatActivity {

    TextView phoneOrg;
    EditText phonenew;
    Button update,delete;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmo);

        phoneOrg = (TextView) findViewById(R.id.phoneOrg);
        phonenew =  findViewById(R.id.edPhoneupdate);


        update = findViewById(R.id.editno);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    mydb = new DatabaseHelper(Dmo.this);
                    SQLiteDatabase sqLiteDatabase = mydb.getReadableDatabase();

                    mydb.updatedata(phonenew.getText().toString(),sqLiteDatabase);



                    Toast.makeText(Dmo.this, "Data Updated..!", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(Dmo.this,Login.class);


                    startActivity(ii);

                }catch (Exception ee){
                    Toast.makeText(Dmo.this, "error"+ee, Toast.LENGTH_SHORT).show();

                }
            }
        });
        delete = findViewById(R.id.delno);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb = new DatabaseHelper(Dmo.this);
                SQLiteDatabase sqLiteDatabase = mydb.getReadableDatabase();

                mydb.deleteData(sqLiteDatabase);

                Toast.makeText(Dmo.this, "Data Deleted..!", Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(Dmo.this,Login.class);


                startActivity(ii);
            }
        });





        phoneOrg.setText(DatabaseHelper.loginuserPhone);


    }
}
