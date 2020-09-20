
package com.rku.demodatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database database;
    Button btndialog;
    AlertDialog.Builder builder;
    EditText name_edt,surname_edt,mark_edt;
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder=new AlertDialog.Builder(this);
        btndialog=findViewById(R.id.btndialog);
        database=new Database(this);
        name_edt=findViewById(R.id.name_edt);
        surname_edt=findViewById(R.id.surname_edt);
        mark_edt=findViewById(R.id.mark_edt);
        txtData=findViewById(R.id.txtdisplay);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Confirmation Message")
                        .setMessage("Do you really want to delete it ??")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Yes Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Toast.makeText(MainActivity.this, "No button is clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
            }
        });
    }

    public void insertdata(View view) {
        if(name_edt.getText().toString().equals(""))
        {
            name_edt.requestFocus();
            name_edt.setError("Please Enter Name");
            return;
        }
        else if(surname_edt.getText().toString().equals(""))
        {
            surname_edt.requestFocus();
            surname_edt.setError("Please Enter Surname");
            return;
        }
        else if(mark_edt.getText().toString().equals(""))
        {
            mark_edt.requestFocus();
            mark_edt.setError("Please Enter Mark");
            return;
        }
        else {
            boolean isInserted = database.insertData(name_edt.getText().toString(), surname_edt.getText().toString(), Integer.parseInt(mark_edt.getText().toString()));
            if (isInserted == true) {
                Toast.makeText(this, "Data is inserted", Toast.LENGTH_SHORT).show();
                name_edt.setText("");
                surname_edt.setText("");
                mark_edt.setText("");
            } else {
                Toast.makeText(this, "Something is wrong while inserting", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void displaydata(View view) {
        Cursor cursor=database.selectdata();
        String data="";
        if(cursor!=null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                String name=cursor.getString(1);
                String surname=cursor.getString(2);
                int marks=cursor.getInt(3);
                data+="     "+name+"               "+surname+"                    "+marks+"\n";
            } while (cursor.moveToNext());
            txtData.setText(data);
        }
        else
        {
            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
        }
    }
}