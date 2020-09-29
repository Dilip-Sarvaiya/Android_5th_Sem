package com.rku.demojsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstData;
    String data []={"ABC","DEF","GHI","JKL","MNO"};
    ArrayList<Item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstData=findViewById(R.id.lstData);
        list=generateData();
        MyAdapter adapter=new MyAdapter(this,list);
        lstData.setAdapter(adapter);
    }
    private ArrayList<Item> generateData()
    {
        ArrayList<Item> items =new ArrayList<Item>();
        items.add(new Item("Item 1:","First Item on the List",R.drawable.number_1));
        items.add(new Item("Item 2:","Second Item on the List",R.drawable.number_6));
        items.add(new Item("Item 3:","Third Item on the List",R.drawable.number_3));
        items.add(new Item("Item 4:","Forth Item on the List",R.drawable.number_4));
        return items;
    }
}