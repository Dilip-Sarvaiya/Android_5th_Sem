package com.rku.tutorial_08;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences preferences;
    Database database;

    String [] data={"Dilip","Bhavesh"};
    ListView listView;
    ArrayAdapter<String> adapter;
    AlertDialog.Builder builder;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new Database(this);
        setContentView(R.layout.activity_welcome);
        builder=new AlertDialog.Builder(this);
        listView=findViewById(R.id.lstview);

        adapter=new ArrayAdapter<String>(WelcomeActivity.this,
                android.R.layout.simple_list_item_1,database.getUserList()
                );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItem =((TextView)view).getText().toString();
                Intent intent=new Intent(WelcomeActivity.this,Register_data.class);
                intent.putExtra("userdata",listItem);
                startActivity(intent);
            }
        });

        preferences=getSharedPreferences("user",MODE_PRIVATE);
        editor=preferences.edit();
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