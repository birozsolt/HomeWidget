package com.project.widget.android.homewidget;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by Zold Attila on 11/12/2016.
 */

public class DictionaryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dictionary.db";
    private static final String TABLE_NAME = "dictionary";
    private static final String COL_1 = "id";
    private static final String COL_2 = "magyar";
    private static final String COL_3 = "angol";
    private static final String COL_4 = "roman";
    private static final String TABLE_CREATE = "create table dictionary (id integer primary key not null auto_increment, " +
                                                "magyar text, angol text, roman text);";
    SQLiteDatabase db;


    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String magyar,String angol, String roman){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,magyar);
        contentValues.put(COL_3,angol);
        contentValues.put(COL_4,roman);
        long res = db.insert(TABLE_NAME,null,contentValues);
        if (res == -1){
            return false;
        }
        else {
            return true;
        }
    }
}