package com.rku.tutorial_06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    TextView userdata;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        userdata=findViewById(R.id.welcome);
        preferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();
        Intent intent=getIntent();
        String username=intent.getStringExtra("userdata_value");
        userdata.setText("Welcome "+ username);

    }

    public void logout_user(View view) {
        editor.remove("username");
        editor.commit();
        Intent intent=new Intent(Welcome.this,MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // MenuInflater menuInflater=getMenuInflater();
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())) {

            case R.id.mnulogot:
                editor.remove("username");
                editor.commit();
                Intent intent=new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}