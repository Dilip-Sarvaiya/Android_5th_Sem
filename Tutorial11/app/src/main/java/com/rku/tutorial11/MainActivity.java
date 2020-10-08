package com.rku.tutorial11;


import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
public class MainActivity extends AppCompatActivity {

    CustomAdapter  customAdapter;
    ListView lstData;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    JsonArrayRequest jsonArrayRequest;
    //JSONArray itemsJsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Userlist");


        //get the listview and attach the adapter
        lstData=(ListView)findViewById(R.id.lstData);




        //new MyAsyncTask().execute();
        volleyAPICAll();

        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,UserData.class);
                intent.putExtra("userdata",i);
                startActivity(intent);
            }
        });
    }

    private void volleyAPICAll() {
        final ProgressDialog dialog=new ProgressDialog(MainActivity.this);
        requestQueue=Volley.newRequestQueue(MainActivity.this);
        jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                MyUtil.URL_USERS,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        MyUtil.userdata=response;
                        customAdapter=new CustomAdapter(MainActivity.this);
                        lstData.setAdapter(customAdapter);
                        if(dialog.isShowing())dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        dialog.setTitle("Welcome");
        dialog.setMessage("Fetching Data");
        dialog.show();
    }
}