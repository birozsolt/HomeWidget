package com.project.widget.android.homewidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The configuration screen for the {@link HomeWidget HomeWidget} AppWidget.
 */
public class HomeWidgetActivity extends Activity {
    DictionaryDatabase myDb;
    SearchView searchView;
    ListView listView;
    String[] words = {"alma","körte","szilva","eper","banán","narancs","citrom","dió"};
    ArrayAdapter<String> adapter;
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

        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}

