package com.rku.tutorial_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Register_data extends AppCompatActivity {

    TextView fname,lname,username,password,city,gender,sts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);
        fname=findViewById(R.id.fname_set);
        lname=findViewById(R.id.lname_set);
        username=findViewById(R.id.username_set);
        password=findViewById(R.id.password_set);
        city=findViewById(R.id.city_set);
        gender=findViewById(R.id.gender_set);
        sts=findViewById(R.id.status);

        Intent intent=getIntent();
        String firstname_set=intent.getStringExtra("first_name");
        String lastname_set=intent.getStringExtra("last_name");
        String uname_set=intent.getStringExtra("uname");
        String psw_set=intent.getStringExtra("psw");
        String cy_set=intent.getStringExtra("cy");
        String gn_set=intent.getStringExtra("gender");
        String sts_set=intent.getStringExtra("ag");

        fname.setText("First Name: "+firstname_set);
        lname.setText("Last Name: "+lastname_set);
        username.setText("Username: "+uname_set);
        password.setText("Password: "+psw_set);
        city.setText("City: "+cy_set);
        gender.setText("Gender: "+gn_set);
        sts.setText("Status: "+sts_set);

    }
}