package com.rku.tutorial11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class UserData extends AppCompatActivity {

    TextView id_txt,id_name,id_username,id_email;
    TextView id_street,id_suite,id_city;
    TextView id_zip,id_lat,id_lng;
    TextView id_phone,id_web;
    TextView id_comp_name,id_catchPhrase,id_bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        getSupportActionBar().setTitle("Userdata");

        id_txt=findViewById(R.id.id_txt);
        id_name=findViewById(R.id.id_name);
        id_username=findViewById(R.id.id_username);
        id_email=findViewById(R.id.id_email);
        id_street=findViewById(R.id.id_street);
        id_suite=findViewById(R.id.id_suite);
        id_city=findViewById(R.id.id_city);

        id_zip=findViewById(R.id.id_zipcode);
        id_lat=findViewById(R.id.id_lat);
        id_lng=findViewById(R.id.id_lng);
        id_phone=findViewById(R.id.id_phone);
        id_web=findViewById(R.id.id_website);
        id_comp_name=findViewById(R.id.id_comp_name);
        id_catchPhrase=findViewById(R.id.id_catchPhrase);
        id_bs=findViewById(R.id.id_bs);

        Intent intent=getIntent();
        int i=intent.getIntExtra("userdata",0);

        try {

            JSONObject userObj=MyUtil.userdata.getJSONObject(i);
            JSONObject addObj=userObj.getJSONObject("address");
            JSONObject geoObj=addObj.getJSONObject("geo");
            JSONObject comObj=userObj.getJSONObject("company");

            id_txt.setText(userObj.getString("id"));
            id_name.setText(userObj.getString("name"));
            id_username.setText(userObj.getString("username"));
            id_email.setText(userObj.getString("email"));
            id_street.setText(addObj.getString("street"));
            id_suite.setText(addObj.getString("suite"));
            id_city.setText(addObj.getString("city"));
            id_zip.setText(addObj.getString("zipcode"));
            id_lat.setText(geoObj.getString("lat"));
            id_lng.setText(geoObj.getString("lng"));
            id_phone.setText(userObj.getString("phone"));
            id_web.setText(userObj.getString("website"));
            id_comp_name.setText(comObj.getString("name"));
            id_catchPhrase.setText(comObj.getString("catchPhrase"));
            id_bs.setText(comObj.getString("bs"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}