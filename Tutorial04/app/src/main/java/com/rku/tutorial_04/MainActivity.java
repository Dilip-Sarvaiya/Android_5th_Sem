package com.rku.tutorial_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
                String valUsername=Username.getText().toString();
                String valPassword=Password.getText().toString();

                Log.i("Login Screen","In onClick");
                if(!Patterns.EMAIL_ADDRESS.matcher(valUsername).matches())
                {
                    Toast.makeText(MainActivity.this, "Email address format is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (valUsername.equals("admin@gmail.com") && valPassword.equals("admin")) {
                    Log.i("Login Screen","In onClick If");

                    Intent intent = new Intent(MainActivity.this, Welcome_activity.class);
                    intent.putExtra("userdata_value",valUsername);
                    startActivity(intent);
                    finish();

                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Username or Password is incorrect.", Toast.LENGTH_SHORT).show();
                    Log.i("Login Screen","In onClick else");
                }
            }
        });
    }
}