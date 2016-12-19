package com.project.widget.android.homewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link HomeWidgetActivity HomeWidgetActivity}
 */
public class HomeWidgetProvider extends AppWidgetProvider {
    String lastSearch = " ";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Create an Intent to launch a Service
        Intent serviceIntent = new Intent(context.getApplicationContext(), HomeWidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        serviceIntent.putExtra("last", lastSearch);
        context.startService(serviceIntent);
        /*for (int widgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.home_widget);
            // Set the text
            remoteViews.setTextViewText(R.id.textView, "Last word: " + lastSearch);
            Log.e("INTENT SET", lastSearch);
            // Create an Intent to launch HomeWidgetActivity
            Intent activityIntent = new Intent(context, HomeWidgetActivity.class);
            activityIntent.putExtra("widgetId", widgetId);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.search, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }*/
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        lastSearch = intent.getStringExtra("text");
        Log.e("INTENT RECEIVE", lastSearch);
        super.onReceive(context, intent);

    }
}


