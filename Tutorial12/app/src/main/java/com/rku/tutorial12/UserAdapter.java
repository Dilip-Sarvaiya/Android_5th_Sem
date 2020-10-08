package com.rku.tutorial12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    JSONArray jsonArray;
    public UserAdapter(JSONArray jsonArray) {
        this.jsonArray=jsonArray;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        try {
            JSONObject jsonObject=jsonArray.getJSONObject(position);
            holder.txtName.setText(jsonObject.getString("name"));
            holder.txtEmail.setText(jsonObject.getString("email"));
            holder.txtPhone.setText(jsonObject.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtName,txtEmail,txtPhone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtPhone=itemView.findViewById(R.id.txtPhone);
        }
    }
}
