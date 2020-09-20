package com.rku.tutorial_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Username;
    EditText Password;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.edt_username);
        Password = (EditText) findViewById(R.id.pass_edt);
        Login=(Button) findViewById(R.id.login_btn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Username.getText().toString().equals("admin@gmail.com") && Password.getText().toString().equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, Welcome_activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Username or Password is incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}