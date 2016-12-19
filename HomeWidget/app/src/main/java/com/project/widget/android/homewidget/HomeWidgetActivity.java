package com.project.widget.android.homewidget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * The configuration screen for the {@link HomeWidgetProvider HomeWidgetProvider} AppWidget.
 */
public class HomeWidgetActivity extends Activity {
    SearchView searchView;
    ListView listView;
    ArrayList<DictionaryItem> words;
    ListViewAdapter adapter;
    Spinner spinner;
    String language;
    Integer widgetID;
    private DictionaryDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        widgetID = getIntent().getIntExtra("widgetId", 0);
        setContentView(R.layout.home_widget_activity);
        myDb = new DictionaryDatabase(this);

        File database = getApplicationContext().getDatabasePath(DictionaryDatabase.DATABASE_NAME);
        if (!database.exists()) {
            myDb.getReadableDatabase();
            if (copyDatabase(this)) {
                Log.d("***Copy database: ", "Succes");
            } else {
                Log.d("***Copy database: ", "Error");
                return;
            }
        }


        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);
        spinner = (Spinner) findViewById(R.id.languages);


        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        language = spinner.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setLanguage(spinner.getSelectedItem().toString());
                listView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        words = myDb.getAllWords();
        adapter = new ListViewAdapter(this, words);
        adapter.setLanguage(language);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();

                } else {
                    listView.setFilterText(newText);
                    Intent textToShowIntent = new Intent(getApplicationContext(), HomeWidgetProvider.class);
                    textToShowIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                    textToShowIntent.putExtra("text", " " + newText);
                    Log.e("INTENT BROADCAST", newText);
                    sendBroadcast(textToShowIntent);
                }
                return true;
            }
        });
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DictionaryDatabase.DATABASE_NAME);
            String outFileName = DictionaryDatabase.DATABASE_LOCATION + DictionaryDatabase.DATABASE_NAME;
            Log.d("***location", outFileName);
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("***Database: ", "copied!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

