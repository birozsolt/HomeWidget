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

    public static final String DATABASE_NAME = "words.sqlite";
    public static final String DATABASE_LOCATION = "/data/data/com.project.widget.android.homewidget/databases/";
    private static final String TABLE_NAME = "words";
    private static final String COL_1 = "id";
    private static final String COL_2 = "magyar";
    private static final String COL_3 = "angol";
    private static final String COL_4 = "roman";
    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2 + " TEXT, " +
            COL_3 + " TEXT, " +
            COL_4 + " TEXT, " +
            "UNIQUE(magyar, angol, roman) ON CONFLICT REPLACE);";
    private Context context;
    private SQLiteDatabase db;


    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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


    public void openDatabase() {
        String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
        if (db != null && db.isOpen()) {
            return;
        }
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }

    public ArrayList<DictionaryItem> getAllWords() {

        ArrayList<DictionaryItem> words;
        words = new ArrayList<>();
        String selectQuery = "SELECT  * FROM words";
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
