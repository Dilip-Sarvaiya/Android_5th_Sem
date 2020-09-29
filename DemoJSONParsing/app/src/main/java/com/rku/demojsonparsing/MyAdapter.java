package com.rku.demojsonparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> list;

    private final int[] images=
    {
            R.drawable.number_1,
            R.drawable.number_6,
            R.drawable.number_3,
            R.drawable.number_4
    };
    MyAdapter(Context context, ArrayList<Item> list)
    {
        super(context,R.layout.listitem,list);
        this.context=context;
        this.list=list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1 Create Inflater
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        //2.get rowView from Inflater
        View rowView=inflater.inflate(R.layout.listitem,parent,false);

        //3.get the two textviews from rowView
        TextView labelView=(TextView)rowView.findViewById(R.id.label);
        TextView valueView=(TextView)rowView.findViewById(R.id.value);
        ImageView imageView=(ImageView)rowView.findViewById(R.id.imageView);

        //4.Set the Text for TextViews
        labelView.setText(list.get(position).getTitle());
        valueView.setText(list.get(position).getDescription());
        imageView.setBackgroundResource(images[position]);

        //Return rowView

        return rowView;
    }
}
