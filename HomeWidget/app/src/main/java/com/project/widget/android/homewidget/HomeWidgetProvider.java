package com.project.widget.android.homewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link HomeWidgetActivity HomeWidgetActivity}
 */
public class HomeWidgetProvider extends AppWidgetProvider {

    String lastSearch = "";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            //Create an Intent to launch a Service
            Intent serviceIntent = new Intent(context.getApplicationContext(), HomeWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            serviceIntent.putExtra("last",lastSearch);
            context.startService(serviceIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Integer widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,0);
        String text = intent.getStringExtra("text");
        if (action != null && action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            lastSearch = text;
        } else {
            super.onReceive(context, intent);
        }
    }
}

