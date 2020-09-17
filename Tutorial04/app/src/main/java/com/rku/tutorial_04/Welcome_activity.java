package com.rku.tutorial_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome_activity extends AppCompatActivity {

    TextView userdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activity);

        userdata=findViewById(R.id.welcome);
        Intent intent=getIntent();
        String username=intent.getStringExtra("userdata_value");
        userdata.setText("Welcome "+ username);
    }
}