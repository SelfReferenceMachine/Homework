package com.lofter.nonalaberin_rosettastone.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME="myrate.db";
    public static final String TB_NAME = "tb_rates";

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
    }
    public DBhelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURNAME TEXT,CURRATE TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

    }

}