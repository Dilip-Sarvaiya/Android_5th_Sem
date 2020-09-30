package com.rku.tutorial10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter  extends BaseAdapter {

    Context context;
    JSONArray  jsonArray;

    public CustomAdapter(@NonNull Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        TextView formula=(TextView)convertView.findViewById(R.id.formula);
        TextView url=(TextView)convertView.findViewById(R.id.url);

        try {
            JSONObject jsonObject=jsonArray.getJSONObject(position);
            formula.setText(jsonObject.getString("name"));
            url.setText(jsonObject.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
