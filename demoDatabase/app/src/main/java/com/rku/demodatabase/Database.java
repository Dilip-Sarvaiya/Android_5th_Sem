package com.rku.demodatabase;

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
    public static final String COL_2= "name";
    public static final String COL_3= "surname";
    public static final String COL_4 = "marks";

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
                COL_4+ " integer)";
        Log.i("sql",sql);
        sqLiteDatabase.execSQL(sql);
       // insertData("dilip","djdjdj",18);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(("drop table "+TABLE));
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String surname,int marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result=db.insert(TABLE,null,contentValues);
        if(result==-1)
        {
            return  false;
        }
        else {
            return true;
        }
    }
    public Cursor selectdata()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(TABLE,null,null,null,null,null,null);
        return cursor;
    }
}
