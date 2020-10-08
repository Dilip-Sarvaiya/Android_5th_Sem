package com.rku.tutorial12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvUsers;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    LinearLayoutManager layoutManager;

    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Recycler View Demo");
        rcvUsers=findViewById(R.id.rcvUsers);

        rcvUsers.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        rcvUsers.setLayoutManager(layoutManager);

        //Add Divider
        DividerItemDecoration dividerItemDecoration=
                new DividerItemDecoration(rcvUsers.getContext(),LinearLayoutManager.VERTICAL);
        rcvUsers.addItemDecoration(dividerItemDecoration);

        //Apply Animation
        int resId =R.anim.layout_animation_fall_down;
        LayoutAnimationController animationController=
                AnimationUtils.loadLayoutAnimation(getApplicationContext(),resId);
        rcvUsers.setLayoutAnimation(animationController);

        volleyNetwrokCallAPI();
    }

    private void volleyNetwrokCallAPI() {

        requestQueue= Volley.newRequestQueue(getApplicationContext());
        jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                MyUtil.URL_USERS,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        MyUtil.userData=response;
                        userAdapter=new UserAdapter(response);
                        rcvUsers.setAdapter(userAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}