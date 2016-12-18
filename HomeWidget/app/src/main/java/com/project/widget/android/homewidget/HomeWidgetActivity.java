package com.project.widget.android.homewidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * The configuration screen for the {@link HomeWidget HomeWidget} AppWidget.
 */
public class HomeWidgetActivity extends Activity {
    DictionaryDatabase myDb;
    SearchView searchView;
    ListView listView;
    ArrayList<DictionaryItem> words;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_widget_activity);
        myDb = new DictionaryDatabase(this);
        /*myDb.insertData("alma", "apple", "mere");
        myDb.insertData("körte", "pear", "pere");
        myDb.insertData("játék", "game", "joc");
        myDb.insertData("barna", "brown", "maro");
        myDb.insertData("akarat", "will", "vointa");
*/
        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);

        words = myDb.getAllWords();
        adapter = new ListViewAdapter(this, words);
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
                }
                return true;
            }
        });
    }
}

