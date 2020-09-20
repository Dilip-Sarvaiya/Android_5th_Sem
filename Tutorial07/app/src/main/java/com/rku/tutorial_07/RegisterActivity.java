package com.rku.tutorial_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import Validation.Validate;

public class RegisterActivity extends AppCompatActivity {

    Database database;

    EditText fname,lname,username,password;
    Switch branch;
    Spinner city;
    CheckBox agree;
    Button Signup;
    RadioGroup rdb_group;
    RadioButton rdb_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database=new Database(this);

        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        username=findViewById(R.id.email);
        password=findViewById(R.id.psw);
        branch=findViewById(R.id.branch);
        city=findViewById(R.id.city);
        agree=findViewById(R.id.agree);

        Signup=findViewById(R.id.signup_btn);

        final Validate validation = new Validate();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first_name, last_name, uname, psw_str, br, gender, cy, ag;
                first_name = fname.getText().toString();
                last_name = lname.getText().toString();
                uname = username.getText().toString();
                psw_str = password.getText().toString();
                br = branch.getText().toString();
                rdb_group = findViewById(R.id.rdb_group);
                int id = rdb_group.getCheckedRadioButtonId();
                rdb_select = findViewById(id);
                gender = rdb_select.getText().toString();
                cy = city.getSelectedItem().toString();
                if (validation.isnull(fname.getText().toString())) {
                    fname.requestFocus();
                    fname.setError(("Please enter first name"));
                    return;
                }
                if (validation.isnull(lname.getText().toString())) {
                    lname.requestFocus();
                    lname.setError(("Please enter last name"));
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(uname).matches()) {
                    username.requestFocus();
                    username.setError(("Email address format is not valid"));
                    return;
                }
                if (validation.isnull(password.getText().toString())) {
                    password.requestFocus();
                    password.setError(("Please Enter Password"));
                    return;
                }
                if (cy.equals("Select One")) {
                    Toast.makeText(RegisterActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (agree.isChecked()) {
                    ag = "Active";
                } else {
                    ag = "Inactive";
                }
               boolean isInserted = database.insertData(first_name, last_name, uname, psw_str, br, gender, cy, ag);
                if (isInserted == true) {
                    Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    fname.setText("");
                    lname.setText("");
                    username.setText("");
                    password.setText("");
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Something is wrong while inserting", Toast.LENGTH_SHORT).show();
                }
                /*Cursor cursor=database.selectdata();
                String u_db,p_db;
                if(cursor!=null)
                {
                    Toast.makeText(RegisterActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "No records found", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}