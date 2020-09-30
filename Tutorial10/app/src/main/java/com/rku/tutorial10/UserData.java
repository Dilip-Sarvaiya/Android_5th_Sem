package com.rku.tutorial10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class UserData extends AppCompatActivity {

    TextView id_txt,id_name,id_username,id_email,id_address,id_phone,id_web,id_comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        id_txt=findViewById(R.id.id_txt);
        id_name=findViewById(R.id.id_name);
        id_username=findViewById(R.id.id_username);
        id_email=findViewById(R.id.id_email);
        id_address=findViewById(R.id.id_address);
        id_phone=findViewById(R.id.id_phone);
        id_web=findViewById(R.id.id_web);
        id_comp=findViewById(R.id.id_comp);

        Intent intent=getIntent();
        int i=intent.getIntExtra("userdata",0);

        try {
            JSONObject userObj=MyUtil.userdata.getJSONObject(i);
            JSONObject addObj=userObj.getJSONObject("address");
            JSONObject comObj=userObj.getJSONObject("company");
            id_txt.setText(userObj.getString("id"));
            id_name.setText(userObj.getString("name"));
            id_username.setText(userObj.getString("username"));
            id_email.setText(userObj.getString("email"));
            id_address.setText(addObj.getString("street"));
            id_phone.setText(userObj.getString("phone"));
            id_web.setText(userObj.getString("website"));
            id_comp.setText(comObj.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}