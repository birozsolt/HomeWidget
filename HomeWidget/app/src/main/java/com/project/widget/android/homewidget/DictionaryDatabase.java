package com.project.widget.android.homewidget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2 + " TEXT, " +
            COL_3 + " TEXT, " +
            COL_4 + " TEXT);";


    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String magyar, String angol, String roman) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, magyar);
        contentValues.put(COL_3, angol);
        contentValues.put(COL_4, roman);
        long res = db.insert(TABLE_NAME, null, contentValues);
        return res != -1;
    }

    public ArrayList<DictionaryItem> getAllWords() {

        ArrayList<DictionaryItem> words;
        words = new ArrayList<>();
        String selectQuery = "SELECT  * FROM dictionary";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                words.add(new DictionaryItem(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());

        }
        cursor.close();
        return words;
    }
}
