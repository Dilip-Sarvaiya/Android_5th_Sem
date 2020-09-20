package com.rku.tutorial_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome_activity extends AppCompatActivity {

    TextView welcome_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activity);
        welcome_txt=findViewById(R.id.welcome);
        Intent intent = getIntent();
        String welcm_user=intent.getStringExtra("userdata_value");
        welcome_txt.setText("Welcome "+welcm_user.toString());
    }
}