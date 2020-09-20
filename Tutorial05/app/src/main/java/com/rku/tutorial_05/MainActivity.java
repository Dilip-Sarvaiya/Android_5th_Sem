package com.rku.tutorial_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        username=findViewById(R.id.email);
        password=findViewById(R.id.psw);
        branch=findViewById(R.id.branch);
        city=findViewById(R.id.city);
        agree=findViewById(R.id.agree);

        Signup=findViewById(R.id.signup_btn);

        username=findViewById(R.id.email);

        final Validate validation = new Validate();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first_name,last_name,uname,psw_str,br,gender,cy,ag;
                first_name=fname.getText().toString();
                last_name=lname.getText().toString();
                uname=username.getText().toString();
                psw_str=password.getText().toString();
                br=branch.getText().toString();
                rdb_group=findViewById(R.id.rdb_group);
                int id=rdb_group.getCheckedRadioButtonId();
                rdb_select=findViewById(id);
                gender=rdb_select.getText().toString();
                cy=city.getSelectedItem().toString();
                if(validation.isnull(fname.getText().toString()))
                {
                    fname.requestFocus();
                    fname.setError(("Please enter first name"));
                    return;
                }
                if(validation.isnull(lname.getText().toString())) {
                    lname.requestFocus();
                    lname.setError(("Please enter last name"));
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(uname).matches())
                {
                    username.requestFocus();
                    username.setError(("Email address format is not valid"));
                    return;
                }
                if(validation.isnull(password.getText().toString())) {
                    password.requestFocus();
                    password.setError(("Please Enter Password"));
                    return;
                }
                if(cy.equals("Select One"))
                {
                    Toast.makeText(MainActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(agree.isChecked()) {
                    ag = "Active";
                }
                else
                {
                    ag = "Inactive";
                }

                Intent intent=new Intent(MainActivity.this,Register_data.class);
                intent.putExtra("first_name",first_name);
                intent.putExtra("last_name",last_name);
                intent.putExtra("uname",uname);
                intent.putExtra("psw",psw_str);
                intent.putExtra("br",br);
                intent.putExtra("gender",gender);
                intent.putExtra("cy",cy);
                intent.putExtra("ag",ag);
                startActivity(intent);
                /*rdb_group=findViewById(R.id.rdb_group);
                int id=rdb_group.getCheckedRadioButtonId();

                rdb_select=findViewById(id);

                if(validation.isnull(username.getText().toString()))
                {
                    username.requestFocus();
                    username.setError(("Please Enter Username"));
                }
                else
                {
                    Toast.makeText(MainActivity.this,username.getText().toString(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this, rdb_select.getText().toString(), Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}