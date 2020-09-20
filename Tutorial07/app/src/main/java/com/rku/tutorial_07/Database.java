package com.rku.tutorial_07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE = "college";
    public static final String TABLE = "student";
    public static final String COL_1 = "id";
    public static final String COL_2= "fname";
    public static final String COL_3= "lname";
    public static final String COL_4 = "username";
    public static final String COL_5 = "password";
    public static final String COL_6 = "branch";
    public static final String COL_7 = "gender";
    public static final String COL_8 = "city";
    public static final String COL_9 = "status";


    public Database(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "+TABLE+"("+
                COL_1 +" integer primary key autoincrement, "+
                COL_2+ " text, " +
                COL_3+ " text, " +
                COL_4+ " text, " +
                COL_5+ " text, " +
                COL_6+ " text, " +
                COL_7+ " text, " +
                COL_8+ " text, " +
                COL_9+ " text)";
        Log.i("sql",sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(("drop table "+TABLE));
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String f_name,String l_name,String username,String password,String branch,
                              String gender,String city,String status)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,f_name);
        contentValues.put(COL_3,l_name);
        contentValues.put(COL_4,username);
        contentValues.put(COL_5,password);
        contentValues.put(COL_6,branch);
        contentValues.put(COL_7,gender);
        contentValues.put(COL_8,city);
        contentValues.put(COL_9,status);

        return (db.insert(TABLE,null,contentValues)!=-1);

    }

    public boolean validation(String username,String password)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(TABLE,
                new String[]{COL_4,COL_5},
                "username=? and password=?",
                new String[]{username,password},
                null,
                null,
                null
                );
         return ((cursor!=null && cursor.getCount()>0)?true:false);
    }
}
