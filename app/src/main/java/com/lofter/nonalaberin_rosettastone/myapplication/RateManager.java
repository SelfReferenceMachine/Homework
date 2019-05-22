package com.lofter.nonalaberin_rosettastone.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RateManager {
    //管理数据库并提供方法
    private DBhelper dBhelper;
    private String TBNAME;

    public RateManager(Context context){
        dBhelper = new DBhelper(context);
        TBNAME = DBhelper.TB_NAME;
    }

    public void add(RateItem item){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public List<RateItem> listAll(){
        List<RateItem> rateList = null;
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            rateList = new ArrayList<RateItem>();
            while (cursor.moveToNext()){
                //move to next row then get id,可理解为光标
                RateItem item = new RateItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }

    public void deleteAll(){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void addAll(List<RateItem> list) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        for (RateItem item : list) {
            ContentValues values = new ContentValues();
            values.put("curname", item.getCurName());
            values.put("currate", item.getCurRate());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(RateItem item){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname", item.getCurName());
        values.put("currate", item.getCurRate());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public RateItem findById(int id){
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null,
                null, null);
        RateItem rateItem = null;
        if(cursor!=null && cursor.moveToFirst()){
            rateItem = new RateItem();
            rateItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            rateItem.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
            rateItem.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
            cursor.close();
        }
        db.close();
        return rateItem;
    }
}