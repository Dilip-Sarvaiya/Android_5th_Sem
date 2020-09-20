package com.rku.tutorial_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText Username;
    EditText Password;
    Button Login;
    Database database;
    String valUsername,valPassword;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database=new Database(this);
        Username = (EditText) findViewById(R.id.edt_username);
        Password = (EditText) findViewById(R.id.pass_edt);
        Login=(Button) findViewById(R.id.login_btn);

        preferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();
        String userPreference=preferences.getString("username","");
        if(!userPreference.equals("")) {
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
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
                if(!Patterns.EMAIL_ADDRESS.matcher(valUsername).matches()) {
                    Toast.makeText(LoginActivity.this, "Email address format is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(database.validation(Username.getText().toString(),Password.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Log.i("Login Screen","In onClick If");
                    editor.putString("username",valUsername);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    intent.putExtra("userdata_value",valUsername);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Username or Password is incorrect.", Toast.LENGTH_SHORT).show();
                    Log.i("Login Screen","In onClick else");
                }
            }
        });
    }

    public void Registerscreen(View view) {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}