package com.rku.tutorial_06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Username;
    EditText Password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    String valUsername;
    String valPassword;

    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = (EditText) findViewById(R.id.edt_username);
        Password = (EditText) findViewById(R.id.pass_edt);
        Login=(Button) findViewById(R.id.login_btn);

        preferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();

        String userPreference=preferences.getString("username","");
        if(!userPreference.equals("")) {
            Intent intent = new Intent(MainActivity.this, Welcome.class);
            intent.putExtra("userdata_value", userPreference);
            startActivity(intent);
            finish();
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valUsername=Username.getText().toString();
                valPassword=Password.getText().toString();

                Log.i("Login Screen","In onClick");
                if(!Patterns.EMAIL_ADDRESS.matcher(valUsername).matches())
                {
                    Toast.makeText(MainActivity.this, "Email address format is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (valUsername.equals("admin@gmail.com") && valPassword.equals("admin")) {
                    Log.i("Login Screen","In onClick If");

                    editor.putString("username",valUsername);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    intent.putExtra("userdata_value",valUsername);
                    startActivity(intent);
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