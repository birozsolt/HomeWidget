package com.project.widget.android.homewidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by Zsolt on 2016. 12. 19..
 */

public class HomeWidgetService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());
        int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        for (int widgetId : allWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.home_widget);
            // Set the text
            remoteViews.setTextViewText(R.id.textView, "Lats word: " + String.valueOf(intent.getStringExtra("last")));

            // Create an Intent to launch HomeWidgetActivity
            Intent activityIntent = new Intent(this.getApplicationContext(), HomeWidgetActivity.class);
            activityIntent.putExtra("widgetId",widgetId);
            PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, activityIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.search, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
