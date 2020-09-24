package com.rku.tutorial09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText store_content;
    TextView display_content;
    final String FILE_ASSETS="feedback.txt";
    final String FILES_INTERNAL="help.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        store_content=findViewById(R.id.edt_content);
        display_content=findViewById(R.id.txt_content);

    }

    public void Read_assets(View view) {
        try {
            InputStream is=getAssets().open(FILE_ASSETS);
            InputStreamReader  isreader=new InputStreamReader(is);
            BufferedReader reader=new BufferedReader(isreader);
            String mLine="";
            StringBuilder stringBuilder =new StringBuilder();
            while ((mLine=reader.readLine())!=null)
            {
                stringBuilder.append(mLine);
            }
            display_content.setText(stringBuilder.toString());
            is.close();
            isreader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Files(View view) {

        try {
            FileInputStream fin=openFileInput(FILES_INTERNAL);
            int c;
            String temp="";
            while((c=fin.read())!=-1)
            {
                temp=temp+String.valueOf((char)c);
            }
            display_content.setText(temp);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Write_into_Files(View view) {
        try {
            FileOutputStream fout=openFileOutput(FILES_INTERNAL, Context.MODE_PRIVATE);
            String data=store_content.getText().toString();
            fout.write(data.getBytes());
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}