package com.rku.tutorial_07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome_txt;
    SharedPreferences preferences;
    AlertDialog.Builder builder;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        builder=new AlertDialog.Builder(this);
        welcome_txt=findViewById(R.id.welcome);
        preferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();
        Intent intent=getIntent();
        String username=intent.getStringExtra("userdata_value");
        welcome_txt.setText("Welcome "+ username);
    }
    public void logout_user(View view) {
        editor.remove("username");
        editor.commit();
        Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                builder.setTitle("Logging out")
                        .setMessage("Are you sure? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editor.remove("username");
                                editor.commit();
                                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                                Toast.makeText(WelcomeActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}