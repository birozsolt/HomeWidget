package com.project.widget.android.homewidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * The configuration screen for the {@link HomeWidget HomeWidget} AppWidget.
 */
public class HomeWidgetActivity extends Activity {
    DictionaryDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_widget_activity);
        myDb  = new DictionaryDatabase(this);
        boolean inserted = myDb.insertData("alma","apple","mere");
        if (inserted==true){
            Log.d("****","berakva");
        }else{
            Log.d("****","nincs berakva");
        }

    }
}

